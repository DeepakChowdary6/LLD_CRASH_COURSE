package atm_design;

public class Main {

    public static void main(String[] args) {
        User user=new User("Suresh");
        BankAccount bankAccount=new BankAccount(user,10000);

        ATM atm=new ATM(50,100,30,10);

        ATMcard atMcard=new ATMcard(1234,bankAccount);

        atm.getAtMstate().insertCard(atm,atMcard);

        atm.getAtMstate().authenticatePin(atm,atMcard,1234);

        atm.getAtMstate().selectOperation(atm,atMcard,TransactionType.CASH_WITHDRAWL);

        atm.getAtMstate().withDrawBalance(atm,atMcard,5500);

        System.out.println("User balance remaining is "+bankAccount.getAccountBalance());




    }
}
