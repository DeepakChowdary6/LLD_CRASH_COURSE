package design_patterns.structural.adapter;

/**
 * The Adapter Pattern is a structural design pattern that allows objects with incompatible interfaces to work together.
 * It acts as a bridge between two incompatible interfaces. This type of design pattern comes under structural pattern as this pattern combines the capability of two independent interfaces.
 * This pattern involves a single class which is responsible to join functionalities of independent or incompatible interfaces.
 * A real life example could be a case of card reader which acts as an adapter between memory card and a laptop. You plug in memory card into card reader and card reader into the laptop so that memory card can be read via laptop.
 * The purpose of an adapter is to act as an intermediary between two objects which couldn't otherwise communicate directly. Adapters are often used to make existing classes work with others without modifying their source code.
 */

//Target interface
    interface Printer{
        void print();
    }
//Adaptee interface
class LegacyPrinter{
        public void printDocument(){
            System.out.println("Legacy printer printing document");
        }
}

class PrinterAdapter implements Printer{
    LegacyPrinter legacyPrinter = new LegacyPrinter();
        @Override
        public void print() {

            legacyPrinter.printDocument();
        }
}

public class Main {

    public static void clientCode(Printer printer){
        printer.print();
    }
    public static void main(String[] args) {

        Printer printer = new PrinterAdapter();
        clientCode(printer);
    }
}
