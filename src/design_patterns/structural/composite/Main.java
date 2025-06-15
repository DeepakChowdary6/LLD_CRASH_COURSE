package design_patterns.structural.composite;
import java.util.*;

/*
* The Composite Design Pattern is a structural design pattern that lets you compose objects into tree-like structures to represent part-whole hierarchies.
* It allows clients to treat individual objects and compositions of objects uniformly.
*  In other words, whether dealing with a single object or a group of objects (composite), clients can use them interchangeably.
* */
interface FileSystemComponent {
    boolean isDirectory();
    void print();
}

// leaf
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public void print() {
        System.out.println(name);
    }
}

// composite
class Directory implements FileSystemComponent {
    private List<FileSystemComponent> children = new ArrayList<>();
    private String name;

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public void print() {
      //  System.out.println(name);
        for (FileSystemComponent component : children) {
            component.print();
        }
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }
}
public class Main {
    // components


    public static void main(String[] args) {
        Directory root = new Directory("root");
        Directory bin = new Directory("bin");
        Directory tmp = new Directory("tmp");
        Directory usr = new Directory("usr");
        root.add(bin);
        root.add(tmp);
        root.add(usr);
        bin.add(new File("vi"));
        bin.add(new File("latex"));
        bin.print();
        root.print();
    }
}
