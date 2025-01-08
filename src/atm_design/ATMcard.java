package atm_design;

public class ATMcard {

    private final Integer pin;
    private final BankAccount bankAccount;

    public ATMcard(Integer pin, BankAccount bankAccount) {
        this.pin = pin;
        this.bankAccount = bankAccount;
    }

    public boolean isCorrectPin(Integer pin){
        return this.pin.equals(pin);
    }

    public Integer getBalance(){
        return this.bankAccount.getAccountBalance();

    }
    public void updateBalance(Integer amount){
        this.bankAccount.updateBalance(amount);
    }
}
