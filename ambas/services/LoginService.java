package com.ambas.services;

import java.sql.SQLException;
import com.ambas.dao.LoginDAO;

public class LoginService {
	
	public boolean areCredentialsValid(String username, String password) throws ClassNotFoundException, SQLException {
		if (!isEmpty(username, password)) {
			LoginDAO loginDao = new LoginDAO();
			if (loginDao.credentialsMatched(username, password)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isEmpty(String username, String password) {
		if (0 >= username.length()  && 0 >= password.length()) {
			return true; 
		} else {
			return false;
		}
	}
	
	public boolean isLoggedIn(String username, String password) throws ClassNotFoundException, SQLException {
		AuthorizationService loggedin = new AuthorizationService();
		if (loggedin.isLoggedIn(username, password)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String retrieveType(String username) throws ClassNotFoundException, SQLException {
		LoginDAO logindao = new LoginDAO();
		return logindao.sendType(username);
	}

	public String retrieveUsername(String username) throws ClassNotFoundException, SQLException {
		LoginDAO logindao = new LoginDAO();
		return logindao.sendUsername(username);
	}
	
}
