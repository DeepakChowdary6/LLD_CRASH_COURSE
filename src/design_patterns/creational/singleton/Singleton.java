package design_patterns.creational.singleton;

public class Singleton {


    // Private constructor to prevent instantiation
    private Singleton() {}

    // Static instance of the class
    private static volatile Singleton instance;

    // Public method to get the instance
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class){
              if(instance==null){
                instance = new Singleton();}
            }
        }
        return instance;
    }



}
