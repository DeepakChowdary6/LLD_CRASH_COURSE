package restaurant_management_system.service;

import restaurant_management_system.dto.Category;
import restaurant_management_system.dto.MenuItem;
import restaurant_management_system.dto.Table;
import restaurant_management_system.repository.MenuRepository;
import restaurant_management_system.repository.TableRepository;

public class AdminService {
    //add items in menu
    //define no of tables in restaurant
    //crud for tables

    //(double price, String name, boolean veg, Category category)
    public void addItemstoMenu(double price, String name, boolean veg, Category category){
        MenuItem item=new MenuItem(price,name,veg,category);
        MenuRepository.getMenuItemHashMap().put(item.getId(), item);

    }
    public void defineTables(int no_of_tables){
        TableRepository.defineTables(no_of_tables);
        System.out.println(no_of_tables+" defined in the restaurant");
    }
    public void addTable(int table_no){
        Table table=new Table(table_no);
        TableRepository.getTableHashMap().put(table_no,table);

    }
    public void removeTable(int table_id){
        TableRepository.getTableHashMap().remove(table_id);
    }


}
