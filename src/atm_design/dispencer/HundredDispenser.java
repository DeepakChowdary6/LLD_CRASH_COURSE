package atm_design.dispencer;

import atm_design.ATM;

public class HundredDispenser extends Dispenser{

   public HundredDispenser(Dispenser dispenser){
        super(dispenser);
    }

    @Override
    public void process(ATM atm, Integer amount){
        int possibleNotes=Math.min(amount/100,atm.getThousandRupeeNotes());
        amount-=100*possibleNotes;
        atm.deducthundredRupeeNotes(possibleNotes);
        if(possibleNotes!=0){
            System.out.println(possibleNotes+" possible notes of 100");
        }
        super.process(atm,amount);

    }
}
