package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class connector {
    public Connection databaselink;

    public Connection getConnection(){
        String databaseName = "hotelmanagement";
        String databaseUser = "root";
        String databasePassword = "root";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaselink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return databaselink;
    }
}
