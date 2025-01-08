package atm_design.dispencer;

import atm_design.ATM;

public class ThousandDispenser extends Dispenser{

  public   ThousandDispenser(Dispenser dispenser){
        super(dispenser);
    }

    @Override
    public void process(ATM atm,Integer amount){
        int possibleNotes=Math.min(amount/1000,atm.getThousandRupeeNotes());
        amount-=1000*possibleNotes;
        atm.deductthousandRupeeNotes(possibleNotes);
       if(possibleNotes!=0){
           System.out.println(possibleNotes+" possible notes of 1000");
        }
        super.process(atm,amount);

    }
}
