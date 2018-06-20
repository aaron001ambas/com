package com.ambas.services;

import java.sql.SQLException;

import com.ambas.dao.ChangePasswordDAO;

public class ChangePasswordService {

	public void isPasswordChanged(String username, String desiredPassword) throws ClassNotFoundException, SQLException {
		ChangePasswordDAO changepassdao = new ChangePasswordDAO();
		changepassdao.updatePassword(username, desiredPassword);
	}
	
}
