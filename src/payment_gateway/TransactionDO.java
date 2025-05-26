package payment_gateway;

public class TransactionDO {
    public  int txnId;
    public int Amount;
    int senderUserID;
    int receiverUserID;
    int debitInstrumentID;
    int creditInstrumentID;
    TransactionStatus status;

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

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
