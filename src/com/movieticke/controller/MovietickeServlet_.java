package com.movieticke.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/movieticke/MovietickeServlet_")
public class MovietickeServlet_ extends HttpServlet {
	private static final long serialVersionUID = -4696804217448552180L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			
			String[] mt_no = req.getParameterValues("mt_no");
			String[] ti_no = req.getParameterValues("ti_no");
			
			
			for(int i = 0;i<mt_no.length;i++) {
				
			System.out.println(mt_no[i]);
			System.out.println(ti_no[i]);
			
			}
		}
		
	}

}
