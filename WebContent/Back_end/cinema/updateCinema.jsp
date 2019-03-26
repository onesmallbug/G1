<%@ page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
<title>設定廳院</title>
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1>設定廳院</h1>

	<!-- 工作區開始 -->
	<jsp:useBean id="cinemaSvc" class="com.cinema.model.CinemaService" scope="page"/>
	<%
	CinemaVO cinemaVO ;
	Object object = request.getAttribute("cinemaVO");
	
	if(object == null){
		cinemaVO = cinemaSvc.getOneCin(request.getParameter("cinema_no"));
		pageContext.setAttribute("cinemaVO", cinemaVO);
	} else{
		cinemaVO = (CinemaVO)object;
	}
	%>

	<div class="container">
		<div class="row justify-content">
			<div class="col-4">
				<form action="<%= request.getContextPath()%>/Cinema/CinemaServlet" method="post">
					<table>
					
						<ul>
							<c:forEach var="message" items="${errorMessage}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>			
									
						<tr>
							<th>廳院編號</th>
							<td>${cinemaVO.cinema_no}</td>
						</tr>
						<tr>
							<th>廳院名稱</th>
							<td><input type="text" name="cinema_name"
								value="${cinemaVO.cinema_name}"></td>
						</tr>
						<tr>
							<th>廳院容量</th>
							<td><P id = "p">${cinemaVO.cinema_size}</P></td>
						</tr>
						<tr>
							<th>票價調整</th>
							<td><input type="text" name="cinema_correct"
								value="${cinemaVO.cinema_correct}"></td>
						</tr>
						<tr>
							<th>廳院狀態</th>
							<td><input type="text" name="cinema_status"
								value="${cinemaVO.cinema_status}"></td>
						</tr>

					</table>
					<input id="hiddensizeinput" type="hidden" name="cinema_size"	value="${cinemaVO.cinema_size}"> 
					<input type="hidden" name="cinema_no" value="${cinemaVO.cinema_no}"> 
					<input id="hiddentypeinput" type="hidden" name="cinema_type" value="${cinemaVO.cinema_type}">
					<input type="hidden" name="action" value="updateCinema"> 
					<input class="btn btn-primary" type="submit" value="送出修改">
				</form>
			</div>
			
			<!-- ---------------------以上一般input---------------------- -->
			<%@ include file="/Back_end/cinema/insertAndupdateTogether.file" %>

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