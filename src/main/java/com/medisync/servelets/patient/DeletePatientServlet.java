package com.medisync.servelets.patient;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.PatientDao;
import com.medisync.util.DBConnection;

@WebServlet("/delete-patient")
public class DeletePatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	PatientDao patientDao = new PatientDao(dbConnection);
	
	public DeletePatientServlet() {
		super();
	}

	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			if(patientDao.delete(id))
			{
				req.setAttribute("SuccessMessage", "Patient Deleted Successfully");
				req.getRequestDispatcher("view-patient").forward(req, res);
			} else {
				HttpSession session=req.getSession(true);
	            session.setAttribute("errorMessage", "Patient Not Deleted");
				res.sendRedirect("receptionist/view-patient.jsp");
			}
			
		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
