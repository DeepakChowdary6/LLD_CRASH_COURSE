package atm_design.state;

import atm_design.ATM;
import atm_design.ATMcard;
import atm_design.dispencer.*;

public class CashWithdrawlState extends ATMstate{


 @Override
    public void withDrawBalance(ATM atm, ATMcard atMcard, Integer amount){
        if(amount>atm.getAtmBalance()){
            System.out.println("ATM have insufficient funds");
            exit(atm);
        }else if(amount>atMcard.getBalance()){
            System.out.println("Your Account doesn't have sufficent funds");
            exit(atm);
        }else{
            atMcard.updateBalance(-amount);
            Dispenser dispencer=new ThousandDispenser(new FiveHundredDispenser(new HundredDispenser(new FiftyDispenser(null)))) ;
            dispencer.process(atm,amount);
            exit(atm);
        }
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
