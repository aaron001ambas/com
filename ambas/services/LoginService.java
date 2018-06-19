package com.ambas.services;

import java.sql.SQLException;

import com.ambas.controllers.LoginController;
import com.ambas.dao.LoginDAO;

public class LoginService {

	LoginDAO loginDao = new LoginDAO();
	
	public boolean areCredentialsValid(String username, String password) throws ClassNotFoundException, SQLException {
		if (isEmpty(username, password)) {
			return false;
		} else if (loginDao.credentialsMatched(username, password)) {
					return true;
		} else {
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
