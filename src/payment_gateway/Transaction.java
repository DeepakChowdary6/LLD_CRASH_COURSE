package payment_gateway;

public class Transaction {

    public  int txnId;
   public int Amount;
   int senderUserID;
   int receiverUserID;
   int debitInstrumentID;
   int creditInstrumentID;
   TransactionStatus status;
TransactionDO transactionDO;

    public TransactionDO getTransactionDO() {
        return transactionDO;
    }

    public void setTransactionDO(TransactionDO transactionDO) {
        this.transactionDO = transactionDO;
    }

    public int getTxnId() {
        return txnId;
    }

    public void setTxnId(int txnId) {
        this.txnId = txnId;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getSenderUserID() {
        return senderUserID;
    }

    public void setSenderUserID(int senderUserID) {
        this.senderUserID = senderUserID;
    }

    public int getReceiverUserID() {
        return receiverUserID;
    }

    public void setReceiverUserID(int receiverUserID) {
        this.receiverUserID = receiverUserID;
    }

    public int getDebitInstrumentID() {
        return debitInstrumentID;
    }

    public void setDebitInstrumentID(int debitInstrumentID) {
        this.debitInstrumentID = debitInstrumentID;
    }

    public int getCreditInstrumentID() {
        return creditInstrumentID;
    }

    public void setCreditInstrumentID(int creditInstrumentID) {
        this.creditInstrumentID = creditInstrumentID;
    }

    public TransactionStatus getTransactionStatus() {
        return status;
    }

    public void setTransactionStatus(TransactionStatus status) {
        this.status = status;
    }
    @Override
    public String toString(){
        return Amount+" transferred from "+senderUserID+" to "+receiverUserID;
    }
}
