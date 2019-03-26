package com.login_filter.LoginFilter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutHandler extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LogoutHandler() {
		super();
	}
	
	protected void doPost(HttpServletRequest req , HttpServletResponse res) 
		throws ServletException,IOException{
			
		String logout = req.getParameter("logout");
		if("logout".equals(logout)) {
			HttpSession session = req.getSession();
			session.invalidate();
			res.sendRedirect(req.getContextPath()+"/Front_end/Login.jsp");
			return;
		}
		
	}

}
