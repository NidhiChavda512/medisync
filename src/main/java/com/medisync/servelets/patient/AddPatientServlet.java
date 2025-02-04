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

@WebServlet("/add-patient")
public class AddPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	PatientDao patientDao = new PatientDao(dbConnection);
	
    public AddPatientServlet() {
        super();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	try {
    		Patient patient = new Patient();
    		patient.setName(req.getParameter("fullname"));
    		patient.setDateOfBirth(Date.valueOf(req.getParameter("dob")));
    		patient.setGender(req.getParameter("gender"));
    		patient.setEmail(req.getParameter("email"));
    		patient.setContactNo(req.getParameter("contactno"));
    		
    		if(patientDao.create(patient)!=null) {
    			req.setAttribute("registrationSuccess", "Patient Registered Successfully");
    			req.getRequestDispatcher("view-patient").forward(req, res);
    		} else {
    			HttpSession session=req.getSession(true);
	            session.setAttribute("errorMessage", "Patient Alaready Exists");
				res.sendRedirect(req.getContextPath()+"/receptionist/add-patient.jsp");
    		}		
    	} catch (Exception e) {
    		e.fillInStackTrace();
    	}
    }
}
