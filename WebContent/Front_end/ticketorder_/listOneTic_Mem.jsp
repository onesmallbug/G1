 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ticketorder.model.*" %>


<%
	TicketorderService ticketorderSvc = new TicketorderService();
	List<TicketorderVO> list = (List)request.getAttribute("list");
	TicketorderVO ticketorderVO = (TicketorderVO)request.getAttribute("ticketorderVO");
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
	<h3>所有購票明細</h3>
	<h4><a href = "<%=request.getContextPath()%>/Front_end/mem/select_page.jsp">回首頁</a></h4>
</table>

<%--錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style = "color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style = "color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
	
	<th>訂單編號</th>
	<th>會員編號</th>
	<th>優惠編號</th>
	<th>場次編號</th>
	<th>訂單時間</th>
	<th>訂單金額</th>
	
	</tr>
<%@ include file = "/File/page1.file" %>
<c:forEach var = "ticketorderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

	<tr>
		<td>${ticketorderVO.order_no }</td>
		<td>${ticketorderVO.member_no }</td>
		<td>${ticketorderVO.fd_no }</td>
		<td>${ticketorderVO.session_no }</td>
		<td>${ticketorderVO.order_time }</td>
		<td>${ticketorderVO.order_amount }</td>
		<td>
			<FORM METHOD = "post" ACTION="<%=request.getContextPath() %>/Front_end/mem/mem.do">
			<input type = "submit" value="查看電影票明細">
			<input type = "hidden" name="order_no" value="${ticketorderVO.order_no}">
			<input type = "hidden" name="action" value="getOne_For_Display_Tic_Mov">
			
			
			</FORM>
		</td>
		
		
		
		
	</tr>
	</c:forEach>
</table>
<%@ include file="/File/page2.file" %>










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