package atm_design.state;

import atm_design.ATM;
import atm_design.ATMcard;

public class IdleState extends ATMstate{

    @Override
    public void insertCard(ATM atm, ATMcard atMcard){
        System.out.println("Card Inserted Successfully");
        atm.setCurrentState(new HasCardState());

    }
}
