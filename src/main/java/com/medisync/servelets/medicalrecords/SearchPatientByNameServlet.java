package com.medisync.servelets.medicalrecords;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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

@WebServlet("/search-patient")
public class SearchPatientByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	PatientDao patientDao = new PatientDao(dbConnection);
	MedicalRecordDao medicalRecordDao = new MedicalRecordDao(dbConnection);
	List<Patient> patientList = new ArrayList<Patient>();
	Map<Integer, Integer> countMedicalRecords = null;

	public SearchPatientByNameServlet() {
		super();
	}

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    try {
	        String patientName = req.getParameter("patientName");
	        HttpSession session = req.getSession(true);
	        String userRole = (String) session.getAttribute("userrole");
	        if(userRole.equals("receptionist")) {	
		        if (patientName == null || patientName.isEmpty()) {
		            
		        	List<Patient> allPatients = patientDao.findAll();
		            countMedicalRecords = medicalRecordDao.countAllMedicalRecords();
					
		            StringBuilder htmlBuilder = new StringBuilder();
		            for (Patient patient : allPatients) {
		                htmlBuilder.append("<tr>");
		                htmlBuilder.append("<td>").append(patient.getPatientId()).append("</td>");
		                htmlBuilder.append("<td>").append(patient.getName()).append("</td>");
		                htmlBuilder.append("<td>").append(patient.getEmail()).append("</td>");
		                
		                
		                if (countMedicalRecords != null) {
								Iterator<Integer> it = countMedicalRecords.keySet().iterator();
									boolean gotPatient = false;
								
									while (it.hasNext()) {
										int key = (int) it.next();
										if(key==patient.getPatientId()){
											gotPatient = true;
											htmlBuilder.append("<td>").append(countMedicalRecords.get(key)).append("</td>");
										}  
									}
									
									if(gotPatient==false){
										htmlBuilder.append("<td>0</td>");
									}
		                }
						htmlBuilder.append("<td><a href='show-medical-records?id=").append(patient.getPatientId()).append("'>Show Medical Records</a></td>");
						htmlBuilder.append("</tr>");
		            }
		            String html = htmlBuilder.toString();
		            
		            res.setContentType("text/html");
		            PrintWriter out = res.getWriter();
		            out.print(html);
		            out.flush();
		        } else {
		            
		        	List<Patient> patientList = patientDao.searchByName(patientName);
		            countMedicalRecords = medicalRecordDao.countAllMedicalRecords();
		            
		            StringBuilder htmlBuilder = new StringBuilder();
		            for (Patient patient : patientList) {
		                htmlBuilder.append("<tr>");
		                htmlBuilder.append("<td>").append(patient.getPatientId()).append("</td>");
		                htmlBuilder.append("<td>").append(patient.getName()).append("</td>");
		                htmlBuilder.append("<td>").append(patient.getEmail()).append("</td>");
		                if (countMedicalRecords != null) {
							Iterator<Integer> it = countMedicalRecords.keySet().iterator();
								boolean gotPatient = false;
							
								while (it.hasNext()) {
									int key = (int) it.next();
									if(key==patient.getPatientId()){
										gotPatient = true;
										htmlBuilder.append("<td>").append(countMedicalRecords.get(key)).append("</td>");
									}  
								}
								
								if(gotPatient==false){
									htmlBuilder.append("<td>0</td>");
								}
		                }
		                htmlBuilder.append("<td><a href='show-medical-records?id=").append(patient.getPatientId()).append("'>Show Medical Records</a></td>");
		                htmlBuilder.append("</tr>");
		            }
		            String html = htmlBuilder.toString();
		            
		            // Send the HTML response
		            res.setContentType("text/html");
		            PrintWriter out = res.getWriter();
		            out.print(html);
		            out.flush();
		        }
	        } else if (userRole.equals("doctor")) {
	        	 if (patientName == null || patientName.isEmpty()) {
			            
			        	List<Patient> allPatients = patientDao.findAllByDoctor((Integer)session.getAttribute("userid"));
			            countMedicalRecords = medicalRecordDao.countAllMedicalRecords();
						
			            StringBuilder htmlBuilder = new StringBuilder();
			            for (Patient patient : allPatients) {
			                htmlBuilder.append("<tr>");
			                htmlBuilder.append("<td>").append(patient.getPatientId()).append("</td>");
			                htmlBuilder.append("<td>").append(patient.getName()).append("</td>");
			                htmlBuilder.append("<td>").append(patient.getEmail()).append("</td>");
			                
			                
			                if (countMedicalRecords != null) {
									Iterator<Integer> it = countMedicalRecords.keySet().iterator();
										boolean gotPatient = false;
									
										while (it.hasNext()) {
											int key = (int) it.next();
											if(key==patient.getPatientId()){
												gotPatient = true;
												htmlBuilder.append("<td>").append(countMedicalRecords.get(key)).append("</td>");
											}  
										}
										
										if(gotPatient==false){
											htmlBuilder.append("<td>0</td>");
										}
			                }
							htmlBuilder.append("<td><a href='show-medical-records?id=").append(patient.getPatientId()).append("'>Show Medical Records</a></td>");
							htmlBuilder.append("</tr>");
			            }
			            String html = htmlBuilder.toString();
			            
			            res.setContentType("text/html");
			            PrintWriter out = res.getWriter();
			            out.print(html);
			            out.flush();
			        } else {
			            
			        	List<Patient> patientList = patientDao.searchByNameAndDoctor(patientName, (Integer)session.getAttribute("userid"));
			            countMedicalRecords = medicalRecordDao.countAllMedicalRecords();
			            
			            StringBuilder htmlBuilder = new StringBuilder();
			            for (Patient patient : patientList) {
			                htmlBuilder.append("<tr>");
			                htmlBuilder.append("<td>").append(patient.getPatientId()).append("</td>");
			                htmlBuilder.append("<td>").append(patient.getName()).append("</td>");
			                htmlBuilder.append("<td>").append(patient.getEmail()).append("</td>");
			                if (countMedicalRecords != null) {
								Iterator<Integer> it = countMedicalRecords.keySet().iterator();
									boolean gotPatient = false;
								
									while (it.hasNext()) {
										int key = (int) it.next();
										if(key==patient.getPatientId()){
											gotPatient = true;
											htmlBuilder.append("<td>").append(countMedicalRecords.get(key)).append("</td>");
										}  
									}
									
									if(gotPatient==false){
										htmlBuilder.append("<td>0</td>");
									}
			                }
			                htmlBuilder.append("<td><a href='show-medical-records?id=").append(patient.getPatientId()).append("'>Show Medical Records</a></td>");
			                htmlBuilder.append("</tr>");
			            }
			            String html = htmlBuilder.toString();
			            
			            // Send the HTML response
			            res.setContentType("text/html");
			            PrintWriter out = res.getWriter();
			            out.print(html);
			            out.flush();
			        }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


}
