package atm_design;

import atm_design.state.ATMstate;
import atm_design.state.IdleState;

public class ATM {

    private Integer atmBalance;
    private Integer fifyRupeeNotes;
    private Integer hundredRupeeNotes;
    private Integer fivehundredRupeeNotes;
    private Integer thousandRupeeNotes;

    private ATMstate currentState;

    public ATM(Integer fifyRupeeNotes, Integer hundredRupeeNotes, Integer fivehundredRupeeNotes, Integer thousandRupeeNotes) {
        this.atmBalance = atmBalance;
        this.fifyRupeeNotes = fifyRupeeNotes;
        this.hundredRupeeNotes = hundredRupeeNotes;
        this.fivehundredRupeeNotes = fivehundredRupeeNotes;
        this.thousandRupeeNotes = thousandRupeeNotes;
        this.atmBalance=fifyRupeeNotes*50+hundredRupeeNotes*100+fivehundredRupeeNotes*500+thousandRupeeNotes*1000;
        this.currentState=new IdleState();
    }
 public void setCurrentState(ATMstate atMstate){
        this.currentState=atMstate;
 }

    public void deductfiftyRupeeNotes(int cnt){
        this.fifyRupeeNotes-=cnt;
        this.atmBalance-=cnt*50;
    }
    public void deducthundredRupeeNotes(int cnt){
        this.fifyRupeeNotes-=cnt;
        this.atmBalance-=cnt*100;
    }
    public void deductfivehundredRupeeNotes(int cnt){
        this.fifyRupeeNotes-=cnt;
        this.atmBalance-=cnt*500;
    }
    public void deductthousandRupeeNotes(int cnt){
        this.fifyRupeeNotes-=cnt;
        this.atmBalance-=cnt*1000;
    }

    public Integer getAtmBalance() {
        return atmBalance;
    }

    public Integer getFifyRupeeNotes() {
        return fifyRupeeNotes;
    }

    public Integer getHundredRupeeNotes() {
        return hundredRupeeNotes;
    }

    public Integer getFivehundredRupeeNotes() {
        return fivehundredRupeeNotes;
    }

    public Integer getThousandRupeeNotes() {
        return thousandRupeeNotes;
    }

    public ATMstate getAtMstate() {
        return currentState;
    }
}
