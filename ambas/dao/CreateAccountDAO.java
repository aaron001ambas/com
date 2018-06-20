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
	
	public boolean attemptInsertAccount(String username, String type) throws ClassNotFoundException, SQLException {
		if (!doesUsernameAlreadyExist(username)) {
			try {
				connect = connectDB();
				String query = "INSERT INTO users (username, password, type, newuser) VALUES (?, 12345, ?, 1);";
				preparedStmt = connect.prepareStatement(query);
				preparedStmt.setString(1, username);
				preparedStmt.setString(2, type);
				preparedStmt.executeUpdate();
			} catch (ClassNotFoundException | SQLException e) {
				System.err.println(e);
			}
			return true;
		} else {
			return false;
		}
	}
	
	public boolean doesUsernameAlreadyExist(String username) throws ClassNotFoundException, SQLException {
		connect = connectDB();
		String query = "SELECT count(*) as NumRow from USERS Where username=?";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setString(1, username);
		resultSet = preparedStmt.executeQuery();
		resultSet.next();
		if (0 < resultSet.getInt("NumRow")) {
			return true;
		} else {
			return false;
		}
	}
	
}
