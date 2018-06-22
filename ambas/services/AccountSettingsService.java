package com.ambas.services;

import java.sql.SQLException;

import com.ambas.dao.AccountDAO;

public class AccountSettingsService {
	
	public void changePassword(String username, String desiredPassword) throws ClassNotFoundException, SQLException {
		AccountDAO changePassDao = new AccountDAO();
		changePassDao.updatePassword(username, desiredPassword);
	}
	
}
