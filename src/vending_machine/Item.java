package vending_machine;

public class Item {

    private final Integer cost;
    private final Integer skuCode;
    private final String itemName;

    public Item(Integer cost, Integer skuCode, String itemName) {
        this.cost = cost;
        this.skuCode = skuCode;
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getSkuCode() {
        return skuCode;
    }
}
