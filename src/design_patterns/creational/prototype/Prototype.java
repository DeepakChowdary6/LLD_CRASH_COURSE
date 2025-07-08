package design_patterns.creational.prototype;

interface Sheep{
    Sheep clone();
    String getName();
}

class WhiteSheep implements Sheep {
    private String name;

    public WhiteSheep(String name) {
        this.name = name;}
    @Override
    public Sheep clone() {
        return new WhiteSheep(this.name);
    }
    @Override
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WhiteSheep{" +
                "name='" + name + '\'' +
                '}';
    }
}

class BlackSheep implements Sheep {
    private String name;

    public BlackSheep(String name) {
        this.name = name;
    }
    @Override
    public Sheep clone() {
        return new BlackSheep(this.name);
    }
    @Override
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BlackSheep{" +
                "name='" + name + '\'' +
                '}';
    }
}

public class Prototype {

/**
 * The Prototype Pattern is a creational design pattern that is used to create a new object by copying an existing object.
 * It is a design pattern that allows you to create an object copy of an existing object in a more efficient way than creating a new object from scratch.
 * It is a structural pattern as this pattern provides one of the best ways to create an object.
 * This pattern involves implementing a prototype interface which tells to create a clone of the current object.
 * This pattern is particularly useful when an object must be created in a way that is independent of how it is implemented.
 * It is a creational design pattern, which can be used to add new functionality to an existing object without modifying its external interface.
 * It is a structural pattern as this pattern acts as a wrapper to existing class.
 * This pattern creates a prototype class which contains the clone method for creating the objects.
 * It is a creational design pattern, which can be used to add new functionality to an existing object without modifying its external interface.
 */


    public static void main(String[] args) {
  BlackSheep blackSheep=new BlackSheep("blacky");
  WhiteSheep whiteSheep=new WhiteSheep("whitey");

  System.out.println(blackSheep);
  System.out.println(whiteSheep);

  Sheep clonedBlackSheep=blackSheep.clone();
  Sheep clonedWhiteSheep=whiteSheep.clone();

  System.out.println("cloned "+clonedBlackSheep);
  System.out.println("cloned "+clonedWhiteSheep);

    }
}
