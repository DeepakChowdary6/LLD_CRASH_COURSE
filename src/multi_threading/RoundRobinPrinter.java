package multi_threading;

public class RoundRobinPrinter {

    private static int number = 1;
    private static final Object lock = new Object();

    public static void main(String[] args)  {
        int N = 10; // Upper limit
        int M = 3;  // Number of threads

        for (int i = 0; i < M; i++) {
            int threadId = i;
             new Thread(() -> {
                while (true) {
                    synchronized (lock) {
                        while (number <= N && (number - 1) % M != threadId) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }

                        if (number > N) {
                            lock.notifyAll(); // Wake others if they're waiting
                            break;
                        }
                        try {
                            Thread.sleep(1000);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }


                        System.out.println(number + " printed by Thread - " + threadId);
                        number++;
                        lock.notifyAll(); // Wake other threads
                    }
                }
            }).start();

        }
    }
}
