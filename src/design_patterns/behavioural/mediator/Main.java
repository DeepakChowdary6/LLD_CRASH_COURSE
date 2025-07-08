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
    
    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }
    
    public abstract void send(String message);
    public abstract void receive(String message, String senderName);
    
    public String getName() {
        return name;
    }
}

// Concrete Colleague 1
class ChatUser extends User {
    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }
    
    @Override
    public void send(String message) {
        System.out.println(this.name + " sends: " + message);
        mediator.sendMessage(message, this);
    }
    
    @Override
    public void receive(String message, String senderName) {
        System.out.println(this.name + " received from " + senderName + ": " + message);
    }
}

// Concrete Colleague 2
class AdminUser extends User {
    public AdminUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }
    
    @Override
    public void send(String message) {
        System.out.println("[ADMIN] " + this.name + " sends: " + message);
        mediator.sendMessage("[ADMIN MESSAGE] " + message, this);
    }
    
    @Override
    public void receive(String message, String senderName) {
        System.out.println("[ADMIN] " + this.name + " received from " + senderName + ": " + message);
    }
    
    // Admin-specific method
    public void sendAlert(String alert) {
        System.out.println("[SYSTEM ALERT] from " + this.name + ": " + alert);
        mediator.sendMessage("[SYSTEM ALERT] " + alert, this);
    }
}

public class Main {
    public static void main(String[] args) {
        // Create the mediator
        ChatMediator chatRoom = new ChatRoom();
        
        // Create users
        User user1 = new ChatUser(chatRoom, "Alice");
        User user2 = new ChatUser(chatRoom, "Bob");
        User user3 = new ChatUser(chatRoom, "Charlie");
        User admin = new AdminUser(chatRoom, "Admin");
        
        // Add users to the chat room
        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);
        chatRoom.addUser(admin);
        
        // Users send messages
        user1.send("Hello everyone!");
        user2.send("Hi Alice, how are you?");
        
        // Admin sends a message and an alert
        admin.send("Welcome to the chat room");
        ((AdminUser) admin).sendAlert("The chat will be down for maintenance at 10 PM");
        
        // Another user responds
        user3.send("Thanks for the heads up, Admin");
    }
}