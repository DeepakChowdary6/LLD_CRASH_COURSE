package vending_machine.states;

import vending_machine.Coin;
import vending_machine.Item;
import vending_machine.VendingMachine;

import java.util.List;

public class IdleSate implements State{

    @Override
    public void insertCoin(VendingMachine vendingMachine, Coin coin){
        vendingMachine.addCoin(coin);
        vendingMachine.setCurrState(new HasMoneyState());
    }

    @Override
    public void  startSelection(VendingMachine vendingMachine) throws Exception {
        throw  new Exception("Insert coins first");
    }
    public void chooseProcuct(VendingMachine vendingMachine,String item)throws Exception {
        throw  new Exception("Insert coins first");
    }
    public Item despenseProduct(VendingMachine vendingMachine, String item)throws Exception {
        throw  new Exception("Insert coins first");
    }

    public List<Coin> refundRemaningMoney(VendingMachine vendingMachine)throws Exception {
        throw  new Exception("Insert coins first");
    }

}
