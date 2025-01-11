package vending_machine;

public class InventoryItem {
    private  final Item item;
    private  Integer quantity;

    public InventoryItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public void increaseQuantity(Integer quantity){
        this.quantity+=quantity;
    }
    public void decreaseQuantity() throws Exception{
        if(this.quantity==0){
            throw new Exception("Item out of Stock");
        }
        this.quantity-=1;

    }

    public Item getItem() {
        return item;
    }
    public Integer getCost(){
        return item.getCost();
    }

    public Integer getQuantity() {
        return quantity;
    }
}
