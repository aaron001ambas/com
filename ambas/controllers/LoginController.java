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
		LoginService loginservice = new LoginService();
		try {
			if (loginService.areCredentialsValid(request.getParameter("username"), 
					request.getParameter("password"))) {
				// Login Success
				request.setAttribute("username", loginservice.getUsername(request.getParameter("username"), request.getParameter("password")));
				request.setAttribute("type", loginservice.getType(request.getParameter("username"), request.getParameter("password")));
				request.getRequestDispatcher("/main.jsp").forward(request, response);
				System.out.println("[" + request.getRemoteAddr() + "] User " + loginservice.getUsername(request.getParameter("username"), request.getParameter("password")) + " has logged in.");
			} else {
				// Login Failed
				request.setAttribute("notification", "Invalid username and/or password");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Login Failed, Error
			request.setAttribute("notification", "Invalid username and/or password");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}