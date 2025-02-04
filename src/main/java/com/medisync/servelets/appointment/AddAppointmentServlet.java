package com.medisync.servelets.appointment;

import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.AppointmentDao;
import com.medisync.dao.DoctorDao;
import com.medisync.dao.PatientDao;
import com.medisync.models.Appointment;
import com.medisync.models.Doctor;
import com.medisync.models.Patient;
import com.medisync.util.DBConnection;

/**
 * Servlet implementation class AddAppointmentServlet
 * 
 */
@WebServlet("/add-appointment")
public class AddAppointmentServlet extends HttpServlet {

	DBConnection dbConnection = DBConnection.getDbConnection();
	AppointmentDao appointmentDao = new AppointmentDao(dbConnection);
	PatientDao patientDao = new PatientDao(dbConnection);
	DoctorDao doctorDao = new DoctorDao(dbConnection);

	Set<String> slots = new HashSet<String>();

	public AddAppointmentServlet() {
		super();
	}

	public AddAppointmentServlet(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("in add appointment servlet");
		try {

			HttpSession session = req.getSession(true);

			// Retrieve available slots from the application scope
//			ServletContext application = getServletContext();
//			Set<String> availableSlots = (Set<String>) application.getAttribute("availableSlots");

			// Retrieve the selected slot from the request parameters
//			String selectedSlot = req.getParameter("appoint_time");

			// Remove the selected slot from available slots
//			if (availableSlots.contains(selectedSlot)) {
//				availableSlots.remove(selectedSlot);
//				application.setAttribute("availableSlots", availableSlots); // Update the application scope
//			}

			Appointment appointment = new Appointment();
			Patient patient = patientDao.findOne(Integer.parseInt(req.getParameter("patientName")));

			Doctor doctor = doctorDao.findOne(Integer.parseInt(req.getParameter("doctorName")));

			appointment.setPatient(patient);
			appointment.setDoctor(doctor);
			appointment.setAppointment_date(Date.valueOf(req.getParameter("appoint_date")));
			appointment.setAppointment_slot(req.getParameter("appoint_slot"));
			appointment.setStatus("Not Completed");

			if (appointmentDao.create(appointment) != null) {
				req.setAttribute("appointmentSuccess", "Appointment Scheduled Successfully");
				req.getRequestDispatcher("view-appointment").forward(req, res); // Forward the request																				// to your JSP file
			} else {

				session.setAttribute("patientListError", "No Result Found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
