package design_patterns.creational.abstract_factory;

/**
 * Abstract Factory Design Pattern Demo
 * All classes in a single file for better understanding
 *
 * Provides an interface for creating families of related or dependent objects without
 * specifying their concrete classes.
 */


// Abstract Factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factory 1
class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

// Concrete Factory 2
class MacOSFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}

// Abstract Product A
interface Button {
    void paint();
}

// Abstract Product B
interface Checkbox {
    void paint();
}

// Concrete Product A1
class WindowsButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a Windows style button");
    }
}

// Concrete Product A2
class MacOSButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a MacOS style button");
    }
}

// Concrete Product B1
class WindowsCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendering a Windows style checkbox");
    }
}

// Concrete Product B2
class MacOSCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendering a MacOS style checkbox");
    }
}

// Client code
class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        // Create and run Windows application
        System.out.println("Creating Windows application:");
        Application windowsApp = new Application(new WindowsFactory());
        windowsApp.paint();

        // Create and run MacOS application
        System.out.println("\nCreating MacOS application:");
        Application macApp = new Application(new MacOSFactory());
        macApp.paint();
    }
}