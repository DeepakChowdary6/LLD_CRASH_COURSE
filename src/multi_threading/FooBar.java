package multi_threading;
import java.util.concurrent.Semaphore;
public class FooBar {




        public static void main(String[] args) {

            // fooSemaphore starts with 1 permit, so it's available initially.
            // It allows the "foo" thread to run first.
            Semaphore fooSemaphore = new Semaphore(1);

            // barSemaphore starts with 0 permits, so it's blocked initially.
            // "bar" thread will wait for foo to release a permit.
            Semaphore barSemaphore = new Semaphore(0);

            // Thread to print "Foo"
            new Thread(() -> {
                while (true) {
                    try {
                        // Acquire permit from fooSemaphore (starts with 1)
                        // First time, it proceeds immediately
                        // Later it waits for the bar thread to release
                        fooSemaphore.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // Critical section for foo
                    System.out.println("Foo printed by " + Thread.currentThread().getName());


                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){}

                    // Now allow "bar" thread to run
                    barSemaphore.release();  // Adds 1 permit to barSemaphore
                }
            },"1").start();

            // Thread to print "Bar"
            new Thread(() -> {
                while (true) {
                    try {
                        // Wait for permit from barSemaphore (starts at 0)
                        // Will block until foo thread releases a permit
                        barSemaphore.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // Critical section for bar
                    System.out.println("Bar printed by " + Thread.currentThread().getName());

                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){}


                    // Now allow "foo" thread to run again
                    fooSemaphore.release();  // Adds 1 permit to fooSemaphore
                }
            },"2").start();
        }
    }


