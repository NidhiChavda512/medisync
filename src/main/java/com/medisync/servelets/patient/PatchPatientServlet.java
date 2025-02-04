package com.medisync.servelets.patient;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.PatientDao;
import com.medisync.models.Patient;
import com.medisync.util.DBConnection;

@WebServlet("/patch-patient")
public class PatchPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	PatientDao patientDao = new PatientDao(dbConnection);
	
    public PatchPatientServlet() {
        super();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			System.out.println("In Patch Service");
			int id = Integer.parseInt(req.getParameter("patientId"));
    		Patient patient = new Patient();
    		patient.setName(req.getParameter("fullname"));
    		patient.setDateOfBirth(Date.valueOf(req.getParameter("dob")));
    		patient.setGender(req.getParameter("gender"));
    		patient.setEmail(req.getParameter("email"));
    		patient.setContactNo(req.getParameter("contactno"));
    		System.out.println("Update Operation : "+patientDao.update(id, patient));
    		if(patientDao.update(id, patient)) {
    			System.out.println("In Patch if");
    			req.setAttribute("editSuccess", "Patient Edited Successfully");
    			req.getRequestDispatcher("view-patient").forward(req, res);
    		} else {
    			HttpSession session=req.getSession(true);
	            session.setAttribute("errorMessage", "Patient Not Updated");
				res.sendRedirect(req.getContextPath()+"/receptionist/edit-patient.jsp");
    		}		
    	} catch (Exception e) {
    		e.fillInStackTrace();
    	}
	}

}
