<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.cinema.model.*"%>
<%@ page import="com.cinema.controller.*"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<title>新增廳院</title>
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1>新增廳院</h1>

	<!-- 工作區開始 -->
	
	<jsp:useBean id="cinemaVO" scope="request" class="com.cinema.model.CinemaVO"/>
	
	<%
		if(cinemaVO.getCinema_type() == null){
			cinemaVO.setCinema_type("4000000000000000000000004400000000000000000000000440000000000000000000000044000000000000000000000004400000000000000000000000440000000000000000000000044000000000000000000000004400000000000000000000000440000000000000000000000044000000000000000000000004400000000000000000000000440000000000000000000000044000000000000000000000004400000000000000000000000440000000000000000000000044444444444444444444444444");
		}
	%>


	<div class="container">
		<div class="row justify-content">
			<div class="col-4">
						<ul>
							<c:forEach var="message" items="${errorMessage}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
				<form action="<%= request.getContextPath()%>/Cinema/CinemaServlet" method="post">
					<table>


						<tr>
							<th>廳院名稱</th>
							<td><input type="text" name="cinema_name" value="<c:out value="${cinemaVO.cinema_name}" default=""/>"></td>
						</tr>
						<tr>
							<th>廳院容量</th>
							<td><P id = "p"><c:out value="${cinemaVO.cinema_size}" default="0"/></P></td>
						</tr>
						<tr>
							<th>票價調整</th>
							<td><input type="text" name="cinema_correct" value="<c:out value="${cinemaVO.cinema_correct}" default="0"/>"></td>
						</tr>
						<tr>
							<th>廳院狀態</th>
							<td><input type="text" name="cinema_status" value="<c:out value="${cinemaVO.cinema_status}" default="可用"/>"></td>
						</tr>

					</table>
					<input id="hiddensizeinput" type="hidden" name="cinema_size"	value="<c:out value="${cinemaVO.cinema_size}" default="0"/>"> 
					<input id="hiddentypeinput" type="hidden" name="cinema_type" value="<c:out value="${cinemaVO.cinema_type}" default="0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"/>">
					<input type="hidden" name="action" value="insertCinema"> 
					<input class="btn btn-primary" type="submit" value="新增廳院">
				</form>
			</div>
			
			<!-- ---------------------以上一般input---------------------- -->
			<%@ include file="/Back_end/cinema/insertAndupdateTogether.file" %>

	<!-- 工作區結束 -->

	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script	src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script	src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>
<%request.getSession().setAttribute("insert_Cinema_key", new Object());%>

