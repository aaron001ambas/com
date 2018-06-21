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
	
	private static final long serialVersionUID = 1L;
	LoginService loginService = new LoginService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		try {
			if (loginService.areCredentialsValid(request.getParameter("username"), 
					request.getParameter("password"))) {
				// Login Success
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getUsername(request.getParameter("username"), request.getParameter("password")));
				session.setAttribute("password", user.getPassword(request.getParameter("username"), request.getParameter("password")));
				session.setAttribute("type", user.getType(request.getParameter("username"), request.getParameter("password")));
				request.getRequestDispatcher("/main.jsp").forward(request, response);
				System.out.println("[" + request.getRemoteAddr() + "] User " + user.getUsername(request.getParameter("username"), request.getParameter("password")) + " has logged in.");
			} else {
				// Login Failed
				request.setAttribute("notification", "Invalid username and/or password");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				System.out.println("[" + request.getRemoteAddr() + "] Attempted to login with the credentials (" + request.getParameter("username") + "," + request.getParameter("password") + ")");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Login Failed, Error
			request.setAttribute("notification", "Invalid username and/or password");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			System.out.println("[" + request.getRemoteAddr() + "] Attempted to crash MySQL with login credentials (" + request.getParameter("username") + "," + request.getParameter("password") + ")");
		}
	}
}