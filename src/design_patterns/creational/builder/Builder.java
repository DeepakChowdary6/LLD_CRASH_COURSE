package design_patterns.creational.builder;
/**
 * The Builder Pattern is a creational design pattern that separates the construction
 * of a complex object from its representation, allowing the same construction process
 * to create different representations.
 *
 * This pattern is particularly useful when an object needs to be created with many
 * optional components or configurations.
 *
 * The Builder Pattern provides a step-by-step approach to build an object, allowing
 * the creation of a complex object by specifying its type and content, and then
 * constructing it with the builder class.
 *
 * It involves four main components:
 * 1. Builder: Declares the interface for creating parts of a Product object.
 * 2. ConcreteBuilder: Constructs and assembles parts of the product by implementing
 *    the Builder interface.
 * 3. Product: Represents the complex object under construction.
 * 4. Director (optional): Constructs an object using the Builder interface.
 */
//
class User{//products
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String address;

   private User(UserBuilder userBuilder){
        this.name=userBuilder.name;
        this.email=userBuilder.email;
        this.phoneNumber=userBuilder.phoneNumber;
        this.address=userBuilder.address;
    }
    //concrete builder
    public static class UserBuilder {
        private String name;
        private String email;
        private String phoneNumber="";
        private String address="";
        public UserBuilder(String name,String email){ //making mandatory fields
            this.name=name;
            this.email=email;
        }
        public UserBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder setAddress(String address) {
            this.address = address;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

public class Builder {
    public static void main(String[] args) {
       User user=new User.UserBuilder("Suresh", "b3kO0@example.com").setPhoneNumber("1234567890").setAddress("Chennai").build();
        System.out.println(user);

    }
}
