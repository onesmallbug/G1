<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.emp.model.*" %>

<%
	EmpVO empVO = (EmpVO)request.getAttribute("empVO");  //empServlet.java(C) 存入req的EmpVO物件
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
<body bgcolor='white'>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
<table id ="table-1">
	<tr><td>
		<h3>員工個人資料</h3>
		<h4><a href ="select_page.jsp">回員工後台首頁</a> </h4>
	</td></tr>
</table>

<table class="table table-hover">
<thead>
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>員工性別</th>
		<th>員工建立日期</th>
		<th>員工離職日期</th>
		<th>員工職位</th>
		<th>員工狀態</th>
		<th>員工密碼</th>
	</tr>
</thead>
<tbody>
	<tr>
		<th scope="row"><%=empVO.getEmployee_no()%></th>
		<td><%=empVO.getEmployee_name()%></td>
		<td>${empVO.employee_sex eq 0?"女":"男"}</td>
		<td><%=empVO.getEmployee_builddate()%></td>
		<td>${empVO.employee_quitdate eq null ? "" : "empVO.employee_quitdate" }</td>
		<td><%=empVO.getEmployee_ability()%></td>
		<td>${empVO.employee_status eq 0?"已離職":"在職中"}</td>
		<td><%=empVO.getEmployee_password()%></td>
	</tr>
</tbody>
</table>


	<!-- 工作區結束 -->
	
		<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>