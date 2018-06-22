package com.ambas.services;

import java.sql.SQLException;

import com.ambas.dao.LoginDAO;

public class AuthorizationService {
	
	public boolean isUserAuthorized(String username, String requiredType) throws ClassNotFoundException, SQLException {
		LoginDAO logindao = new LoginDAO();
		if (requiredType.equals(logindao.sendType(username))) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isLoggedIn(String username, String password) throws ClassNotFoundException, SQLException {
		LoginDAO loginDao = new LoginDAO();
		if (loginDao.credentialsMatched(username, password)) {
			return true;
		} else {
			return false;
		}
	}
	
}
