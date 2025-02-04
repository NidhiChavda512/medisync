package com.medisync.servelets.labtest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medisync.dao.LabTestDao;
import com.medisync.models.LabTest;
import com.medisync.util.DBConnection;

/**
 * Servlet implementation class FetchLabTestServlet
 */
@WebServlet("/fetch-labtest")
public class FetchLabTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = DBConnection.getDbConnection();
	LabTestDao labtestdao = new LabTestDao(dbConnection);
	
    public FetchLabTestServlet() {
        super();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			System.out.println("In fetch labtest servlet");
			int id = Integer.parseInt(req.getParameter("id"));
			LabTest labtest = labtestdao.findOne(id);
			if(labtest!=null) {
				req.setAttribute("fetchlabtest", labtest);
				req.getRequestDispatcher("receptionist/edit-labtest.jsp").forward(req, res);
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}


       
    
}
