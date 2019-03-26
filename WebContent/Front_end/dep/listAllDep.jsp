 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dep.model.*" %>

<%
	DepService depSvc = new DepService();
	List<DepVO> list = depSvc.getAll();
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
<body bgcolor='white'>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
<table id = "table-1">
	<tr><td>
		<h3>所有儲值明細 - listAllDep.jsp</h3>
		<h4><a href = "select_page.jsp">回首頁</a></h4>
</table>

<%--錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style = "color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message"  items="${errorMsgs}">
			<li style = "color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>儲值明細編號</th>
		<th>會員編號</th>
		<th>異動金額</th>
		<th>異動日期</th>
	</tr>
	
	<%@ include file="page1.file" %>
	<c:forEach var="depVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
	<tr>
		<td>${depVO.deposit_change_no}</td>
		<td>${depVO.deposit_member_no}</td>
		<td>${depVO.deposit_change_money}</td>
		<td>${depVO.deposit_change_date}</td>
	</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>







	<!-- 工作區結束 -->
	
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>