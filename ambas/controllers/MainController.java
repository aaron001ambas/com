package com.ambas.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.ambas.domain.User;
import com.ambas.services.AuthorizationService;

public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthorizationService authserv = new AuthorizationService();
		HttpSession session = request.getSession();
		
		String requiredType = "admin";
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		
		// Logout button pressed
		if (request.getParameter("logout") != null) {
			session.invalidate();
			request.setAttribute("notification", "You have successfully logged out.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		// Logout button ends here
		
		
		
		// Main button pressed
		if (request.getParameter("main") != null) {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		// Main button ends here
		
		
		
		// If Manage Accounts button is pressed
		if (request.getParameter("manageAccounts") != null) {
			requiredType = "admin";
			username = (String) session.getAttribute("username");
			password = (String) session.getAttribute("password");
			try {
				if (authserv.isUserAuthorized(username, password, requiredType)) {
					System.out.println("user authorized");
					request.getRequestDispatcher("/manageaccounts.jsp").forward(request, response);
					return;
				} else
					System.out.println("User not authorized");
				request.setAttribute("notificationForMain", "You are not authorized to manage accounts.");
				request.getRequestDispatcher("/main.jsp").forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				request.setAttribute("notificationForMain", "You are not authorized to manage accounts. ERROR CAUGHT");
				request.getRequestDispatcher("/main.jsp").forward(request, response);
			}
		}
		// Manage Accounts button ends here
		
		
		
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