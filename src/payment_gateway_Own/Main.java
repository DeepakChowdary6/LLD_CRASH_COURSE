package payment_gateway_Own;
import payment_gateway_Own.dto.*;
import payment_gateway_Own.service.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        // Initialize services
        UserService userService = new UserService();
        InstrumentService instrumentService = new InstrumentService();
        TransactionService transactionService = new TransactionService();

        // Create users
        userService.createUser("user1", "Alice");
        userService.createUser("user2", "Bob");

        // Add instruments to users
        instrumentService.addInstrument("user1", "inst1", "bank");
        instrumentService.addInstrument("user1", "inst2", "card");
        instrumentService.addInstrument("user2", "inst3", "bank");

        // Perform transactions
        transactionService.sendMoney("user1", "user2", "inst1", 500.00);
        transactionService.sendMoney("user1", "user2", "inst2", 250.00);
        transactionService.sendMoney("user2", "user1", "inst3", 100.00);

        // Fetch and display all transactions
        System.out.println("\n--- All Transactions ---");
        List<Transaction> transactions = transactionService.getAllTransactions();
        for (Transaction txn : transactions) {
            System.out.println("Transaction ID: " + txn.getTransactionId());
            System.out.println("From: " + txn.getSenderUserId());
            System.out.println("To: " + txn.getReceiverUserId());
            System.out.println("Instrument: " + txn.getInstrumentId());
            System.out.println("Amount: $" + txn.getAmount());
            System.out.println("Timestamp: " + txn.getTimestamp());
            System.out.println("--------------------------");
        }
    }
}
