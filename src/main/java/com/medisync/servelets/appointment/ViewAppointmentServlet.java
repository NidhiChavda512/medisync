package com.medisync.servelets.appointment;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.AppointmentDao;
import com.medisync.models.Appointment;
import com.medisync.util.DBConnection;

@WebServlet("/view-appointment")
public class ViewAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DBConnection dbConnection = DBConnection.getDbConnection();
	AppointmentDao appointmentDao = new AppointmentDao(dbConnection);
	List<Appointment> appointmentList = null;

	public ViewAppointmentServlet() {
		super();
	}

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		HttpSession session = req.getSession(true);
		String userRole = (String) req.getSession().getAttribute("userrole");
		try {
			if (userRole != null && userRole.equalsIgnoreCase("Receptionist")) {
				System.out.println("in view appointment Servlet of receptionist");
				appointmentList = appointmentDao.findAll();
				System.out.print("appointmentlist : " + appointmentList);
				if (appointmentList != null) {
					req.setAttribute("appointmentList", appointmentList);
					req.getRequestDispatcher("/receptionist/view-appointment.jsp").forward(req, res);
				} else {
					HttpSession session = req.getSession(true);
					session.setAttribute("appointmentListError", "No Result Found");
					req.getRequestDispatcher("/receptionist/view-appointment.jsp").forward(req, res);
				}
			}
			
			else if(userRole != null && userRole.equalsIgnoreCase("doctor")) {
				int doctorid = (Integer) req.getSession().getAttribute("userid");
				System.out.println("in view appointment Servlet of doctor");
				appointmentList = appointmentDao.findAllByDoctorId(doctorid);
				System.out.print("appointmentlist : " + appointmentList);
				if (appointmentList != null) {
					req.setAttribute("appointmentList", appointmentList);
					req.getRequestDispatcher("/doctor/view-appointment.jsp").forward(req, res);
				} else {
					HttpSession session = req.getSession(true);
					session.setAttribute("appointmentListError", "No Result Found");
					req.getRequestDispatcher("/doctor/view-appointment.jsp").forward(req, res);
				}
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}
}
