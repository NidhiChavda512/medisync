package com.medisync.servelets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medisync.dao.UserDao;
import com.medisync.models.User;
import com.medisync.util.DBConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	UserDao userDao = new UserDao(dbConnection);
	
    public LoginServlet() {
        super();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String username = req.getParameter("username");
		String password = req.getParameter("password");

		try {
			User user = userDao.findOne(username);
			System.out.print("User : "+user);
			if (user != null) {
				if (user.getPassword().equals(password)) {
					
					HttpSession session = req.getSession(true);
					session.setAttribute("username", user.getUserName());
					session.setAttribute("userid", user.getUserId());

					if (user.getRole().equalsIgnoreCase("receptionist")) {
						res.getWriter().println("receptionist found");
						req.getRequestDispatcher("receptionist/index.jsp").forward(req, res);
						
					} else if (user.getRole().equalsIgnoreCase("doctor")) {
						res.getWriter().println("doctor found");
					} else {
						res.getWriter().println("W");
					}
				} else {
					req.setAttribute("passwordError", "Invalid Password");
					req.getRequestDispatcher("/login.jsp").forward(req, res);
				}
			} else {
				req.setAttribute("usernameError", "Invalid Username");
				req.getRequestDispatcher("/login.jsp").forward(req, res);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
