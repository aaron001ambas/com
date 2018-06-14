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
				System.out.println("ELSE, NOT CAUGHT NO ERRORS");
				request.setAttribute("notification", "Invalid username and/or password");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("[ERROR] Bad input. TRY CAUGHT");
			request.setAttribute("notification", "Invalid username and/or password");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
