package com.medisync.servelets.labtest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import com.medisync.dao.AppointmentDao;
import com.medisync.dao.LabTestDao;
import com.medisync.util.DBConnection;

/**
 * Servlet implementation class DeleteLabTestServlet
 */
@WebServlet("/delete-labtest")
public class DeleteLabTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	DBConnection dbConnection = DBConnection.getDbConnection();
	LabTestDao labtestDao = new LabTestDao(dbConnection);
	
	public DeleteLabTestServlet() {
		super();
	}

	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			System.out.println("In delete labtest servlet");
			int id = Integer.parseInt(req.getParameter("id"));
			if(labtestDao.delete(id))
			{
				req.setAttribute("SuccessMessage", "labtest Deleted Successfully");
				req.getRequestDispatcher("view-labtest").forward(req, res);
			} else {
				HttpSession session=req.getSession(true);
	            session.setAttribute("errorMessage", "labtest Not Deleted");
				res.sendRedirect("receptionist/view-labtest.jsp");
			}
			
		} catch (Exception e) {
			System.out.print(e);
		}

	}
}
