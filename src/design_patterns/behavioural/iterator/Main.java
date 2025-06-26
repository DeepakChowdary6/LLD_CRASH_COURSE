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
        //return new ConcreteIterator(items); without generics
        return new ConcreteIterator<>(items); //can infer data type without <String>(items)
    }
    public String[] getItems() {
        return items;
    }
}
//class ConcreteIterator implements Iterator { // without generics
//    private String[] items;
//    private int index;
//    public ConcreteIterator(String[] items) {
//        this.items = items;
//    }
//    public boolean hasNext() {
//        return index < items.length;
//    }
//    public String next() {
//        return items[index++];
//    }
//}
class ConcreteIterator<T> implements Iterator {
    private T[] items;

    private int index;
    public ConcreteIterator(T[] items) {
        this.items = items;

    }

    public boolean hasNext() {
        return index < items.length;

    }
    public T next() {
        return items[index++];

}}
public class Main {

    public static void main(String[] args) {
        String[] items = {"Item 1", "Item 2", "Item 3"};
        Collection collection = new ConcreteCollection(items);
        Iterator iterator = collection.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
