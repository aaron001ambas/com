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
import com.ambas.services.ManageRecordsService;

public class ModifyRecordController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
    	String recordid = request.getParameter("recordid");
    	ManageRecordsService modify = new ManageRecordsService();
    	List<Record> record;
		try {
			record = modify.retrieveRecordToBeModified(recordid);
			session.setAttribute("recordid", record.get(0).getRecordid());
			session.setAttribute("firstname", record.get(0).getFirstname());
			session.setAttribute("lastname", record.get(0).getLastname());
			session.setAttribute("nameOfResource", record.get(0).getNameOfResource());
			session.setAttribute("serialNumber", record.get(0).getSerialNumber());
			session.setAttribute("JRSS", record.get(0).getJRSS());
			session.setAttribute("band", record.get(0).getBand());
			session.setAttribute("account", record.get(0).getAccount());
			session.setAttribute("pmpSeat", record.get(0).getPmpseat());
			session.setAttribute("seatJrss", record.get(0).getSeatjrss());
			session.setAttribute("openSeatDesc", record.get(0).getOpenSeatDesc());
			session.setAttribute("reqSkills", record.get(0).getReqSkills());
			session.setAttribute("requestedBand", record.get(0).getRequestedband());
			session.setAttribute("dateOfRejection", record.get(0).getDateOfrejection());
			session.setAttribute("reasonForReject", record.get(0).getReasonForReject());
			session.setAttribute("detailedActionPlan", record.get(0).getDetailedActionPlan());
			session.setAttribute("targetDate", record.get(0).getTargetDate());
			session.setAttribute("status", record.get(0).getStatus());
			request.getRequestDispatcher("/ModifyRecord.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
