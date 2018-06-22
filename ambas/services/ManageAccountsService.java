package com.ambas.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ambas.dao.AccountDAO;
import com.ambas.domain.User;

public class ManageAccountsService {
	
	public void updateAccountType(String username, String desiredType) throws ClassNotFoundException, SQLException, IOException {
		AccountDAO updateTypeDao = new AccountDAO();
		updateTypeDao.updateAccountType(username, desiredType);
	}
	
	public void deleteAccount(String username) throws ClassNotFoundException, SQLException, IOException {
		AccountDAO delete = new AccountDAO();
		delete.deleteAccount(username);
	}
	
	public List<User> retrieveInfoToBeModified(String username) throws ClassNotFoundException, SQLException, IOException {
		AccountDAO retrieve = new AccountDAO();
		return retrieve.retrieveUser(username);
	}
	
	public List<User> listAccounts() throws ClassNotFoundException, SQLException, IOException {
		AccountDAO listaccounts = new AccountDAO();
		return listaccounts.listAccounts();
	}
}
