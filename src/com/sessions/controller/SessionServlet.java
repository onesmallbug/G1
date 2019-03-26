package com.sessions.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.cinema.model.CinemaService;
import com.cinema.model.CinemaVO;
import com.movieinfo.model.*;
import com.sessions.model.*;

import ToolClasses.MyRequest;

@WebServlet(name = "/sessions/SessionServlet", loadOnStartup = 1, urlPatterns = "/sessions/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 6787696732983961662L;

	private SessionsService sessionsService;
	private MovieInfoService movieInfoService;

	@Override
	public void init() throws ServletException {

		sessionsService = new SessionsService();
		movieInfoService = new MovieInfoService();
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insertSessions".equals(action)) {
			
			if(req.getSession().getAttribute("insert_sessions_key") == null) {
				RequestDispatcher rd = req.getRequestDispatcher("/Back_end/sessions/select_date.jsp");
				rd.forward(req, res);
				return;
			}
			

			SessionsService ss = new SessionsService();

			String[] sessionss = req.getParameterValues("sessions");

			CinemaService cinemaService = new CinemaService();

			for (String string : sessionss) {

				String[] strs = string.split(";");

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Date udate = null;

				try {
					udate = df.parse(strs[2]);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				CinemaVO cinemaVO = cinemaService.getOneCin(strs[1]);
				
				ss.addSes(strs[0], strs[1], new java.sql.Timestamp(udate.getTime()), cinemaVO.getCinema_type(),
						cinemaVO.getCinema_size());

			}


			MyRequest myRequest = new MyRequest(req);

			int size = sessionsService.getAll().size();
			
			if ((size % 10) == 0) {

				myRequest.setMyParameter("whichPage", String.valueOf(size / 10));

			} else {

				myRequest.setMyParameter("whichPage", String.valueOf((size / 10) + 1));

			}
			req.getSession().removeAttribute("insert_sessions_key");
			
			req.getRequestDispatcher("/Back_end/sessions/listAllSessions.jsp?action=all").forward(myRequest, res);
			return;

		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		long now = System.currentTimeMillis();

		if ("insert_need".equals(action)) {

			MovieInfoService ms = new MovieInfoService();
			List<MovieInfoVO> movieInfoVOList = new ArrayList<MovieInfoVO>();
			req.setAttribute("movieInfoVOList", movieInfoVOList);

			for (MovieInfoVO movieInfoVO : ms.getAll()) {

				if (movieInfoVO.getMovie_out().getTime() > now) {
					movieInfoVOList.add(movieInfoVO);
				}

			}

			req.getRequestDispatcher("/Back_end/sessions/insertSessions.jsp").forward(req, res);

		}
	}

}
