package com.cinema.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.model.*;

@WebServlet("/CinemaTest")
public class CinemaTest extends HttpServlet{
	private static final long serialVersionUID = 6014780442490165198L;
	private CinemaDAOImpl cinemaDAOImpl;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CinemaVO cinemaVO = new CinemaVO();
		cinemaVO.setCinema_type("CINEMA_TYPE");
		cinemaVO.setCinema_size(400);
		cinemaVO.setCinema_name("CINEMA_N");
		cinemaVO.setCinema_correct(0);
		cinemaVO.setCinema_status("CINEMA_STATUS");
		
		cinemaDAOImpl.insert(cinemaVO);
		
		CinemaVO cinemaVO2 = new CinemaVO();
		cinemaVO2.setCinema_no("CINEMA014");
		cinemaVO2.setCinema_type("CINEMA_TYPE_update");
		cinemaVO2.setCinema_size(400);
		cinemaVO2.setCinema_name("CINEMA_N");
		cinemaVO2.setCinema_correct(0);
		cinemaVO2.setCinema_status("CINEMA_STATUS");
		
		cinemaDAOImpl.update(cinemaVO2);
		
		cinemaDAOImpl.delete("CINEMA019");
		
		System.out.println(cinemaDAOImpl.findByPrimaryKey("CINEMA002"));
		System.out.println(cinemaDAOImpl.getAll());
		
	}

	@Override
	public void init() throws ServletException {
		cinemaDAOImpl = new CinemaDAOImpl();
	}

}
