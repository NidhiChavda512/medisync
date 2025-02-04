package com.medisync.servelets.medicalrecords;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.MedicalRecordDao;
import com.medisync.dao.PrescriptionDao;
import com.medisync.models.MedicalRecord;
import com.medisync.util.DBConnection;

@WebServlet("/show-medical-records")
public class ShowMedicalRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	MedicalRecordDao medicalRecordDao = new MedicalRecordDao(dbConnection);
	List<MedicalRecord> medicalRecordList = null;   
	
    public ShowMedicalRecordsServlet() {
        super();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			int patientId = Integer.parseInt(req.getParameter("id"));
			HttpSession session = req.getSession(true);
			session.setAttribute("PatientID", patientId);
			medicalRecordList = medicalRecordDao.findAllByPatientId(patientId);
			System.out.println("Medical Records : "+medicalRecordList);
			req.setAttribute("MedicalRecordList", medicalRecordList);
			req.getRequestDispatcher("/receptionist/medical-records.jsp").forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
