package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static Connection getConnection() throws SQLException{
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://"+
                    "localhost\\SQLExpress:1433;databaseName=Project;"+
                    "user=Marcinez;password=2137;");
            System.out.println("Połączono z bazą danych");
            return connection;
        }
    }
