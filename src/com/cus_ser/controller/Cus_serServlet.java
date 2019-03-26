package com.cus_ser.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cus_ser.model.Cus_serService;
import com.cus_ser.model.Cus_serVO;

public class Cus_serServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req , HttpServletResponse res)
			throws ServletException,IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs" , errorMsgs);
		try {
			/************1.接收請求參數**************************/
			String str=req.getParameter("customer_service_event_no");
			if (str==null || (str.trim()).length()==0) {
				errorMsgs.add("請輸入客服單據編號");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/cus_ser/select_page.jsp");
				failureView.forward(req,res);
				return ;
			}
			
			String customer_service_event_no = null;
			try {
				customer_service_event_no = new String(str);
			}catch(Exception e) {
				errorMsgs.add("客服單據編號不正確");
			}
					
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/cus_ser/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************2.開始查詢資料*********************/
			Cus_serService cus_serSvc = new Cus_serService();
			Cus_serVO cus_serVO = cus_serSvc.getonecus_ser(customer_service_event_no);
			if(cus_serVO ==null) {
				errorMsgs.add("查無資料");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/cus_ser/select_page.jsp");
				failureView.forward(req,res);
				return;
			}
			
			/*******************3.查詢完成 準備轉交************************/
			req.setAttribute("cus_serVO", cus_serVO);
			String url = "/Front_end/cus_ser/listOneCus_ser.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/****************其他可能的錯誤處理*************/
			
		}catch(Exception e) {
			errorMsgs.add("無法取得資料"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("Front_end/cus_ser/select_page.jsp");
			failureView.forward(req, res);
		}		
	}
	
	if("getOne_for_Update".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs" , errorMsgs);
		
		try {
			/*****************1.接收請求參數***********/
			String customer_service_event_no = new String(req.getParameter("customer_service_event_no"));
			
			/*****************2.**************/
			Cus_serService cus_serSvc = new Cus_serService();
			Cus_serVO cus_serVO = cus_serSvc.getonecus_ser(customer_service_event_no);
			
			/****************3.*************/
			
			req.setAttribute("cus_serVO",cus_serVO);
			String url = "/Front_end/cus_ser/update_cus_ser_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req,res);
			
			/****************************/
		}catch (Exception e) {
			errorMsgs.add("錯誤" +e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Front_end/cus_ser/listAllCus_ser.jsp");
			failureView.forward(req, res);
		}
	}
	
	
	if("update".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs" , errorMsgs);
		
		try {
			/***************客服單據編號******************/
			String customer_service_event_no = new String(req.getParameter("customer_service_event_no").trim());   
			
			/***************會員編號******************/
			String member_no = req.getParameter("member_no");
			String member_noReg="^[(a-zA-Z0-9_)]{2,10}$";
			  if(member_no ==null || member_no.trim().length()==0) {
				  errorMsgs.add("會員編號請勿空白");
			  }else if (!member_no.trim().matches(member_noReg)) {
				  errorMsgs.add("會員編號必須是英文、數字，且長度必須在2~10之間");
			  }
			  
			  /***************客服處理開始時間******************/  
			java.sql.Date customer_service_start_date = null;
			try {
				customer_service_start_date =java.sql.Date.valueOf(req.getParameter("customer_service_start_date").trim());
				
			}catch(IllegalArgumentException e) {
				customer_service_start_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			  
			/***************客服處理狀態******************/  
			  
			Integer customer_service_status =null;
			try {
				
				customer_service_status = new Integer(req.getParameter("customer_service_status").trim());
			}catch(Exception e) {
				errorMsgs.add("請選擇處理狀態");
			}
			  
			Cus_serVO cus_serVO = new Cus_serVO();
			cus_serVO.setCustomer_service_event_no(customer_service_event_no);
			cus_serVO.setMember_no(member_no);
			cus_serVO.setCustomer_service_start_date(customer_service_start_date);
			cus_serVO.setCustomer_service_status(customer_service_status);
			  
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("cus_serVO",cus_serVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/cus_ser/update_cus_ser_input.jsp");
				
				failureView.forward(req, res);
				return;
			}
			
			/***************2.開始新增資料********************/
			Cus_serService cus_serSvc = new Cus_serService();
			cus_serVO = cus_serSvc.updatecus_ser(customer_service_event_no,member_no,customer_service_start_date,customer_service_status);
			  
			
			/**************3.新增完成，準備轉交**************/
			
			req.setAttribute("cus_serVO",cus_serVO);
			String url = "/Front_end/cus_ser/listOneCus_ser.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			/*******************其他可能的錯誤處理******/
		}catch(Exception e) {
			
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Front_end/cus_ser/update_cus_ser_input.jsp");
			failureView.forward(req, res);
		}	
	}
	
	if("insert".equals(action)) {
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			  
			
			/***************會員編號******************/
			String member_no = req.getParameter("member_no");
			String member_noReg="^[(a-zA-Z0-9_)]{2,10}$";
			  if(member_no ==null || member_no.trim().length()==0) {
				  errorMsgs.add("會員編號請勿空白");
			  }else if (!member_no.trim().matches(member_noReg)) {
				  errorMsgs.add("會員編號必須是英文、數字，且長度必須在2~10之間");
			  }
			  
			  /***************客服處理開始時間******************/  
			java.sql.Date customer_service_start_date = null;
			try {
				customer_service_start_date =java.sql.Date.valueOf(req.getParameter("customer_service_start_date").trim());
				
			}catch(IllegalArgumentException e) {
				customer_service_start_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			  
			/***************客服處理狀態******************/  
			  
			Integer customer_service_status =null;
			try {
				
				customer_service_status = new Integer(req.getParameter("customer_service_status").trim());
			}catch(Exception e) {
				errorMsgs.add("請選擇處理狀態");
			}
			Cus_serVO cus_serVO = new Cus_serVO();
			cus_serVO.setMember_no(member_no);
			cus_serVO.setCustomer_service_start_date(customer_service_start_date);
			cus_serVO.setCustomer_service_status(customer_service_status);
			  
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("cus_serVO",cus_serVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front_end/cus_ser/addCus_ser.jsp");
				failureView.forward(req, res);
				return;
			}
			/**************2.開始新增資料*************************/
			Cus_serService cus_serSvc = new Cus_serService();
			cus_serVO = cus_serSvc.addCus_ser(member_no,customer_service_start_date,customer_service_status);
			
			/**************3.新增完成，準備轉交***********************/
		
			String url = "/Front_end/cus_ser/listAllCus_ser.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*****************其他可能的錯誤處理*************/
		
	}catch(Exception e) {
		
		errorMsgs.add(e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/Front_end/cus_ser/addCus_ser.jsp");
		failureView.forward(req, res);
	  }
	}
	
	if("delete".equals(action)) {
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			/****************1.接收請求參數********************/
			
			String customer_service_event_no = new String(req.getParameter("customer_service_event_no"));
			
			/****************2.開始刪除資料*******************/
			Cus_serService cus_serSvc = new Cus_serService();
			cus_serSvc.deletecus_ser(customer_service_event_no);
			
			/*****************3.刪除完成，準備轉交************/
			String url = "/Front_end/cus_ser/listAllCus_ser.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req,res);
		}catch(Exception e) {
			errorMsgs.add("刪除資料失敗"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Front_end/cus_ser/listAllCus_ser.jsp");
			failureView.forward(req, res);
			
		}
		
	  }
	}

}
