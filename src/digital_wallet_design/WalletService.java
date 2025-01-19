package digital_wallet_design;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class WalletService {
    public WalletDao wallet;
    public WalletService(){
        wallet=new WalletDao();
    }
    public void createWallet(String name, BigDecimal amount){
         Account account=new Account(name,amount);
         wallet.getAccountMap().put(account.getAccNo(),account);
         System.out.println("Account created for the user "+name+" with account number "+account.getAccNo());
    }
    public void transfer(int fromAcc,int toAcc,BigDecimal amount){
        if(!validate(fromAcc,toAcc,amount)){
            return;
        }
        Transaction transaction=new Transaction(fromAcc,toAcc,amount,new Date());
        Account fromAccount=wallet.getAccountMap().get(fromAcc);
        Account toAccount=wallet.getAccountMap().get(toAcc);
        if(fromAccount.getBalance().compareTo(amount)<0){
            System.out.println("insufficient funds");return;
        }
        //update balance in both  the accounts
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        // add the transaction in both the accounts
        fromAccount.getTransactions().add(transaction); toAccount.getTransactions().add(transaction);
        System.out.println("Transfer Successful");
        return ;
    }
    public boolean validate(int fromAcc,int toAcc,BigDecimal transferAmount){
        if(fromAcc==toAcc){
            System.out.println("Sender and Receiver cannot be same");
            return false;
        }
        if(transferAmount.compareTo(new BigDecimal(0.001))<0){
            System.out.println("Transfer Amount too low");
            return false;
        }
        if(!wallet.getAccountMap().containsKey(fromAcc)){
            System.out.println("Invalid Sender Account number");
            return false;
        }
        if(!wallet.getAccountMap().containsKey(toAcc)){
            System.out.println("Invalid receiver Account number");
            return false;
        }

     return true;
    }

    public void statement(int accountNumber){
        Account account=wallet.getAccountMap().get(accountNumber);
        if(account==null){
            System.out.println("Invalid Account Number");
            return;
        }
        System.out.println(account);
        System.out.println("Your transaction History ");
        System.out.println(account.getTransactions());
    }

    public void overview(){
       for(int accNum:wallet.getAccountMap().keySet()){
           System.out.println("Balance for the account number:"+accNum+" is "+wallet.getAccountMap().get(accNum).getBalance());
       }
    }

}
