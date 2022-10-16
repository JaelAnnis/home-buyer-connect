package com.algonquin.home_buyer_connect.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String dbUser = "root";
    private static final String dbPassword = "Podledoo1!elantra";

    public static Connection getConnectionToDatabase() {
    	
        Connection connection = null;

        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	System.out.println("MySQL JBDC Driver Registered!");
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hbc", DBConnection.dbUser, DBConnection.dbPassword);
        	
        } catch (ClassNotFoundException e) {
        	
        	System.out.println("Where is your MySQL JDBC Driver?");
        	e.printStackTrace();
        	
        }
        
        catch (SQLException e) {
        	
        	System.out.println("Connection Failed! Check Output Console");
        	e.printStackTrace();
        }
        
        if (connection != null) {
        	
        	System.out.println("Connection made to DB!");
        }
        
        return connection;
    }
}