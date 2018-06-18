package com.ambas.services;

import java.sql.SQLException;

import com.ambas.controllers.LoginController;
import com.ambas.dao.LoginDAO;

public class LoginService {

	private String badMsg = "[ERROR] Bad login.";
	LoginDAO loginDao = new LoginDAO();
	
	public boolean areCredentialsValid(String username, String password) throws ClassNotFoundException, SQLException {
		if (isEmpty(username, password)) {
			System.out.println(badMsg);
			return false;
		} else if (loginDao.credentialsMatched(username, password)) {
//					System.out.println("[CONSOLE] User " + getUsername(username, password) + " has logged in.");
					return true;
		} else {
			System.out.println(badMsg);
			return false;
		}
	}
	
	public String getUsername(String username, String password) throws ClassNotFoundException, SQLException {
		LoginDAO logindao = new LoginDAO();
		return logindao.sendUsername(username, password);
	}
	
	public String getType(String username, String password) throws ClassNotFoundException, SQLException {
		LoginDAO logindao = new LoginDAO();
		return logindao.sendType(username, password);
	}
	
	public boolean isEmpty(String username, String password) {
		if (0 >= username.length()  && 0 >= password.length()) {
			return true; 
		} else {
			return false;
		}
	}
}
