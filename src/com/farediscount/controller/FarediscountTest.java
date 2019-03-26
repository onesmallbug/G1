package com.farediscount.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farediscount.model.FarediscountDAOImpl;
import com.farediscount.model.FarediscountVO;


@WebServlet("/FarediscountTest")
public class FarediscountTest extends HttpServlet{
	

	private static final long serialVersionUID = -1921776397555789457L;
	private FarediscountDAOImpl farediscountDAOImpl;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		FarediscountVO farediscountVO = new FarediscountVO();
		farediscountVO.setFd_narrative("FD_NARRATIVEinsert");
		farediscountVO.setFd_name("FD_NAME");
		farediscountVO.setFd_offer(-10);
		farediscountVO.setFd_start(new java.sql.Date(System.currentTimeMillis()));
		farediscountVO.setFd_end(new java.sql.Date(System.currentTimeMillis()+24*60*60*1000));
		farediscountVO.setFd_upper(30);
		farediscountVO.setFd_lower(20);
		farediscountVO.setFd_blob(null);
		
		farediscountDAOImpl.insert(farediscountVO);
		
		FarediscountVO farediscountVO2 = new FarediscountVO();
		farediscountVO2.setFd_no("FD002");
		farediscountVO2.setFd_narrative("FD_NARRATIVEinsert");
		farediscountVO2.setFd_name("FD_NAME");
		farediscountVO2.setFd_offer(-10);
		farediscountVO2.setFd_start(new java.sql.Date(System.currentTimeMillis()));
		farediscountVO2.setFd_end(new java.sql.Date(System.currentTimeMillis()+24*60*60*1000));
		farediscountVO2.setFd_upper(30);
		farediscountVO2.setFd_lower(20);
		farediscountVO2.setFd_blob(null);
		
		farediscountDAOImpl.update(farediscountVO2);
		
		farediscountDAOImpl.delete("FD016");
		
		System.out.println(farediscountDAOImpl.findByPrimaryKey("FD001"));
		System.out.println(farediscountDAOImpl.getAll());
		
	}

	@Override
	public void init() throws ServletException {
		farediscountDAOImpl = new FarediscountDAOImpl();
	}

}
