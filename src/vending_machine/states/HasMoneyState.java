package vending_machine.states;

import vending_machine.Coin;
import vending_machine.Item;
import vending_machine.VendingMachine;

import java.util.List;

public class HasMoneyState implements State
{
    @Override
    public void insertCoin(VendingMachine vendingMachine, Coin coin){
       vendingMachine.addCoin(coin);

    }

    @Override
    public void  startSelection(VendingMachine vendingMachine) throws Exception {
        System.out.println("Product Selection.. Started");
        vendingMachine.setCurrState(new SelectionState());
    }
    public void chooseProcuct(VendingMachine vendingMachine,String item)throws Exception {
        throw  new Exception("Insert coins first");
    }
    public Item despenseProduct(VendingMachine vendingMachine, String item)throws Exception {
        throw  new Exception("Insert coins first");
    }

    public List<Coin> refundRemaningMoney(VendingMachine vendingMachine)throws Exception {
        List<Coin>coinList=vendingMachine.getCoinList();

        vendingMachine.resetValue();
        vendingMachine.setCurrState(new IdleSate());
        System.out.println("Refunded the pending amount successfully");
        return coinList;
    }
}
