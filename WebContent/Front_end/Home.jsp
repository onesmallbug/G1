<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.movieinfo.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MovieInfoVO movieinfoVO = (MovieInfoVO) request.getAttribute("movieinfoVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
		<!-- Bootstrap CSS start-->
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
		<!-- Bootstrap CSS end-->
	<title>Home</title>

	<style>
	  table {
		background-color: 	#e0e0e0;
	    border: 2px solid black;
	    width:250px;
	    heigh:100px;
	  }
	  table  h4{
	    color: red;
	    display: block;
	    margin-bottom: 1px;
	    text-align:center;
	  }
	  table  h3{
	    color: red;
	    text-align:left;
	    display: block;
	    margin-bottom: 1px;
	  }
	  
	</style>

</head>

<body >
<jsp:include page="/FrontHeaderFooter/Header.jsp" />
		<h1></h1><br>
		
		<div class="container">
		<div class="row justify-content">
			<div class="col-1"></div>
			<div class="col-4">
<!-- 工作區開始 -->
	<br>
	<H4>影城首頁</H4>
	
	
	
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