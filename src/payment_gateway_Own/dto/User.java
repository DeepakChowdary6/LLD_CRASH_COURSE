package payment_gateway_Own.dto;

import java.util.*;

public class User {
    private final String userId;
    private final String name;
    private final List<Instrument> instruments = new ArrayList<>();

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }
}