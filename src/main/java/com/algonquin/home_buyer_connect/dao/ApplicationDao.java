package com.algonquin.home_buyer_connect.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.algonquin.home_buyer_connect.beans.Account;
import com.algonquin.home_buyer_connect.services.ApplicationService;

public class ApplicationDao implements ApplicationService {

    public ApplicationDao() {}

    @Override
    public boolean createAccount(Account account) {
    	
    	int rowsAffected = 0;
    	
    	try {
    		
    		Connection connection = DBConnection.getConnectionToDatabase();
    		
    		String createAccountQuery = "insert into accounts (id, firstName, lastName, email, password) values(?, ?, ?, ?, ?)";
    		
    		java.sql.PreparedStatement statement = connection.prepareStatement(createAccountQuery);
    		
    		statement.setString(1, account.getId());
    		
    		statement.setString(2, account.getFirstName());
    		
    		statement.setString(3, account.getLastName());

    		statement.setString(4, account.getEmail());
    		
    		statement.setString(5, account.getPassword());
    		
    		rowsAffected = statement.executeUpdate();
    		
    	} catch (SQLException exception) {
    		
    		exception.printStackTrace();
    	}
    	
    	if (rowsAffected == 0) {
    		
    		return false;
    	}
    	
    	return true;
    }

	@Override
	public ResultSet signInAccount(String email, String password) {

    	ResultSet results = null;
    	
    	try {
    		
    		Connection connection = DBConnection.getConnectionToDatabase();
    		
    		String signInAccountQuery = "select * from accounts where email = \"" + email + "\" and password = \"" + password + "\" limit 1";
    		
    		Statement statement = connection.createStatement();
    		
    		results = statement.executeQuery(signInAccountQuery);
    		
    	} catch (SQLException exception) {
    		
    		exception.printStackTrace();
    	}
    	
    	return results;
	}
}