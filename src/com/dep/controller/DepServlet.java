package com.dep.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dep.model.DepService;
import com.dep.model.DepVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

public class DepServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException,IOException{
		
	  req.setCharacterEncoding("UTF-8");
	  String action = req.getParameter("action");
	
	  
	  /*************************************************前台*****************************************************/
	  
	  
	  
	  if ("getOne_For_Display".equals(action)) {
		  
		  List<String> errorMsgs = new LinkedList<String>();
		  req.setAttribute("errorMsgs",errorMsgs);
		  
		  try {
			  /**********1.接收請求參數*************************/
			  System.out.println("檢查點1");
			  String str = req.getParameter("deposit_change_no");
			  if (str==null || (str.trim()).length()==0) {
				  
				  errorMsgs.add("請輸入儲值明細編號");
			  }
			  System.out.println("檢查點2");
			  if(!errorMsgs.isEmpty()) {
				  RequestDispatcher failureView = req
						  .getRequestDispatcher("/Front_end/dep/select_page.jsp");
				  failureView.forward(req, res);
				  return;
			  }
			  System.out.println("檢查點3");
			  String deposit_change_no = null;
			  try {
				  deposit_change_no = new String(str);
			  }catch(Exception e) {
				  errorMsgs.add("儲值編號格式不正確");
			  }
			  System.out.println("檢查點4");
			  if(!errorMsgs.isEmpty()) {
				  RequestDispatcher failureView =req
						  .getRequestDispatcher("/Front_end/dep/select_page.jsp");
				  failureView.forward(req, res);
				  return;
			  }
			  
			  /***************2.開始查詢資料***************************/
			  System.out.println("檢查點5");
			  DepService depSvc = new DepService();
			  DepVO depVO = depSvc.getoneDep(deposit_change_no);
			  if(depVO == null) {
				  errorMsgs.add("查無資料");
			  }
			  System.out.println("檢查點6");
			  if(!errorMsgs.isEmpty()){
				  RequestDispatcher failureView = req
						  .getRequestDispatcher("/Front_end/dep/select_page.jsp");
				  failureView.forward(req, res);
				  return;
			  }
		  
		  
			  /******************3.查詢完成 準備轉交********************************/
			  req.setAttribute("depVO", depVO);
			  String url="/Front_end/dep/listOneDep.jsp";
			  RequestDispatcher successView = req.getRequestDispatcher(url);
			  successView.forward(req, res);
			  System.out.println("檢查點8");
			  /***********其他可能的錯誤處理*************/
		  }catch(Exception e) {
			  errorMsgs.add("無法取得資料" + e.getMessage());
			  RequestDispatcher failureView = req
					  .getRequestDispatcher("/Front_end/dep/select_page.jsp");
			  failureView.forward(req, res);
			  System.out.println("檢查點9");
		  }
	  }
	  
	  
 if ("getOne_For_Display_Mem".equals(action)) {
		  

			  /**********1.接收請求參數*************************/
			  
			  String deposit_member_no = req.getParameter("member_no");
			 
				
			  
			  /***************2.開始查詢資料***************************/
			 
			  DepService depSvc = new DepService();
			  List<DepVO> list = depSvc.findByMem_no1(deposit_member_no);
			 
		  System.out.println(list);
		  
			  /******************3.查詢完成 準備轉交********************************/
			  req.setAttribute("list", list);
			  String url="/Front_end/dep/listOneDep_Mem.jsp";
			  RequestDispatcher successView = req.getRequestDispatcher(url);
			  successView.forward(req, res);
			  
			 
		
	  }
	  
	  
	  
	  
	  
	  
	  
		  
	
