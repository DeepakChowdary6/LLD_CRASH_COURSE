package vending_machine.states;

import vending_machine.Coin;
import vending_machine.InventoryItem;
import vending_machine.Item;
import vending_machine.VendingMachine;

import java.util.List;

public class SelectionState implements State{



    @Override
    public void insertCoin(VendingMachine vendingMachine, Coin coin)throws Exception{
        throw  new Exception("choose the product firt");
    }

    @Override
    public void  startSelection(VendingMachine vendingMachine) throws Exception {
        throw  new Exception("choose the product firt");
    }
    public void chooseProcuct(VendingMachine vendingMachine,String itemName)throws Exception {
        InventoryItem inventoryItem=vendingMachine.getInventory().get(itemName);
        Integer balance=vendingMachine.getCurrentBalance();
        Integer quantity=inventoryItem.getQuantity();
        if(balance>=inventoryItem.getCost() && quantity>0){
            vendingMachine.deductBalance(inventoryItem.getCost());
            vendingMachine.setCurrState(new DispenseState());
            vendingMachine.getCurrState().despenseProduct(vendingMachine,itemName);

        }else{
            if(quantity==0){
            throw new Exception("Item Out of stock");}else{
                throw new Exception("Insuffisient balance in the machine");
            }
        }
    }
    public Item despenseProduct(VendingMachine vendingMachine, String item)throws Exception {
        throw  new Exception("choose the product firt");
    }

    public List<Coin> refundRemaningMoney(VendingMachine vendingMachine)throws Exception {
        throw  new Exception("choose the product firt");
    }
}
