package com.medisync.servelets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutServlet() {
        super();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	System.out.println("Session : "+req.getSession());
    	HttpSession session = req.getSession();
    	session.invalidate();
    	res.setHeader("Cache-Control","no-cache");
    	res.setHeader("Cache-Control","no-store");
    	res.setDateHeader("Expires", 0);
    	res.sendRedirect(req.getContextPath()+"/login.jsp");
    }

}
