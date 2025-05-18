package ObjectPool;

import java.util.ArrayList;
import java.util.List;

public class DBconnectionPoolManager {
    private  final int MAXPOOL_SIZE=6;
    private final int INITIAL_POOL_SIZE=3;
    private List<DBconnection>freeConnections;
    private List<DBconnection>usedConnections;
   private static DBconnectionPoolManager dbConnectionPoolManagerInstance=null;
   private DBconnectionPoolManager(){
       freeConnections=new ArrayList<>();
       usedConnections=new ArrayList<>();
         for(int i=0;i<INITIAL_POOL_SIZE;i++){
             freeConnections.add(new DBconnection());
         }
     }
     public static DBconnectionPoolManager getInstance(){
      if(dbConnectionPoolManagerInstance==null) {

          synchronized (DBconnectionPoolManager.class){

              if(dbConnectionPoolManagerInstance==null){
              dbConnectionPoolManagerInstance = new DBconnectionPoolManager();
              }
          }

      }
       return dbConnectionPoolManagerInstance;
     }

     public synchronized DBconnection getConnection(){
            if(freeConnections.isEmpty() && usedConnections.size()<MAXPOOL_SIZE){ //check if there are no free connections
                //still less than max_pool size
                freeConnections.add(new DBconnection());
            }else if(freeConnections.isEmpty() && usedConnections.size()>=MAXPOOL_SIZE){
                //if exceeded return
                return null;
            }
            DBconnection dBconnection=freeConnections.remove(freeConnections.size()-1);
            usedConnections.add(dBconnection);
            return dBconnection;
     }

     public synchronized void releaseConnection(DBconnection dBconnection){
          if(dBconnection!=null){
              usedConnections.remove(dBconnection);
              freeConnections.add(dBconnection);
          }
     }


}
