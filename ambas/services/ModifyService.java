package com.ambas.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ambas.dao.DeleteAccountDAO;
import com.ambas.dao.RetrievingRecordDAO;
import com.ambas.dao.RetrievingUserInfoDAO;
import com.ambas.dao.UpdatingAccountTypeDAO;
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
		delete.deleteRecord(username);
	}
	
	public List<Record> retrieveRecordToBeModified(String recordid) throws ClassNotFoundException, SQLException {
		RetrievingRecordDAO retrieve = new RetrievingRecordDAO();
		return retrieve.retrieveRecord(recordid);
	}
	
}
