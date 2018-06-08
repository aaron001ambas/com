package com.ambas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	
	private String dbUser = "root";
	private String dbPassword = "root";
	private String dbUrl = "jdbc:mysql://localhost/mydb";
	private Connection connect = null;
	private PreparedStatement preparedStmt = null;
	private ResultSet resultSet = null;
	
	public Connection connectDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
		return connect;
	}
	
	public boolean isUserFound(String username, String password) throws ClassNotFoundException, SQLException {
		connect = connectDB();
		String query = "SELECT * FROM accounts WHERE username=? and  password=?;";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setString(1, username);
		preparedStmt.setString(2, password);
		resultSet = preparedStmt.executeQuery();
		System.out.println("username is " + username + " and password is " + password);
		if(resultSet.next() == true) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
}
