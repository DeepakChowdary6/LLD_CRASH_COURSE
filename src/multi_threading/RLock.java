package multi_threading;

import java.util.concurrent.locks.*;

public class RLock {

    // A ReentrantLock allows a thread to reacquire the same lock it already holds
    private static final Lock lock = new ReentrantLock();

    // Condition allows threads to wait for certain conditions while holding the lock
    // Multiple condition variables can be created from a single Lock
    private static final Condition condition = lock.newCondition();

    // Shared flag to control when the worker thread can proceed
    private static boolean ready = false;

    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            lock.lock();  // Worker thread acquires the lock before waiting
            try {
                // The worker waits until ready becomes true
                while (!ready) {
                    System.out.println("Waiting for the signal...");

                    /**
                     * condition.await():
                     * - Releases the lock temporarily so that other threads can acquire it.
                     * - Puts the current thread into the wait set of this condition.
                     * - Once signaled, the thread reacquires the lock before continuing.
                     */
                    condition.await();
                }

                // Once signaled and lock reacquired, thread proceeds
                System.out.println("Got the signal! Continuing work...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // Release the lock no matter what
                lock.unlock();
            }
        });

        worker.start(); // Start the worker thread

        // Simulate some delay to ensure the worker thread starts and waits
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}

        // Main thread now signals the condition
        lock.lock(); // Main thread acquires the lock to change shared state and signal
        try {
            ready = true; // Set the flag so the worker can proceed

            /**
             * condition.signal():
             * - Wakes up one thread waiting on this condition.
             * - The woken thread will reacquire the lock before resuming.
             */
            condition.signal();

            System.out.println("Signal sent!");
        } finally {
            // Main thread releases the lock so that worker thread can acquire it again
            lock.unlock();
        }
    }



}
