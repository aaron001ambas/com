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
	
	public boolean credentialsMatched(String username, String password) {
		try {
			connect = connectDB();
			String query = "SELECT count(*) as NumRow FROM users WHERE username=? and password=?;";
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
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			if(connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public String sendUsername(String username) {
		try {
			connect = connectDB();
			String query = "SELECT * FROM users WHERE username=?";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, username);
			resultSet = preparedStmt.executeQuery();
			resultSet.next();
			return resultSet.getString("username");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR COULD NOT FETCH USERNAME";
		} finally {
			if(connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	
	public String sendType(String username) {
		try {
			connect = connectDB();
			String query = "SELECT * FROM users WHERE username=?;";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, username);
			resultSet = preparedStmt.executeQuery();
			resultSet.next();
			return resultSet.getString("type");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR COULD NOT FETCH TYPE";
		} finally {
			if(connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
