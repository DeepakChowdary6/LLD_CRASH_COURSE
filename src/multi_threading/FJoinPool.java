package multi_threading; // package declaration groups related classes under multi_threading

import java.util.concurrent.ForkJoinPool;   // ForkJoinPool: specialized pool for recursive parallelism (fork/join)
import java.util.concurrent.RecursiveAction; // RecursiveAction: a ForkJoinTask with no return value (side-effect tasks)
import java.util.concurrent.RecursiveTask;   // RecursiveTask<R>: a ForkJoinTask that returns a result of type R

/**
 * ForkJoinPool basics (simple demo)
 * used for recursive parallelism
 * - Use for CPU-bound, divide-and-conquer problems.
 * - You split a big task into smaller ones (fork) and then combine results (join).
 *
 * WHAT IS ForkJoinPool?
 * - A thread pool tuned for tasks that can be recursively split into smaller pieces.
 * - Uses work-stealing: idle worker threads take (steal) work from busier threads to keep CPUs saturated.
 * - Prefer for CPU-bound tasks; avoid blocking I/O inside tasks (or use managedBlock if necessary).
 *
 * KEY BUILDING BLOCKS
 * - RecursiveTask<R>: override compute() and return a value.
 * - RecursiveAction:  override compute() but return nothing (perform side-effects like in-place array changes).
 * - fork(): schedule a subtask asynchronously in the pool (current thread continues without waiting).
 * - join(): wait for a previously forked task to complete and get its result.
 * - invoke(task): submit a task to the pool and block until it completes; returns the result for RecursiveTask,
 *                 or null for RecursiveAction.
 * - invokeAll(t1, t2): convenience to fork and join multiple tasks; blocks until all complete.
 *   In code below, we call invokeAll inside a RecursiveAction to run two subtasks in parallel.
 *
 * DESIGN TIP
 * - Choose a sensible THRESHOLD (stop-splitting size). Too small => too many tasks (overhead). Too large => less parallelism.
 */
public class FJoinPool { // class housing both RecursiveTask and RecursiveAction demos

    // RecursiveTask<R>: returns a result (here: Long)
     class SumTask extends RecursiveTask<Long> { // computes sum over a slice of an int[] and returns Long
        private static final int THRESHOLD = 1000; // stop splitting when range <= 1000 (base case size)

        private final int[] arr;        // input array to sum from
        private final int start; // inclusive index of slice start
        private final int end;   // exclusive index of slice end

        public SumTask(int[] arr, int start, int end) { // constructor wires the problem slice
            this.arr = arr;    // capture reference to the input array
            this.start = start; // capture start index (inclusive)
            this.end = end;     // capture end index (exclusive)
        }

        @Override
        protected Long compute() { // compute() is where we decide to split or solve directly
            // Base case: sum directly if small enough
            if (end - start <= THRESHOLD) { // if the slice length is at/below threshold, do sequential sum
                long sum = 0;                // accumulator for the sum
                for (int i = start; i < end; i++) { // iterate within slice bounds
                    sum += arr[i];                 // add current element to sum
                }
                return sum; // return the computed partial sum as Long
            }

            // Split into two halves
            int mid = (start + end) / 2; // midpoint; splits [start, end) into [start, mid) and [mid, end)
            SumTask leftTask = new SumTask(arr, start, mid); // left subtask for first half
            SumTask rightTask = new SumTask(arr, mid, end);  // right subtask for second half

            // Fork left, compute right, then join left
            leftTask.fork();                         // fork(): schedule leftTask asynchronously in the pool
            long rightResult = rightTask.compute();  // compute right half directly (helps locality; no extra scheduling)
            long leftResult = leftTask.join();       // join(): wait for leftTask to finish and get its result

            return leftResult + rightResult; // combine results (join step in divide-and-conquer)
        }
    }

    // RecursiveAction: performs work but returns no result
   class IncrementAction extends RecursiveAction { // increments elements of an int[] slice in place
        private static final int THRESHOLD = 1000; // base case size for in-place increment

        private final int[] arr;        // array to mutate
        private final int start; // inclusive start index of the slice to increment
        private final int end;   // exclusive end index of the slice to increment

        public IncrementAction(int[] arr, int start, int end) { // wires array slice to operate on
            this.arr = arr;   // capture array reference
            this.start = start; // capture slice start
            this.end = end;     // capture slice end
        }

        @Override
        protected void compute() { // compute(): split vs act-in-place
            if (end - start <= THRESHOLD) { // base case: small enough slice => do the work directly
                for (int i = start; i < end; i++) { // iterate over slice
                    arr[i] += 1; // modify in place (no return value for RecursiveAction)
                }
                return; // done with base case path
            }

            int mid = (start + end) / 2; // split into two slices
            // invokeAll(...) is a convenience that forks both tasks and waits for both to complete.
            // It’s defined in ForkJoinTask as a static generic method, available to RecursiveAction/RecursiveTask subclasses.
            invokeAll(new IncrementAction(arr, start, mid), // run left half in parallel
                      new IncrementAction(arr, mid, end));  // run right half in parallel
            // After invokeAll returns, both subtasks are completed.
        }
    }

    public static void main(String[] args) { // entry point to run the demos
        // Step 1: Prepare data
        int[] arr = new int[10_000_000]; // allocate a large array
        for (int i = 0; i < arr.length; i++) { // initialize each element
            arr[i] = 1; // easy to verify: expected sum = arr.length (every element is 1)
        }
      FJoinPool fJoinPool=new FJoinPool();
        // Step 2: Use common ForkJoinPool to compute the sum
        ForkJoinPool pool = ForkJoinPool.commonPool(); // obtain the shared, lazily-initialized common pool
        SumTask task = fJoinPool.new SumTask(arr, 0, arr.length); // create root task spanning the whole array
        long result = pool.invoke(task); // invoke blocks until task finishes; equivalent to submit+get
        System.out.println("Sum = " + result + " (expected " + arr.length + ")"); // print the sum vs expected

        // Step 3: Show RecursiveAction (no result)
        int[] nums = new int[10]; // small array for display (all zeros initially)
        pool.invoke(fJoinPool.new IncrementAction(nums, 0, nums.length)); // invoke an action (returns null after completion)
        System.out.println("IncrementAction: first=" + nums[0] + ", last=" + nums[nums.length - 1]); // both should be 1

        // NOTES ON COMMON METHODS (default helpers):
        // - fork(): schedules a subtask; returns immediately. Use join() later to wait for it.
        // - join(): waits for completion and returns the result (for RecursiveTask); rethrows exceptions if any.
        // - invoke(task): pool-level convenience to submit a task and wait synchronously.
        // - invokeAll(t1, t2): static helper that forks both tasks and waits for both; used above in IncrementAction.
        // - commonPool(): returns a shared ForkJoinPool for general use; its parallelism is typically #CPU cores - 1.
        //
        // WHEN TO CHOOSE ForkJoinPool
        // - Recursive parallelism (divide-and-conquer) over arrays, collections, trees.
        // - CPU-bound tasks with minimal blocking; keep tasks small but not tiny (tune THRESHOLD).
        // - Avoid heavy blocking I/O; if unavoidable, consider ForkJoinPool.ManagedBlocker or different executors.
    }
}
