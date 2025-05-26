package payment_gateway_shreyansh_Jain;
import java.util.*;
public class TransactionService {

    static Map<Integer, List<Transaction>> userVsTransactionsList = new HashMap<>();
    InstrumentController instrumentController=new InstrumentController();
    Processor processor=new Processor();

    public List<Transaction> getTransactionHistory(int userID) {
        return userVsTransactionsList.get(userID);
    }

    public Transaction makePayment(TransactionDO txnDO) {

        // get sender instrument details
        InstrumentDO senderInstrumentDetails = instrumentController.getInstrumentByID(txnDO.getSenderUserID(),txnDO.getDebitInstrumentID());

        // get receiver instrument details
        InstrumentDO receiverInstrumentDetails = instrumentController.getInstrumentByID(txnDO.getReceiverUserID(),txnDO.getCreditInstrumentID());

        // process payment
        //pass this to processor senderInstrumentDetails, receiverInstrumentDetails, txnDO.getAmount()
        processor.processPayment();

        // create transaction object
        Transaction transaction = new Transaction();
        transaction.setTransactionDO(txnDO);
        transaction.setTxnId((int)(Math.random() * 1000));
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        // update sender transaction list
        if(!userVsTransactionsList.containsKey(txnDO.getSenderUserID())) {
            userVsTransactionsList.put(txnDO.getSenderUserID(), new ArrayList<>());
        }
        userVsTransactionsList.get(txnDO.getSenderUserID()).add(transaction);

        // update receiver transaction list
        if(!userVsTransactionsList.containsKey(txnDO.getReceiverUserID())) {
            userVsTransactionsList.put(txnDO.getReceiverUserID(), new ArrayList<>());
        }
        userVsTransactionsList.get(txnDO.getReceiverUserID()).add(transaction);

        return transaction;
    }
}
