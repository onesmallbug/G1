package com.login_filter.LoginFilter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ToolClasses.MyRequest;

public class LoginFilter_for_chooseSeatS implements Filter{
	
	private FilterConfig config;
	
	public void init(FilterConfig config) {
		this.config = config;
	}
	
	public void destroy() {
		config = null;
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		Object memVO = session.getAttribute("memVO");
		
		if (memVO == null) {
			
			Map<String, String> requestS = new HashMap<String, String>();
			
			Enumeration<String> enumeration = request.getParameterNames();
			
			while(enumeration.hasMoreElements()) {
				
				String parameterName = enumeration.nextElement();
				requestS.put(parameterName, request.getParameter(parameterName));
				
			}
			
			session.setAttribute(session.getId(), requestS);			
			session.setAttribute("location", req.getRequestURI());
			
			res.sendRedirect(req.getContextPath()+"/Front_end/Login.jsp");
			return;
			
		}else {
			
			Map<String, String> requestS = (Map<String, String>)session.getAttribute(session.getId());
			
			MyRequest myRequest = new MyRequest(req);
			
			if(requestS != null) {
			
			requestS.forEach((k,v) -> myRequest.setMyParameter(k, v));
			session.removeAttribute(session.getId());
			
			}
			chain.doFilter(myRequest, response);
		}
		
	}

}
