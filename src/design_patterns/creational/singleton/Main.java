package design_patterns.creational.singleton;

import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) {
        AtomicReference<Singleton> singleton1 = new AtomicReference<>(), singleton2 =new AtomicReference<>();
        // AtomicReference is a thread-safe reference that can be used to wrap a Singleton class's instance field.
        // It provides a way to atomically set the value of the reference if the current value is equal to a given expected value.
        //

      Thread t1= new Thread(()->{
            singleton1.set(Singleton.getInstance());
            System.out.println(singleton1);
        });
        Thread t2= new Thread(()->{
            singleton2.set(Singleton.getInstance());
            System.out.println(singleton2);
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

      if(singleton1.get()==singleton2.get()){
         System.out.println("Same instance");
      }


    }
}
