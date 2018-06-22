package com.ambas.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatingRecordDAO {

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
	
	public void updateRecord(String firstname, String lastname, String nameOfResource, String serialNumber, String JRSS, String band, String account, String pmpseat, String seatjrss, String openSeatDesc, String reqSkills, String requestedBand, String dateOfRejection, String reasonForRejection, String detailedActionPlan, String targetDate, String status, String recordid) throws ClassNotFoundException, SQLException, IOException {
		connect = connectDB();
		String query = "UPDATE records SET firstname=?, lastname=?, nameOfResource=?, serialNumber=?, JRSS=?, band=?, account=?, pmpseat=?, seatjrss=?, openSeatDesc=?, reqSkills=?, requestedband=?, dateOfrejection=?, reasonForReject=?, detailedActionPlan=?, targetDate=?, status=? WHERE recordid=?";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setString(1, firstname);
		preparedStmt.setString(2, lastname);
		preparedStmt.setString(3, nameOfResource);
		preparedStmt.setString(4, serialNumber);
		preparedStmt.setString(5, JRSS);
		preparedStmt.setString(6, band);
		preparedStmt.setString(7, account);
		preparedStmt.setString(8, pmpseat);
		preparedStmt.setString(9, seatjrss);
		preparedStmt.setString(10, openSeatDesc);
		preparedStmt.setString(11, reqSkills);
		preparedStmt.setString(12, requestedBand);
		preparedStmt.setString(13, dateOfRejection);
		preparedStmt.setString(14, reasonForRejection);
		preparedStmt.setString(15, detailedActionPlan);
		preparedStmt.setString(16, targetDate);
		preparedStmt.setString(17, status);
		preparedStmt.setString(18, recordid);
		preparedStmt.executeUpdate();
	}
	
}
