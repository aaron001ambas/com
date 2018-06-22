package com.ambas.services;

import java.sql.SQLException;

import com.ambas.dao.AccountDAO;

public class CreateAccountService {

	public boolean createAccount(String username, String type) throws ClassNotFoundException, SQLException {
		AccountDAO createAccount = new AccountDAO();
		if (createAccount.attemptInsertAccount(username, type)) {
			return true;
		}
		return false;
	}
}