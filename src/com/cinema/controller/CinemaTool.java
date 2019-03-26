package com.cinema.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import ToolClasses.StatusOfSit;

@WebServlet(name = "/CinemaTool", loadOnStartup = 1, urlPatterns = "/CinemaTool")
public class CinemaTool extends HttpServlet{
	private static final long serialVersionUID = 1201483449723249054L;
	
	public void init() throws ServletException {
		
		List<StatusOfSit> statusOfSitList = new ArrayList<StatusOfSit>();
		statusOfSitList.add(new StatusOfSit("走道","#fff", new Integer(0)));
		statusOfSitList.add(new StatusOfSit("可用","#3f9", new Integer(1)));
		statusOfSitList.add(new StatusOfSit("維修中","#999", new Integer(2)));
		statusOfSitList.add(new StatusOfSit("損壞","#f00", new Integer(3)));
		statusOfSitList.add(new StatusOfSit("牆壁","#333", new Integer(4)));
		statusOfSitList.add(new StatusOfSit("廁所","#cc6", new Integer(5)));
		statusOfSitList.add(new StatusOfSit("已訂位","#f63", new Integer(6)));
		
		getServletContext().setAttribute("statusOfSitList", statusOfSitList);
		
		
	}
	
}
