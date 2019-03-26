 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dep.model.*" %>
<%@ page import="com.mem.model.*" %>

<%
		DepService depSvc = new DepService();
		List<DepVO> list = depSvc.findByMem_no1(((MemVO)session.getAttribute("memVO")).getMember_no());
		DepVO depVO = (DepVO)request.getAttribute("depVO");
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
	
	<h1></h1>

	<!-- 工作區開始 -->
<table id = "table-1">
	<tr><td>
		<h3>所有儲值明細 </h3>
		<input type="button" value="回首頁"
				style="padding: 5px 15px; background: white; border: 1 none; color: #969696; cursor: pointer; -webkit-border-radius: 5px; border-radius: 5px;"
				onclick="location.href='<%=request.getContextPath()%>/Front_end/mem/select_page.jsp'"></table>

<%--錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style = "color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message"  items="${errorMsgs}">
			<li style = "color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table class="table table-hover">
 <thead>
	<tr>
		<th scope="col">儲值明細編號</th>
		<th scope="col">會員編號</th>
		<th scope="col">異動金額</th>
		<th scope="col">異動日期</th>
	</tr>
</thead>
<tbody>	
	<%@ include file="page1.file" %>
	<c:forEach var="depVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
	<tr>
		<th scope="row">${depVO.deposit_change_no}</th>
		<td>${depVO.deposit_member_no}</td>
		<td>${depVO.deposit_change_money}</td>
		<td>${depVO.deposit_change_date}</td>
	</tr>
	</c:forEach>
</tbody>
</table>
<table class="table table-hover">
<tr>
<th scope="col"><font size="4">儲值剩餘點數</font></th>
<td align="left"><font color="#DC143C" size="5">${memVO.member_point}</font></td>
</tr>
</table>
<%@ include file="page2.file" %>







	<!-- 工作區結束 -->
	
	
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->
</body>
</html>