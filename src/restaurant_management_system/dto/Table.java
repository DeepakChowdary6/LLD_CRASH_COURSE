package restaurant_management_system.dto;

import java.util.*;
import java.util.concurrent.atomic.*;

public class Table {

    private final int tableid;
    private List<Order>orders;
    public Table(int tableid){
        orders=new ArrayList<>();

        this.tableid=tableid;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order){
        orders.add(order);
        return;
    }

    public int getTableid() {
        return tableid;
    }
}
