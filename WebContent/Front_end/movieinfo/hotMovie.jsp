<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
		<!-- Bootstrap CSS start-->
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
		<!-- Bootstrap CSS end-->
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
		<jsp:include page="/FrontHeaderFooter/Header.jsp" />
		<h1></h1><br>
		
		<div class="container">
		<div class="row justify-content">
			<div class="col-1"></div>
			<div class="col-4">
<!-- 工作區開始 -->













<!-- 工作區結束 -->
			</div>
		</div>
	</div>
		
		<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
		<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
		<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
		<!-- jQuery first, then Popper.js, then Bootstrap JS end-->
</body>
</html>