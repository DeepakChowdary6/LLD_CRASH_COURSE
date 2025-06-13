package restaurant_management_system.repository;

import restaurant_management_system.dto.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {
    private  static HashMap<Integer, Order>orderMap;
      public OrderRepository(){
              orderMap=new HashMap<>();
        }

        public static Map<Integer,Order> getOrderRepositoryMap(){
           return orderMap;
      }

}
