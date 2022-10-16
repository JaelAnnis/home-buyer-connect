package com.algonquin.home_buyer_connect.services;

import java.sql.ResultSet;
import com.algonquin.home_buyer_connect.beans.Account;

public interface ApplicationService {

	public boolean createAccount(Account account);
	public ResultSet signInAccount(String email, String password);
}