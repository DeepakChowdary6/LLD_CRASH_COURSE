package restaurant_management_system.service;

import restaurant_management_system.dto.MenuItem;
import restaurant_management_system.dto.Order;
import restaurant_management_system.dto.OrderItem;
import restaurant_management_system.repository.MenuRepository;
import restaurant_management_system.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {
    // place order
    //add items to existing order
    //view menu items ,veg,non-veg,all
    //calculate the bill for given order
    //
 // public   Scanner sc=new Scanner(System.in);
    // int tableId=sc.nextInt();
    public void placeOrder(int tableId){
        Order order=new Order(tableId);
        System.out.println("New order is created for you with the orderId "+order.getOrderId());
        OrderRepository.getOrderRepositoryMap().put(order.getOrderId(), order);

      // addItemstoExistingorder(order.getOrderId());
    }
    //
//        System.out.println("Enter the menuitem id ");
//        int menuItemid= sc.nextInt();
//        System.out.println("Enter the quantity");
//        int quantity=sc.nextInt();
    public void addItemstoExistingorder(Integer orderId,int menuItemid,int quantity){


          Order order=OrderRepository.getOrderRepositoryMap().get(orderId);
        order.addOrderItem(MenuRepository.getMenuItemHashMap().get(menuItemid),quantity);
    }
    //      int type= sc.nextInt();
    public void viewMenuItems(int type){
        System.out.println("Enter type \n 1.veg \n2.non-veg \n 3.any");


        List<MenuItem>itemList=MenuRepository.getMenuItemHashMap().entrySet().
                stream().map(entry->entry.getValue()).filter(menuItem -> {
                    if(type==1)return menuItem.isVeg();
                    if(type==2)return !menuItem.isVeg();
                    return true;
                })
                .collect(Collectors.toList());
        itemList.forEach(MenuItem->System.out.println("menu item id: "+MenuItem.getId()+" Name "+MenuItem.getName()+" Price: "+MenuItem.getPrice()));

    }
    public void CalculateBill(int orderId){
        Order order=OrderRepository.getOrderRepositoryMap().get(orderId);
        double cost=0;
        for(OrderItem item:order.getOrderItemList()){
            cost+= item.getQuantity()*(item.getItem().getPrice());
        }
        System.out.println("Bill for the order "+orderId+" is "+cost);
    }


}
