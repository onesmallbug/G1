 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mem.model.*" %>

<%
	MemVO memVO = (MemVO)session.getAttribute("memVO"); 
%>



<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS start-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->
<title></title>
</head>
<body>
	
	<h1></h1>

	<!-- 工作區開始 -->
<table id = "table-1" >
	<tr><td>
		<h3>會員個人資料</h3>
		<input type="button" value="回首頁"
				style="padding: 5px 15px; background: white; border: 1 none; color: #969696; cursor: pointer; -webkit-border-radius: 5px; border-radius: 5px;"
				onclick="location.href='select_page.jsp'">

			</td></tr>
</table>	

<table class="table table-hover">
<thead>
	<tr>
		<th scope="col">會員編號</th>
		<th scope="col">會員帳號</th>
		<th scope="col">會員密碼</th>
		<th scope="col">會員姓名</th>
		<th scope="col">會員暱稱</th>
		<th scope="col">會員性別</th>
		<th scope="col">會員生日</th>
		<th scope="col">會員地址</th>
		<th scope="col">會員電話</th>
		<th scope="col">會員信箱</th>
		<th scope="col">會員圖片</th>
		<th scope="col">會員信用卡</th>
		<th scope="col">會員驗證碼</th>
		<th scope="col">會員建立日期</th>
		<th scope="col">會員儲值點數</th>
		<th scope="col">會員狀態</th>
	</tr>
</thead>
<tbody>
	<tr>
		<td><%=memVO.getMember_no()%></td>
		<td><%=memVO.getMember_account()%></td>
		<td><%=memVO.getMember_password()%></td>
		<td><%=memVO.getMember_name()%></td>
		<td><%=memVO.getMember_nick()%></td>
		<td>${memVO.member_sex eq 0?"女":"男"}</td>
		<td><%=memVO.getMember_birthday()%></td>
		<td><%=memVO.getMember_address()%></td>
		<td><%=memVO.getMember_telephone()%></td>
		<td><%=memVO.getMember_email()%></td>
		<td>
			<img src='<%= request.getContextPath() %>/Front_end/mem/mem.do?member_no=${memVO.member_no}' width='200' height = '200'/>
		</td>
		<td><%=memVO.getMember_credit_number()%></td>
		<td><%=memVO.getMember_back_verification()%></td>
		<td><%=memVO.getMember_buildday()%></td>
		<td><%=memVO.getMember_point()%></td>
		<td>${memVO.member_status eq 1?"已驗證":"未驗證"}</td>
		
	</tr>
	</tbody>
	
</table>


	<!-- 工作區結束 -->
	
	
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>