package com.ambas.domain;

import java.sql.SQLException;

import com.ambas.dao.LoginDAO;

public class User {

	private String username;
	private String type;
	private String password;
	
	public String getUsername(String username, String password) throws ClassNotFoundException, SQLException {
		LoginDAO logindao = new LoginDAO();
		return logindao.sendUsername(username, password);
	}
	
	public String getType(String username, String password) throws ClassNotFoundException, SQLException {
		LoginDAO logindao = new LoginDAO();
		return logindao.sendType(username, password);
	}
	
	public String getPassword(String username, String password) throws ClassNotFoundException, SQLException {
		LoginDAO logindao = new LoginDAO();
		return logindao.sendPassword(username, password);
	}
	
}
