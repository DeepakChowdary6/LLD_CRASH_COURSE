package atm_design.state;

import atm_design.ATM;
import atm_design.ATMcard;

public class HasCardState extends ATMstate{

    @Override
    public void authenticatePin(ATM atm, ATMcard atMcard, Integer pin){
        if(atMcard.isCorrectPin(pin)){
            System.out.println("Authentcation success");
            atm.setCurrentState(new SelectOperatioinState());
        }else {
            System.out.println("wrong pin entered");
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
