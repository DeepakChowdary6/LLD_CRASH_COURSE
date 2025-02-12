package digital_wallet_design;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Transaction {
    private String transactionId;
    private Integer fromAcc;
    private  Integer toAcc;
    private BigDecimal amount;
    private LocalDateTime date;

    public Transaction(Integer fromAcc, Integer toAcc, BigDecimal amount, LocalDateTime date) {
        this.transactionId = generateTransactionId();
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        this.amount = amount;
        this.date = date;
    }

    public String generateTransactionId(){
        return "TXN"+ UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }

    @Override
    public String toString(){
        return "Transaction id:"+transactionId+" from "+fromAcc+" to the "+toAcc+", amount="+amount+" is done on "+date+"\n";
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getFromAcc() {
        return fromAcc;
    }

    public void setFromAcc(Integer fromAcc) {
        this.fromAcc = fromAcc;
    }

    public Integer getToAcc() {
        return toAcc;
    }

    public void setToAcc(Integer toAcc) {
        this.toAcc = toAcc;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }


}
