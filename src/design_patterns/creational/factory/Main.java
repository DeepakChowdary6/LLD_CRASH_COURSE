package design_patterns.creational.factory;
interface Product{
    public void display();
}

class ProductA implements Product{
    @Override
    public void display() {
        System.out.println("this is concrete Product A");
    }
}

class ProductB implements Product{
 @Override
 public void display() {
     System.out.println("this is concrete Product B");
 }
}

interface Factory {
public Product getProduct();
}
class FactoryA implements Factory{
    @Override
    public Product getProduct() {
        return new ProductA();
    }
}
class FactoryB implements Factory{
    @Override
    public Product getProduct() {
        return new ProductB();
    }
}

public class Main {

    public static void main(String[] args) {

        Factory factoryA = new FactoryA();
        Product producta= factoryA.getProduct();
        producta.display();
        Factory factoryB=new FactoryB();
        Product productb=factoryB.getProduct();
        productb.display();

    }

}
