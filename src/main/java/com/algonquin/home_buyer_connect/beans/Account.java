package com.algonquin.home_buyer_connect.beans;

import java.util.UUID;

public class Account {
	
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    
	public Account(String firstName, String lastName, String email, String password) {
		
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
    
	public String getId() {
		
		return id.toString();
	}
	
	public void setId(UUID id) {
		
		this.id = id;
	}
	
	public String getFirstName() {
		
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		
		this.firstName = firstName;
	}
	
	public String getLastName() {
		
		return lastName;
	}
	
	public void setLastname(String lastName) {
		
		this.lastName = lastName;
	}
	
	public String getEmail() {
		
		return email;
	}
	
	public void setEmail(String email) {
		
		this.email = email;
	}
	
	public String getPassword() {
		
		return password;
	}
	
	public void setPassword(String password) {
		
		this.password = password;
	}
}