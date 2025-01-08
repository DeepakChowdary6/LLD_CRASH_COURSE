package atm_design.dispencer;

import atm_design.ATM;

public class FiveHundredDispenser extends Dispenser{
  public   FiveHundredDispenser(Dispenser dispenser){
        super(dispenser);
    }

    @Override
    public void process(ATM atm, Integer amount){
        int possibleNotes=Math.min(amount/500,atm.getThousandRupeeNotes());
        amount-=500*possibleNotes;
        atm.deductfivehundredRupeeNotes(possibleNotes);
        if(possibleNotes!=0){
            System.out.println(possibleNotes+" possible notes of 500");
        }
        super.process(atm,amount);

    }
}
