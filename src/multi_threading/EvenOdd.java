package multi_threading;

// print the numbers 1,2,3,4,5 by odd printer and even printer
public class EvenOdd {
    public static int num=1;
    public static final int MAX = 10;
    public static void main(String[] args) {
        Object o=new Object();

      Thread oddPrinter=new Thread(()->{
          while (true){
              synchronized (o){

          while(num%2==0){
             try {
                 o.wait();
             }catch (Exception e){

             }

          }
                  if (num > MAX) {
                      o.notifyAll();
                      break;
                  }

          System.out.println("Odd printer prints: "+num);
           try {
               Thread.sleep(1000);
           }catch (Exception e){

          }
           num++;
           o.notifyAll();
          }

          }
      }) ;

      Thread evenPrinter=new Thread(()->{
          while (true){
              synchronized (o){

                  while(num%2==1){
                      try {
                          o.wait();
                      }catch (Exception e){

                      }

                  }

                  if (num > MAX) {
                      o.notifyAll();
                      break;
                  }

                  System.out.println("Even Printer prints: "+num);
                  try {
                      Thread.sleep(1000);
                  }catch (Exception e){

                     }

                  num++;
                  o.notifyAll();
              }

          }
      });

      oddPrinter.start();
      evenPrinter.start();

    }


}
