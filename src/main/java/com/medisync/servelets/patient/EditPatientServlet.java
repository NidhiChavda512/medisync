package com.medisync.servelets.patient;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medisync.dao.PatientDao;
import com.medisync.models.Patient;
import com.medisync.util.DBConnection;

@WebServlet("/edit-patient")
public class EditPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	PatientDao patientDao = new PatientDao(dbConnection);
	
    public EditPatientServlet() {
        super();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Patient patient = patientDao.findOne(id);
			if(patient!=null) {
				req.setAttribute("patientEdit", patient);
				req.getRequestDispatcher("/receptionist/edit-patient.jsp").forward(req, res);
			}
		} catch (Exception e) {
			
		}
	}

}
