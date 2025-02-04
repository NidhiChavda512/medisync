package com.medisync.servelets.appointment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import com.medisync.dao.AppointmentDao;
import com.medisync.util.DBConnection;

/**
 * Servlet implementation class DeleteAppointmentServlet
 */
@WebServlet("/delete-appointment")
public class DeleteAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	AppointmentDao appointmentDao = new AppointmentDao(dbConnection);

	public DeleteAppointmentServlet() {
		super();
	}

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {
			System.out.println("In delete servlet of appointment");
			int id = Integer.parseInt(req.getParameter("id"));
			if (appointmentDao.delete(id)) {
				req.setAttribute("SuccessMessage", "appointment Deleted Successfully");
				req.getRequestDispatcher("view-appointment").forward(req, res);
			} else {
				HttpSession session = req.getSession(true);
				session.setAttribute("errorMessage", "appointment Not Deleted");
				res.sendRedirect(req.getContextPath()+"/receptionist/view-appointment.jsp");
			}

		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
