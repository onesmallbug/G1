package com.moviegenre.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moviegenre.model.MovieGenreService;
import com.moviegenre.model.MovieGenreVO;

public class MovieGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MovieGenreServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = req.getParameter("genre_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影種類編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/moviegenre/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer genre_no = null;
				try {
					genre_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("電影種類編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/moviegenre/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				MovieGenreService moviegenreSvc = new MovieGenreService();
				MovieGenreVO moviegenreVO = moviegenreSvc.getOneGenre(genre_no);
				if (moviegenreVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/moviegenre/listOneMovieGenre.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("moviegenreVO", moviegenreVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/moviegenre/listOneMovieGenre.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/moviegenre/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer genre_no = new Integer(req.getParameter("genre_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				MovieGenreService moviegenreSvc = new MovieGenreService();
				MovieGenreVO moviegenreVO = moviegenreSvc.getOneGenre(genre_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("moviegenreVO", moviegenreVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/moviegenre/update_MovieGenre_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/moviegenre/listAllMovieGenre.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer genre_no = new Integer(req.getParameter("genre_no").trim());

				String genre_name = req.getParameter("genre_name");
				String genre_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
				if (genre_name == null || genre_name.trim().length() == 0) {
					errorMsgs.add("電影編號名稱: 請勿空白");
				} else if (!genre_name.trim().matches(genre_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影編號名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到50之間");
				}

				MovieGenreVO moviegenreVO = new MovieGenreVO();
				moviegenreVO.setGenre_no(genre_no);
				moviegenreVO.setGenre_name(genre_name);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("moviegenreVO", moviegenreVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/moviegenre/update_MovieGenre_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MovieGenreService moviegenreSvc = new MovieGenreService();
				moviegenreVO = moviegenreSvc.updateMovieGenre(genre_no, genre_name);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("moviegenreVO", moviegenreVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Back_end/moviegenre/listOneMovieGenre.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/moviegenre/update_MovieGenre_input.jsp");
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
				String genre_name = req.getParameter("genre_name");
				String genre_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
				if (genre_name == null || genre_name.trim().length() == 0) {
					errorMsgs.add("電影種類名稱: 請勿空白");
				} else if (!genre_name.trim().matches(genre_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影種類名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				MovieGenreVO moviegenreVO = new MovieGenreVO();
				moviegenreVO.setGenre_name(genre_name);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("moviegenreVO", moviegenreVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/moviegenre/addMovieGenre.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MovieGenreService moviegenreSvc = new MovieGenreService();
				moviegenreVO = moviegenreSvc.addMovieGenre(genre_name);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/Back_end/moviegenre/listAllMovieGenre.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/moviegenre/addMovieGenre.jsp");
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
				Integer genre_no = new Integer(req.getParameter("genre_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				MovieGenreService moviegenreSvc = new MovieGenreService();
				moviegenreSvc.deleteMovieGenre(genre_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/Back_end/moviegenre/listAllMovieGenre.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:此種類有相關連電影" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/moviegenre/listAllMovieGenre.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
