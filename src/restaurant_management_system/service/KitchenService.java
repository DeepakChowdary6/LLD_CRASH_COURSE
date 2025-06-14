package restaurant_management_system.service;

import restaurant_management_system.dto.Order;
import restaurant_management_system.dto.OrderItem;
import restaurant_management_system.dto.Restaurant;
import restaurant_management_system.repository.OrderRepository;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

public class KitchenService {
    //view incoming orders
    //mark the order as prepared
    //
    public void viewIncomingOrders(){
        List<Order>orderList= OrderRepository.getOrderRepositoryMap().entrySet().stream().map(order->order.getValue()).filter(order->!order.isServed()).collect(Collectors.toList());

        orderList.forEach(order -> {
            System.out.println("order id "+order.getOrderId());
        });

    }
    public  void viewItemsInOrder(int orderId){
        List<OrderItem>orderItemList=OrderRepository.getOrderRepositoryMap().get(orderId).getOrderItemList();

        orderItemList.forEach(OrderItem->{
            System.out.println("Menu item is : "+OrderItem.getItem().getName()+" with quantity "+OrderItem.getQuantity());
        });

    }
    public void serveTheOrder(int orderId){

        Order order=OrderRepository.getOrderRepositoryMap().get(orderId);
        order.setServed(true);
       Restaurant.getReadyQueue().offer(order);

    }



}
