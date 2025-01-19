package digital_wallet_design;

import java.util.HashMap;

public class WalletDao {
    private HashMap<Integer,Account> accountMap;

    public WalletDao() {
        this.accountMap = new HashMap<>();
    }

    public HashMap<Integer, Account> getAccountMap() {
        return accountMap;
    }

    public void setAccountMap(HashMap<Integer, Account> accountMap) {
        this.accountMap = accountMap;
    }
}
