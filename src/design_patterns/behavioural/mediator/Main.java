package design_patterns.behavioural.mediator;
import java.util.*;

/**
 * The Mediator Pattern is a behavioral design pattern that defines an object that encapsulates how a set of objects interact.
 * This pattern promotes loose coupling by keeping objects from referring to each other explicitly,
 * and it lets you vary their interaction independently.
 * 
 * Key components:
 * 1. Mediator: Defines an interface for communicating with Colleague objects
 * 2. ConcreteMediator: Implements the Mediator interface and coordinates communication between Colleague objects
 * 3. Colleague: Defines an interface for objects that communicate through the mediator
 * 4. ConcreteColleague: Implements the Colleague interface and communicates with other Colleagues through its mediator
 */

// Mediator interface
interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
    void registerUser(User user);
}

// Concrete Mediator
class ChatRoom implements ChatMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
        System.out.println(user.getName() + " joined the chat room");
    }

    @Override
    public void registerUser(User user) {
        user.setMediator(this);
        this.users.add(user);
        System.out.println(user.getName() + " joined the chat room");
    }

    @Override
    public void sendMessage(String message, User sender) {
        // The mediator distributes the message to all users except the sender
        for (User user : users) {
            if (user != sender) {
                user.receive(message, sender.getName());
            }
        }
    }
}

// Colleague abstract class
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    // Constructor without mediator
    public User(String name) {
        this.name = name;
    }

    // Method to set mediator after creation
    public void setMediator(ChatMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void send(String message);
    public abstract void receive(String message, String senderName);

    public String getName() {
        return name;
    }
}

// Concrete Colleague 1
class ChatUser extends User {
    public ChatUser(String name) {
        super(name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " sends: " + message);
        if (mediator != null) {
            mediator.sendMessage(message, this);
        } else {
            System.out.println("Error: User not registered with any chat room");
        }
    }

    @Override
    public void receive(String message, String senderName) {
        System.out.println(this.name + " received from " + senderName + ": " + message);
    }
}

// Concrete Colleague 2
class AdminUser extends User {
    public AdminUser(String name) {
        super(name);
    }

    @Override
    public void send(String message) {
        System.out.println("[ADMIN] " + this.name + " sends: " + message);
        if (mediator != null) {
            mediator.sendMessage("[ADMIN MESSAGE] " + message, this);
        } else {
            System.out.println("Error: Admin not registered with any chat room");
        }
    }

    @Override
    public void receive(String message, String senderName) {
        System.out.println("[ADMIN] " + this.name + " received from " + senderName + ": " + message);
    }

    // Admin-specific method
    public void sendAlert(String alert) {
        System.out.println("[SYSTEM ALERT] from " + this.name + ": " + alert);
        if (mediator != null) {
            mediator.sendMessage("[SYSTEM ALERT] " + alert, this);
        } else {
            System.out.println("Error: Admin not registered with any chat room");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create the mediator
        ChatMediator chatRoom = new ChatRoom();

        // Create users without mediator
        User user1 = new ChatUser("Alice");
        User user2 = new ChatUser("Bob");
        User user3 = new ChatUser("Charlie");
        User admin = new AdminUser("Admin");

        // Register users with the chat room
        chatRoom.registerUser(user1);
        chatRoom.registerUser(user2);
        chatRoom.registerUser(user3);
        chatRoom.registerUser(admin);

        // Users send messages
        user1.send("Hello everyone!");
        user2.send("Hi Alice, how are you?");

        // Admin sends a message and an alert
        admin.send("Welcome to the chat room");
        ((AdminUser) admin).sendAlert("The chat will be down for maintenance at 10 PM");

        // Another user responds
        user3.send("Thanks for the heads up, Admin");

        // Demonstrate creating a new chat room and moving a user
        ChatMediator privateRoom = new ChatRoom();
        User user4 = new ChatUser("Dave");
        privateRoom.registerUser(user4);
        privateRoom.registerUser(user1); // Alice joins the private room

        // Messages in private room
        user4.send("Welcome to the private room, Alice!");
        user1.send("Thanks for inviting me, Dave!");
    }
}
