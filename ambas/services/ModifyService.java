package com.ambas.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ambas.dao.DeleteAccountDAO;
import com.ambas.dao.DeleteRecordDAO;
import com.ambas.dao.RetrievingRecordDAO;
import com.ambas.dao.RetrievingUserInfoDAO;
import com.ambas.dao.UpdatingAccountTypeDAO;
import com.ambas.dao.UpdatingRecordDAO;
import com.ambas.domain.Record;
import com.ambas.domain.User;

public class ModifyService {

	public List<User> retrieveInfoToBeModified(String username) throws ClassNotFoundException, SQLException, IOException {
		RetrievingUserInfoDAO retrieve = new RetrievingUserInfoDAO();
		return retrieve.retrieveUser(username);
	}
	
	public void updateAccountType(String username, String desiredType) throws ClassNotFoundException, SQLException, IOException {
		UpdatingAccountTypeDAO update = new UpdatingAccountTypeDAO();
		update.updateAccountType(username, desiredType);
	}
	
	public void deleteAccount(String username) throws ClassNotFoundException, SQLException, IOException {
		DeleteAccountDAO delete = new DeleteAccountDAO();
		delete.deleteAccount(username);
	}
	
	public List<Record> retrieveRecordToBeModified(String recordid) throws ClassNotFoundException, SQLException {
		RetrievingRecordDAO retrieve = new RetrievingRecordDAO();
		return retrieve.retrieveRecord(recordid);
	}
	
	public void deleteRecord(String recordid) throws ClassNotFoundException, SQLException, IOException {
		DeleteRecordDAO delete = new DeleteRecordDAO();
		delete.deleteRecord(recordid);
	}
	public void modifyRecord(String firstname, String lastname, String nameOfResource, String serialNumber, String JRSS, String band, String account, String pmpseat, String seatjrss, String openSeatDesc, String reqSkills, String requestedband, String dateOfrejection, String reasonForReject, String detailedActionPlan, String targetDate, String status, String recordid) throws ClassNotFoundException, SQLException, IOException {
		UpdatingRecordDAO update = new UpdatingRecordDAO();
		update.updateRecord(firstname, lastname, nameOfResource, serialNumber, JRSS, band, account, pmpseat, seatjrss, openSeatDesc, reqSkills, requestedband, dateOfrejection, reasonForReject, detailedActionPlan, targetDate, status, recordid);
	}
	
}
