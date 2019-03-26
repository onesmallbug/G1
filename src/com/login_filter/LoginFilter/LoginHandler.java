package com.login_filter.LoginFilter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import com.mem.controller.memberServlet.MailService;
import com.mem.model.MemService;
import com.mem.model.MemVO;

@WebServlet("/loginhandler")
public class LoginHandler extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		if ("getone_for_login".equals(action)) { // 來自pswForget.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/************* 1.接收請求參數 輸入格式的錯誤處理 ***********/
				/* 帳號錯誤處理 */
				String str1 = req.getParameter("member_account");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/Login.jsp");
					failureView.forward(req, res);
					return;
				}

				String member_account = null;
				try {
					member_account = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("會員帳號格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/Login.jsp");
					failureView.forward(req, res);
					return;
				}

				/* 密碼錯誤處理 */

				String str2 = req.getParameter("member_password");
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/Login.jsp");
					failureView.forward(req, res);
					return;
				}

				String member_password = null;
				try {
					member_password = new String(str2);
				} catch (Exception e) {
					errorMsgs.add("會員密碼格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/Login.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************會員狀態錯誤處理******************************/
//				String str3 = req.getParameter("member_status");
//				if (!str3.equals("1")) {
//					errorMsgs.add("請至EMAIL收取驗證信");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/Login.jsp");
//					failureView.forward(req, res);
//					return;
//				}

				/*********************** 2.開始查詢資料 **************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getoneByAccountMem(member_account);
				if (memVO == null) {
					errorMsgs.add("帳號輸入錯誤");

				} 
					
				else if (!memVO.getMember_password().equals(req.getParameter("member_password"))) {
						errorMsgs.add("密碼輸入錯誤");
					}
				
				else if (!memVO.getMember_status().equals ("1")) {
					errorMsgs.add("帳號未驗證，請至信箱收取驗證信");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/Login.jsp");
					failureView.forward(req, res);
					return;
				}

				/************************* 3.查詢完成 準備轉交 ***********************************/
				
				HttpSession session = req.getSession();
				session.setAttribute("memVO", memVO);
				
				try {
					String myself = (String) session.getAttribute("myself");
					if (myself !=null) {
						session.removeAttribute("myself");
						res.sendRedirect(myself);
						return;
					}
					
					String location =(String) session.getAttribute("location");
					if (location !=null) {
						session.removeAttribute("location");
						res.sendRedirect(location);
						return;
					}

				}catch( Exception ignored) { }
				
				res.sendRedirect(req.getContextPath()+"/Front_end/mem/select_page.jsp");

//				String url = "/Front_end/mem/select_page.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交至LoginSucess.jsp
//				successView.forward(req, res);
				
				/***********************其他可能錯誤處理*************/
System.out.println("檢查點9");

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/Front_end/Login.jsp");
				failView.forward(req, res);
				
System.out.println("檢查點10");
			}
		}
		
		
		/*************************************忘記密碼頁面，帳號與EMAIL***************************************/
		
		if ("getone_for_pswForget".equals(action)) { // 來自Login.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/************* 1.接收請求參數 輸入格式的錯誤處理 ***********/
				/* 帳號錯誤處理 */
				String str1 = req.getParameter("member_account");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/mem/pswForget.jsp");
					failureView.forward(req, res);
					return;
				}

				String member_account = null;
				try {
					member_account = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("會員帳號格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/mem/pswForget.jsp");
					failureView.forward(req, res);
					return;
				}

				/* 信箱錯誤處理 */

				String member_email = req.getParameter("member_email");
				if (member_email == null || (member_email.trim()).length() == 0) {
					errorMsgs.add("請輸入信箱");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/mem/pswForget.jsp");
					failureView.forward(req, res);
					return;
				}

				

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/mem/pswForget.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
		
				
				
				
				

				/*********************** 2.開始查詢資料 **************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getoneByAccountMem(member_account);
				if (memVO == null) {
					errorMsgs.add("帳號輸入錯誤");

				} 
					
				else if (!memVO.getMember_email().equals(req.getParameter("member_email"))) {
						errorMsgs.add("信箱輸入錯誤");
					}
				

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/mem/pswForget.jsp");
					failureView.forward(req, res);
					return;
				}
System.out.println("檢查點1");
				/************************* 3.查詢完成 準備轉交 ***********************************/
				//req.getSession().setAttribute("memVO", memVO); // 資料庫取出的empVO物件，存入reqi
System.out.println("檢查點2");
				
				HttpSession session = req.getSession();
				
				/***************************************信箱發送********************************************/
//				
				//String member_password = new Double((Math.random()*Math.pow(10, 20))/Math.pow(10, 20)).toString();  
				String member_password = new Integer((int) (Math.random()*900001+100000)).toString();
				MemService memSvc2 = new MemService();
				memSvc2.update_member_password(str1,member_password);
				
				String subject = "修改密碼通知";
			      
//			    String member_name = "親愛的會員";
//			    String member_password = "您的密碼";
			  //String messageText = "Hello! " + member_name + " 請謹記您的密碼: " + member_password + "\n" +" (已經啟用)"+"\n"+"請點擊連結重新登入"+"http://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/Front_end/mem/mem.do"+"?action=verified&"+"member_no="+memVO.getMember_no(); 
			    String messageText = "Hello! " + memVO.getMember_name() +" 請謹記您的新密碼: " + member_password +"\n"+"請點擊連結重返登入畫面"+"http://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/Front_end/Login.jsp";  
			    
			    MailService mailService = new MailService();
			    mailService.sendMail(member_email, subject, messageText);


					req.setAttribute("memVO",memVO);
					String url = "/Front_end/mem/pswEmailForget.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
				//res.sendRedirect(req.getContextPath()+"/Front_end/mem/pswEmailForget.jsp");
				
System.out.println("檢查點8");	

//				String url = "/Front_end/mem/select_page.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交至LoginSucess.jsp
//				successView.forward(req, res);
				
				/***********************其他可能錯誤處理*************/
System.out.println("檢查點9");

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/Front_end/mem/pswForget.jsp");
				failView.forward(req, res);
				
System.out.println("檢查點10");
			}
		}
		
		
		
	}
	
public class MailService {
		
		// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
		public void sendMail(String to, String subject, String messageText) {
				
		   try {
			   // 設定使用SSL連線至 Gmail smtp Server
			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
		       // ●須將myGmail的【安全性較低的應用程式存取權】打開
		     final String myGmail = "ca106join@gmail.com";
		     final String myGmail_password = "JOIN123456";
			   Session session = Session.getInstance(props, new Authenticator() {
				   protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(myGmail, myGmail_password);
				   }
			   });

			   Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(myGmail));
			   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			  
			   //設定信中的主旨 
			   message.setSubject(subject);
			   //設定信中的內容
			   message.setText(messageText);

			   Transport.send(message);
			   System.out.println("傳送成功!");
	     }catch (MessagingException e){
		     System.out.println("傳送失敗!");
		     e.printStackTrace();
	     }
	   }
		
		 

	}
	
}
