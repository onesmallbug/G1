package com.cinema.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.model.*;

@WebServlet(name = "/Cinema/CinemaServlet", loadOnStartup = 1, urlPatterns = "/Cinema/CinemaServlet")
public class CinemaServlet extends HttpServlet {
	private static final long serialVersionUID = -6487812844357722812L;

	private CinemaService cinemaService;

	private static final int CINEMA_NAME_LENGTH = 10;
	private static final String CINEMA_NAME_LENGTH_ILLEGAL_MESSAGE = "廳院名稱長度異常，請重新輸入長度1~10個字元的廳院名稱";

	private static final int CINEMA_STATUS_LENGTH = 20;
	private static final String CINEMA_STATUS_LENGTH_ILLEGAL_MESSAGE = "廳院狀態長度異常，請重新輸入長度1~20個字元的廳院名稱";

	private static final int CINEMA_CORRECT_MAX = 100;
	private static final int CINEMA_CORRECT_MIN = -100;
	private static final String CINEMA_CORRECT_ILLEGAL_MESSAGE = "票價修正異常，請重新輸入-100~100的票價修正";

	private static final String CINEMA_CORRECT_ISNOT_NUMBER_ILLEGAL_MESSAGE = "票價調整必須是數字";

	@Override
	public void init() throws ServletException {

		cinemaService = new CinemaService();
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// ------------以下------------update------------------------

		if ("updateCinema".equals(action)) {

			List<String> errorMessage = new ArrayList<String>();

			String cinema_no = req.getParameter("cinema_no");
			String cinema_type = req.getParameter("cinema_type");
			Integer cinema_size = new Integer(req.getParameter("cinema_size"));

			String cinema_correct_str = req.getParameter("cinema_correct");
			Integer cinema_correct;

			try {
				cinema_correct = new Integer(Integer.parseInt(cinema_correct_str));
			} catch (Exception e) {
				errorMessage.add(CINEMA_CORRECT_ISNOT_NUMBER_ILLEGAL_MESSAGE);
				cinema_correct = new Integer(0);
			}

			String cinema_name = req.getParameter("cinema_name").trim();
			String cinema_status = req.getParameter("cinema_status").trim();

			CinemaVO cinemaVO = new CinemaVO();
			cinemaVO.setCinema_no(cinema_no);
			cinemaVO.setCinema_type(cinema_type);
			cinemaVO.setCinema_size(cinema_size);
			cinemaVO.setCinema_correct(cinema_correct);
			cinemaVO.setCinema_name(cinema_name);
			cinemaVO.setCinema_status(cinema_status);

			if (cinema_name.length() > CINEMA_NAME_LENGTH || cinema_name.length() < 1) {

				errorMessage.add(CINEMA_NAME_LENGTH_ILLEGAL_MESSAGE);
			}

			// ------------以上-----------名稱長度驗證---------------------------

			if (cinema_status.length() > CINEMA_STATUS_LENGTH || cinema_name.length() < 1) {

				errorMessage.add(CINEMA_STATUS_LENGTH_ILLEGAL_MESSAGE);

			}

			// ------------以上-----------狀態長度驗證---------------------------
			
			if (cinema_correct > CINEMA_CORRECT_MAX || cinema_correct < CINEMA_CORRECT_MIN) {

				errorMessage.add(CINEMA_CORRECT_ILLEGAL_MESSAGE);

			}

			// ------------以上-----------票價修正數值驗證---------------------------

			if (!errorMessage.isEmpty()) {
				req.setAttribute("cinemaVO", cinemaVO);
				req.setAttribute("errorMessage", errorMessage);
				req.getRequestDispatcher("/Back_end/cinema/updateCinema.jsp").forward(req, res);
				return;
			}

			cinemaService.updateCin(cinema_no, cinema_type, cinema_size, cinema_name, cinema_correct, cinema_status);
			req.getRequestDispatcher("/Back_end/cinema/ListAllCinema.jsp").forward(req, res);
		}

		// ------------以下------------insert------------------------

		if ("insertCinema".equals(action)) {
			
			if(req.getSession().getAttribute("insert_Cinema_key") == null) {
				RequestDispatcher rd = req.getRequestDispatcher("/Back_end/cinema/insertCinema.jsp");
				rd.forward(req, res);
				return;
			}
			
			List<String> errorMessage = new ArrayList<String>();

			String cinema_type = req.getParameter("cinema_type");
			Integer cinema_size = new Integer(req.getParameter("cinema_size"));

			String cinema_correct_str = req.getParameter("cinema_correct");
			Integer cinema_correct;

			try {
				cinema_correct = new Integer(Integer.parseInt(cinema_correct_str));
			} catch (Exception e) {
				errorMessage.add(CINEMA_CORRECT_ISNOT_NUMBER_ILLEGAL_MESSAGE);
				cinema_correct = new Integer(0);
			}

			String cinema_name = req.getParameter("cinema_name").trim();
			String cinema_status = req.getParameter("cinema_status").trim();

			CinemaVO cinemaVO = new CinemaVO();
			cinemaVO.setCinema_type(cinema_type);
			cinemaVO.setCinema_size(cinema_size);
			cinemaVO.setCinema_correct(cinema_correct);
			cinemaVO.setCinema_name(cinema_name);
			cinemaVO.setCinema_status(cinema_status);

			if (cinema_name.length() > CINEMA_NAME_LENGTH || cinema_name.length() < 1) {

				errorMessage.add(CINEMA_NAME_LENGTH_ILLEGAL_MESSAGE);
			}

			// ------------以上-----------名稱長度驗證---------------------------

			if (cinema_status.length() > CINEMA_STATUS_LENGTH || cinema_name.length() < 1) {

				errorMessage.add(CINEMA_STATUS_LENGTH_ILLEGAL_MESSAGE);

			}

			// ------------以上-----------狀態長度驗證---------------------------
			
			if (cinema_correct > CINEMA_CORRECT_MAX || cinema_correct < CINEMA_CORRECT_MIN) {

				errorMessage.add(CINEMA_CORRECT_ILLEGAL_MESSAGE);

			}

			// ------------以上-----------票價修正數值驗證---------------------------

			if (!errorMessage.isEmpty()) {
				req.setAttribute("cinemaVO", cinemaVO);
				req.setAttribute("errorMessage", errorMessage);
				req.getRequestDispatcher("/Back_end/cinema/insertCinema.jsp").forward(req, res);
				return;
			}

			cinemaService.addCin(cinema_type, cinema_size, cinema_name, cinema_correct, cinema_status);
			req.getSession().removeAttribute("insert_Cinema_key");
			req.getRequestDispatcher("/Back_end/cinema/ListAllCinema.jsp").forward(req, res);
		}

	}

}
