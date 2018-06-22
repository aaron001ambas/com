package com.ambas.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ambas.domain.User;
import com.ambas.services.ManageAccountsService;

public class ModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	String targetUser = request.getParameter("targetUser");
    	ManageAccountsService modify = new ManageAccountsService();
    	try {
			List<User> user = modify.retrieveInfoToBeModified(targetUser);
			session.setAttribute("selectedUserUsername", user.get(0).getUsername());
			session.setAttribute("selectedUserType", user.get(0).getType());
			request.getRequestDispatcher("/ModifyAccount.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}
