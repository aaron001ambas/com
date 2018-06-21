package com.ambas.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ambas.domain.User;

public class RetrievingUserInfoDAO {
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
	
	public List<User> retrieveUser(String username) throws ClassNotFoundException, SQLException, IOException {
		connect = connectDB();
		String query = "SELECT username, type FROM users WHERE username=?;";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setString(1, username);
		resultSet = preparedStmt.executeQuery();
		List<User> users = new ArrayList<User>();
		resultSet.next();
		User user = new User();
		user.setUsername(resultSet.getString(1));
		user.setType(resultSet.getString(2));
		users.add(user);
		return users;
	}
}
