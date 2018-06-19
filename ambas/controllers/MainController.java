package com.ambas.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// If Logout button is pressed
		if (request.getParameter("logout") != null) {
			session.invalidate();
			request.setAttribute("notification", "You have successfully logged out.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		// If Main button is pressed
		if (request.getParameter("main") != null) {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		// If Manage Accounts button is pressed
		if (request.getParameter("manageAccounts") != null) {
			request.getRequestDispatcher("/manageaccounts.jsp").forward(request, response);
		}
		// If Manage Records button is pressed
		if (request.getParameter("manageRecords") != null) {
			request.getRequestDispatcher("/managerecords.jsp").forward(request, response);
		}
		// If Add Records button is pressed
		if (request.getParameter("addRecords") != null) {
			request.getRequestDispatcher("/addrecords.jsp").forward(request, response);
		}
	}
}