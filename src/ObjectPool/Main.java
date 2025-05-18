package ObjectPool;

public class Main {

    public static void main(String[] args) {
        DBconnectionPoolManager connectionPool=DBconnectionPoolManager.getInstance();
        DBconnection dBconnection1=connectionPool.getConnection();
        DBconnection dBconnection2=connectionPool.getConnection();
        connectionPool.releaseConnection(dBconnection1);
    }

}
