package vending_machine;

import vending_machine.states.IdleSate;
import vending_machine.states.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VendingMachine {
    public Map<String,InventoryItem> Inventory;
    public List<Coin> coinList;
    private Integer currentBalance;
    private State currState;

    public  VendingMachine(){
        this.coinList=new ArrayList<>();
        this.Inventory=new ConcurrentHashMap<>();
        this.currentBalance=0;
        this.currState=new IdleSate();
    }

    public void displayBalance(){
        System.out.println("The current balance is vending machine is "+getCurrentBalance());
    }

    public void addItem(InventoryItem inventoryItem){
        String itemName=inventoryItem.getItem().getItemName();
        Integer quantity=inventoryItem.getQuantity();
        if(this.Inventory.containsKey(itemName)){
            this.Inventory.get(inventoryItem).increaseQuantity(quantity);
        }else{
        this.Inventory.putIfAbsent(itemName,inventoryItem);}
    }
    public void deductBalance(Integer amount){
        this.currentBalance-=amount;
    }
    public void addCoin(Coin coin){
        this.currentBalance+=coin.getValue();
        coinList.add(coin);
    }
    public void resetValue(){
        this.currentBalance=0;
        this.coinList.clear();
    }

    public void setCurrState(State currState) {
        this.currState = currState;
    }

    public Map<String, InventoryItem> getInventory() {
        return Inventory;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

    public Integer getCurrentBalance() {
        return currentBalance;
    }

    public State getCurrState() {
        return currState;
    }
}
