 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.util.*" %>
<%@ page import="com.movieticke.model.*" %>

<% 


	MovieticketService movieticketSvc = new MovieticketService();
	List<MovieticketVO> list = movieticketSvc.findByOrder_no(request.getParameter("order_no"));
	MovieticketVO movieticketVO = (MovieticketVO)request.getAttribute("movieticketVO");
	pageContext.setAttribute("list",list);
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
		<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Header.jsp" />
		<h1></h1>
	
		<!-- 工作區開始 -->
		<table id = "table-1">
			<tr><td>
				<h3>電影票明細</h3>
				<input type="button" value="回首頁"
				style="padding: 5px 15px; background: white; border: 1 none; color: #969696; cursor: pointer; -webkit-border-radius: 5px; border-radius: 5px;"
				onclick="location.href='<%=request.getContextPath()%>/Front_end/mem/select_page.jsp'">
		</table>
	
		<%--錯誤列表 --%>
		<c:if test="${not empty errorMsgs}">
			<font style = "color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs }">
					<li style = "color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<table class="table table-hover">
		<thead>
			<tr>
			<th scope="col">座位編號</th>
			<th scope="col">訂單編號</th>
			<th scope="col">票種編號</th>
			</tr>
		</thead>
		<tbody>		
		<%@ include file = "/File/page1.file" %>
		<c:forEach var = "movieticketVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
			<tr>
				<th scope="row">${movieticketVO.mt_no}</th>
				<td>${movieticketVO.order_no}</td>
				<td>${movieticketVO.ti_no}</td>
			</tr>
		</c:forEach>
		</tbody>
		</table>
			
		<%@ include file="/Front_end/movieticke_/page2.file" %>	
		
	
	
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