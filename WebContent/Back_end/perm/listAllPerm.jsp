<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*" %>
<%@ page import="com.perm.model.*" %>

<%
	PermService permSvc = new PermService();
	List<PermVO>list = permSvc.getAll();
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
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	
	<table id = "table-1">
		<tr><td>
			<h3>所有員工資料</h3>
			<h4><a href ="select_page.jsp">回首頁</a></h4>
		</td></tr>
	</table>
	
	<%--錯誤列表 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
<table>
	<tr>
		<th>權限編號</th>
		<th>權限名稱</th>
	</tr>
	
	<%@ include file = "page1.file" %>
	<c:forEach var="permVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1 %>">              
	
	<tr>
		<td>${permVO.permission_no}</td>
		<td>${permVO.permission_name}</td>	
		<td>
			<FORM METHOD = "post" ACTION="<%=request.getContextPath()%>/Back_end/perm/perm.do" style="margin-bottom:0px;">     
				<input type = "submit" value="修改">
				<input type = "hidden" name="permission_no" value="${permVO.permission_no}">
				<input type = "hidden" name="action" value="getOne_for_Update">
			</FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_end/perm/perm.do" style="margin-bottom:0px;">
				<input type ="submit" value="刪除">
				<input type = "hidden" name="permission_no" value="${permVO.permission_no}">
				<input type = "hidden" name="action" value="delete">
			</FORM>	
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
	
	



	<!-- 工作區結束 -->
	
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>