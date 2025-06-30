import java.util.HashMap;
import java.util.concurrent.*;

public class Main
{
    public static void main(String[] args) {


//       ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//
//        Runnable task = () -> System.out.println("Executed at " + java.time.LocalTime.now());
//
//        // Schedule at fixed rate (initial delay = 0s, repeat every 2s)
//        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

new Thread(()->{   while(true){
    System.out.println(" Thread 1");
//    try {
//        Thread.sleep(2000);
//    }catch (Exception e){
//        System.out.println(e.getMessage());
//    }
}}).start();

new Thread(()->{   while(true){
//    try {
//        Thread.sleep(2000);
//    }catch (Exception e){
//        System.out.println(e.getMessage());
//    }
    System.out.println(" Thread 2");
}}).start();
new Thread(()->{   while(true){
//    try {
//        Thread.sleep(4000);
//    }catch (Exception e){
//        System.out.println(e.getMessage());
//    }
    System.out.println(" Thread 3");
}}).start();

        //obj.display(); // "Display"
    }
}