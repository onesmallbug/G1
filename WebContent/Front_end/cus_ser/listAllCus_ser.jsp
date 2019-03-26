 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*" %>
<%@ page import="com.cus_ser.model.*" %>

<%
	Cus_serService cus_serSvc = new Cus_serService();
	List<Cus_serVO> list = cus_serSvc.getAll();
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
<table id="table-1">
	<tr><td>
		<h3>所有客服管理資料</h3>
		<h4><a href = "select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%--錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style ="color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style = "color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>客服編號</th>
		<th>會員編號</th>
		<th>客服處理開始時間</th>
		<th>客服處理狀態</th>
	</tr>
	
	<%@ include file = "page1.file" %>
	<c:forEach var="cus_serVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
	<tr>
		<td>${cus_serVO.customer_service_event_no}</td>
		<td>${cus_serVO.member_no}</td>
		<td>${cus_serVO.customer_service_start_date}</td>
		<td>${cus_serVO.customer_service_status}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Front_end/cus_ser/cus_ser.do">
				<input type="submit" value="修改">
				<input type = "hidden" name="customer_service_event_no" value="${cus_serVO.customer_service_event_no}">
				<input type = "hidden" name="action" value="getOne_for_Update">
			</FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Front_end/cus_ser/cus_ser.do">
				<input type="submit" value="刪除">
				<input type = "hidden" name="customer_service_event_no" value="${cus_serVO.customer_service_event_no}">
				<input type = "hidden" name="action" value="delete">
			</FORM>
		</td>
	</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>


	<!-- 工作區結束 -->
	
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
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