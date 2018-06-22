package com.ambas.services;

import java.sql.SQLException;

import com.ambas.dao.RecordDAO;

public class CreateRecordService {

	public void createRecord(
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
			) throws ClassNotFoundException, SQLException {
		RecordDAO createrecord = new RecordDAO();
		createrecord.insertRecord(firstname, lastname, nameOfResource, serialNumber, JRSS, band, account, pmpseat, seatjrss, openSeatDesc, reqSkills, requestedband, dateOfrejection, reasonForReject, detailedActionPlan, targetDate, status);
	}
	
}
