package DBConnecter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnecter {
    private String url = "jdbc:sqlite:course.db";
    private Connection connection;

    public void connect(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            if (connection != null){
                System.out.println("Connect !");
            }else{
                System.out.println("Not found !");
            }
        }catch (ClassNotFoundException | SQLException e){
            System.err.println("Failure !");
        }
    }
}
