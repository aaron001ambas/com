package com.ambas.services;

import com.ambas.dao.CreateAccountDAO;

public class CreateAccountService {

	public void createAccount(String username, String type) {
		if (isEmpty(username, type)) {
			System.out.println("Fail, empty fields");
		} else {
			CreateAccountDAO createAccount = new CreateAccountDAO();
			createAccount.insertAccount(username, type);
		}
	}
	
	public boolean isEmpty(String username, String type) {
		if (0 >= username.length()  && 0 >= type.length()) {
			return true; 
		} else {
			return false;
		}
	}
	
}
