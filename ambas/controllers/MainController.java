package com.ambas.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.ambas.dao.ListingAccountsDAO;
import com.ambas.domain.User;
import com.ambas.services.AuthorizationService;
import com.ambas.services.CreateAccountService;

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
		
		if (request.getParameter("modify") != null) {
			
		}
		
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
					ListingAccountsDAO listaccounts = new ListingAccountsDAO();
					List<User> accounts = listaccounts.listAccounts();
					session.setAttribute("accounts", accounts);
					request.getRequestDispatcher("/manageaccounts.jsp").forward(request, response);
					return;
				} else
				request.setAttribute("notificationForMain", "You are not authorized to manage accounts.");
				request.getRequestDispatcher("/main.jsp").forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				request.setAttribute("notificationForMain", "You are not authorized to manage accounts. ERROR CAUGHT");
				request.getRequestDispatcher("/main.jsp").forward(request, response);
			}
		}
		// Manage Accounts button ends here
		
		// Create account button is pressed
			if (request.getParameter("createAccount") != null) {
				request.getRequestDispatcher("/AddAccount.jsp").forward(request,response);
			}
		// Create account button ends here
		
		// Creating account
			if (request.getParameter("submitCreateAccount") != null) {
				CreateAccountService createAccount = new CreateAccountService();
				createAccount.createAccount(request.getParameter("username"), request.getParameter("type"));
			}
		// Creating account ends here
		
		
		
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