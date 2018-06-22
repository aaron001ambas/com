package com.ambas.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteRecordDAO {

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
	
	public void deleteRecord(String recordid) throws ClassNotFoundException, SQLException, IOException {
		connect = connectDB();
		String query = "DELETE FROM records WHERE recordid=?";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setString(1, recordid);
		preparedStmt.executeUpdate();
	}
	
}