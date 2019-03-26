package com.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

public class EmpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req , HttpServletResponse res)
			throws ServletException,IOException {
		doPost(req ,res);
		
	}
	
		
		public void doPost(HttpServletRequest req , HttpServletResponse res)
			throws ServletException,IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) { //來自select_page.jsp之請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs" , errorMsgs);
			
			try {
				/***************1.接收請求參數-輸入格式錯誤的處理*/
				String str =req.getParameter("employee_no");
				if (str== null || (str.trim()).length()==0) {
					errorMsgs.add("請輸入員工編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/emp/select_page.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				
				String employee_no = null;
				try {
					employee_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
			
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req
							.getRequestDispatcher("/Back_end/emp/select_page.jsp");
					failureView.forward(req,res);
					return;//程式中斷
				}
				
				/**************2.開始查詢資料**********/
				
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getoneEmp(employee_no);
				if(empVO ==null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/emp/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/**************3.查詢完成，準備轉交*****************/
				req.setAttribute("empVO" , empVO); //資料庫取出的empVO物件，存入req
				String url = "/Back_end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listOneEmp.jsp
				successView.forward(req, res);
				
				
				/****************其他可能的錯誤處理***********************/	
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Back_end/emp/select_page.jsp");
			failureView.forward(req, res);
		}
	
	}
		
	if ("getOne_for_Update".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs",errorMsgs);
		try {
			/***************************1.接收請求參數*******************/
			String employee_no = new String(req.getParameter("employee_no"));
			
			/****************2.********************/
			EmpService empSvc = new EmpService();
			EmpVO empVO = empSvc.getoneEmp(employee_no);
			
			/****************3.******************/
			req.setAttribute("empVO", empVO);
			String url = "/Back_end/emp/update_emp_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			/*********************/
			
		}catch (Exception e) {
			errorMsgs.add("錯誤:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Back_end/emp/listAllEmp.jsp");
			failureView.forward(req, res);
		}
		
		
		
	}
	

	if("update".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		
		
		try {
			
			String employee_no = new String(req.getParameter("employee_no").trim());
			
			String employee_name = req.getParameter("employee_name");
			String employee_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			System.out.println(!employee_name.trim().matches(employee_nameReg));
				if(employee_name ==null || employee_name.trim().length()==0 ) {
					errorMsgs.add("員工姓名: 請勿空白");
				}else if (!employee_name.trim().matches(employee_nameReg)) {
					errorMsgs.add("員工姓名: 只能是中、英文、數字和_ ， 且長度必須在2~10之間");
				}
			
			Integer employee_sex = null;
			try {
			employee_sex = new Integer(req.getParameter("employee_sex").trim());
			}catch(Exception e) {
				errorMsgs.add("請勾選性別!");
			}
			/*********員工建立日期******/
			
			java.sql.Date employee_builddate = null;
			try {
				employee_builddate  =java.sql.Date.valueOf(req.getParameter("employee_builddate").trim());
			}catch(IllegalArgumentException e) {
				employee_builddate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			
			
			/*********離職日期******/
			
			java.sql.Date employee_quitdate = null;
//			employee_quitdate  =java.sql.Date.valueOf(req.getParameter("employee_quitdate").trim());
			
			/***********員工職稱***********/
			String employee_ability = req.getParameter("employee_ability");
			String employee_abilityReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(employee_ability ==null || employee_ability.trim().length()==0 ) {
					errorMsgs.add("職位名稱: 請勿空白");
				}else if (!employee_ability.trim().matches(employee_abilityReg)) {
					errorMsgs.add("職位名稱: 只能是中、英文、數字和_ ， 且長度必須在2~10之間");
				}
			
			/************員工狀態***************/
			String employee_status = req.getParameter("employee_status");
			
			/***********員工密碼*******************/
			
			String employee_password = req.getParameter("employee_password");
			String employee_passwordReg = "^[(a-zA-Z0-9)]{2,20}$";
			//System.out.println(!member_password.trim().matches(member_passwordReg));
				if(employee_password==null || employee_password.trim().length()==0) {
					errorMsgs.add("員工密碼: 請勿空白");
				}else if (!employee_password.trim().matches(employee_passwordReg)) {
					errorMsgs.add("員工密碼: 只能是英文、數字，且長度必須在2~10之間");
				}
		
			/***********再次確認員工密碼*****************/			
				String employee_password1 = req.getParameter("employee_password1");
				String employee_passwordReg1 = "^[(a-zA-Z0-9)]{2,20}$";
				//System.out.println(!employee_password1.trim().matches(employee_passwordReg1));
					if(employee_password1==null || employee_password1.trim().length()==0) {
						errorMsgs.add("員工密碼: 請勿空白");
					}else if (!employee_password1.trim().matches(employee_passwordReg1)) {
						errorMsgs.add("員工密碼: 只能是英文、數字，且長度必須在2~10之間");
					}
					
					
				if (!employee_password .equals (employee_password1)) {
						errorMsgs.add("員工密碼輸入不一致 請重新輸入");
					}
				
			
			
			
			
				EmpVO empVO = new EmpVO();
				empVO.setEmployee_no(employee_no);
				empVO.setEmployee_name(employee_name);
				empVO.setEmployee_sex(employee_sex);
				empVO.setEmployee_builddate(employee_builddate);
				empVO.setEmployee_quitdate(employee_quitdate);
				empVO.setEmployee_ability(employee_ability);
				empVO.setEmployee_status(employee_status);
				empVO.setEmployee_password(employee_password);
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); //含有輸入格式錯誤的empVO物件，也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return;
					
				}
				/************************2.開始新增資料***********/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(employee_no,employee_name, employee_sex,employee_builddate,employee_quitdate,employee_ability, employee_status,employee_password);
				
				/**************************3.新增完成 準備轉交******************/
				
				req.setAttribute("empVO" , empVO);
				String url = "/Back_end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);  //新增成功後轉交listAll
				successView.forward(req, res);
				
				/*******************其他可能的錯誤處理******************/
			
		}catch(Exception e) {
			
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Back_end/emp/update_emp_input.jsp");
			failureView.forward(req, res);
			
		}
	
 }

	
	
	
	
if("insert".equals(action)) { //來自addEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*******員工姓名***************/
			try {
					
					String employee_name = req.getParameter("employee_name");
					String employee_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					System.out.println(!employee_name.trim().matches(employee_nameReg));
						if(employee_name ==null || employee_name.trim().length()==0 ) {
							errorMsgs.add("員工姓名: 請勿空白");
						}else if (!employee_name.trim().matches(employee_nameReg)) {
							errorMsgs.add("員工姓名: 只能是中、英文、數字和_ ， 且長度必須在2~10之間");
						}
					
					Integer employee_sex = null;
					try {
					employee_sex = new Integer(req.getParameter("employee_sex").trim());
					}catch(Exception e) {
						errorMsgs.add("請勾選性別!");
					}
					/*********員工建立日期******/
					
					java.sql.Date employee_builddate = null;
					try {
						employee_builddate  =java.sql.Date.valueOf(req.getParameter("employee_builddate").trim());
					}catch(IllegalArgumentException e) {
						employee_builddate = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					
					/*********離職日期******/
					
					java.sql.Date employee_quitdate = null;
//					employee_quitdate  =java.sql.Date.valueOf(req.getParameter("employee_quitdate").trim());
					
					/***********員工職稱***********/
					String employee_ability = req.getParameter("employee_ability");
					String employee_abilityReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
						if(employee_ability ==null || employee_ability.trim().length()==0 ) {
							errorMsgs.add("職位名稱: 請勿空白");
						}else if (!employee_ability.trim().matches(employee_abilityReg)) {
							errorMsgs.add("職位名稱: 只能是中、英文、數字和_ ， 且長度必須在2~10之間");
						}
					
					/************員工狀態***************/
					String employee_status = req.getParameter("employee_status");
					
					
					/***********員工密碼*******************/
					
					String employee_password = req.getParameter("employee_password");
					String employee_passwordReg = "^[(a-zA-Z0-9)]{2,20}$";
					//System.out.println(!member_password.trim().matches(member_passwordReg));
						if(employee_password==null || employee_password.trim().length()==0) {
							errorMsgs.add("員工密碼: 請勿空白");
						}else if (!employee_password.trim().matches(employee_passwordReg)) {
							errorMsgs.add("員工密碼: 只能是英文、數字，且長度必須在2~10之間");
						}
				
					/***********再次確認員工密碼*****************/			
						String employee_password1 = req.getParameter("employee_password1");
						String employee_passwordReg1 = "^[(a-zA-Z0-9)]{2,20}$";
						//System.out.println(!employee_password1.trim().matches(employee_passwordReg1));
							if(employee_password1==null || employee_password1.trim().length()==0) {
								errorMsgs.add("員工密碼: 請勿空白");
							}else if (!employee_password1.trim().matches(employee_passwordReg1)) {
								errorMsgs.add("員工密碼: 只能是英文、數字，且長度必須在2~10之間");
							}
							
							
						if (!employee_password .equals (employee_password1)) {
								errorMsgs.add("員工密碼輸入不一致 請重新輸入");
							}
					
					
					
					
					EmpVO empVO = new EmpVO();
					empVO.setEmployee_name(employee_name);
					empVO.setEmployee_sex(employee_sex);
					empVO.setEmployee_builddate(employee_builddate);
					empVO.setEmployee_quitdate(employee_quitdate);
					empVO.setEmployee_ability(employee_ability);
					empVO.setEmployee_status(employee_status);
					empVO.setEmployee_password(employee_password);
					
					
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("empVO", empVO); //含有輸入格式錯誤的empVO物件，也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Back_end/emp/addEmp.jsp");
						failureView.forward(req, res);
						return;
						
					}
					/************************2.開始新增資料***********/
					EmpService empSvc = new EmpService();
					empVO = empSvc.addEmp(employee_name,employee_sex,employee_builddate,employee_quitdate,employee_ability, employee_status,employee_password);
					
					/**************************3.新增完成 準備轉交******************/
					
					String url = "/Back_end/emp/listAllEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);  //新增成功後轉交listAll
					successView.forward(req, res);
					
					/*******************其他可能的錯誤處理******************/
				
			}catch(Exception e) {
				
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/emp/addEmp.jsp");
				failureView.forward(req, res);
				
			}
		
	 }

if ("delete".equals(action)) { // 來自listAllEmp.jsp的請求

	List<String> errorMsgs = new LinkedList<String>();
	
	req.setAttribute("errorMsgs", errorMsgs);
	
	try {
		/***************************1.接收請求參數*******************/
		
		String employee_no = new String (req.getParameter("employee_no"));
		
		/*********************2.開始刪除資料*************************/
		EmpService empSvc = new EmpService();
		empSvc.delete(employee_no);
		
		/*********************3.刪除完成 準備轉交******************************/
		String url = "/Back_end/emp/listAllEmp.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後，轉交回送出刪除的來源網頁
		successView.forward(req, res);
	}catch (Exception e) {
		errorMsgs.add("刪除資料失敗:" +e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/Back_end/emp/listAllEmp.jsp");
		failureView.forward(req, res);
		}
	

	}


	if("listEmps_ByCompositeQuery".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			
			/**************************1.將資料轉為MAP**************/
			
			HttpSession session = req.getSession();
			Map<String,String[]> map = (Map<String,String[]>)session.getAttribute("map");
			if (req.getParameter("whichPage")==null) {
				HashMap<String,String[]>map1 = new HashMap<String,String[]>(req.getParameterMap());
				session.setAttribute("map", map1);
				map = map1;
			}
			
			/******************************2.開始複合查詢*************************/
			EmpService empSvc = new EmpService();
			List<EmpVO> list = empSvc.getAll(map);
			
			/*****************3.查詢完成 準備轉交**********************/
			req.setAttribute("listEmps_ByCompositeQuery",list);
			RequestDispatcher successView = req.getRequestDispatcher("/Back_end/emp/listEmps_ByCompositeQuery.jsp");
			successView.forward(req, res);	
		}catch(Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Back_end/emp/select_page.jsp");
			failureView.forward(req, res);
		}
		
		
	}



















	
	
  }
}
