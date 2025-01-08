package atm_design.state;

import atm_design.ATM;
import atm_design.ATMcard;
import atm_design.TransactionType;

public class SelectOperatioinState extends ATMstate{

    SelectOperatioinState(){
        showOperations();
    }


    @Override
    public void  selectOperation(ATM atm, ATMcard atMcard, TransactionType transactionType){
        switch (transactionType){

            case CHECK_BALANCE -> {
             atm.setCurrentState(new CheckBalanceState());
             break;
            }
            case CASH_WITHDRAWL -> {
              atm.setCurrentState(new CashWithdrawlState());
              break;
            }
            default -> {
                System.out.println("invalid operation");
                exit(atm);
            }
        }

    }

    public void showOperations(){
        System.out.println("Please Select the operation");
        System.out.println("1: "+TransactionType.CASH_WITHDRAWL);
        System.out.println("2: "+TransactionType.CHECK_BALANCE);
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
