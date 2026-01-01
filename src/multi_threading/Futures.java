package multi_threading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Futures vs CompletableFuture (basics and recipes)
 *
 * - Future<V> (Java 5): represents the result of an async computation submitted to an ExecutorService.
 *   Methods: get(), get(timeout), isDone(), cancel(), isCancelled().
 *   Limitations: cannot manually complete, no chaining/combining, blocking get with checked exceptions.
 *
 * - CompletableFuture<T> (Java 8): both a Future and a CompletionStage.
 *   - Can be completed manually (complete, completeExceptionally).
 *   - Non-blocking composition: thenApply, thenCompose, thenCombine, allOf, anyOf, etc.
 *   - Can run async via supplyAsync/runAsync using ForkJoinPool.commonPool() or a given Executor.
 *
 * get() vs join() (on CompletableFuture)
 * - get(): blocks, but throws checked exceptions: InterruptedException, ExecutionException, TimeoutException.
 * - join(): blocks, but throws unchecked CompletionException (wraps the cause). No checked exceptions.
 *   Choose join() when used inside streams/pipelines where checked exceptions are inconvenient.
 *
 * Thread.join() vs CompletableFuture.join():
 * - Thread.join(): waits for a Thread to finish; does NOT rethrow task exceptions (you must capture inside the Runnable).
 * - CompletableFuture.join(): waits and propagates the task's exception as CompletionException (unchecked).
 */
public class Futures {

    public static void main(String[] args) {
        System.out.println("=== 1) Classic Future with ExecutorService ===");
        classicFutureDemo();

        System.out.println("\n=== 2) CompletableFuture basics: supplyAsync/runAsync + thenApply ===");
        completableBasics();

        System.out.println("\n=== 3) thenCompose (flat-map) vs thenApply (map) ===");
        compositionDemo();

        System.out.println("\n=== 4) thenCombine (combine two independent futures) ===");
        combineDemo();

        System.out.println("\n=== 5) allOf / anyOf (coordination) ===");
        allAnyOfDemo();

        System.out.println("\n=== 6) Exception handling: exceptionally / handle / whenComplete; get vs join ===");
        exceptionHandlingDemo();

        System.out.println("\n=== 7) join() vs get() quick comparison ===");
        joinVsGetDemo();

        System.out.println("\n=== 8) CompletableFuture.join vs Thread.join ===");
        threadJoinComparisonDemo();
    }

    // 1) Classic Future demo using ExecutorService + Callable
    private static void classicFutureDemo() {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        try {
            // Submit a Callable (returns a value);
            Future<Integer> future = pool.submit(() -> {
                // Simulate work
                Thread.sleep(300);
                return 42; // result
            });

            // Non-blocking check
            System.out.println("isDone before get? " + future.isDone());

            // get(): blocks until result available, throws checked exceptions
            try {
                Integer value = future.get();
                System.out.println("Future.get value = " + value);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                System.out.println("Execution failed: " + e.getCause());
            }

            // Timeout variant
            try {
                Future<String> f2 = pool.submit(() -> {
                    Thread.sleep(200);
                    return "done";
                });
                String s = f2.get(1, TimeUnit.SECONDS); // may throw TimeoutException
                System.out.println("Future with timeout = " + s);
            } catch (TimeoutException te) {
                System.out.println("Timed out waiting for future");
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException ee) {
                System.out.println("Execution failed: " + ee.getCause());
            }
        } finally {
            pool.shutdown();
        }
    }

