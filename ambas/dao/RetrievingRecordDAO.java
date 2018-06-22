package com.ambas.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ambas.domain.Record;
import com.ambas.domain.User;

public class RetrievingRecordDAO {
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
	
	public List<Record> retrieveRecord(String recordid) throws ClassNotFoundException, SQLException {
		connect = connectDB();
		String query = "SELECT firstname, lastname, nameOfResource, serialNumber, JRSS, band, account, pmpseat, seatjrss, openSeatDesc, reqSkills, requestedband, dateOfrejection, reasonForReject, detailedActionPlan, targetDate, status, recordid FROM records WHERE recordid=?;";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setString(1, recordid);
		resultSet = preparedStmt.executeQuery();
		List<Record> records = new ArrayList<Record>();
		resultSet.next();
		Record record = new Record();
		record.setFirstname(resultSet.getString(1));
		record.setLastname(resultSet.getString(2));
		record.setNameOfResource(resultSet.getString(3));
		record.setSerialNumber(resultSet.getString(4));
		record.setJRSS(resultSet.getString(5));
		record.setBand(resultSet.getString(6));
		record.setAccount(resultSet.getString(7));
		record.setPmpseat(resultSet.getString(8));
		record.setSeatjrss(resultSet.getString(9));
		record.setOpenSeatDesc(resultSet.getString(10));
		record.setReqSkills(resultSet.getString(11));
		record.setRequestedband(resultSet.getString(12));
		record.setDateOfrejection(resultSet.getString(13));
		record.setReasonForReject(resultSet.getString(14));
		record.setDetailedActionPlan(resultSet.getString(15));
		record.setTargetDate(resultSet.getString(16));
		record.setStatus(resultSet.getString(17));
		record.setRecordid(resultSet.getString(18));
		records.add(record);
		return records;
	}

}
