package com.medisync.servelets.prescription;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.AppointmentDao;
import com.medisync.dao.PrescriptionDao;
import com.medisync.models.Appointment;
import com.medisync.models.Prescription;
import com.medisync.util.DBConnection;

@WebServlet("/view-prescription")
public class ViewPrescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DBConnection dbConnection = DBConnection.getDbConnection();
	PrescriptionDao prescriptionDao = new PrescriptionDao(dbConnection);
	List<Prescription> prescriptionList = null;

	public ViewPrescriptionServlet() {
		super();
	}

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			String successMsg=null;
			if(req.getAttribute("SuccessMessage")!=null) {
				successMsg = req.getParameter("SuccessMessage");
			}
			int doctorid = (Integer) req.getSession().getAttribute("userid");
			System.out.println("in view prescription Servlet of doctor");
			prescriptionList = prescriptionDao.findAllByDoctorId(doctorid);
			System.out.print("prescriptionList : " + prescriptionList);
			if (prescriptionList != null) {
				System.out.println("Inside if block viewprescription servlet..");
				req.setAttribute("prescriptionList", prescriptionList);
				req.setAttribute("SuccessMessage", successMsg);
				req.getRequestDispatcher("/doctor/view-prescription.jsp").forward(req, res);
			} else {
				HttpSession session = req.getSession(true);
				session.setAttribute("prescriptionListError", "No Result Found");
				req.getRequestDispatcher("/doctor/view-prescription.jsp").forward(req, res);
			}

		} catch (Exception e) {
			System.out.print(e);
		}
	}
}
