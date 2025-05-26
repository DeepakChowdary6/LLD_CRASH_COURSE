package payment_gateway_Own.dto;

public class Instrument {
    private final String instrumentId;
    private final String type; // "card" or "bank"

    public Instrument(String instrumentId, String type) {
        this.instrumentId = instrumentId;
        this.type = type;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public String getType() {
        return type;
    }
}