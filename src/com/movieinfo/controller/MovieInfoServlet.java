package com.movieinfo.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONObject;

import com.movieinfo.model.*;

@MultipartConfig
public class MovieInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("movie_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/movieinfo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String movie_no = null;
				try {
					movie_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("電影編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/movieinfo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MovieInfoService movieinfoSvc = new MovieInfoService();
				MovieInfoVO movieinfoVO = movieinfoSvc.getOneMovieInfo(movie_no);
				if (movieinfoVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/movieinfo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movieinfoVO", movieinfoVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/movieinfo/listOneMovieInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/movieinfo/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
/*********************************前台上映中單一查詢************************************************/		
		if ("getOne_For_Display_Front".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("movie_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影編號");
				}

				String movie_no = null;
				movie_no = new String(str);

				/*************************** 2.開始查詢資料 *****************************************/
				MovieInfoService movieinfoSvc = new MovieInfoService();
				MovieInfoVO movieinfoVO = movieinfoSvc.getOneMovieInfo(movie_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movieinfoVO", movieinfoVO); // 資料庫取出的empVO物件,存入req
				String url = "/Front_end/movieinfo/listOneMovieInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

		}
		
		/*********************************前台電影院單一查詢************************************************/		
		if ("getOne_For_Display_Front_Chatroom".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("movie_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影編號");
				}

				String movie_no = null;
				movie_no = new String(str);

				/*************************** 2.開始查詢資料 *****************************************/
				MovieInfoService movieinfoSvc = new MovieInfoService();
				MovieInfoVO movieinfoVO = movieinfoSvc.getOneMovieInfo(movie_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movieinfoVO", movieinfoVO); // 資料庫取出的empVO物件,存入req
				String url = "/Front_end/chatroom/chatroom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

		}
		
/*********************************前台未上映單一查詢************************************************/		
		if ("getOne_For_Display_FrontUpComing".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("movie_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影編號");
				}

				String movie_no = null;
				movie_no = new String(str);

				/*************************** 2.開始查詢資料 *****************************************/
				MovieInfoService movieinfoSvc = new MovieInfoService();
				MovieInfoVO movieinfoVO = movieinfoSvc.getOneMovieInfo(movie_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movieinfoVO", movieinfoVO); // 資料庫取出的empVO物件,存入req
				String url = "/Front_end/movieinfo/upComingListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

		}
		
/*********************************前台年度排名查詢************************************************/	
		
		if("get_list_byscore".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				java.sql.Date stdate = java.sql.Date.valueOf(req.getParameter("stdate"));
				java.sql.Date enddate = java.sql.Date.valueOf(req.getParameter("enddate"));
				

				/*************************** 2.開始查詢資料 *****************************************/
				MovieInfoService movieinfoSvc = new MovieInfoService();
				List<MovieInfoVO> rankmovie = movieinfoSvc.getAllByScore(stdate, enddate);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				String outstr = new Gson().toJson(rankmovie);
//				System.out.println(outstr);
//				res.setContentType("text/plain");
//				res.setCharacterEncoding("UTF-8");
//				PrintWriter out = res.getWriter();
//				out.write(outstr);
//				out.flush();
//				out.close();
//				
				
				
				req.setAttribute("movieinfoVO", rankmovie); // 資料庫取出的empVO物件,存入req
				String url = "/Front_end/movieinfo/rankMovie.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			
			
	}
		
/***************************未上映電影期待按鈕***************************************/	
		if ("exp".equals(action) ) { // 來自select_page.jsp的請求
			
			MovieInfoService mis = new MovieInfoService();
			String movie_no = req.getParameter("movie_no");
			MovieInfoVO mvo = mis.getOneMovieInfo(movie_no);
			Integer exp = mvo.getMovie_exp();
			Integer noexp = mvo.getMovie_noexp();
					
			exp+=1;
			mis.updateExp(movie_no, exp);
				
			HashMap checkMap = new HashMap();
			checkMap.put("exp", exp);
			checkMap.put("noexp",noexp);
			
			JSONObject responseJSONObject = new JSONObject(checkMap);
			PrintWriter out = res.getWriter();
			out.println(responseJSONObject);
			out.close();
		}
		
/***************************未上映電影不期待按鈕***************************************/	
		if ("noexp".equals(action) ) { // 來自select_page.jsp的請求
			
			MovieInfoService mis2 = new MovieInfoService();
			String movie_no = req.getParameter("movie_no");
			MovieInfoVO mvo2 = mis2.getOneMovieInfo(movie_no);
			Integer noexp = mvo2.getMovie_noexp();
			Integer exp = mvo2.getMovie_exp();
			
			noexp+=1;
			mis2.updateNoExp(movie_no, noexp);
			
			HashMap checkMap = new HashMap();
			checkMap.put("exp", exp);
			checkMap.put("noexp",noexp);	
			
			JSONObject responseJSONObject = new JSONObject(checkMap);
			PrintWriter out = res.getWriter();
			out.println(responseJSONObject);
			out.close();
			
		}
		
		
/******************************************************************/		

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String movie_no = new String(req.getParameter("movie_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				MovieInfoService movieinfoSvc = new MovieInfoService();
				MovieInfoVO movieinfoVO = movieinfoSvc.getOneMovieInfo(movie_no);
				
				Base64.Encoder encoder = Base64.getEncoder();


				if (movieinfoVO.getMovie_level() != null) {
					String encodeText = encoder.encodeToString(movieinfoVO.getMovie_level());
					req.setAttribute("encodeText", encodeText);
				}
				
				if (movieinfoVO.getMovie_pic() != null) {
					String encodeText2 = encoder.encodeToString(movieinfoVO.getMovie_pic());
					req.setAttribute("encodeText2", encodeText2);
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("movieinfoVO", movieinfoVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/movieinfo/update_MovieInfo_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/movieinfo/listAllMovieInfo.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String movie_no = new String(req.getParameter("movie_no").trim());
				
				Integer genre_no = null;
				try {
					genre_no = new Integer(req.getParameter("genre_no").trim());
				}catch(Exception e) {
					errorMsgs.add("電影種類:請勿空白");
				}
				
				String movie_name = req.getParameter("movie_name").trim();
				if (movie_name == null || movie_name.trim().length() == 0) {
					errorMsgs.add("電影名稱:請勿空白");
				} 
				
				Double movie_score = new Double(req.getParameter("movie_score").trim());
//				String scoreReg= new String("^[(0-9)]$");
//				
//				if(!(String.valueOf(movie_score).trim().matches(scoreReg))) {
//					errorMsgs.add("電影分數:只能是數字");
//				}
				
				byte[] movie_level = null;
				try {
					Part part = req.getPart("movie_level");
					if (part.getSubmittedFileName() != "") {
						BufferedInputStream bif = new BufferedInputStream(part.getInputStream());
						ByteArrayOutputStream bao = new ByteArrayOutputStream();
						int len;
						byte[] b = new byte[8192];
						while ((len = bif.read(b)) != -1) {
							bao.write(b);
						}
						movie_level = bao.toByteArray();
					}else {
						movie_level = new MovieInfoService().getOneMovieInfo(movie_no).getMovie_level();
					}
				} catch (Exception e) {
					errorMsgs.add("上傳照片失敗，請重新上傳");
				}
				
				String movie_director = req.getParameter("movie_director").trim();
				if (movie_director == null || movie_director.trim().length() == 0) {
					errorMsgs.add("導演請勿空白");
				}
				
				String movie_intro = req.getParameter("movie_intro").trim();
				if (movie_intro == null || movie_intro.trim().length() == 0) {
					errorMsgs.add("簡介請勿空白");
				}
				
				String movie_trailer = req.getParameter("movie_trailer").trim();
				
				
				byte[] movie_pic = null;
				try {
					Part part = req.getPart("movie_pic");
					if (part.getSubmittedFileName() != "") {
						BufferedInputStream bif = new BufferedInputStream(part.getInputStream());
						ByteArrayOutputStream bao = new ByteArrayOutputStream();
						int len;
						byte[] b = new byte[8192];
						while ((len = bif.read(b)) != -1) {
							bao.write(b);
						}
						movie_pic = bao.toByteArray();
					}else {
					movie_pic = new MovieInfoService().getOneMovieInfo(movie_no).getMovie_pic();
				}
				} catch (Exception e) {
					errorMsgs.add("上傳封面失敗，請重新上傳");
				}
				
				java.sql.Date movie_in = null;
				try {
					movie_in = java.sql.Date.valueOf(req.getParameter("movie_in").trim());
				} catch (IllegalArgumentException e) {
					movie_in = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上映日期!");
				}
				
				java.sql.Date movie_out = null;
				try {
					movie_out = java.sql.Date.valueOf(req.getParameter("movie_out").trim());
				} catch (IllegalArgumentException e) {
					movie_out = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入下映日期!");
				}
				
				String movie_length = req.getParameter("movie_length").trim();
				if (movie_length == null || movie_length.trim().length() == 0) {
					errorMsgs.add("片長請勿空白");
				}
				
				String movie_cast = req.getParameter("movie_cast").trim();
				if (movie_cast == null || movie_cast.trim().length() == 0) {
					errorMsgs.add("演員請勿空白");
				}
				
				Integer movie_count = 0;
				
				
				Integer movie_exp = 0;
					movie_exp = new Integer(req.getParameter("movie_exp").trim());
				
				Integer movie_noexp = 0;
					movie_noexp = new Integer(req.getParameter("movie_noexp").trim());
				
				Integer movie_touch = 0;
				
				
				Integer movie_ticket = 0;
				try {
					movie_ticket = new Integer(req.getParameter("movie_ticket").trim());
				} catch (Exception e) {
					errorMsgs.add("片長加價請勿空白");
				}
				

				MovieInfoVO movieinfoVO = new MovieInfoVO();
				
				movieinfoVO.setMovie_no(movie_no);
				movieinfoVO.setGenre_no(genre_no);
				movieinfoVO.setMovie_name(movie_name);
				movieinfoVO.setMovie_score(movie_score);
				movieinfoVO.setMovie_level(movie_level);
				movieinfoVO.setMovie_director(movie_director);
				movieinfoVO.setMovie_cast(movie_cast);
				movieinfoVO.setMovie_intro(movie_intro);
				movieinfoVO.setMovie_length(movie_length);
				movieinfoVO.setMovie_trailer(movie_trailer);
				movieinfoVO.setMovie_pic(movie_pic);
				movieinfoVO.setMovie_in(movie_in);
				movieinfoVO.setMovie_out(movie_out);
				movieinfoVO.setMovie_count(movie_count);
				movieinfoVO.setMovie_exp(movie_exp);
				movieinfoVO.setMovie_noexp(movie_noexp);
				movieinfoVO.setMovie_touch(movie_touch);
				movieinfoVO.setMovie_ticket(movie_ticket);
				
				/******************* 若原本有圖片會保留 *********************************/
				Base64.Encoder encoder = Base64.getEncoder();

				if (movieinfoVO.getMovie_level() != null) {
					String encodeText = encoder.encodeToString(movieinfoVO.getMovie_level());
					req.setAttribute("encodeText", encodeText);
				}
				
				if (movieinfoVO.getMovie_pic() != null) {
					String encodeText2 = encoder.encodeToString(movieinfoVO.getMovie_pic());
					req.setAttribute("encodeText2", encodeText2);
				}
				

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movieinfoVO", movieinfoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/movieinfo/update_MovieInfo_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MovieInfoService movieinfoSvc = new MovieInfoService();
				movieinfoVO = movieinfoSvc.updateMovieInfo(movie_no, genre_no, movie_name, movie_score, movie_level, movie_director,
						movie_cast, movie_intro, movie_length, movie_trailer, movie_pic, movie_in, movie_out, movie_count, 
						movie_exp, movie_noexp, movie_touch, movie_ticket);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movieinfoVO", movieinfoVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Back_end/movieinfo/listOneMovieInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/movieinfo/update_MovieInfo_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				Integer genre_no = null;
				try {
					genre_no = new Integer(req.getParameter("genre_no").trim());
				}catch(Exception e) {
					errorMsgs.add("電影種類請勿空白");
				}
				
				String movie_name = req.getParameter("movie_name");
				String movie_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (movie_name == null || movie_name.trim().length() == 0) {
					errorMsgs.add("電影名稱: 請勿空白");
				} else if (!movie_name.trim().matches(movie_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
				}
				Double movie_score = 0.0;
				byte[] movie_level = null;
				try {
					Part part = req.getPart("movie_level");
					if (part.getSubmittedFileName() != "") {
						BufferedInputStream bif = new BufferedInputStream(part.getInputStream());
						ByteArrayOutputStream bao = new ByteArrayOutputStream();
						int len;
						byte[] b = new byte[8192];
						while ((len = bif.read(b)) != -1) {
							bao.write(b);
						}
						movie_level = bao.toByteArray();
					}
				} catch (Exception e) {
					errorMsgs.add("上傳照片失敗，請重新上傳");
				}
				String movie_director = req.getParameter("movie_director").trim();
				if (movie_director == null || movie_director.trim().length() == 0) {
					errorMsgs.add("導演請勿空白");
				}
				String movie_intro = req.getParameter("movie_intro").trim();
				if (movie_intro == null || movie_intro.trim().length() == 0) {
					errorMsgs.add("簡介請勿空白");
				}
				String movie_trailer = req.getParameter("movie_trailer").trim();
				if (movie_trailer == null || movie_trailer.trim().length() == 0) {
					errorMsgs.add("預告片網址請勿空白");
				}
				
				byte[] movie_pic = null;
				try {
					Part part = req.getPart("movie_pic");
					if (part.getSubmittedFileName() != "") {
						BufferedInputStream bif = new BufferedInputStream(part.getInputStream());
						ByteArrayOutputStream bao = new ByteArrayOutputStream();
						int len;
						byte[] b = new byte[8192];
						while ((len = bif.read(b)) != -1) {
							bao.write(b);
						}
						movie_pic = bao.toByteArray();
					}
				} catch (Exception e) {
					errorMsgs.add("上傳封面失敗，請重新上傳");
				}
				
				java.sql.Date movie_in = null;
				try {
					movie_in = java.sql.Date.valueOf(req.getParameter("movie_in").trim());
				} catch (IllegalArgumentException e) {
					movie_in = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上映日期!");
				}
				
				java.sql.Date movie_out = null;
				try {
					movie_out = java.sql.Date.valueOf(req.getParameter("movie_out").trim());
				} catch (IllegalArgumentException e) {
					movie_out = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入下映日期!");
				}
				
				String movie_length = req.getParameter("movie_length").trim();
				if (movie_length == null || movie_length.trim().length() == 0) {
					errorMsgs.add("片長請勿空白");
				}
				
				String movie_cast = req.getParameter("movie_cast").trim();
				if (movie_cast == null || movie_cast.trim().length() == 0) {
					errorMsgs.add("演員請勿空白");
				}
				
				Integer movie_count = 0;
				
				
				Integer movie_exp = 0;
				
				
				Integer movie_noexp = 0;
				
				
				Integer movie_touch = 0;
				
				
				Integer movie_ticket = 0;
				try {
					movie_ticket = new Integer(req.getParameter("movie_ticket").trim());
				} catch (Exception e) {
					errorMsgs.add("片長加價請勿空白");
				}

				MovieInfoVO movieinfoVO = new MovieInfoVO();
				
				movieinfoVO.setGenre_no(genre_no);
				movieinfoVO.setMovie_name(movie_name);
				movieinfoVO.setMovie_score(movie_score);
				movieinfoVO.setMovie_level(movie_level);
				movieinfoVO.setMovie_director(movie_director);
				movieinfoVO.setMovie_cast(movie_cast);
				movieinfoVO.setMovie_intro(movie_intro);
				movieinfoVO.setMovie_length(movie_length);
				movieinfoVO.setMovie_trailer(movie_trailer);
				movieinfoVO.setMovie_pic(movie_pic);
				movieinfoVO.setMovie_in(movie_in);
				movieinfoVO.setMovie_out(movie_out);
				movieinfoVO.setMovie_count(movie_count);
				movieinfoVO.setMovie_exp(movie_exp);
				movieinfoVO.setMovie_noexp(movie_noexp);
				movieinfoVO.setMovie_touch(movie_touch);
				movieinfoVO.setMovie_ticket(movie_ticket);

				
				Base64.Encoder encoder = Base64.getEncoder();


				if (movieinfoVO.getMovie_level() != null) {
					String encodeText = encoder.encodeToString(movieinfoVO.getMovie_level());
					req.setAttribute("encodeText", encodeText);
				}
				
				if (movieinfoVO.getMovie_pic() != null) {
					String encodeText2 = encoder.encodeToString(movieinfoVO.getMovie_pic());
					req.setAttribute("encodeText2", encodeText2);
				}
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movieinfoVO", movieinfoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/movieinfo/addMovieInfo.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				

				/*************************** 2.開始新增資料 ***************************************/
				MovieInfoService movieinfoSvc = new MovieInfoService();
				movieinfoVO = movieinfoSvc.addMovieInfo(genre_no, movie_name, movie_score, movie_level, movie_director,
						movie_cast, movie_intro, movie_length, movie_trailer, movie_pic, movie_in, movie_out, movie_count, movie_exp, movie_noexp, movie_touch, movie_ticket);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/Back_end/movieinfo/listAllMovieInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/movieinfo/addMovieInfo.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String movie_no = new String(req.getParameter("movie_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				MovieInfoService movieinfoSvc = new MovieInfoService();
				movieinfoSvc.deleteMovieInfo(movie_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/Back_end/movieinfo/listAllMovieInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/movieinfo/listAllMovieInfo.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
