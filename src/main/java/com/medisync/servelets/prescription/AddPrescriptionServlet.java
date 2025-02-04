package com.medisync.servelets.prescription;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.DoctorDao;
import com.medisync.dao.LabTestDao;
import com.medisync.dao.MedicalRecordDao;
import com.medisync.dao.PatientDao;
import com.medisync.dao.PrescriptionDao;
import com.medisync.models.Doctor;
import com.medisync.models.LabTest;
import com.medisync.models.MedicalRecord;
import com.medisync.models.Patient;
import com.medisync.models.Prescription;
import com.medisync.util.DBConnection;

@WebServlet("/add-prescription-servlet")
public class AddPrescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	PrescriptionDao prescriptiondao = new PrescriptionDao(dbConnection);
	PatientDao patientDao = new PatientDao(dbConnection);
	DoctorDao doctorDao = new DoctorDao(dbConnection);
	LabTestDao labTestDao = new LabTestDao(dbConnection);
	MedicalRecordDao medicalRecordDao = new MedicalRecordDao(dbConnection);
	List<Patient> patientList = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPrescriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String patientName = request.getParameter("fullname");
		String durationString = request.getParameter("duration");
		int patientId = Integer.parseInt(request.getParameter("patientId"));
		int doctorId = (Integer)request.getSession().getAttribute("userid");
		try {
			Patient patient = patientDao.findOne(patientId);
			Doctor doctor = doctorDao.findOne(doctorId);
//			patientList = patientDao.fetchPatientByDoctorId(doctorId); 
			System.out.println("Inside try block addprescription servlet..");
//			int patientId = prescriptiondao.getPatientIdByPatientName(patientName);
			
			int duration = Integer.parseInt(durationString);
			Prescription prescription = new Prescription();
			prescription.setPatient(patient);
			prescription.setDoctor(doctor);
			long millis=System.currentTimeMillis();  
			prescription.setDatePrescribed(new java.sql.Date(millis));
			prescription.setMedicineName(request.getParameter("medname"));
			prescription.setDuration(duration);
		//	prescription.setDuration(request.getParameter("duration"));
			
			if(prescriptiondao.create(prescription)!=null) {
				System.out.println("Inside if block addprescription servlet..");
				request.setAttribute("SuccessMessage", "Prescription added Successfully");
				//request.getRequestDispatcher("view-patient").forward(request, response);
				String labTestName = request.getParameter("LabTestName");
				
				if(!labTestName.equals("")) {
					LabTest labTest = new LabTest();
					labTest.setLabTestName(labTestName);
					labTest.setPatient(patient);
					labTest.setDoctor(doctor);
					
					if(labTestDao.create(labTest)!=null) {
						System.out.println("Inside if block labtest servlet..");
//						request.setAttribute("prescriptionRegistrationSuccess", "Prescription added Successfully");
					}
				}
				MedicalRecord medicalRecord = new MedicalRecord();
				medicalRecord.setSymptoms(request.getParameter("symptoms"));
				medicalRecord.setDiagnosis(request.getParameter("diagnosis"));
				medicalRecord.setPatient(patient);
				medicalRecord.setPrescription(prescriptiondao.getLatestPrescriptionId());
				if(!labTestName.equals("")) {
					medicalRecord.setLabTest(labTestDao.getLatestLabTestId());
				}
//				else {
//					medicalRecord.setLabTest(null);
//				}
				System.out.println("Medical Record is : "+medicalRecord);
				if(medicalRecordDao.create(medicalRecord)!=null) {
					System.out.println("Inside if block medical record creation servlet..");
					request.setAttribute("prescriptionAddedSuccess", "Prescription added Successfully");
					request.getRequestDispatcher("view-prescription").forward(request, response);
				}
				
				
    		} else {
    			HttpSession session=request.getSession(true);
	            session.setAttribute("errorMessage", "Prescription not added");
	            //response.sendRedirect("receptionist/add-patient.jsp");
    		}		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
