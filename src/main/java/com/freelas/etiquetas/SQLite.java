
package com.freelas.etiquetas;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite {
    

    private static String url = "jdbc:sqlite:resources\\banco_etiquetas.db";
    public static Connection connection;

    public Connection getConnection() throws SQLException{
    
        if(SQLite.connection == null){
            SQLite.connection = DriverManager.getConnection(url);
            return SQLite.connection;
        }else{
            return SQLite.connection;
        
        }
        
    }

}
