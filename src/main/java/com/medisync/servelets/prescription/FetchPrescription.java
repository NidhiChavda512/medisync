package com.medisync.servelets.prescription;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.MedicalRecordDao;
import com.medisync.dao.PatientDao;
import com.medisync.dao.PrescriptionDao;
import com.medisync.models.MedicalRecord;
import com.medisync.models.Patient;
import com.medisync.models.Prescription;
import com.medisync.util.DBConnection;

/**
 * Servlet implementation class FetchAppointment
 */
@WebServlet("/fetch-prescription")
public class FetchPrescription extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	PrescriptionDao prescriptionDao = new PrescriptionDao(dbConnection);
	MedicalRecordDao medicalRecordDao = new MedicalRecordDao(dbConnection);
	// PatientDao patientDao=new PatientDao(dbConnection);
	// DoctorDao doctorDao=new DoctorDao(dbConnection);

	public FetchPrescription() {
		super();
	}

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			MedicalRecord medicalRecord = new MedicalRecord();
			Prescription prescription = new Prescription();
			System.out.println("In Fetch Servlet 111 of prescription");
			int id = Integer.parseInt(req.getParameter("id"));
			
			System.out.println("before patient");
			Patient patient = new PatientDao(dbConnection).findOne(Integer.parseInt(req.getParameter("id")));
			//Doctor doctor = new DoctorDao(dbConnection).findOne(Integer.parseInt(req.getParameter("doctorName")));
			System.out.println("after patient");
			prescription.setPatient(patient);
			//appointment.setDoctor(doctor);
			prescription.setDatePrescribed(Date.valueOf(req.getParameter("presdate")));

			prescription.setMedicineName(req.getParameter("medName"));
			prescription.setDuration(Integer.parseInt(req.getParameter("duration")));
			
			System.out.println("\nUpdate Operation : " );
			boolean updateStatus = prescriptionDao.update(id, prescription);
//			if (updateStatus) {
//				System.out.println("In fetch if");
//				req.setAttribute("editPrescriptionSuccess", "Prescription Edited Successfully");
//				req.getRequestDispatcher("view-prescription").forward(req, res);;
//			} else {
//				HttpSession session = req.getSession(true);
//				session.setAttribute("errorMessage", "prescription Not Updated");
//				//res.sendRedirect(req.getContextPath()+"/receptionist/edit-appointment.jsp");
//			}
			
//			medicalRecord.setPatient(patient);
			medicalRecord.setSymptoms(req.getParameter("symptoms"));
			medicalRecord.setDiagnosis(req.getParameter("diagnosis"));
			boolean updateStatusMedRec = medicalRecordDao.update(id, medicalRecord);
			if (updateStatusMedRec && updateStatus) {
				System.out.println("In fetch if");
				req.setAttribute("editPrescriptionSuccess", "Prescription Edited Successfully");
				req.setAttribute("editMedicalRecordSuccess", "Medical Recorded Edited Successfully");
				req.getRequestDispatcher("view-prescription").forward(req, res);;
			} else {
				HttpSession session = req.getSession(true);
				session.setAttribute("errorMessage", "Prescription Not Updated");
				//res.sendRedirect(req.getContextPath()+"/receptionist/edit-appointment.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
