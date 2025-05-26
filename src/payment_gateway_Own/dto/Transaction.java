package payment_gateway_Own.dto;


import java.time.LocalDateTime;

public class Transaction {
    private final String transactionId;
    private final String senderUserId;
    private final String receiverUserId;
    private final String instrumentId;
    private final double amount;
    private final LocalDateTime timestamp;

    public Transaction(String transactionId, String senderUserId, String receiverUserId, String instrumentId, double amount) {
        this.transactionId = transactionId;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.instrumentId = instrumentId;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public String getReceiverUserId() {
        return receiverUserId;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}