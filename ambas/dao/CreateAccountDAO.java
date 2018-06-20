package com.ambas.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateAccountDAO{

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
	
	public void insertAccount(String username, String type) {
		try {
			connect = connectDB();
			String query = "INSERT INTO users (username, password, type) VALUES (?, 12345, ?);";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, type);
			preparedStmt.executeUpdate();
			System.out.println("insert success");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("insert failed");
			System.err.println(e);
		}
	}
	
}
