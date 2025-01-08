package atm_design;

public class BankAccount {

    private final User user;
    private Integer AccountBalance;

    public BankAccount(User user, Integer accountBalance) {
        this.user = user;
        AccountBalance = accountBalance;
    }

    public synchronized void updateBalance(Integer amount){
        this.AccountBalance+=amount;
    }

    public User getUser() {
        return user;
    }

    public Integer getAccountBalance() {
        return AccountBalance;
    }
}
