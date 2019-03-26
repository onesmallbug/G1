package com.mealsorderdetail.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mealsorderdetail.model.*;


public class MealsorderdetailServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("order_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mealsorderdetail/select_page_mealsorderdetail.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String order_no = null;
				try {
					order_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mealsorderdetail/select_page_mealsorderdetail.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MealsorderdetailService mealsorderdetailSvc = new MealsorderdetailService();
				MealsorderdetailVO mealsorderdetailVO = mealsorderdetailSvc.getOneMealsorderdetail(order_no);
				if (mealsorderdetailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mealsorderdetail/select_page_mealsorderdetail.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mealsorderdetailVO", mealsorderdetailVO); // 資料庫取出的empVO物件,存入req
				String url = "/mealsorderdetail/listOneMealsorderdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mealsorderdetail/select_page_mealsorderdetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String order_no = new String(req.getParameter("order_no"));
				
				/***************************2.開始查詢資料****************************************/
				MealsorderdetailService mealsorderdetailSvc = new MealsorderdetailService();
				MealsorderdetailVO mealsorderdetailVO = mealsorderdetailSvc.getOneMealsorderdetail(order_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("mealsorderdetailVO", mealsorderdetailVO);         // 資料庫取出的empVO物件,存入req
				String url = "/mealsorderdetail/update_mealsorderdetail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mealsorderdetail/listAllMealsorderdetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String order_no = new String(req.getParameter("order_no").trim());
				
				String meals_no = req.getParameter("meals_no");
				if (meals_no == null || meals_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} 

				
				Integer mo_count = null;
				try {
					mo_count = new Integer(req.getParameter("mo_count").trim());
				} catch (NumberFormatException e) {
					mo_count = 0;
					errorMsgs.add("揪團訂單 請填數字.");
				}
				
				

			

				MealsorderdetailVO mealsorderdetailVO = new MealsorderdetailVO();
				
				mealsorderdetailVO.setOrder_no(order_no);
				mealsorderdetailVO.setMeals_no(meals_no);
				mealsorderdetailVO.setMo_count(mo_count);
				
				
				System.out.println("檢查點1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點2");
					req.setAttribute("mealsorderdetailVO", mealsorderdetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mealsorderdetail/update_mealsorderdetail_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MealsorderdetailService mealsorderdetailSvc = new MealsorderdetailService();
				mealsorderdetailVO = mealsorderdetailSvc.updateMealsorderdetail(order_no, meals_no, mo_count);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mealsorderdetailVO", mealsorderdetailVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/mealsorderdetail/listOneMealsorderdetail.jsp";
				System.out.println("檢查點3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點4");
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mealsorderdetail/update_mealsorderdetail_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String order_no = req.getParameter("order_no");
				if (order_no == null || order_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} 
				
				String meals_no = req.getParameter("meals_no");
				if (meals_no == null || meals_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} 
				
				
				Integer mo_count = null;
				try {
					mo_count = new Integer(req.getParameter("mo_count").trim());
				} catch (NumberFormatException e) {
					mo_count = 0;
					errorMsgs.add("揪團訂單 請填數字.");
				}
				
				
				

				MealsorderdetailVO mealsorderdetailVO = new MealsorderdetailVO();
				
				mealsorderdetailVO.setMeals_no(meals_no);
				mealsorderdetailVO.setMo_count(mo_count);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mealsorderdetailVO", mealsorderdetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mealsorderdetail/addMealsorderdetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MealsorderdetailService mealsorderdetailSvc = new MealsorderdetailService();
				mealsorderdetailVO = mealsorderdetailSvc.addMealsorderdetail(order_no,meals_no, mo_count);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/mealsorderdetail/listAllMealsorderdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mealsorderdetail/addMealsorderdetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String order_no = new String(req.getParameter("order_no"));
				
				/***************************2.開始刪除資料***************************************/
				MealsorderdetailService mealsorderdetailSvc = new MealsorderdetailService();
				mealsorderdetailSvc.deleteMealsorderdetail(order_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/mealsorderdetail/listAllMealsorderdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mealsorderdetail/listAllMealsorderdetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
	}

}
