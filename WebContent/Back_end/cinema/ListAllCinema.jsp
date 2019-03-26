<%@page import="com.cinema.model.CinemaVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
		<title>瀏覽廳院</title>
		<style>
		  table {
			width: 600px;
			background-color: white;
			margin-top: 5px;
			margin-bottom: 5px;
		  }
		  	table, th, td {
		    border: 1px solid #CCCCFF;
		  }
		 	th, td {
		    padding: 5px;
		    text-align: center;
		  }
		</style>
	
	</head>
	<body>
		<jsp:include page="/BackHeaderFooter/Header.jsp" />
		<div class="container">
			<div class="row justify-content">
				<div class="col-12">
					<h1>瀏覽廳院</h1>
				
					<!-- 工作區開始 -->
					<table class="table table-sm">
						<thead>
							<tr>
								<th scope="col">廳院編號</th>
								<th scope="col">廳院名稱</th>
								<th scope="col">廳院容量</th>
								<th scope="col">票價調整</th>
								<th scope="col">廳院狀態</th>
								<th scope="col">設定廳院</th>
							</tr>
						</thead>
						<tbody>
							<jsp:useBean id="cinemaSvc" class="com.cinema.model.CinemaService" scope="page"/>
							
							<%
							List<CinemaVO> list = cinemaSvc.getAll();
							request.setAttribute("list", list); 
							%>
							<%@ include file="/File/page1.file" %>
							<c:forEach var="cinemaVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									
									<td>${cinemaVO.cinema_no}</td>
									<td>${cinemaVO.cinema_name}</td>
									<td>${cinemaVO.cinema_size}</td>
									<td>${cinemaVO.cinema_correct}</td>
									<td>${cinemaVO.cinema_status}</td>
									<td><a class = "btn btn-primary" href="<%=request.getContextPath()%>/Back_end/cinema/updateCinema.jsp?cinema_no=${cinemaVO.cinema_no}">設定</a></td>
										
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="/File/page2_modify.file" %>
				</div>
			</div>
		</div>

	<!-- 工作區結束 -->
	
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script	src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script	src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>