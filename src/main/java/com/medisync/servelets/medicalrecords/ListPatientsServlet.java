package com.medisync.servelets.medicalrecords;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.MedicalRecordDao;
import com.medisync.dao.PatientDao;
import com.medisync.models.Patient;
import com.medisync.util.DBConnection;

@WebServlet("/medical-records")
public class ListPatientsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	PatientDao patientDao = new PatientDao(dbConnection);
	MedicalRecordDao medicalRecordDao = new MedicalRecordDao(dbConnection);
	List<Patient> patientList = null;
    Map<Integer, Integer> countMedicalRecords = null;
	
    public ListPatientsServlet() {
        super();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(true);
			
			String userRole = (String)session.getAttribute("userrole");
			
			
			if(userRole.equals("receptionist")) {	
				String successMsg=null;
				if(req.getAttribute("SuccessMessage")!=null) {
					successMsg = req.getParameter("SuccessMessage");
				}
				patientList = patientDao.findAll();
				countMedicalRecords = medicalRecordDao.countAllMedicalRecords();
				if(patientList!=null && countMedicalRecords!=null) {
					req.removeAttribute("patientList");
					req.setAttribute("patientList", patientList);
					req.setAttribute("countMedicalRecords", countMedicalRecords);
					req.setAttribute("SuccessMessage", successMsg);
					req.getRequestDispatcher("/receptionist/medical-records-patient-list.jsp").forward(req, res);
				} else {
					session.setAttribute("patientListError", "No Result Found");
					req.getRequestDispatcher("/receptionist/view-patient.jsp").forward(req, res);
				}
			} else if(userRole.equalsIgnoreCase("doctor")) {
				int doctorId = (Integer) session.getAttribute("userid");
				String successMsg=null;
				if(req.getAttribute("SuccessMessage")!=null) {
					successMsg = req.getParameter("SuccessMessage");
				}
				patientList = patientDao.findAllByDoctor(doctorId);
				countMedicalRecords = medicalRecordDao.countAllMedicalRecords();
				if(patientList!=null && countMedicalRecords!=null) {
					req.removeAttribute("patientList");
					req.setAttribute("patientList", patientList);
					req.setAttribute("countMedicalRecords", countMedicalRecords);
					req.setAttribute("SuccessMessage", successMsg);
					req.getRequestDispatcher("/doctor/medical-records-patient-list.jsp").forward(req, res);
				} else {
					session.setAttribute("patientListError", "No Result Found");
					req.getRequestDispatcher("/doctor/view-patient.jsp").forward(req, res);
				}
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
