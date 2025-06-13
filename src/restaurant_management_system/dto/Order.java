package restaurant_management_system.dto;

import java.util.*;

public class Order {
    private static int counter=0;

    private final int orderId;
    private List<OrderItem> orderItemList;
    private final int tableId;
    public  boolean served;

    public void setServed(boolean served) {
        this.served = served;
    }

    public Order(int tableId) {
        this.orderId = counter++;
        this.tableId=tableId;
        this.served=false;
        this.orderItemList=new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public int getTableId() {
        return tableId;
    }

    public boolean isServed() {
        return served;
    }
    public void addOrderItem(MenuItem menuItem,int quantity){
        orderItemList.add(new OrderItem(menuItem,quantity));
    }
}
