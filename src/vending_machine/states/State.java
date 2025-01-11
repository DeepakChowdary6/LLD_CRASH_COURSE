package vending_machine.states;

import vending_machine.Coin;
import vending_machine.Item;
import vending_machine.VendingMachine;

import java.util.List;

public interface State {
    public void insertCoin(VendingMachine vendingMachine, Coin coin) throws Exception;
    public void  startSelection(VendingMachine vendingMachine) throws Exception;
    public void chooseProcuct(VendingMachine vendingMachine,String item) throws Exception;
    public Item despenseProduct(VendingMachine vendingMachine, String item) throws Exception;

    public List<Coin> refundRemaningMoney(VendingMachine vendingMachine) throws Exception;


}
