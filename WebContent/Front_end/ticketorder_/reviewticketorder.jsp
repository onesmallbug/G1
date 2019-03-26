
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS start-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->
<title></title>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	<jsp:useBean id="tfSer" class="com.ticketinformation.model.TicketinformationService"></jsp:useBean>
	<div class="container">
		<div class="row justify-content">
			<div class="col-2">
			
			</div>
			<div class="col-4">
				<div>
				<H2>訂單明細</H2>
				<h5>訂單金額</h5>
				<h5>${ticketorderVO.order_amount}</h5>
				<h5>訂單時間</h5>
				<h5>${ticketorderVO.order_time}　</h5>
				<h5>優惠名稱</h5>
				<h5>${farediscountVO.fd_name}　</h5>
				</div>
			</div>
			<div class="col-4">
				<div class="row justify-content">
					<div class="col-6">
						座位
						<c:forEach var="movieticketVO" items="${list}">
						<p>${movieticketVO.mt_no}</p>
						</c:forEach>
					</div>
					<div class="col-6">
						票種
						<c:forEach var="movieticketVO" items="${list}">
						<p>${tfSer.getOneTicketinformation(movieticketVO.ti_no).ti_name}</p>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-2"></div>
		</div>
	</div>

	<!-- 工作區結束 -->

	<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>