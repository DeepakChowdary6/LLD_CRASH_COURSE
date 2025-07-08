package design_patterns.structural.decorator;

/**
 * The Decorator Pattern is a structural design pattern that lets you attach new behaviors to objects without modifying their implementation.
 * It allows behavior to be added to an individual object, either statically or dynamically, without affecting the behavior of other objects from the same class.
 * It is a design pattern that allows an object to add additional responsibilities to an existing object without modifying its external interface.
 * It is a structural pattern as this pattern acts as a wrapper to existing class.
 * This type of design pattern comes under structural pattern as this pattern acts as a wrapper to existing class.
 * This pattern creates a decorator class which wraps the original class and provides additional functionality keeping class methods signature intact.
 * We can add new behavior to an object without modifying its implementation.
 * It is a structural design pattern, which can be used to add new functionality to an existing object without changing its implementation.
 * It is a wrapper, which contains the component it should decorate with.
 * It is a structural pattern as this pattern acts as a wrapper to existing class.
 */
 interface Pizza {
    public String getDescription();
    public double getCost();
}

 class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain Pizza";
    }
    @Override
    public double getCost() {
        return 8.0;
    }
}

 abstract class PizzaDecorator implements Pizza {
    protected Pizza pizzadecorator;
    PizzaDecorator(Pizza pizzadecorator) {
        this.pizzadecorator = pizzadecorator;
    }
    @Override
    public String getDescription() {
        return pizzadecorator.getDescription();
    }
    @Override
    public double getCost() {
        return pizzadecorator.getCost();
    }
}

 class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza pizzadecorator) {
        super(pizzadecorator);
    }
    @Override
    public String getDescription() {
        return pizzadecorator.getDescription() + " with cheese";
    }
    @Override
    public double getCost() {
        return pizzadecorator.getCost() + 1.0;
    }
}
class HamDecorator extends PizzaDecorator {
    public HamDecorator(Pizza pizzadecorator) {
        super(pizzadecorator);
    }
    @Override
    public String getDescription() {
        return pizzadecorator.getDescription() + " with ham";
    }
    @Override
    public double getCost() {
        return pizzadecorator.getCost() + 1.8;
    }
}

class MushRoomDecorator extends PizzaDecorator {
    public MushRoomDecorator(Pizza pizzadecorator) {
        super(pizzadecorator);
    }
    @Override
    public String getDescription() {
        return pizzadecorator.getDescription() + " with mushroom";
    }
    @Override
    public double getCost() {
        return pizzadecorator.getCost() + 1.5;
    }
}

public class Decorator {

    public static void main(String[] args) {
        Pizza pizza = new PlainPizza();
        pizza = new CheeseDecorator(pizza);
        pizza = new HamDecorator(pizza);
        pizza = new MushRoomDecorator(pizza);
        System.out.println(pizza.getDescription());
        System.out.println(pizza.getCost());
    }
}
