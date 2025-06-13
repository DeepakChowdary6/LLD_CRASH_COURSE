package restaurant_management_system.service;

import restaurant_management_system.repository.MenuRepository;
import restaurant_management_system.repository.OrderRepository;
import restaurant_management_system.repository.TableRepository;

public class InMemoryService {
public InMemoryService() {
    MenuRepository menuRepository = new MenuRepository();
    OrderRepository orderRepository = new OrderRepository();
    TableRepository tableRepository = new TableRepository();
    new Thread(new servingStaffservice()).start();
}


}
