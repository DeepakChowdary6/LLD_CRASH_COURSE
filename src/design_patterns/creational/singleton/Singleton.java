package design_patterns.creational.singleton;

public class Singleton {

    /* write the code for single ton design pattern here */

    /*write code for subtracting 2 number
add 2 numbers
     */
        //wpublic class Singleton {
    // Private constructor to prevent instantiation
    private Singleton() {}

    // Static instance of the class
    private static Singleton instance;

    // Public method to get the instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    public double subtract(double a, double b) {
        return a - b;
    }
    /**
     * Returns the sum of {@code a}, {@code b}, and {@code c}.
     *
     * @param a the first number to add
     * @param b the second number to add
     * @param c the third number to add
     * @return the sum of {@code a}, {@code b}, and {@code c}
     */
    public double add(double a, double b, double c) {
        return a + b + c;
    }

    public double multiply(double a, double b) {
        return a * b;
    }
}
