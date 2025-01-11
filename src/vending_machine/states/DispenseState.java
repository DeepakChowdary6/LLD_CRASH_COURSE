package vending_machine.states;

import vending_machine.Coin;
import vending_machine.Item;
import vending_machine.VendingMachine;

import java.util.List;

public class DispenseState implements State{
    @Override
    public void insertCoin(VendingMachine vendingMachine, Coin coin) throws Exception{
        throw  new Exception("Product is in dispense State");
    }

    @Override
    public void  startSelection(VendingMachine vendingMachine) throws Exception {
        throw  new Exception("Product is in dispense State");
    }
    public void chooseProcuct(VendingMachine vendingMachine,String item)throws Exception {
        throw  new Exception("Product is in dispense State");
    }
    public Item despenseProduct(VendingMachine vendingMachine, String itemName)throws Exception {
Item item=vendingMachine.getInventory().get(itemName).getItem();
        vendingMachine.getInventory().get(itemName).decreaseQuantity();
        System.out.println(item.getItemName()+" Product is dispensing");
        Integer currBalance=vendingMachine.getCurrentBalance();
        if(currBalance>0){
            vendingMachine.setCurrState(new HasMoneyState());
        }else{
            vendingMachine.setCurrState(new IdleSate());
        }
        return  item;
    }

    public List<Coin> refundRemaningMoney(VendingMachine vendingMachine)throws Exception {
        throw  new Exception("Product is in dispense State");
    }
}
