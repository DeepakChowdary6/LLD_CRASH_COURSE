package digital_wallet_design;

import java.util.UUID;

public class User {
    private final String name;
    private final String id;

    public User(String name) {
        this.name = name;
        this.id=UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
