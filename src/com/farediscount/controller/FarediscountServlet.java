package com.farediscount.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farediscount.model.*;


@WebServlet("/farediscount/FarediscountServlet")
public class FarediscountServlet extends HttpServlet{
	

	private static final long serialVersionUID = -1921776397555789457L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		 req.setCharacterEncoding("UTF-8");
		 String action = req.getParameter("action");
		 
		 if("insert".equals(action)){
			 
			 if(req.getSession().getAttribute("insert_fd_key") == null) {
					RequestDispatcher rd = req.getRequestDispatcher("/Back_end/farediscount/insertFarediscount.jsp");
					rd.forward(req, res);
					return;
				}
			 
			 List<String> errorMessage = new ArrayList<String>();
			 req.setAttribute("errorMessage", errorMessage);
			 
			 String fd_name = req.getParameter("fd_name");
			 String fd_offer = req.getParameter("fd_offer");
			 String fd_start = req.getParameter("fd_start");
			 String fd_end = req.getParameter("fd_end");
			 String fd_upper = req.getParameter("fd_upper");
			 String fd_lower = req.getParameter("fd_lower");
			 
			 if(fd_name.length() > 20) {
				 errorMessage.add("請輸入50字以內的名字");
			 }
			 //以上名稱驗證
			 Integer fd_offer_= null;
			 try {
				 fd_offer_ = Integer.valueOf(fd_offer);
				 
				 if(fd_offer_ > 0 || fd_offer_ < -100) {
					 errorMessage.add("折扣需介於0~100");
				 }
			 }catch(Exception e) {
				 errorMessage.add("優惠折扣需為數字");
			 }
			 try {
				 if(!java.sql.Date.valueOf(fd_start).before(java.sql.Date.valueOf(fd_end))) {
					 errorMessage.add("優惠起始時間需在優惠結束時間之前");
				 }
			 }catch(Exception e) {
				 errorMessage.add("優惠起始時間與優惠結束時間請使用預設方式輸入");
			 }
			 
			 if(! (Integer.valueOf(fd_upper) > Integer.valueOf(fd_lower))) {
				 errorMessage.add("優惠人數上限需大於等於優惠人數下限");
			 }
			 
			 RequestDispatcher rd= req.getRequestDispatcher("/Back_end/farediscount/insertFarediscount.jsp");
			
			 if(!errorMessage.isEmpty()) {
				 rd.forward(req, res);
				 return;
			 } {
				 errorMessage.add("新增成功");
			 }
			 
			 FarediscountService farediscountService = new FarediscountService();
			 farediscountService.addFar(" ", fd_name, fd_offer_, java.sql.Date.valueOf(fd_start), java.sql.Date.valueOf(fd_end), Integer.valueOf(fd_upper), Integer.valueOf(fd_lower), null);
			 
			 req.getSession().removeAttribute("insert_fd_key");
			 rd.forward(req, res);
			 return;
			 
		 }
		
	}
	
	
}
