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

public class RecordDAO {
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
	
	public void insertRecord (
			String firstname,
			String lastname,
			String nameOfResource,
			String serialNumber,
			String JRSS,
			String band,
			String account,
			String pmpseat,
			String seatjrss,
			String openSeatDesc,
			String reqSkills,
			String requestedband,
			String dateOfrejection,
			String reasonForReject,
			String detailedActionPlan,
			String targetDate,
			String status
			) {
		try {
			connect = connectDB();
			String query = "insert into `mydb`.`records` (firstname, lastname, nameOfResource, serialNumber, JRSS, band, account, pmpseat, seatjrss, openSeatDesc, reqSkills, requestedband, dateOfrejection, reasonForReject, detailedActionPlan, targetDate, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			preparedStmt.setString(12, requestedband);
			preparedStmt.setString(13, dateOfrejection);
			preparedStmt.setString(14, reasonForReject);
			preparedStmt.setString(15, detailedActionPlan);
			preparedStmt.setString(16, targetDate);
			preparedStmt.setString(17, status);
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
	
	public void deleteRecord(String recordid) {
		try {
			connect = connectDB();
			String query = "DELETE FROM records WHERE recordid=?";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, recordid);
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
	
	public List<Record> listRecords() {
		List<Record> records = new ArrayList<Record>();
		try {
			connect = connectDB();
			String query = "select firstname, lastname, nameOfResource, serialNumber, JRSS, band, account, pmpseat, seatjrss, openSeatDesc, reqSkills, requestedband, dateOfrejection, reasonForReject, detailedActionPlan, targetDate, status, recordid from `mydb`.`records`";
			preparedStmt = connect.prepareStatement(query);
			resultSet = preparedStmt.executeQuery();
			

			while (resultSet.next()) {
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
			}
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
		return records;
	}
	
	public List<Record> retrieveRecord(String recordid) {
		List<Record> records = new ArrayList<Record>();
		
		try {
			connect = connectDB();
			String query = "SELECT firstname, lastname, nameOfResource, serialNumber, JRSS, band, account, pmpseat, seatjrss, openSeatDesc, reqSkills, requestedband, dateOfrejection, reasonForReject, detailedActionPlan, targetDate, status, recordid FROM records WHERE recordid=?;";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, recordid);
			resultSet = preparedStmt.executeQuery();
			

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
		return records;
	}
	
	public void updateRecord(String firstname, String lastname, String nameOfResource, String serialNumber, String JRSS, String band, String account, String pmpseat, String seatjrss, String openSeatDesc, String reqSkills, String requestedBand, String dateOfRejection, String reasonForRejection, String detailedActionPlan, String targetDate, String status, String recordid) {
		try {
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
}
