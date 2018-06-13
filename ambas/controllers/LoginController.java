package com.ambas.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ambas.services.LoginService;

public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	LoginService loginService = new LoginService();

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		try {
			if (loginService.areCredentialsValid(request.getParameter("username"), 
					request.getParameter("password"))) {
				response.sendRedirect("main.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("[ERROR] Bad input.");
		}
	}
}
