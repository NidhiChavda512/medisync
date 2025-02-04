package com.medisync.servelets.labtest;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.DoctorDao;
import com.medisync.dao.LabTestDao;
import com.medisync.dao.PatientDao;
import com.medisync.models.Appointment;
import com.medisync.models.Doctor;
import com.medisync.models.LabTest;
import com.medisync.models.Patient;
import com.medisync.util.DBConnection;

/**
 * Servlet implementation class AddLabTestServlet
 */
@WebServlet("/add-labtest")
public class AddLabTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	LabTestDao labtestDao = new LabTestDao(dbConnection);

	public AddLabTestServlet() {
		super();
	}

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			System.out.println("In add labtest Servlet");
			int id = Integer.parseInt(req.getParameter("labtestId"));

			LabTest labtest = new LabTest();
			Patient patient = new PatientDao(dbConnection).findOne(Integer.parseInt(req.getParameter("patientId")));
			Doctor doctor = new DoctorDao(dbConnection).findOne(Integer.parseInt(req.getParameter("doctorId")));

			labtest.setPatient(patient);
			labtest.setDoctor(doctor);
			labtest.setLabTestName(req.getParameter("labtest_name"));
			labtest.setTestDate(Date.valueOf(req.getParameter("labtest_date")));
			labtest.setTestResult(req.getParameter("labtest_result"));
			System.out.println("Update Operation : " + labtestDao.update(id, labtest));
			if (labtestDao.update(id, labtest)) {
				System.out.println("In add labtest" + "");
				req.setAttribute("labResultSuccess", "Lab Result Added Successfully");
				req.getRequestDispatcher("view-labtest").forward(req, res);
			} else {
				HttpSession session = req.getSession(true);
				session.setAttribute("errorMessage", "labtest Not Updated");
				res.sendRedirect("receptionist/edit-labtest.jsp");
			}
		} catch (Exception e) {
			e.fillInStackTrace();
		}
	}

}
