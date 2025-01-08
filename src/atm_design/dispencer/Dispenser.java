package atm_design.dispencer;

import atm_design.ATM;
import atm_design.ATMcard;

public abstract class Dispenser {
    public Dispenser nextDisenser;

    public Dispenser(Dispenser dispenser){
        this.nextDisenser=dispenser;
    }

    public void process(ATM atm,Integer amount){

        if(nextDisenser!=null){
            this.nextDisenser.process(atm,amount);
        }else{
            if(amount!=0){
                System.out.println("cannot dispense the complete amount");
            }else if(amount==0){
                System.out.println("Successfully dispensed the complete amount");
            }

        }

    }

}
