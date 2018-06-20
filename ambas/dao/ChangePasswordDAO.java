package com.ambas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePasswordDAO {

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
	
	public void updatePassword(String username, String desiredPassword) throws ClassNotFoundException, SQLException {
		connect = connectDB();
		String query = "UPDATE users SET password=? WHERE username=?";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setString(1, desiredPassword);
		preparedStmt.setString(2, username);
		preparedStmt.executeUpdate();
	}
	
}
