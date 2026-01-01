package multi_threading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CBandCL {
/*
* Failure behavior (important)
CountDownLatch

If a thread never calls countDown()
→ waiting threads wait forever

CyclicBarrier

If one thread breaks or times out
→ barrier breaks
→ all waiting threads get exception
* */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("CountDownLatch demo (one-shot latch)");
        countDownLatchDemo();

        System.out.println("\n------------------------------\n");

        System.out.println("CyclicBarrier demo (reusable barrier)");
        cyclicBarrierDemo();
    }

    // Basic example of CountDownLatch: main thread waits until N workers finish
    private static void countDownLatchDemo() throws InterruptedException {
        int workers = 3;
        CountDownLatch latch = new CountDownLatch(workers);

        for (int i = 1; i <= workers; i++) {
            final int id = i;
            Thread worker = new Thread(() -> {
                try {
                    System.out.println("Worker-" + id + " starting work...");
                    Thread.sleep(400 + id * 200L);
                    System.out.println("Worker-" + id + " finished work.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    // Decrement the latch so the waiter can proceed when it hits zero
                    latch.countDown();
                }
            }, "Worker-" + i);
            worker.start();
        }

        System.out.println("Main waiting for all workers to finish...");
        latch.await(); // Wait until count reaches zero
        System.out.println("All workers finished. Main proceeds.\n");
    }

    // Basic example of CyclicBarrier: N threads meet at a barrier across multiple phases
    private static void cyclicBarrierDemo() throws InterruptedException {
        int parties = 3; // number of threads to wait for at the barrier

        // Barrier action runs when all parties reach the barrier
        CyclicBarrier barrier = new CyclicBarrier(parties, () ->
                System.out.println("All parties reached barrier. Barrier action by: " + Thread.currentThread().getName())
        );

        Runnable phasedTask = () -> {
            String name = Thread.currentThread().getName();
            try {
                // Phase 1
                System.out.println(name + " - Phase 1 work");
                Thread.sleep(300);
                barrierAwait(barrier, name, 1);

                // Phase 2
                System.out.println(name + " - Phase 2 work");
                Thread.sleep(300);
                barrierAwait(barrier, name, 2);

                // Phase 3 (no barrier at the end, just finish)
                System.out.println(name + " - Phase 3 work (done)");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread t1 = new Thread(phasedTask, "T1");
        Thread t2 = new Thread(phasedTask, "T2");
        Thread t3 = new Thread(phasedTask, "T3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Barrier demo complete.");
    }

    private static void barrierAwait(CyclicBarrier barrier, String name, int phase) {
        try {
            System.out.println(name + " waiting at barrier after Phase " + phase);
            barrier.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            System.out.println(name + " - barrier broken at phase " + phase);
        }
    }
}
