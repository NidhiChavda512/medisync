package com.medisync.servelets.labtest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import com.medisync.dao.LabTestDao;
import com.medisync.models.LabTest;
import com.medisync.util.DBConnection;

/**
 * Servlet implementation class ViewLabTestServlet
 */
@WebServlet("/view-labtest")
public class ViewLabTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	LabTestDao labtestDao = new LabTestDao(dbConnection);
	List<LabTest> labtestList = null;

	public ViewLabTestServlet() {
		super();
	}

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userRole = (String) req.getSession().getAttribute("userrole");
		try {
			if (userRole != null && userRole.equalsIgnoreCase("Receptionist")) 
			{
			System.out.println("in receptionist view labtest servlet");
			String successMsg = null;
			if (req.getAttribute("SuccessMessage") != null) {
				successMsg = req.getParameter("SuccessMessage");
			}
			labtestList = labtestDao.findAll();
			// System.out.print("patient list : "+patientList);
			if (labtestList != null) {

				req.setAttribute("labtestList", labtestList);
				req.setAttribute("SuccessMessage", successMsg);
				req.getRequestDispatcher("/receptionist/view-labtest.jsp").forward(req, res);
			} 
			else {
				HttpSession session = req.getSession(true);
				session.setAttribute("labtestListError", "No Result Found");
				req.getRequestDispatcher("/receptionist/view-labtest.jsp").forward(req, res);
			}}	
		
		else if(userRole != null && userRole.equalsIgnoreCase("doctor")) {
			int id = Integer.parseInt(req.getParameter("id"));
			System.out.println("in doctor view labtest servlet");
			String successMsg = null;
			if (req.getAttribute("SuccessMessage") != null) {
				successMsg = req.getParameter("SuccessMessage");
			}
			labtestList = (List<LabTest>) labtestDao.findOne(id);
			// System.out.print("patient list : "+patientList);
			if (labtestList != null) {

				req.setAttribute("labtestList", labtestList);
				req.setAttribute("SuccessMessage", successMsg);
				req.getRequestDispatcher("/doctor/view-labtest.jsp").forward(req, res);
			} 
			else {
				HttpSession session = req.getSession(true);
				session.setAttribute("labtestListError", "No Result Found");
				req.getRequestDispatcher("/doctor/view-labtest.jsp").forward(req, res);
			}
		} 
			
			
		}
			catch (Exception e) {
			System.out.print(e);
		}

	}}
