package atm_design.dispencer;

import atm_design.ATM;

public class FiftyDispenser extends Dispenser {

   public FiftyDispenser(Dispenser dispenser){
        super(dispenser);
    }

    @Override
    public void process(ATM atm, Integer amount){
        int possibleNotes=Math.min(amount/50,atm.getThousandRupeeNotes());
        amount-=50*possibleNotes;
        atm.deductfiftyRupeeNotes(possibleNotes);
        if(possibleNotes!=0){
            System.out.println(possibleNotes+" possible notes of 50");
        }
        super.process(atm,amount);

    }
}
