package digital_wallet_design;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Account {

    private static AtomicInteger cnt=new AtomicInteger(1);
    private final User user;
    private BigDecimal balance;
    private Integer accNo;
    private Set<Transaction> transactions;

    public Account(String name, BigDecimal balance) {
        this.user = new User(name);
        this.balance = balance;
        this.accNo=cnt.getAndIncrement();
        transactions=new TreeSet<>((a,b)->a.getDate().compareTo(b.getDate()));
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString(){
        return "Account no "+accNo+" with name: "+user.getName()+" contains balance="+balance;
    }

    public static AtomicInteger getCnt() {
        return cnt;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Integer getAccNo() {
        return accNo;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }
}
