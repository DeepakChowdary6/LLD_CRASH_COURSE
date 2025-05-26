package payment_gateway;
import java.util.*;
public class PaymentGateway {

    public static void main(String args[]) {

        InstrumentController instrumentController = new InstrumentController();
        UserController userController = new UserController();
        TransactionController transactionController = new TransactionController();

        //1. add USER1
        UserDO user1 = new UserDO();
        user1.setName("Si");
        user1.setMail("si@conceptandcoding.com");
        UserDO user1Details = userController.addUser(user1);

        //1. add USER2
        UserDO user2 = new UserDO();
        user2.setName("Pi");
        user2.setMail("Pi@conceptandcoding.com");
        UserDO user2Details = userController.addUser(user2);

        //add bank to User1
        InstrumentDO bankInstrumentDO = new InstrumentDO();
        bankInstrumentDO.setBankAccountNumber("234324234324324");
        bankInstrumentDO.setInstrumentType(InstrumentType.BANK);
        bankInstrumentDO.setUserID(user1Details.getUserID());
        bankInstrumentDO.setIfscCode("ER3223E");
        InstrumentDO user1BankInstrument = instrumentController.addInstrument(bankInstrumentDO);
        System.out.println("Bank Instrument created for User1: " + user1BankInstrument.getInstrumentID());

        //add Card to User2
        InstrumentDO cardInstrumentDO = new InstrumentDO();
        cardInstrumentDO.setCardNumber("123099");
        cardInstrumentDO.setInstrumentType(InstrumentType.CARD);
        cardInstrumentDO.setCvvNumber("0000");
        cardInstrumentDO.setUserID(user2Details.getUserID());
        InstrumentDO user2CardInstrument = instrumentController.addInstrument(cardInstrumentDO);
        System.out.println("Card Instrument created for User2: " + user2CardInstrument.getInstrumentID());

        //make payment
        TransactionDO transactionDO = new TransactionDO();
        transactionDO.setAmount(10);
        transactionDO.setSenderUserID(user1Details.getUserID());
        transactionDO.setReceiverUserID(user2Details.getUserID());
        transactionDO.setDebitInstrumentID(user1BankInstrument.getInstrumentID());
        transactionDO.setCreditInstrumentID(user2CardInstrument.getInstrumentID());
        transactionController.makePayment(transactionDO);

        //get All instruments of USER1
        List<InstrumentDO> user1Instruments = instrumentController.getAllInstruments(user1Details.getUserID());
        for(InstrumentDO instrumentDO : user1Instruments) {
            System.out.println("User1 InstID: " + instrumentDO.getInstrumentID() +
                    ": UserID: " + instrumentDO.getUserID() +
                    ": InstrumentType: " + instrumentDO.getInstrumentType().name());
        }

        //get All instruments of user2
        List<InstrumentDO> user2Instruments = instrumentController.getAllInstruments(user2Details.getUserID());
        for(InstrumentDO instrumentDO : user2Instruments) {
            System.out.println("User2 InstID: " + instrumentDO.getInstrumentID() +
                    ": UserID: " + instrumentDO.getUserID() +
                    ": InstrumentType: " + instrumentDO.getInstrumentType().name());
        }

        //get All transaction history
        List<Transaction> user1TransactionList = transactionController.getTransactionHistory(user1Details.getUserID());
        for(Transaction txn : user1TransactionList) {
            System.out.println(txn);
        }
    }
}

