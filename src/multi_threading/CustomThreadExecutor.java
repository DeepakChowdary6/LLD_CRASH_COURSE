package multi_threading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadExecutor {
    private final BlockingQueue<Runnable> taskQueue;
    private  class WorkerThread extends Thread {
        public  WorkerThread(String name){
            super(name);
        }

        @Override
        public void run(){
            while(!isShutdown || !taskQueue.isEmpty()){
                try {
                    Runnable task=taskQueue.take();
                    task.run();
                }catch (Exception e){
                    if(isShutdown)break;
                }
            }
        }
    }

    private final WorkerThread[] workers;
    private volatile boolean isShutdown=false;

    public  CustomThreadExecutor(int numThreads){
        taskQueue=new LinkedBlockingQueue<>();
        workers=new WorkerThread[numThreads];
        for(int i=0;i<numThreads;i++){
            workers[i]=new WorkerThread("worker "+i);
            workers[i].start();
        }
    }

    public void submit(Runnable task) throws Exception{
         if(isShutdown){
             throw  new Exception("ThreadExecutor has been shut down");
         }
         taskQueue.offer(task);
    }
    public void shutdown(){
         isShutdown=true;
         for(WorkerThread worker:workers){
             worker.interrupt();
         }
    }

    public static void main(String[] args) {
        CustomThreadExecutor executor=new CustomThreadExecutor(3);
        for(int i=0;i<10;i++){

            try {       int taskNumber=i;
            executor.submit(()->{

                System.out.println(Thread.currentThread().getName()+" is executing task "+taskNumber);
                  try {
                      Thread.sleep(1000);
                  }catch (Exception e){
                      System.out.println(e.getMessage());
                  }


            });
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        executor.shutdown();
    }


}
