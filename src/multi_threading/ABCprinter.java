package multi_threading;

public class ABCprinter {
     private static final Object lock=new Object();
     private static int turn=0;
     private static int N=5;

    public static void main(String[] args) {
        Thread threadA=new Thread(()->printChar("A",0));
        Thread threadB=new Thread(()->printChar("B",1));
        Thread threadC=new Thread(()->printChar("C",2));
        threadA.start();
        threadB.start();
        threadC.start();

    }
    private  static void printChar(String letter,int threadTurn){

        for(int i=0;i<N;i++){
            synchronized (lock){
                while(turn!=threadTurn){
                    try {
                        lock.wait();
                    }catch (Exception e){
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(letter);
                turn=(turn+1)%3;
                lock.notifyAll();
            }

        }

    }
}
