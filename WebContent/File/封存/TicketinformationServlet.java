package com.ticketinformation.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.ticketinformation.model.*;


public class TicketinformationServlet extends HttpServlet {
	
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
				String str = req.getParameter("ti_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketinformation/select_page_ticketinformation.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String ti_no = null;
				try {
					ti_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketinformation/select_page_ticketinformation.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				TicketinformationService ticketinformationSvc = new TicketinformationService();
				TicketinformationVO ticketinformationVO = ticketinformationSvc.getOneTicketinformation(ti_no);
				if (ticketinformationVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketinformation/select_page_ticketinformation.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ticketinformationVO", ticketinformationVO); // 資料庫取出的empVO物件,存入req
				String url = "/ticketinformation/listOneTicketinformation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketinformation/select_page_ticketinformation.jsp");
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
				String ti_no = new String(req.getParameter("ti_no"));
				
				/***************************2.開始查詢資料****************************************/
				TicketinformationService ticketinformationSvc = new TicketinformationService();
				TicketinformationVO ticketinformationVO = ticketinformationSvc.getOneTicketinformation(ti_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("ticketinformationVO", ticketinformationVO);         // 資料庫取出的empVO物件,存入req
				String url = "/ticketinformation/update_ticketinformation_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketinformation/listAllTicketinformation.jsp");
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
				String ti_no = new String(req.getParameter("ti_no").trim());
				
				String ti_name = req.getParameter("ti_name");
				if (ti_name == null || ti_name.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} 
				
				String ti_price = req.getParameter("ti_price").trim();
				if (ti_price == null || ti_price.trim().length() == 0) {
					errorMsgs.add("優惠編號 請勿空白");
				}	
				
				

				TicketinformationVO ticketinformationVO = new TicketinformationVO();
				
				ticketinformationVO.setTi_no(ti_no);
				ticketinformationVO.setTi_name(ti_name);
				ticketinformationVO.setTi_price(ti_price);
				
				
				System.out.println("檢查點1");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點2");
					req.setAttribute("ticketinformationVO", ticketinformationVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketinformation/update_ticketinformation_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TicketinformationService ticketinformationSvc = new TicketinformationService();
				ticketinformationVO = ticketinformationSvc.updateTicketinformation(ti_no, ti_name, ti_price);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ticketinformationVO", ticketinformationVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/ticketinformation/listOneTicketinformation.jsp";
				System.out.println("檢查點3");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("檢查點4");
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketinformation/update_ticketinformation_input.jsp");
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
				String ti_name = req.getParameter("ti_name");
				if (ti_name == null || ti_name.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} 
				
				String ti_price = req.getParameter("ti_price").trim();
				if (ti_price == null || ti_price.trim().length() == 0) {
					errorMsgs.add("優惠編號 請勿空白");
				}	
				
				
				

				TicketinformationVO ticketinformationVO = new TicketinformationVO();
				
				ticketinformationVO.setTi_no(ti_name);
				ticketinformationVO.setTi_name(ti_price);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ticketinformationVO", ticketinformationVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketinformation/addTicketinformation.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				TicketinformationService ticketinformationSvc = new TicketinformationService();
				ticketinformationVO = ticketinformationSvc.addTicketinformation(ti_name, ti_price);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/ticketinformation/listAllTicketinformation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketinformation/addTicketinformation.jsp");
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
				String ti_no = new String(req.getParameter("ti_no"));
				
				/***************************2.開始刪除資料***************************************/
				TicketinformationService ticketinformationSvc = new TicketinformationService();
				ticketinformationSvc.deleteTicketinformation(ti_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/ticketinformation/listAllTicketinformation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketinformation/listAllTicketinformation.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
	}

}
