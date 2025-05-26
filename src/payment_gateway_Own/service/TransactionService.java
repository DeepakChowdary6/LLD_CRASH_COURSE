package payment_gateway_Own.service;
import payment_gateway_Own.dto.*;
import payment_gateway_Own.repo.Repository;

import java.util.*;
public class TransactionService {
    public void sendMoney(String senderId, String receiverId, String instrumentId, double amount) {
        User sender = Repository.userMap.get(senderId);
        User receiver = Repository.userMap.get(receiverId);
        Instrument instrument = Repository.instrumentMap.get(instrumentId);

        if (sender == null || receiver == null || instrument == null) {
            System.out.println("Invalid sender, receiver, or instrument");
            return;
        }

        String txnId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(txnId, senderId, receiverId, instrumentId, amount);
        Repository.transactionMap.put(txnId, transaction);
        System.out.println("Transaction successful: " + txnId);
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(Repository.transactionMap.values());
    }
}
