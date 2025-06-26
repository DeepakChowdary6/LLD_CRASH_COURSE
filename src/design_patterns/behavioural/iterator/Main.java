package design_patterns.behavioural.iterator;
/*
* The Iterator Pattern provides a way to access elements of a collection sequentially without exposing its internal structure.
*  decouples traversal logic from the collection’s structure.
* */


interface Iterator {
    boolean hasNext();
    Object next();
}

interface Collection {
    Iterator createIterator();
}

class ConcreteCollection implements Collection {
    private String[] items;
    public ConcreteCollection(String[] items) {
        this.items = items;
    }
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }
    public String[] getItems() {
        return items;
    }
}

class ConcreteIterator implements Iterator {
    private ConcreteCollection collection;
    private int index;
    public ConcreteIterator(ConcreteCollection collection) {
        this.collection = collection;
    }
    public boolean hasNext() {
        return index < collection.getItems().length;
    }
    public Object next() {
        return collection.getItems()[index++];
    }
}
public class Main {

    public static void main(String[] args) {
        String[] items = {"Item 1", "Item 2", "Item 3"};
        ConcreteCollection collection = new ConcreteCollection(items);
        Iterator iterator = collection.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
