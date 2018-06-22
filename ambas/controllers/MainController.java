package com.ambas.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ambas.domain.Record;
import com.ambas.domain.User;
import com.ambas.services.AccountSettingsService;
import com.ambas.services.AuthorizationService;
import com.ambas.services.CreateAccountService;
import com.ambas.services.CreateRecordService;
import com.ambas.services.LoginService;
import com.ambas.services.ManageAccountsService;
import com.ambas.services.ManageRecordsService;

public class MainController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LoginService loginService = new LoginService();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		
		AuthorizationService authserv = new AuthorizationService();
		
		try {
			if (loginService.isLoggedIn(username, password)) {
				if (request.getParameter("settingsBtn") != null) {
					request.getRequestDispatcher("/accountsettings.jsp").forward(request, response);
				}
							// INSIDE SETTINGS PAGE
							if (request.getParameter("changePasswordBtn") != null) {
								try {
									changePassword(request, response, session);
								} catch (ClassNotFoundException | SQLException e) {
									e.printStackTrace();
								}
							}
							// END OF SETTINGS PAGE
				
				if (request.getParameter("logoutBtn") != null) {
					logout(request, response, session, "You have successfully logged out.");
				}
				
				if (request.getParameter("mainBtn") != null) {
					request.getRequestDispatcher("/main.jsp").forward(request, response);
				}
				
			//////////////////////USER MUST BE ADMIN TO MANAGE AND CREATE ACCOUNTS///////////////////////
				if (request.getParameter("manageAccountsBtn") != null) {
					if (authserv.isUserAuthorized(username, "admin")) {
						manageAccounts(request, response, session);
					} else {
						request.setAttribute("notificationForMain", "You are not authorized to manage accounts.");
						request.getRequestDispatcher("/main.jsp").forward(request, response);
					}
				}
							// INSIDE MANAGE ACCOUNTS PAGE
							if (request.getParameter("createAccountBtn") != null) {
								if (authserv.isUserAuthorized(username, "admin")) {
									request.getRequestDispatcher("/AddAccount.jsp").forward(request,response);
								} else {
									request.setAttribute("notificationForMain", "You are not authorized to manage accounts.");
									request.getRequestDispatcher("/main.jsp").forward(request, response);
								}
							}
							// END OF MANAGE ACCOUNTS PAGE
							
										// INSIDE ADD ACCOUNT PAGE
										if (request.getParameter("submitCreateAccountBtn") != null) {
											if (authserv.isUserAuthorized(username, "admin")) {
												try {
													createAccount(request, response);
												} catch (ClassNotFoundException | SQLException e) {
													System.out.println("Error creating account");
												}
											} else {
												request.setAttribute("notificationForMain", "You are not authorized to manage accounts.");
												request.getRequestDispatcher("/main.jsp").forward(request, response);
											}
										}
										// END OF ADD ACCOUNT PAGE
			//////////////////////////////////////////////////////////////////////////////////////////////
					
										// INSIDE MODIFY ACCOUNT PAGE
										if (request.getParameter("changeTypeBtn") != null) {
											try {
												updateType(request.getParameter("selectedUserUsername"), request.getParameter("desiredType"));
												request.setAttribute("notifForChangingType", "<div style=\"color:green\">You have successfully modified the record!</div>");
												manageAccounts(request, response, session);
											} catch (ClassNotFoundException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
										
										if (request.getParameter("deleteAccountBtn") != null) {
											try {
												deleteAccount(request, response, session);
												request.setAttribute("notifForChangingType", "<div style=\"color:green\">You have successfully deleted the record!</div>");
												manageAccounts(request, response, session);
											} catch (ClassNotFoundException | SQLException e) {
												request.getRequestDispatcher("/main.jsp").forward(request, response);
											}
											
										}
										// END OF MODIFY ACCOUNT PAGE
							
				if (request.getParameter("manageRecordsBtn") != null) {
					try {
						manageRecords(request, response, session);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
							// INSIDE MODIFY RECORD PAGE
							if (request.getParameter("deleteRecordBtn") != null) {
								try {
									deleteRecord(request, response, session);
									request.setAttribute("notifForModifying", "<div style=\"color:green\">You have successfully deleted the record!</div>");
									manageRecords(request, response, session);
								} catch (ClassNotFoundException | SQLException e) {
									request.getRequestDispatcher("/main.jsp").forward(request, response);
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							if (request.getParameter("modifyRecordBtn") != null) {
								try {
									modifyRecord(request, response, session);
									request.setAttribute("notifForModifying", "<div style=\"color:green\">You have successfully modified the record!</div>");
									manageRecords(request, response, session);
								} catch (ClassNotFoundException | SQLException e) {
									request.getRequestDispatcher("/main.jsp").forward(request, response);
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							// END OF MODIFY RECORD PAGE
				
				if (request.getParameter("addRecordsBtn") != null) {
					request.getRequestDispatcher("/addrecords.jsp").forward(request, response);
				}
				
				if (request.getParameter("submitCreateRecordBtn") != null) {
					try {
						createRecord(request, response, session);
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				}
			
			} else {
				logout(request, response, session, "You must be logged in to do that!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			logout(request, response, session, "You must be logged in to do that!");
		}
		
	}
	
	private void modifyRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ClassNotFoundException, SQLException, IOException {
		ManageRecordsService modify = new ManageRecordsService();
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
		String recordid = request.getParameter("recordid");
		modify.modifyRecord(firstname, lastname, nameOfResource, serialNumber, JRSS, band, account, pmpseat, seatjrss, openSeatDesc, reqSkills, requestedband, dateOfrejection, reasonForReject, detailedActionPlan, targetDate, status, recordid);
	}
		
	private void deleteRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ClassNotFoundException, SQLException, IOException {
		ManageRecordsService modify = new ManageRecordsService();
		modify.deleteRecord(request.getParameter("recordid"));
	}
	
	private void deleteAccount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ClassNotFoundException, SQLException, IOException, ServletException {
		ManageAccountsService modify = new ManageAccountsService();
		modify.deleteAccount(request.getParameter("selectedUserUsername"));
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session, String notif) throws ServletException, IOException {
		session.invalidate();
		request.setAttribute("notification", notif);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	private void manageAccounts(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException, ClassNotFoundException, SQLException {
		ManageAccountsService list = new ManageAccountsService();
		List<User> accounts = list.listAccounts();
		session.setAttribute("accounts", accounts);
		request.getRequestDispatcher("/manageaccounts.jsp").forward(request, response);
	}
	
	private void updateType(String targetUser, String desiredType) throws ClassNotFoundException, SQLException, IOException {
		ManageAccountsService changeAccountType = new ManageAccountsService();
		changeAccountType.updateAccountType(targetUser, desiredType);
	}
	
	private void manageRecords(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException, ClassNotFoundException, SQLException {
		ManageRecordsService list = new ManageRecordsService();
		List<Record> recordlist = list.listRecords();
		session.setAttribute("recordlist", recordlist);
		request.getRequestDispatcher("/managerecords.jsp").forward(request, response);
	}
	
	private void createRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ClassNotFoundException, SQLException, ServletException, IOException {
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
		
		if (!isEmpty(firstname) &&
				!isEmpty(lastname) &&
				!isEmpty(nameOfResource) &&
				!isEmpty(serialNumber) &&
				!isEmpty(JRSS) &&
				!isEmpty(band) &&
				!isEmpty(account) &&
				!isEmpty(pmpseat) &&
				!isEmpty(seatjrss) &&
				!isEmpty(openSeatDesc) &&
				!isEmpty(reqSkills) &&
				!isEmpty(requestedband) &&
				!isEmpty(dateOfrejection) &&
				!isEmpty(detailedActionPlan) &&
				!isEmpty(targetDate) &&
				!isEmpty(status) &&
				!isEmpty(lastname)) {
			
			request.setAttribute("notifForModifying", "<div style=\"color:green\">You have successfully added the record!</div>");
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
			manageRecords(request, response, session);
		} else {
			request.setAttribute("notifForCreatingRecord", "<div style=\"color:red\">You can't leave empty fields!</div>");
			request.getRequestDispatcher("/addrecords.jsp").forward(request, response);
		}
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
		String username = (String) session.getAttribute("username");
		String newPass = request.getParameter("newPassword");
		String retypePass = request.getParameter("retypePassword");
		
		if (!isEmpty(newPass) || !isEmpty(retypePass)) {
			if (newPass.equals(retypePass)) {
				approvePasswordChange(request, response, session, "<div style=\"color:green\">Password has been changed!</div>", username, newPass);
			} else {
				denyPasswordChange(request, response, session, "<div style=\"color:red\">Retyped password does not match!</div>");
			}
		} else {
			denyPasswordChange(request, response, session, "<div style=\"color:red\">You must fill up the form!</div>");
		}
	}
	
	private void approvePasswordChange(HttpServletRequest request, HttpServletResponse response, HttpSession session, String notif, String username, String newPass) throws ServletException, IOException, ClassNotFoundException, SQLException {
		AccountSettingsService changePass = new AccountSettingsService();
		changePass.changePassword(username, newPass);
		request.setAttribute("notification", notif);
		request.getRequestDispatcher("/login.jsp").forward(request,response);
	}
	
	private void denyPasswordChange(HttpServletRequest request, HttpServletResponse response, HttpSession session, String notif) throws ServletException, IOException {
		request.setAttribute("notifForChangingPassword", notif);
		request.getRequestDispatcher("/accountsettings.jsp").forward(request,response);
	}
	
	private boolean isEmpty(String username) {
		if (0 >= username.length()) {
			return true;
		}
		return false;
	}
	
}