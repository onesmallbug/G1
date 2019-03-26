package com.ticketorder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.websocket.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cinema.model.CinemaService;
import com.cinema.model.CinemaVO;
import com.dep.model.DepVO;
import com.farediscount.model.FarediscountService;
import com.farediscount.model.FarediscountVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.movieinfo.model.*;
import com.movieticke.model.MovieticketVO;
import com.sessions.model.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.ticketinformation.model.TicketinformationService;
import com.ticketorder.model.TicketorderService;
import com.ticketorder.model.TicketorderVO;

@WebServlet("/ticketorder/TicketorderServlet_")
public class TicketorderServlet_ extends HttpServlet {
	private static final long serialVersionUID = 7545074246590891136L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		map.put("d", "4");

		if ("getdate".equals(action)) {

			String movie_no = req.getParameter("movie_no");

			JSONArray array = new JSONArray();
			JSONArray array1 = new JSONArray();
			java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

			MovieInfoVO movieInfoVO = new MovieInfoService().getOneMovieInfo(movie_no);
			JSONObject jsonObject = new JSONObject();

			try {
				jsonObject.put("movie_name", movieInfoVO.getMovie_name());
				jsonObject.put("movie_intro", movieInfoVO.getMovie_intro());
				jsonObject.put("movie_ticket", movieInfoVO.getMovie_ticket());
				jsonObject.put("path",
						req.getContextPath() + "/ToolClasses/Images?action=movie_pic&movie_no=" + movie_no);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			array.put(jsonObject);

			Set<String> dates = new LinkedHashSet<String>();
			
			new SessionsService().getAll()
			.stream()
			.filter(sessions -> sessions.getMovie_no().equals(movie_no))
			.filter(sessions -> sessions.getSessions_start().after(now))
			.forEach(sessions -> dates.add(sessions.getSessions_start().toString().substring(0, 10)));
			
			for (String item : dates) {
				JSONObject jsonObject1 = new JSONObject();
				try {
					jsonObject1.put("date", item);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				array1.put(jsonObject1);
			}
			
			array.put(array1);

			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
		}

		if ("getsessions".equals(action)) {

			String movie_no = req.getParameter("movie_no");
			java.sql.Date date = java.sql.Date.valueOf(req.getParameter("date"));
			java.sql.Date date2 = new java.sql.Date(date.getTime() + 86400000);

			JSONArray array = new JSONArray();
			
			new SessionsService().getAll()
			.stream()
			.filter(sessionsVO -> sessionsVO.getMovie_no().equals(movie_no))
			.filter(sessionsVO -> !sessionsVO.getSessions_start().before(date))
			.filter(sessionsVO -> sessionsVO.getSessions_start().before(date2))
			.forEach(sessionsVO -> {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("sessions_no", sessionsVO.getSessions_no());
					jsonObject.put("sessions_start", sessionsVO.getSessions_start().toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				array.put(jsonObject);
			} );

			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();

		}

		if ("getsessions_remaining".equals(action)) {
			JSONObject jsonObject = new JSONObject();
			String sessions_no = req.getParameter("sessions_no");
			SessionsVO sessionsVO = new SessionsService().getOneSes(sessions_no);
			try {
				jsonObject.put("sessions_remaining", sessionsVO.getSessions_remaining());
				jsonObject.put("cinema_correct", new CinemaService().getOneCin(sessionsVO.getCinema_no()).getCinema_correct());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(jsonObject.toString());
			out.flush();
			out.close();

		}
		
		if("calculat".equals(action)) {
			
			String sessions_no = req.getParameter("sessions_no");
			String[] ti_no = req.getParameter("ti_no").split(";");
			//要處理ti_no為空的問題
			for (int i = 0; i < ti_no.length; i++) {
				ti_no[i] = map.get(ti_no[i]);
			}
			
			int count;
			if(ti_no[0] == null) {
				count = 0;
			}else {
				count = ti_no.length;
			}
			
			SessionsService sessionsService = new SessionsService();
			
			SessionsVO sessionsVO = sessionsService.getOneSes(sessions_no);
			FarediscountVO farediscountVO = whitchFd(count, sessionsVO.getSessions_start());
			int order_amount = calculatOrder_amount(ti_no, sessionsVO, farediscountVO);
			
			JSONObject jsonObject = new JSONObject();
			try {
				if(farediscountVO != null) {
					jsonObject.put("fd_name", farediscountVO.getFd_name());
					jsonObject.put("fd_offer", farediscountVO.getFd_offer());
				} else {
					jsonObject.put("fd_name", "　");
					jsonObject.put("fd_offer", "　");
				}
				jsonObject.put("order_amount", order_amount);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(jsonObject.toString());
			out.flush();
			out.close();
			
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		map.put("d", "4");

		if ("insert".equals(action)) {
			
			if(req.getSession().getAttribute("ticket_tx_key") == null) {
				RequestDispatcher rd = req.getRequestDispatcher("/Front_end/ticketorder_/choiseSessions.jsp");
				rd.forward(req, res);
				return;
			}

			String[] mt_no = req.getParameterValues("mt_no");
			// 座位編號
			String[] ti_no = req.getParameterValues("ti_no");
			int count = ti_no.length;
			// 票種
			String sessions_no = req.getParameter("sessions_no");
			// 場次編號
			MemVO memVO = (MemVO) req.getSession().getAttribute("memVO");
			// 購票的會員
			SessionsVO sessionsVO = new SessionsService().getOneSes(sessions_no);
			//取得場次值物件
			String sessions_status = sessionsVO.getSessions_status();
			sessionsVO.setSessions_remaining(sessionsVO.getSessions_remaining()-count);

			for (String sit : mt_no) {

				sessions_status = sessions_status.substring(0, Integer.parseInt(sit)) + 6
						+ sessions_status.substring(Integer.parseInt(sit)+1 , sessions_status.length());
				
			}
			sessionsVO.setSessions_status(sessions_status);
			
			//更新場次座位狀態
			
			DepVO depVO = new DepVO();
			depVO.setDeposit_member_no(memVO.getMember_no());
			depVO.setDeposit_change_date(new java.sql.Timestamp(System.currentTimeMillis()));
			// 點數異動

			for (int i = 0; i < count; i++) {
				ti_no[i] = map.get(ti_no[i]);

			}

			List<MovieticketVO> list = new ArrayList<MovieticketVO>();
			// 電影票=訂明細的VO們
			
			for (int i = 0; i < count; i++) {

				Integer mt_no_int = Integer.parseInt(mt_no[i]);
				String mt_no_str = (mt_no_int/25+1)+"-"+(mt_no_int%25+1);
				
				MovieticketVO movieticketVO = new MovieticketVO();
				movieticketVO.setMt_no(mt_no_str);
				movieticketVO.setTi_no(ti_no[i]);
				movieticketVO.setMt_admission(0);

				list.add(movieticketVO);

			}
			//進行票種代碼轉置

			FarediscountVO farediscountVO = whitchFd(mt_no.length, sessionsVO.getSessions_start());
			//計算優惠種類
			
			TicketorderVO ticketorderVO = new TicketorderVO();
			
			ticketorderVO.setMember_no(memVO.getMember_no());
			
			if (farediscountVO != null) {
				ticketorderVO.setFd_no(farediscountVO.getFd_no());
			}
			
			ticketorderVO.setSession_no(sessionsVO.getSessions_no());
			ticketorderVO.setOrder_takemeals(0);
			
			int order_amount = calculatOrder_amount(ti_no, sessionsVO, farediscountVO);
			//計算訂單總價
			
			ticketorderVO.setOrder_amount(order_amount);
			//設定訂單主檔

			depVO.setDeposit_change_money(-order_amount);
			//設定儲值異動明細
			
				//防止重新整理重複送出請求重複產生訂單造成資料異常
				TicketorderService ts = new TicketorderService();
				ts.insertTicketorderMain(ticketorderVO, memVO, depVO, sessionsVO, list);
				req.getSession().removeAttribute("ticket_tx_key");
			//進行資料更新，交易區間貫穿五個model
			
			req.getSession().setAttribute("ticketorderVO", ticketorderVO);
			req.setAttribute("farediscountVO", farediscountVO);
			req.setAttribute("list", list);
			
			memVO.setMember_point(new MemService().getoneMem(memVO.getMember_no()).getMember_point());
			//進行HttpSession中會員VO更新
			
			RequestDispatcher rd = req.getRequestDispatcher("/Front_end/ticketorder_/reviewticketorder.jsp");
			rd.forward(req, res);
			
			//開始伺服器推播
			Map<String, Set<Session>> connectedSessions = (Map<String, Set<Session>>)getServletContext().getAttribute("wsSessions");
			//取得所有javax.websocket.Session
			
			Set<Session> set =  connectedSessions.get(memVO.getMember_no());
			//取得該會員javax.websocket.Session
			
			JSONArray jsonArray_amember = new JSONArray();
			
			JSONObject jsonObject_amember_action = new JSONObject();
			JSONObject jsonObject_amember = new JSONObject();
			
			try {
				jsonObject_amember_action.put("action", "amember");
				jsonObject_amember.put("member_point", memVO.getMember_point());
				jsonObject_amember.put("message", "有人用你的帳號訂票，花了"+order_amount+"元");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			jsonArray_amember.put(jsonObject_amember_action);
			jsonArray_amember.put(jsonObject_amember);
			
			String text_amember = jsonArray_amember.toString();
			if(set != null) {
				for (Session session : set) {
					if (session.isOpen())
					session.getBasicRemote().sendText(text_amember);
				}
			}
			//推播警示單一會員有新的購票紀錄
			
			
			JSONArray jsonArray_everyone = new JSONArray();
			JSONObject jsonObject_everyone_action = new JSONObject();
			JSONObject jsonObject_everyone = new JSONObject();
			JSONArray jsonArray_everyone_mt_no = new JSONArray();

			for (String str : mt_no) {
				
				JSONObject object = new JSONObject();
				
				try {
					object.put("mt_no", str);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				jsonArray_everyone_mt_no.put(object);
			}
			//置入被訂走的位置資訊
			
			try {
				jsonObject_everyone_action.put("action", "everyone");
				jsonObject_everyone.put("message", "已經有人訂走了" + count + "個位子，要加緊腳步喔!! ^^~");
				jsonObject_everyone.put("sessions_no", sessions_no);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			jsonArray_everyone.put(jsonObject_everyone_action);
			jsonArray_everyone.put(jsonObject_everyone);
			jsonArray_everyone.put(jsonArray_everyone_mt_no);
			
			//置入提示訊息
			
			String text_everyOne = jsonArray_everyone.toString();
			
				for(Set<Session> set_ : connectedSessions.values()) {
					for (Session session : set_) {
						if (session.isOpen())
								session.getAsyncRemote().sendText(text_everyOne);
					}
				}

		}
	}

	private int calculatOrder_amount(String[] ti_no, SessionsVO sessionsVO, FarediscountVO farediscountVO) {

		TicketinformationService ticketinformationService = new TicketinformationService();
		CinemaVO cinemaVO = new CinemaService().getOneCin(sessionsVO.getCinema_no());
		MovieInfoVO movieInfoVO = new MovieInfoService().getOneMovieInfo(sessionsVO.getMovie_no());
		
		int sum = 
				Arrays.asList(ti_no)
				.stream()
				.filter(str -> str != null)
				.mapToInt(str -> calculatTicketPrice(str, ticketinformationService, cinemaVO, movieInfoVO))
				.sum();
		
		if (farediscountVO != null) {
			sum += ti_no.length * farediscountVO.getFd_offer();
		}

		return sum;
	}
	// 以上算訂單總價

	private int calculatTicketPrice(String ti_no, TicketinformationService ticketinformationService, CinemaVO cinemaVO, MovieInfoVO movieInfoVO) {
		int anser = 0;
		anser += ticketinformationService.getOneTicketinformation(ti_no).getTi_price();
		anser += cinemaVO.getCinema_correct();
		anser += movieInfoVO.getMovie_ticket();

		return anser;
	}
	// 以上算場各票種次票價

	private FarediscountVO whitchFd(int count, java.sql.Timestamp sessions_start) {

		FarediscountService fs = new FarediscountService();

		Optional<FarediscountVO> fd = 
			fs.getAll()
			.stream()
			.filter(farediscountVO -> farediscountVO.getFd_start().before(sessions_start))
			.filter(farediscountVO -> farediscountVO.getFd_end().after(sessions_start))
			.filter(farediscountVO -> farediscountVO.getFd_lower() <= count)
			.filter(farediscountVO -> farediscountVO.getFd_upper() >= count)
			.min(FarediscountVO :: compareTo);
		
		if(fd.isPresent()) {
			return fd.get();
		}else {
			return null;
		}

	}
	// 辨識優惠種類

}
