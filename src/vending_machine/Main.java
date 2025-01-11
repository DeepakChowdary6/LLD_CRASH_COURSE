package vending_machine;

public class Main {
    public static void main(String[] args) throws Exception{

        VendingMachine vendingMachine=new VendingMachine();


        Item item1=new Item(5,312,"Coke");
        Item item2=new Item(10,312,"Sprite");
        vendingMachine.addItem(new InventoryItem(item1,10));
        vendingMachine.addItem(new InventoryItem(item2,10));

        vendingMachine.getCurrState().insertCoin(vendingMachine,Coin.TEN);
        vendingMachine.getCurrState().insertCoin(vendingMachine,Coin.TEN);
        vendingMachine.getCurrState().insertCoin(vendingMachine,Coin.FIVE);
        vendingMachine.getCurrState().insertCoin(vendingMachine,Coin.TWO);
        vendingMachine.getCurrState().insertCoin(vendingMachine,Coin.ONE);
        vendingMachine.displayBalance();
        vendingMachine.getCurrState().startSelection(vendingMachine);
        vendingMachine.getCurrState().chooseProcuct(vendingMachine,"Coke");
        vendingMachine.getCurrState().startSelection(vendingMachine);
        vendingMachine.getCurrState().chooseProcuct(vendingMachine,"Sprite");
        vendingMachine.displayBalance();


    }

}
