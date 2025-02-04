package com.medisync.servelets.medicalrecords;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.MedicalRecordDao;
import com.medisync.models.MedicalRecord;
import com.medisync.util.DBConnection;

@WebServlet("/show-medical-records-by-date")
public class ShowMedicalRecordsByDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	MedicalRecordDao medicalRecordDao = new MedicalRecordDao(dbConnection);
	List<MedicalRecord> medicalRecordList = null;
       
    public ShowMedicalRecordsByDateServlet() {
        super();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			System.out.println("in the show medical records by date");
			Date startDate = Date.valueOf(req.getParameter("startDate"));
			Date endDate = Date.valueOf(req.getParameter("endDate"));
			
			HttpSession session = req.getSession(true);
			
			int patientId = (Integer)session.getAttribute("PatientID");
			
			System.out.println("Patient ID : "+patientId);
			medicalRecordList = medicalRecordDao.findAllByDate(startDate, endDate, patientId);
			
			 StringBuilder htmlBuilder = new StringBuilder();
	            for (MedicalRecord medicalRecord : medicalRecordList) {
	            	
	            	
	            	htmlBuilder.append("<p><strong>Record ID:</strong>").append(medicalRecord.getRecordId()).append("</p>");
	            	htmlBuilder.append("<input type=\"hidden\" value=\"").append(medicalRecord.getPatient().getPatientId()).append("\" id=\"patientId\"/>")
	                .append("<p><strong>Patient:</strong>").append(medicalRecord.getPatient().getName()).append("</p>")
	                .append("<p><strong>Doctor:</strong> Dr. ").append(medicalRecord.getPrescription().getDoctor().getName()).append("</p>")
	                .append("<p><strong>Prescription:</strong>").append(medicalRecord.getPrescription().getPrescriptionId()).append("</p>")
	                .append("<p><strong>Prescription Date:</strong>").append(medicalRecord.getPrescription().getDatePrescribed()).append("</p>")
	                .append("<p><strong>Lab Test:</strong>").append(medicalRecord.getLabTest().getLabTestName()).append("</p>")
	                .append("<p><strong>Lab Test Date:</strong>").append(medicalRecord.getLabTest().getTestDate()).append("</p>")
	                .append("<p><strong>Lab Test Result:</strong>").append(medicalRecord.getLabTest().getTestResult()).append("</p>")
	                .append("<p><strong>Symptoms:</strong>").append(medicalRecord.getSymptoms()).append("</p>")
	                .append("<p><strong>Recommended Medicine:</strong>").append(medicalRecord.getPrescription().getMedicineName()).append("</p>")
	                .append("<p><strong>Duration for Medicine (In Days):</strong>").append(medicalRecord.getPrescription().getDuration()).append("</p>")
	                .append("<p><strong>Diagnosis:</strong>").append(medicalRecord.getDiagnosis()).append("</p>"); 
	            	
//	            	<input type="hidden" value="<%= medicalRecord.getPatient().getPatientId() %>" id="patientId"/>
//	                 <p><strong>Patient:</strong> <%= medicalRecord.getPatient().getName() %></p>
//	                 <p><strong>Doctor:</strong> Dr. <%= medicalRecord.getPrescription().getDoctor().getName() %></p>
//	                 <p><strong>Prescription:</strong> <%= medicalRecord.getPrescription().getPrescriptionId() %></p>
//	                 <p><strong>Prescription Date:</strong> <%= medicalRecord.getPrescription().getDatePrescribed() %></p>
//	                 <p><strong>Lab Test:</strong> <%= medicalRecord.getLabTest().getLabTestName() %></p>
//	                 <p><strong>Lab Test Date:</strong> <%= medicalRecord.getLabTest().getTestDate() %></p>
//	                	<p><strong>Lab Test Result:</strong> <%= medicalRecord.getLabTest().getTestResult() %></p>
//	                 <p><strong>Symptoms:</strong> <%= medicalRecord.getSymptoms() %></p>
//	                 <p><strong>Recommended Medicine:</strong> <%= medicalRecord.getPrescription().getMedicineName() %></p>
//	                 <p><strong>Duration for Medicine (In Days):</strong> <%= medicalRecord.getPrescription().getDuration() %></p>
//	                 <p><strong>Diagnosis:</strong> <%= medicalRecord.getDiagnosis() %></p>
	            	
	            }
	            String html = htmlBuilder.toString();
	            
	            // Send the HTML response
	            res.setContentType("text/html");
	            PrintWriter out = res.getWriter();
	            out.print(html);
	            out.flush();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
