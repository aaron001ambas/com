package com.ambas.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ambas.dao.RecordDAO;
import com.ambas.domain.Record;

public class ManageRecordsService {
	
	public void deleteRecord(String recordid) throws ClassNotFoundException, SQLException, IOException {
		RecordDAO delete = new RecordDAO();
		delete.deleteRecord(recordid);
	}
	public void modifyRecord(String firstname, String lastname, String nameOfResource, String serialNumber, String JRSS, String band, String account, String pmpseat, String seatjrss, String openSeatDesc, String reqSkills, String requestedband, String dateOfrejection, String reasonForReject, String detailedActionPlan, String targetDate, String status, String recordid) throws ClassNotFoundException, SQLException, IOException {
		RecordDAO update = new RecordDAO();
		update.updateRecord(firstname, lastname, nameOfResource, serialNumber, JRSS, band, account, pmpseat, seatjrss, openSeatDesc, reqSkills, requestedband, dateOfrejection, reasonForReject, detailedActionPlan, targetDate, status, recordid);
	}
	
	public List<Record> retrieveRecordToBeModified(String recordid) throws ClassNotFoundException, SQLException {
		RecordDAO retrieve = new RecordDAO();
		return retrieve.retrieveRecord(recordid);
	}
	
	public List<Record> listRecords() throws ClassNotFoundException, SQLException, IOException {
		RecordDAO list = new RecordDAO();
		return list.listRecords();
	}
}
