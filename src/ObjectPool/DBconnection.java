package ObjectPool;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
    Connection mysqlConnection;
    public DBconnection(){
        try {
            mysqlConnection= DriverManager.getConnection("url","username","passowrd");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
