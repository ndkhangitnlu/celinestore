package com.fit.nlu.CelineShop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static Connection getJDBCConnection() {
        String db = "celinedatabase";
        String host = "celinedatabase.mysql.database.azure.com:3306";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "kangitnlu";
            String pass = "Khang3301@";
            String url = "jdbc:mysql://" + host + "/" + db;
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("thanh cong");


        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        return connection;
    }
}