package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

    private static final String URl = "jdbc:mysql://localhost:3306/hexaware";
    private static final String USER = "root";
    private static final String PASS = "MSmysql@1";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URl,USER,PASS);
        }catch (SQLException e){
            System.out.println("DBConnection Failed" + e.getMessage());
        }
        return null;
    }
}
