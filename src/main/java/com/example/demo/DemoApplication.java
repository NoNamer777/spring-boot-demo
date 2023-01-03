package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
    public static Connection getConnection() throws SQLException {
        if (databaseConnection != null) return databaseConnection;
        
        databaseConnection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/demodb",
            "devuser",
            "Password1234!"
        );
        
        return databaseConnection;
    }
    
    private static Connection databaseConnection;
}
