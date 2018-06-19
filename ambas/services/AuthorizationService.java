package com.ambas.services;

import java.sql.SQLException;

import com.ambas.dao.LoginDAO;

public class AuthorizationService {
	LoginDAO logindao = new LoginDAO();
	
	public boolean isUserAuthorized(String username, String password, String requiredType) throws ClassNotFoundException, SQLException {
		if (requiredType.equals(logindao.sendType(username, password))) {
			return true;
		} else {
			return false;
		}
	}
	
}
