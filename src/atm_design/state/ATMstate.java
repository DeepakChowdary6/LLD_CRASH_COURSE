package atm_design.state;

import atm_design.ATM;
import atm_design.ATMcard;
import atm_design.TransactionType;

public abstract class ATMstate {

    public void insertCard(ATM atm, ATMcard atMcard){
        System.out.println("System error please try again");
    }
    public void authenticatePin(ATM atm,ATMcard atMcard,Integer pin){
        System.out.println("System error please try again");
    }
    public void checkBalance(ATM atm,ATMcard atMcard){
        System.out.println("System error please try again");
    }
    public void withDrawBalance(ATM atm,ATMcard atMcard,Integer amount){
        System.out.println("System error please try again");
    }
    public void  selectOperation(ATM atm, ATMcard atMcard, TransactionType transactionType){
        System.out.println("System error please try again");
    }

    public void returnCard(){
        System.out.println("System error please try again");
    }

    public void exit(ATM atm){
        System.out.println("System error please try again");
    }


}
