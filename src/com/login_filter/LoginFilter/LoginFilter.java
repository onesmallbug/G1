package com.login_filter.LoginFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{
	
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
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//取得session 
		HttpSession session = req.getSession();
		
		//從Session 判斷此user是否登入過
		
		Object memVO = session.getAttribute("memVO");
		if (memVO == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/Front_end/Login.jsp");
			return;
		}else {
			chain.doFilter(request, response);
		}
		
		
	}

}