	  if("insert".equals(action)) {
		  
		  List<String> errorMsgs = new LinkedList<String>();
		  req.setAttribute("errorMsgs",errorMsgs);
		  
		  /*************會員編號***************/
		  try {
		  String deposit_member_no = req.getParameter("deposit_member_no");
		  
		  /************異動金額***************/
		  
		  Integer deposit_change_money = null;
		  try {
			  deposit_change_money = new Integer(req.getParameter("deposit_change_money").trim());
			  
		  }catch (NumberFormatException e) {
			  deposit_change_money = 0;
			  errorMsgs.add("請輸入正確數字");
		  }
		  
		  
		  /***********異動日期***************/
		  
		  java.sql.Timestamp deposit_change_date = null;
//		  try {
//			  deposit_change_date = java.sql.Timestamp.valueOf(req.getParameter("deposit_change_date").trim());
//		  }catch (IllegalArgumentException e) {
			  deposit_change_date = new java.sql.Timestamp(System.currentTimeMillis());
//			  errorMsgs.add("請選取正確日期");  
//		  }
		  
		  DepVO depVO = new DepVO();
		  depVO.setDeposit_member_no(deposit_member_no);
		  depVO.setDeposit_change_money(deposit_change_money);
		  depVO.setDeposit_change_date(deposit_change_date);
		  
		  if (!errorMsgs.isEmpty()) {
			  req.setAttribute("depVO",depVO);
			  RequestDispatcher failureView = req
					  .getRequestDispatcher("/Front_end/dep/addDep.jsp");
			  failureView.forward(req,res);
			  return;
		  }
		  
		  /*******************2.開始新增資料*************************/
		  DepService depSvc = new DepService();
		  depVO = depSvc.addDep(deposit_member_no, deposit_change_money,deposit_change_date);
		  
		  /******************3.新增完成 準備轉交*******************/
		  
		  String url = "/Front_end/dep/listAllDep.jsp";
		  RequestDispatcher successView = req.getRequestDispatcher(url);
		  successView.forward(req, res);
		  
		  /***************其他的錯誤處理************/
		  
	    }catch(Exception e) {
	    	errorMsgs.add(e.getMessage());
	    	RequestDispatcher failureView = req
	    			.getRequestDispatcher("/Front_end/dep/addDep.jsp");
	    	failureView.forward(req, res);
	    }
		  
	  }
	  
	  
if("mem_insert".equals(action)) {
		  
		  List<String> errorMsgs = new LinkedList<String>();
		  req.setAttribute("errorMsgs",errorMsgs);
		  
		  
		  /*************儲值編號********************/
		  try {
		  String deposit_change_no = req.getParameter("deposit_change_no");
		  
		  /*************會員編號***************/
		  
		  String deposit_member_no = req.getParameter("deposit_member_no");
		  
		  /************異動金額***************/
		  
		  Integer deposit_change_money = null;
		  try {
			  deposit_change_money = new Integer(req.getParameter("deposit_change_money").trim());
			  
		  }catch (NumberFormatException e) {
			  deposit_change_money = 0;
			  errorMsgs.add("請輸入正確數字");
		  }
		  
		  
		  /***********異動日期***************/
		  
		  java.sql.Timestamp deposit_change_date = null;
//		  try {
//			  deposit_change_date = java.sql.Timestamp.valueOf(req.getParameter("deposit_change_date").trim());
//		  }catch (IllegalArgumentException e) {
			  deposit_change_date = new java.sql.Timestamp(System.currentTimeMillis());
//			  errorMsgs.add("請選取正確日期");  
//		  }
		  
		  DepVO depVO = new DepVO();
		  depVO.setDeposit_member_no(deposit_member_no);
		  depVO.setDeposit_change_money(deposit_change_money);
		  depVO.setDeposit_change_date(deposit_change_date);
		  
System.out.println("檢查點1");		  

		  MemVO memVO = (MemVO)req.getSession().getAttribute("memVO");
		  
		  
System.out.println("檢查點2");

		  if (!errorMsgs.isEmpty()) {
			  req.setAttribute("depVO",depVO);
			  RequestDispatcher failureView = req
					  .getRequestDispatcher("/Front_end/dep/addDep.jsp");
			  failureView.forward(req,res);
			  return;
		  }
System.out.println("檢查點3");		  
		  /*******************2.開始新增資料*************************/
		  DepService depSvc = new DepService();
		  //depVO = depSvc.addDep(deposit_member_no, deposit_change_money,deposit_change_date);
		  depSvc.insertDepositMain(depVO,memVO);
		  
System.out.println("檢查點4");		  

		  req.setAttribute("depVO", depVO);
		  
		  
		  memVO.setMember_point(new MemService().getoneMem(memVO.getMember_no()).getMember_point());
		  
System.out.println("檢查點5");
		  /******************3.新增完成 準備轉交*******************/
		  
		  String url = "/Front_end/dep/listOneDep.jsp";
		  RequestDispatcher successView = req.getRequestDispatcher(url);
		  successView.forward(req, res);
		  
		  /***************其他的錯誤處理************/
		  
	    }catch(Exception e) {
	    	errorMsgs.add(e.getMessage());
	    	RequestDispatcher failureView = req
	    			.getRequestDispatcher("/Front_end/dep/addDep.jsp");
	    	failureView.forward(req, res);
	    }
System.out.println("檢查點6");		  
	  }


