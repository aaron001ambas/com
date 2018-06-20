package com.ambas.services;

import java.sql.SQLException;

import com.ambas.dao.CreateAccountDAO;

public class CreateAccountService {

	public boolean createAccount(String username, String type) throws ClassNotFoundException, SQLException {
		CreateAccountDAO createAccount = new CreateAccountDAO();
		if (createAccount.attemptInsertAccount(username, type)) {
			return true;
		}
		return false;
	}
}