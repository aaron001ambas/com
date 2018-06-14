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
	
	public boolean isCredentialsMatched(String username, String password) throws ClassNotFoundException, SQLException {
		if (hasResult(username, password)) {
			connect = connectDB();
			String query = "SELECT * FROM users WHERE username=? and  password=?;";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			resultSet = preparedStmt.executeQuery();
			resultSet.next();
			if (username.equalsIgnoreCase(resultSet.getString("username"))
					&& password.equals(resultSet.getString("password"))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasResult(String username, String password) throws ClassNotFoundException, SQLException {
		connect = connectDB();
		String query = "SELECT count(*) as NumRow FROM users WHERE username=? and  password=?;";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setString(1, username);
		preparedStmt.setString(2, password);
		resultSet = preparedStmt.executeQuery();
		resultSet.next();
		if (0 < resultSet.getInt("NumRow")) {
			return true;
		} else {
			return false;
		}
	}
}
