package atm_design.state;

import atm_design.ATM;
import atm_design.ATMcard;

public class CheckBalanceState extends ATMstate{


    @Override
    public void checkBalance(ATM atm, ATMcard atMcard){
        System.out.println("Current balance in your account is : "+atMcard.getBalance());
        exit(atm);
    }
    @Override
    public void returnCard(){
        System.out.println("Please Collect your Card");
    }

    @Override
    public void exit(ATM atm){
        returnCard();
        atm.setCurrentState(new IdleState());
        System.out.println("Successfully exited the atm card");
    }
}
