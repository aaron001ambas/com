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
import com.ambas.services.ChangePasswordService;
import com.ambas.services.CreateAccountService;
import com.ambas.services.CreateRecordService;

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
		HttpSession session = request.getSession();
		
		if (request.getParameter("changePasswordBtn") != null) {
			try {
				changePassword(request, response, session);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (request.getParameter("submitCreateRecordBtn") != null) {
			try {
				createRecord(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (request.getParameter("modifyBtn") != null) {
//			String selectedUserUsername = request.getParameter("users.username");
			System.out.println(request.getParameter("username"));
		}
		
		if (request.getParameter("logoutBtn") != null) {
			logout(request, response, session);
		}
		
		if (request.getParameter("mainBtn") != null) {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		
		if (request.getParameter("manageAccountsBtn") != null) {
			manageAccounts(request, response, session);
		}
		
		if (request.getParameter("createAccountBtn") != null) {
			request.getRequestDispatcher("/AddAccount.jsp").forward(request,response);
		}
		
		if (request.getParameter("submitCreateAccountBtn") != null) {
			try {
				createAccount(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Error creating account");
			}
		}
		
		if (request.getParameter("manageRecordsBtn") != null) {
			request.getRequestDispatcher("/managerecords.jsp").forward(request, response);
		}
		
		if (request.getParameter("addRecordsBtn") != null) {
			request.getRequestDispatcher("/addrecords.jsp").forward(request, response);
		}
		
		if (request.getParameter("accountSettingsBtn") != null) {
			request.getRequestDispatcher("/accountsettings.jsp").forward(request, response);
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		session.invalidate();
		request.setAttribute("notification", "You have successfully logged out.");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	private void manageAccounts(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		String userUsername = (String) session.getAttribute("username"); 
		String requiredType = "admin";
		try {
			AuthorizationService authserv = new AuthorizationService();
			if (authserv.isUserAuthorized(userUsername, requiredType)) {
				ListingAccountsDAO listaccounts = new ListingAccountsDAO();
				List<User> accounts = listaccounts.listAccounts();
				session.setAttribute("accounts", accounts);
				request.getRequestDispatcher("/manageaccounts.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("notificationForMain", "You are not authorized to manage accounts.");
				request.getRequestDispatcher("/main.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("notificationForMain", "You are not authorized to manage accounts. ERROR CAUGHT");
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
	}
	
	private void createRecord(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String nameOfResource = request.getParameter("resourceName");
		String serialNumber = request.getParameter("serialNumber");
		String JRSS = request.getParameter("jrss");
		String band = request.getParameter("band");
		String account = request.getParameter("proposedAcc");
		String pmpseat = request.getParameter("pmpSeat");
		String seatjrss = request.getParameter("seatJRSS");
		String openSeatDesc = request.getParameter("openSeatDesc");
		String reqSkills = request.getParameter("reqSkills");
		String requestedband = request.getParameter("requestedBand");
		String dateOfrejection = request.getParameter("dateofReject");
		String reasonForReject = request.getParameter("reasonForReject");
		String detailedActionPlan = request.getParameter("detailedAction");
		String targetDate = request.getParameter("targetDateOfComp");
		String status = request.getParameter("status");
		
		CreateRecordService createrecord = new CreateRecordService();
		createrecord.createRecord(
				firstname,
				lastname,
				nameOfResource,
				serialNumber,
				JRSS,
				band,
				account,
				pmpseat,
				seatjrss,
				openSeatDesc,
				reqSkills,
				requestedband,
				dateOfrejection,
				reasonForReject,
				detailedActionPlan,
				targetDate,
				status
				);
	}
	
	private void createAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		CreateAccountService createAccount = new CreateAccountService();
		String newUsername = request.getParameter("desiredUsername");
		String newType = request.getParameter("desiredType");
		if (!isEmpty(newUsername)) {
			if (createAccount.createAccount(newUsername, newType)) {
				request.setAttribute("notifForCreatingAccount", "<div style=\"color:green\">Account Created!</div>");
				request.getRequestDispatcher("/AddAccount.jsp").forward(request,response);
			} else {
				request.setAttribute("notifForCreatingAccount", "<div style=\"color:red\">Account Already Exists!</div>");
				request.getRequestDispatcher("/AddAccount.jsp").forward(request,response);
			}
		} else {
			request.setAttribute("notifForCreatingAccount", "<div style=\"color:red\">You need to enter a username!</div>");
			request.getRequestDispatcher("/AddAccount.jsp").forward(request,response);
		}
	}
	
	private void changePassword(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException, ClassNotFoundException, SQLException {
		String newPass = request.getParameter("newPassword");
		String retypePass = request.getParameter("retypePassword");
		String username = (String) session.getAttribute("username");
		if (!isEmpty(newPass) || !isEmpty(retypePass)) {
			if (newPass.equals(retypePass)) {
				ChangePasswordService changepass = new ChangePasswordService();
				changepass.isPasswordChanged(username, newPass);
				request.setAttribute("notifForChangingPassword", "<div style=\"color:green\">Password has been changed!</div>");
				request.getRequestDispatcher("/accountsettings.jsp").forward(request,response);
			} else {
				request.setAttribute("notifForChangingPassword", "<div style=\"color:red\">Retyped password does not match!</div>");
				request.getRequestDispatcher("/accountsettings.jsp").forward(request,response);
			}
		} else {
			request.setAttribute("notifForChangingPassword", "<div style=\"color:red\">You must fill up the form!</div>");
			request.getRequestDispatcher("/accountsettings.jsp").forward(request,response);
		}
	}
	
	private boolean isEmpty(String username) {
		if (0 >= username.length()) {
			return true;
		}
		return false;
	}
	
}