    // 2) Basics: supplyAsync/runAsync and thenApply (map)
    private static void completableBasics() {
        // supplyAsync(Supplier<T>): produces a value asynchronously
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return 10;
        });

        // thenApply(Function<T,R>): transform result synchronously (still runs on completion thread)
        CompletableFuture<Integer> cf2 = cf1.thenApply(x -> x * 2); // 20

        // runAsync(Runnable): fire-and-forget task, returns CompletableFuture<Void>
        CompletableFuture<Void> fireAndForget = CompletableFuture.runAsync(() -> {
            sleep(100);
            System.out.println("runAsync side-effect on " + Thread.currentThread().getName());
        });

        // Block to display results (join is fine for demos; it throws unchecked on failure)
        System.out.println("supplyAsync->thenApply result = " + cf2.join());
        fireAndForget.join(); // wait until side-effect completes
    }

    // 3) thenCompose vs thenApply
    private static void compositionDemo() {
        // thenApply: map from T to R
        CompletableFuture<Integer> base = CompletableFuture.supplyAsync(() -> 5);
        CompletableFuture<Integer> mapped = base.thenApply(x -> x + 1); // wraps into same future chain

        // thenCompose: flatMap - when your mapper returns another CompletableFuture
        CompletableFuture<Integer> flatMapped = base.thenCompose(x -> asyncDouble(x));

        System.out.println("thenApply result = " + mapped.join()); // 6
        System.out.println("thenCompose(async flatMap) result = " + flatMapped.join()); // 10
    }

    private static CompletableFuture<Integer> asyncDouble(int x) {
        return CompletableFuture.supplyAsync(() -> x * 2);
    }

    // 4) thenCombine: combine two independent futures (both must complete)
    private static void combineDemo() {
        CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> {
            sleep(150);
            return 2;
        });
        CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> 40);

        // thenCombine waits for both and applies combiner function
        CompletableFuture<Integer> combined = a.thenCombine(b, Integer::sum); // 42
        System.out.println("thenCombine result = " + combined.join());
    }

    // 5) allOf / anyOf coordination
    private static void allAnyOfDemo() {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> slowCompute(1, 200));
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> slowCompute(2, 300));
        CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(() -> slowCompute(3, 100));

        // allOf waits for all to finish (returns CompletableFuture<Void>)
        CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2, f3);
        all.join(); // wait for all
        int sum = f1.join() + f2.join() + f3.join();
        System.out.println("allOf sum = " + sum);

        // anyOf completes with the first completed result (type is Object)
        CompletableFuture<Object> any = CompletableFuture.anyOf(f1, f2, f3);
        Object first = any.join(); // could be Integer from any of f1/f2/f3
        System.out.println("anyOf first result = " + first);
    }

    // 6) Exceptions: exceptionally / handle / whenComplete; and how join/get differ
    private static void exceptionHandlingDemo() {
        // A future that fails
        CompletableFuture<Integer> failing = CompletableFuture.supplyAsync(() -> {
            sleep(50);
            throw new IllegalStateException("boom"); // unchecked exception from supplier
        });

        // exceptionally: recover by returning a fallback value on failure
        int recovered = failing
                .exceptionally(ex -> { // ex is the cause (wrapped in CompletionException at boundary)
                    System.out.println("exceptionally saw: " + ex);
                    return -1; // fallback
                })
                .join();
        System.out.println("exceptionally recovered value = " + recovered);

        // handle: always runs (success or failure) and returns a replacement value
        int handled = CompletableFuture.<Integer>supplyAsync(() -> {
                    throw new RuntimeException("oops");
                })
                .handle((val, ex) -> {
                    if (ex != null) {
                        System.out.println("handle saw: " + ex);
                        return 0; // recover
                    }
                    return val;
                })
                .join();
        System.out.println("handle recovered value = " + handled);

        // whenComplete: side-effect observer; does NOT change the value (useful for logging/cleanup)
        int observed = CompletableFuture.supplyAsync(() -> 7)
                .whenComplete((val, ex) -> {
                    System.out.println("whenComplete: val=" + val + ", ex=" + ex);
                })
                .thenApply(x -> x + 1)
                .join();
        System.out.println("whenComplete observed, result after thenApply = " + observed);

        // get() vs join() when there is an exception
        CompletableFuture<Integer> willFail = CompletableFuture.supplyAsync(() -> { throw new ArithmeticException("/0"); });

        // join(): throws unchecked CompletionException
        try {
            willFail.join();
        } catch (CompletionException ce) {
            System.out.println("join threw CompletionException, cause = " + ce.getCause());
        }

        // get(): throws checked ExecutionException wrapping the cause
        try {
            willFail.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException ee) {
            System.out.println("get threw ExecutionException, cause = " + ee.getCause());
        }
    }

    // 7) Quick join vs get demonstration on success path
    private static void joinVsGetDemo() {
        CompletableFuture<String> ok = CompletableFuture.supplyAsync(() -> "ok");

        // join has no checked exceptions
        String j = ok.join();
        System.out.println("join value = " + j);

        // get forces handling checked exceptions
        try {
            String g = ok.get();
            System.out.println("get value = " + g);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Unexpected: " + e.getCause());
        }
    }

    // 8) Compare CompletableFuture.join with Thread.join
    private static void threadJoinComparisonDemo() {
        // Thread.join: just waits; exceptions in the task do not propagate automatically
        final RuntimeException[] captured = new RuntimeException[1];
        Thread t = new Thread(() -> {
            try {
                throw new RuntimeException("thread failure");
            } catch (RuntimeException e) {
                captured[0] = e; // you must capture it yourself
            }
        });
        t.start();
        try {
            t.join(); // only waits; no exception thrown here
            System.out.println("Thread.join completed; captured exception? " + (captured[0] != null));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // CompletableFuture.join: propagates as CompletionException
        try {
            CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
                throw new RuntimeException("cf failure");
            });
            cf.join(); // this line throws CompletionException
        } catch (CompletionException ce) {
            System.out.println("CompletableFuture.join threw CompletionException, cause = " + ce.getCause());
        }
    }

    // Helpers
    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }
    }

    private static int slowCompute(int base, long ms) {
        sleep(ms);
        return base * 10;
    }
}
