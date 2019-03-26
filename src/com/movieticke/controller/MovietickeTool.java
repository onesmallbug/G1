package com.movieticke.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import ToolClasses.TicketType;


@WebServlet(name = "/movieticke/MovietickeTool", loadOnStartup = 1, urlPatterns = "/movieticke/MovietickeTool")
public class MovietickeTool extends HttpServlet{
	private static final long serialVersionUID = 2844655041564625380L;

	@Override
	public void init() throws ServletException {

		Map<String, TicketType> map = new HashMap<String, TicketType>();
		map.put( "1", new TicketType("a","#00e") );
		//一般票
		map.put("2", new TicketType("b","#3aa"));
		//半票
		map.put("3", new TicketType("c","#90c"));
		//軍公教
		map.put("4", new TicketType("d","#fdd"));
		//敬老票
		
		getServletContext().setAttribute("TicketTypeMap", map);
		
		
	}
	
	
	

}
