package restaurant_management_system.repository;

import restaurant_management_system.dto.Table;

import java.util.HashMap;

public class TableRepository {
    public static HashMap<Integer, Table>tableHashMap;
    public TableRepository(){
     tableHashMap=new HashMap<>();
    }
    public static void defineTables(int no_of_Tables){
        for(int i=1;i<=no_of_Tables;i++){
            Table table=new Table(i);
            tableHashMap.put(i,table);
        }
    }
    public static HashMap<Integer, Table> getTableHashMap() {
        return tableHashMap;
    }
}
