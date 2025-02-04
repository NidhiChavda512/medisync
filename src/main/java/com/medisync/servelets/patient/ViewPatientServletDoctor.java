package com.medisync.servelets.patient;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.PatientDao;
import com.medisync.models.Patient;
import com.medisync.util.DBConnection;

@WebServlet("/view-patient-servlet-doctor")
public class ViewPatientServletDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	PatientDao patientDao = new PatientDao(dbConnection);
	List<Patient> patientList = null;
	
	//private Object patientList;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPatientServletDoctor() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String successMsg=null;
			if(request.getAttribute("SuccessMessage")!=null) {
				successMsg = request.getParameter("SuccessMessage");
			}
			
			HttpSession session = request.getSession(true);
			int loggedInDoctorId = (Integer)session.getAttribute("userid");
			//patientList = patientDao.findAll();
			patientList = patientDao.findAllByDoctor(loggedInDoctorId);
			
			//System.out.print("patient list : "+patientList);

			if(patientList!=null) {
				request.removeAttribute("patientList");
				request.setAttribute("patientList", patientList);
				request.setAttribute("SuccessMessage", successMsg);
				
				
				request.getRequestDispatcher("doctor/view-patient.jsp").forward(request, response);
			} else {
				//HttpSession session = request.getSession(true);
				session.setAttribute("patientListError", "No Result Found");
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
