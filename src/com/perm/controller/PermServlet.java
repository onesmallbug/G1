package com.perm.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.perm.model.PermService;
import com.perm.model.PermVO;

public class PermServlet extends HttpServlet{

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
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************1.接收請求參數-輸入格式錯誤處理*********/
				String str = req.getParameter("permission_no");
				if (str==null || (str.trim()).length()==0) {
					errorMsgs.add("請輸入權限編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/perm/select_page.jsp");
					failureView.forward(req, res);
					return;
				
				}
				
				String permission_no = null;
				try {
					permission_no =new String(str);
				}catch (Exception e) {
					errorMsgs.add("權限編號格式不正確");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/perm/select_page.jsp");
					
					failureView.forward(req, res);
					return;
				}
				
				/**********2.開始查詢資料*************/
				
				PermService permSvc = new PermService();
				PermVO permVO = permSvc.getonePerm(permission_no);
				if(permVO ==null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/perm/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/**************3.查詢完成 準備轉交*****************/
				req.setAttribute("permVO",permVO);
				String url = "/Back_end/perm/listOnePerm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/********其他可能的錯誤處理****************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/perm/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
	if("getOne_for_Update".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs",errorMsgs);
		try {
			/************1.接收請求參數*******************/
			
			String permission_no = new String(req.getParameter("permission_no"));
			
			/************2.****************/
			PermService permSvc = new PermService();
			PermVO permVO = permSvc.getonePerm(permission_no);
			
			
			/************3.****************/
			req.setAttribute("permVO",permVO);
			String url = "/Back_end/perm/update_perm_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			/******************************/
			
		}catch (Exception e) {
			errorMsgs.add("錯誤"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Back_end/perm/listAllPerm.jsp");
			failureView.forward(req, res);
		}
	}
	
	
	if("update".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		
		try {
			
			String permission_no = new String(req.getParameter("permission_no").trim());
			
			String permission_name = req.getParameter("permission_name");
			String permission_nameReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
				if(permission_name == null||permission_name.trim().length()==0) {
					errorMsgs.add("權限名稱:請勿空白");
				}else if (!permission_name.trim().matches(permission_nameReg)) {
					errorMsgs.add("權限名稱: 只能是中英文、數字和_，且長度必須在2~10之間");
				}
			
			
			PermVO permVO = new PermVO();
			permVO.setPermission_no(permission_no);
			permVO.setPermission_name(permission_name);
			
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("permVO",permVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/perm/update_perm_input.jsp");
				failureView.forward(req,res);
				return;
			}
			/************2.開始新增資料********************/
			PermService permSvc = new PermService();
			permVO = permSvc.updatePerm(permission_no , permission_name);
			
			/************3.新增完成準備轉交******************/
			
			req.setAttribute("permVO", permVO);
			String url = "/Back_end/perm/listOnePerm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			/*********************其他可能的錯誤處理**************/
			
		}catch(Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Back_end/perm/update_perm_input.jsp");
			failureView.forward(req, res);
		}
	}
	
	
	if("insert".equals(action)) {
		
		List<String>errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs",errorMsgs);
		
		/***********權限名稱***************/
		try {
		
		String permission_name = req.getParameter("permission_name");
		String permission_nameReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
			if(permission_name == null|| permission_name.trim().length()==0) {
				errorMsgs.add("權限名稱:請勿空白");
			}else if (!permission_name.trim().matches(permission_nameReg)) {
				errorMsgs.add("權限名稱: 只能是中英文、數字和_，且長度必須在2~10之間");
			}
		
		PermVO permVO = new PermVO();
		permVO.setPermission_name(permission_name);
		
		if(!errorMsgs.isEmpty()) {
			req.setAttribute("permVO",permVO);
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Back_end/perm/addPerm.jsp");
			failureView.forward(req, res);
			return;
			
	
		}
		
		
		/************2.開始新增資料*****************/
		PermService permSvc = new PermService();
		permVO = permSvc.addPerm(permission_name);
		
		/***********3.新增完成，準備轉交**************/
		
		String url = "/Back_end/perm/listAllPerm.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		
		
		/******************其他可能的錯誤處理***************/
		
		
	}catch(Exception e) {
		errorMsgs.add(e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/Back_end/perm/addPerm.jsp");
		failureView.forward(req,res);
	    }
	
    }
	
	if("delete".equals(action)) {
		List<String>errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		System.out.println("檢查點1");
		try {
			/****************1.接收請求參數***************/
			
			String permission_no = new String(req.getParameter("permission_no"));
			System.out.println("檢查點2");
			/*****************2.開始刪除資料*************/
			PermService permSvc = new PermService();
			permSvc.deletePerm(permission_no);
			System.out.println(permission_no);
			System.out.println("檢查點3");
			/******************3.刪除完成，準備轉交************/
			String url = "/Back_end/perm/listAllPerm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			System.out.println("檢查點4");
			
		}catch(Exception e) {
			errorMsgs.add("刪除資料失敗"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Back_end/perm/listAllPerm.jsp");
			failureView.forward(req, res);
			System.out.println("檢查點5");
		}
		
	}
		
	}

}
