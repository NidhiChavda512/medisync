package com.medisync.servelets.appointment;

import java.io.IOException;
import java.sql.Date;
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
 * Servlet implementation class FetchAppointment
 */
@WebServlet("/fetch-appointment")
public class FetchAppointment extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	AppointmentDao appointmentDao = new AppointmentDao(dbConnection);
	// PatientDao patientDao=new PatientDao(dbConnection);
	// DoctorDao doctorDao=new DoctorDao(dbConnection);

	public FetchAppointment() {
		super();
	}

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			System.out.println("In Fetch Servlet of appointment");
			int id = Integer.parseInt(req.getParameter("appointmentId"));
			Appointment appointment = new Appointment();
			Patient patient = new PatientDao(dbConnection).findOne(Integer.parseInt(req.getParameter("patientId")));
			Doctor doctor = new DoctorDao(dbConnection).findOne(Integer.parseInt(req.getParameter("doctorName")));

			appointment.setPatient(patient);
			appointment.setDoctor(doctor);
			appointment.setAppointment_date(Date.valueOf(req.getParameter("appoint_date")));
			// Retrieve available slots from the application scope
//            ServletContext application = getServletContext();
//            Set<String> availableSlots = (Set<String>) application.getAttribute("availableSlots");

			// Retrieve the selected slot from the request parameters
//            String selectedSlot = req.getParameter("appoint_time");
//            
			// Remove the selected slot from available slots
//            if (availableSlots.contains(selectedSlot)) {
//                availableSlots.remove(selectedSlot);
//                application.setAttribute("availableSlots", availableSlots); // Update the application scope
//            } 
			appointment.setAppointment_slot(req.getParameter("appoint_slot"));
			appointment.setStatus(req.getParameter("status"));

			System.out.println("Update Operation : " + appointmentDao.update(id, appointment));
			if (appointmentDao.update(id, appointment)) {
				System.out.println("In fetch if");
				req.setAttribute("editAppointmentSuccess", "Appointment Edited Successfully");
				req.getRequestDispatcher("view-appointment").forward(req, res);;
			} else {
				HttpSession session = req.getSession(true);
				session.setAttribute("errorMessage", "appointment Not Updated");
				res.sendRedirect(req.getContextPath()+"/receptionist/edit-appointment.jsp");
			}

		} catch (Exception e) {
			e.fillInStackTrace();
		}
	}

}
