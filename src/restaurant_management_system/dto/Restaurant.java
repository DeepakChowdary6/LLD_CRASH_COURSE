package restaurant_management_system.dto;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {

    private static BlockingQueue<Order> readyQueue=new LinkedBlockingQueue<>();







    public static BlockingQueue<Order> getReadyQueue() {
        return readyQueue;
    }
}
