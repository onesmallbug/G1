package com.ticketorder.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;


import com.ticketorder.model.*;

public class TicketorderServlet extends HttpServlet {
	
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
							.getRequestDispatcher("/ticketorder/select_page_ticketorder.jsp");
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
							.getRequestDispatcher("/ticketorder/select_page_ticketorder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				TicketorderService ticketorderSvc = new TicketorderService();
				TicketorderVO ticketorderVO = ticketorderSvc.getOneTicketorder(order_no);
				if (ticketorderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketorder/select_page_ticketorder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ticketorderVO", ticketorderVO); // 資料庫取出的empVO物件,存入req
				String url = "/ticketorder/listOneTicketorder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/select_page_ticketorder.jsp");
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
				TicketorderService ticketorderSvc = new TicketorderService();
				TicketorderVO ticketorderVO = ticketorderSvc.getOneTicketorder(order_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("ticketorderVO", ticketorderVO);         // 資料庫取出的empVO物件,存入req
				String url = "/ticketorder/update_ticketorder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/listAllTicketorder.jsp");
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
				
				String member_no = req.getParameter("member_no");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} 
//				else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				
				String fd_no = req.getParameter("fd_no").trim();
				if (fd_no == null || fd_no.trim().length() == 0) {
					errorMsgs.add("優惠編號 請勿空白");
				}	
				
				String session_no = req.getParameter("session_no").trim();
				if (session_no == null || session_no.trim().length() == 0) {
					errorMsgs.add("場次編號 請勿空白");
				}
				
				String employee_no = req.getParameter("employee_no").trim();
				if (employee_no == null || employee_no.trim().length() == 0) {
					errorMsgs.add("員工編號 請勿空白");
				}
				
				
				Integer order_group = null;
				try {
					order_group = new Integer(req.getParameter("order_group").trim());
				} catch (NumberFormatException e) {
					order_group = 0;
					errorMsgs.add("揪團訂單 請填數字.");
				}
				
				Integer order_takemeals = null;
				try {
					order_takemeals = new Integer(req.getParameter("order_takemeals").trim());
				} catch (NumberFormatException e) {
					order_takemeals = 0;
					errorMsgs.add("是否取餐 請填數字.");
				}
				
				
				java.sql.Date order_time = null;
				try {
					order_time = java.sql.Date.valueOf(req.getParameter("order_time").trim());
				} catch (IllegalArgumentException e) {
					order_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer order_amount = null;
				try {
					order_amount = new Integer(req.getParameter("order_amount").trim());
				} catch (NumberFormatException e) {
					order_amount = 0;
					errorMsgs.add("請填數字.");
				}

			

				TicketorderVO ticketorderVO = new TicketorderVO();
				
				ticketorderVO.setOrder_no(order_no);
				ticketorderVO.setMember_no(member_no);
				ticketorderVO.setFd_no(fd_no);
				ticketorderVO.setSession_no(session_no);
				ticketorderVO.setEmployee_no(employee_no);
				ticketorderVO.setOrder_group(order_group);
				ticketorderVO.setOrder_takemeals(order_takemeals);
				ticketorderVO.setOrder_time(order_time);
				ticketorderVO.setOrder_amount(order_amount);
				
				System.out.println("檢查點1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點2");
					req.setAttribute("ticketorderVO", ticketorderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketorder/update_ticketorder_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TicketorderService ticketorderSvc = new TicketorderService();
				ticketorderVO = ticketorderSvc.updateTicketorder(order_no, member_no, fd_no, session_no, employee_no, order_group, order_takemeals, order_time, order_amount);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ticketorderVO", ticketorderVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/ticketorder/listOneTicketorder.jsp";
				System.out.println("檢查點3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點4");
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/update_ticketorder_input.jsp");
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
				String member_no = req.getParameter("member_no");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} 
//				else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				
				String fd_no = req.getParameter("fd_no").trim();
				if (fd_no == null || fd_no.trim().length() == 0) {
					errorMsgs.add("優惠編號 請勿空白");
				}	
				
				String session_no = req.getParameter("session_no").trim();
				if (session_no == null || session_no.trim().length() == 0) {
					errorMsgs.add("場次編號 請勿空白");
				}
				
				String employee_no = req.getParameter("employee_no").trim();
				if (employee_no == null || employee_no.trim().length() == 0) {
					errorMsgs.add("員工編號 請勿空白");
				}
				
				
				Integer order_group = null;
				try {
					order_group = new Integer(req.getParameter("order_group").trim());
				} catch (NumberFormatException e) {
					order_group = 0;
					errorMsgs.add("揪團訂單 請填數字.");
				}
				
				Integer order_takemeals = null;
				try {
					order_takemeals = new Integer(req.getParameter("order_takemeals").trim());
				} catch (NumberFormatException e) {
					order_takemeals = 0;
					errorMsgs.add("是否取餐 請填數字.");
				}
				
				
				java.sql.Date order_time = null;
				try {
					order_time = java.sql.Date.valueOf(req.getParameter("order_time").trim());
				} catch (IllegalArgumentException e) {
					order_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer order_amount = null;
				try {
					order_amount = new Integer(req.getParameter("order_amount").trim());
				} catch (NumberFormatException e) {
					order_amount = 0;
					errorMsgs.add("請填數字.");
				}
				

				TicketorderVO ticketorderVO = new TicketorderVO();
				
				ticketorderVO.setMember_no(member_no);
				ticketorderVO.setFd_no(fd_no);
				ticketorderVO.setSession_no(session_no);
				ticketorderVO.setEmployee_no(employee_no);
				ticketorderVO.setOrder_group(order_group);
				ticketorderVO.setOrder_takemeals(order_takemeals);
				ticketorderVO.setOrder_time(order_time);
				ticketorderVO.setOrder_amount(order_amount);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ticketorderVO", ticketorderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketorder/addTicketorder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				TicketorderService ticketorderSvc = new TicketorderService();
				ticketorderVO = ticketorderSvc.addTicketorder(member_no, fd_no, session_no, employee_no, order_group, order_takemeals, order_time, order_amount);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/ticketorder/listAllTicketorder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/addTicketorder.jsp");
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
				TicketorderService ticketorderSvc = new TicketorderService();
				ticketorderSvc.deleteTicketorder(order_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/ticketorder/listAllTicketorder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/listAllTicketorder.jsp");
				failureView.forward(req, res);
			}
		}
		
	
		
		
		
	}

}
