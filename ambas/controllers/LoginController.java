package com.ambas.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ambas.domain.User;
import com.ambas.services.LoginService;

public class LoginController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			LoginService loginService = new LoginService();
			if (loginService.areCredentialsValid(username, password)) {
				grantAccess(request, response, username, password);
			} else {
				denyAccess(request, response, username, password);
			}
		} catch (ClassNotFoundException | SQLException e) {
			denyAccess(request, response, username, password);
		}
	}
	
	private void grantAccess(HttpServletRequest request, HttpServletResponse response, String username, String password) throws ClassNotFoundException, SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		LoginService loginService = new LoginService();
		session.setAttribute("username", loginService.retrieveUsername(username));
		session.setAttribute("password", password);
		session.setAttribute("type", loginService.retrieveType(username));
		request.getRequestDispatcher("/main.jsp").forward(request, response);
		System.out.println("[" + request.getRemoteAddr() + "] User " + loginService.retrieveUsername(username) + " has logged in.");
	}
	
	private void denyAccess(HttpServletRequest request, HttpServletResponse response, String username, String password) throws ServletException, IOException {
		request.setAttribute("notification", "Invalid username and/or password");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		System.out.println("[" + request.getRemoteAddr() + "] Attempted to login with the credentials (" + username + "," + password + ")");
	}
	
}