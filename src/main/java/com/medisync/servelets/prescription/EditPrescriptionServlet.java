package com.medisync.servelets.prescription;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import com.medisync.dao.PrescriptionDao;
import com.medisync.models.Appointment;
import com.medisync.models.Doctor;
import com.medisync.models.Patient;
import com.medisync.models.Prescription;
import com.medisync.util.DBConnection;

/**
 * Servlet implementation class EditAppointmentServlet
 */
@WebServlet("/edit-prescription")
public class EditPrescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	PrescriptionDao prescriptionDao = new PrescriptionDao(dbConnection);
	DoctorDao doctorDao = new DoctorDao(dbConnection);
	PatientDao patientDao = new PatientDao(dbConnection);

	public EditPrescriptionServlet() {
		super();

	}


	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			System.out.println("in edit prescription servlet");
			HttpSession session = req.getSession(true);
			int id = Integer.parseInt(req.getParameter("id"));

			Prescription prescription = prescriptionDao.findOne(id);
			System.out.println(prescription);

			if (prescription != null) {
				req.setAttribute("PrescriptionEdit", prescription);

//				List<Prescription> prescription = (List<Prescription>) prescriptionDao.findOne(id); // Fetch patient data from the database
//				List<Doctor>  doctors = doctorDao.findOne(request); // Fetch doctor data from the database

//            ServletContext application = getServletContext();
//            Set<String> availableSlots = (Set<String>) application.getAttribute("availableSlots");
//            if (availableSlots == null) {
//                availableSlots = initializeAvailableSlots(); // Initialize available time slots if not already set
//                application.setAttribute("availableSlots", availableSlots); // Set available time slots in session
//            }
//            else
//            {
//	        
//	   	     application.setAttribute("availableSlots", availableSlots);
//   	     
//            }

//				if (!(prescription.isEmpty())) {
//
//					req.setAttribute("patientNames", prescription);
//					// req.setAttribute("patientNames", patients);
//
//				}
				req.getRequestDispatcher("/doctor/edit-prescription.jsp").forward(req, res);
			} else {

				session.setAttribute("editPrescriptionListError", "No Result Found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
