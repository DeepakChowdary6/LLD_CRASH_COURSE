package payment_gateway_shreyansh_Jain;
import java.util.*;
public class TransactionController {

    TransactionService txnService=new TransactionService();

    public Transaction makePayment(TransactionDO txnDO) {
        return txnService.makePayment(txnDO);
    }

    public List<Transaction> getTransactionHistory(int userID) {
        return txnService.getTransactionHistory(userID);
    }
}
