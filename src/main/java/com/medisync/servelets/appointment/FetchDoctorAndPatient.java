package com.medisync.servelets.appointment;

import java.io.IOException;
import java.util.HashSet;
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
import com.medisync.models.Doctor;
import com.medisync.models.Patient;
import com.medisync.util.DBConnection;

@WebServlet("/fetch-doctor-patient")
public class FetchDoctorAndPatient extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	AppointmentDao appointmentDao = new AppointmentDao(dbConnection);
	PatientDao patientDao = new PatientDao(dbConnection);
    DoctorDao doctorDao = new DoctorDao(dbConnection);
    public FetchDoctorAndPatient() {
        super();
    }
    
    public FetchDoctorAndPatient(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}
    
    
//	Set<String> slots = new HashSet<String>();
//    private Set<String> initializeAvailableSlots() {
//        slots.add("10:00-11:00");
//        slots.add("11:00-12:00");
//        slots.add("13:00-14:00");
//        slots.add("14:00-15:00");
//        slots.add("15:00-16:00");
//        return slots;
//    }
	 protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    	System.out.println("in add appointment");
	    	try {
	        	System.out.println("in add appointment try block");
	            HttpSession session = req.getSession(true);
	            
//	            String patientName = req.getParameter("name");
//	            Patient patient = patientDao.findByName(patientName);
//
//	            String doctorName = req.getParameter("doct");
//	            Doctor doctor = doctorDao.findByName(doctorName);
	            Map<Integer, String> patients = patientDao.findAllNames(); // Fetch patient data from the database
	            Map<Integer, String> doctors = doctorDao.findAllNames(); // Fetch doctor data from the database
	            
//	            ServletContext application = getServletContext();
//	            Set<String> availableSlots = (Set<String>) application.getAttribute("availableSlots");
//	            if (availableSlots == null) {
//	                availableSlots = initializeAvailableSlots(); // Initialize available time slots if not already set
//	                application.setAttribute("availableSlots", availableSlots); // Set available time slots in session
//	            }
//	            else
//	            {
//		        
//		   	     application.setAttribute("availableSlots", availableSlots);
//	   	     
//	            }
//	   	 
//
//	            
//
//
	            
	            if(!(patients.isEmpty() && doctors.isEmpty())){
	            	req.setAttribute("doctorNames", doctors);
	            	req.setAttribute("patientNames", patients);
	            	req.getRequestDispatcher("/receptionist/add-appointment.jsp").forward(req, res);
	            }else {
					session.setAttribute("appointmentListError", "No Result Found");
					
	            }
	    	}
	    	
	    	catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
	 
}
