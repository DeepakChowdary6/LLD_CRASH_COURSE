package restaurant_management_system.service;

import restaurant_management_system.dto.Order;
import restaurant_management_system.dto.Restaurant;

import java.util.concurrent.BlockingQueue;

public class servingStaffservice implements Runnable {

    @Override
    public void run(){
         BlockingQueue<Order>readyqueue= Restaurant.getReadyQueue();

         while (true){
             try {
                 Order order=readyqueue.take();
                 System.out.println("Your order with order id: "+ order.getOrderId()+" is served");
                 Thread.sleep(2000);
             }catch (Exception e){
                  System.out.println(e.getMessage());
             }


         }
    }
}
