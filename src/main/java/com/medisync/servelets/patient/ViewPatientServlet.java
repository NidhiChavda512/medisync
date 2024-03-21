package com.medisync.servelets.patient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.PatientDao;
import com.medisync.models.Patient;
import com.medisync.util.DBConnection;

@WebServlet("/view-patient")
public class ViewPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	PatientDao patientDao = new PatientDao(dbConnection);
	List<Patient> patientList = null;
	
    public ViewPatientServlet() {
        super();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			String successMsg=null;
			if(req.getAttribute("SuccessMessage")!=null) {
				successMsg = req.getParameter("SuccessMessage");
			}
			patientList = patientDao.findAll();
			//System.out.print("patient list : "+patientList);
			if(patientList!=null) {
				req.removeAttribute("patientList");
				req.setAttribute("patientList", patientList);
				req.setAttribute("SuccessMessage", successMsg);
				req.getRequestDispatcher("/receptionist/view_patient.jsp").forward(req, res);
			} else {
				HttpSession session = req.getSession(true);
				session.setAttribute("patientListError", "No Result Found");
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
