package restaurant_management_system.repository;

import restaurant_management_system.dto.MenuItem;

import java.util.HashMap;

public class MenuRepository {
    public  static HashMap<Integer, MenuItem>menuItemHashMap;
    public  MenuRepository(){
        menuItemHashMap=new HashMap<>();
    }

    public static HashMap<Integer, MenuItem> getMenuItemHashMap() {
        return menuItemHashMap;
    }

}
