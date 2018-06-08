package com.ambas.services;

import java.sql.SQLException;

import com.ambas.controllers.LoginController;
import com.ambas.dao.LoginDAO;

public class LoginService {

	LoginDAO loginDao = new LoginDAO();
	private String badMsg = "[ERROR] Bad login.";
	
	
	public boolean areCredentialsValid(String username, 
			String password) throws ClassNotFoundException, SQLException {
		if (isEmpty(username, password)) {
			System.out.println(badMsg);
			return false;
		} else if (loginDao.isUserFound(username, password)) {
			System.out.println("[CONSOLE] User "
								+ username + " is logged in.");
			return true;
		} else {
			System.out.println(badMsg);
			return false;
		}
	}
	
	public boolean isEmpty(String username, String password) {
		if (0 >= username.length()  && 0 >= password.length()) {
			return true; 
		} else {
			return false;
		}
	}
}