	if("insert_Token".equals(action)) {
		String deposit_member_no = req.getParameter("member_no");
		DepVO depVO = new DepVO();
		depVO.setDeposit_member_no(deposit_member_no);
		req.setAttribute("depVO",depVO);
		RequestDispatcher successView = req.getRequestDispatcher("/Front_end/dep/addDep.jsp");
		successView.forward(req, res);
		
		
	}
	  
	  
	  
/******************************************************後台************************************************************/
	 if ("getOne_For_Display_Back".equals(action)) {
		  
		  List<String> errorMsgs = new LinkedList<String>();
		  req.setAttribute("errorMsgs",errorMsgs);
		  
		  try {
			  /**********1.接收請求參數*************************/
			  System.out.println("檢查點1");
			  String str = req.getParameter("deposit_change_no");
			  if (str==null || (str.trim()).length()==0) {
				  
				  errorMsgs.add("請輸入儲值明細編號");
			  }
			  System.out.println("檢查點2");
			  if(!errorMsgs.isEmpty()) {
				  RequestDispatcher failureView = req
						  .getRequestDispatcher("/Back_end/dep/select_page.jsp");
				  failureView.forward(req, res);
				  return;
			  }
			  System.out.println("檢查點3");
			  String deposit_change_no = null;
			  try {
				  deposit_change_no = new String(str);
			  }catch(Exception e) {
				  errorMsgs.add("儲值編號格式不正確");
			  }
			  System.out.println("檢查點4");
			  if(!errorMsgs.isEmpty()) {
				  RequestDispatcher failureView =req
						  .getRequestDispatcher("/Back_end/dep/select_page.jsp");
				  failureView.forward(req, res);
				  return;
			  }
			  
			  /***************2.開始查詢資料***************************/
			  System.out.println("檢查點5");
			  DepService depSvc = new DepService();
			  DepVO depVO = depSvc.getoneDep(deposit_change_no);
			  if(depVO == null) {
				  errorMsgs.add("查無資料");
			  }
			  System.out.println("檢查點6");
			  if(!errorMsgs.isEmpty()){
				  RequestDispatcher failureView = req
						  .getRequestDispatcher("/Back_end/dep/select_page.jsp");
				  failureView.forward(req, res);
				  return;
			  }
		  
		  
			  /******************3.查詢完成 準備轉交********************************/
			  req.setAttribute("depVO", depVO);
			  String url="/Back_end/dep/listOneDep.jsp";
			  RequestDispatcher successView = req.getRequestDispatcher(url);
			  successView.forward(req, res);
			  System.out.println("檢查點8");
			  /***********其他可能的錯誤處理*************/
		  }catch(Exception e) {
			  errorMsgs.add("無法取得資料" + e.getMessage());
			  RequestDispatcher failureView = req
					  .getRequestDispatcher("/Back_end/dep/select_page.jsp");
			  failureView.forward(req, res);
			  System.out.println("檢查點9");
		  }
	  }
	
	  
	  
	  
	  
	  
	}
	  
	  
	  
	  }
  
		
	

