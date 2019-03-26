 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.dep.model.*" %>

<%
	DepVO depVO = (DepVO)request.getAttribute("depVO");
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
<title>會員儲值明細</title>
</head>
<body bgcolor='white'>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
<table id="table-1">
	<tr><td>
		<h3>會員儲值明細</h3>
		<h4><a href = "<%=request.getContextPath()%>/Back_end/dep/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table class="table table-hover">
<thead>
	<tr>
		<th>儲值明細編號</th>
		<th>會員編號</th>
		<th>異動金額</th>
		<th>異動日期</th>
	</tr>
</thead>
<tbody>
	<tr>
		<th scope="row"><%=depVO.getDeposit_change_no()%></th>
		<td><%=depVO.getDeposit_member_no()%></td>
		<td><%=depVO.getDeposit_change_money()%></td>
		<td><%=depVO.getDeposit_change_date()%></td>
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