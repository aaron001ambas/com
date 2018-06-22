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

public class AccountDAO {

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
	
	public void updatePassword(String username, String desiredPassword){
		try {
			connect = connectDB();
			String query = "UPDATE users SET password=? WHERE username=?";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, desiredPassword);
			preparedStmt.setString(2, username);
			preparedStmt.executeUpdate();
		}catch (ClassNotFoundException | SQLException e){
			e.printStackTrace();
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
	
	public void updateAccountType(String username, String type){
		try {
			connect = connectDB();
			String query = "UPDATE users SET type=? WHERE username=?";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, type);
			preparedStmt.setString(2, username);
			preparedStmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public boolean attemptInsertAccount(String username, String type) throws ClassNotFoundException, SQLException {
		try {
			if (!doesUsernameAlreadyExist(username)) {
	
					connect = connectDB();
					String query = "INSERT INTO users (username, password, type, newuser) VALUES (?, 12345, ?, 1);";
					preparedStmt = connect.prepareStatement(query);
					preparedStmt.setString(1, username);
					preparedStmt.setString(2, type);
					preparedStmt.executeUpdate();
					return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
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
	
	public boolean doesUsernameAlreadyExist(String username) {
		try {
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
	
	public void deleteAccount(String username) {
		try {
			connect = connectDB();
			String query = "DELETE FROM users WHERE username=?";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, username);
			preparedStmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public List<User> listAccounts(){
		List<User> users = new ArrayList<User>();
		try {
			connect = connectDB();
			String query = "SELECT username, type FROM users;";
			preparedStmt = connect.prepareStatement(query);
			resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setUsername(resultSet.getString(1));
				user.setType(resultSet.getString(2));
				users.add(user);
			}
			return users;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return users;
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
	
	public List<User> retrieveUser(String username){
		List<User> users = new ArrayList<User>();
		try {
			connect = connectDB();
			String query = "SELECT username, type FROM users WHERE username=?;";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, username);
			resultSet = preparedStmt.executeQuery();
			resultSet.next();
			User user = new User();
			user.setUsername(resultSet.getString(1));
			user.setType(resultSet.getString(2));
			users.add(user);
			return users;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return users;
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
