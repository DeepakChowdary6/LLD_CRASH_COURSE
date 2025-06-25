package design_patterns.structural.flyweight;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

// Flyweight interface
// Shape interface
interface Shape {
    void draw();
}

// Concrete Flyweight class
class Circle implements Shape {
    private final String color;
    private final int radius;

    public Circle(String color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle with radius " + radius + ".");
    }
}

// Composite Key for Flyweight Map
class CircleKey {
    public final String color;
    public final int radius;

    public CircleKey(String color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    // Important: Override equals and hashCode for key comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CircleKey)) return false;
        CircleKey key = (CircleKey) o;
        return radius == key.radius && Objects.equals(color, key.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, radius);
    }
}

// Flyweight Factory
class ShapeFactory {
    private static final Map<CircleKey, Shape> circleMap = new ConcurrentHashMap<>();

    public static Shape getCircle(String color, int radius) {
        CircleKey key = new CircleKey(color, radius);
        circleMap.computeIfAbsent(key, k -> new Circle(k.color, k.radius));
        //if only one argument is there then it will be used as key
        // circleMap.computeIfAbsent(color, c -> new Circle(c)); // color is passed as key   i.e k->new Circle(k) can only pass  one argument
        return circleMap.get(key);
    }
}

// Demo class
public class Main {

    /*
    * Flyweight pattern is primarily used to reduce the number of objects created and to decrease memory footprint and increase performance.
    * This type of design pattern comes under structural pattern as this pattern provides ways to decrease object count thus improving the object structure of application.
    * */
    public static void main(String[] args) {
        Shape circle1 = ShapeFactory.getCircle("red", 10);
        Shape circle2 = ShapeFactory.getCircle("red", 10);
        Shape circle3 = ShapeFactory.getCircle("blue", 20);

        circle1.draw();
        circle2.draw();
        circle3.draw();

        System.out.println("circle1 == circle2? " + (circle1 == circle2)); // true
        System.out.println("circle1 == circle3? " + (circle1 == circle3)); // false
    }
}
