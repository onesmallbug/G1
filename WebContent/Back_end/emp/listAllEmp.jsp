<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*" %>
<%@ page import="com.emp.model.*" %>

<% 
	EmpService empSvc = new EmpService();
	List<EmpVO> list = empSvc.getAll();
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
<title>所有員工資料-listAllEmp.jsp</title>

<style>
	table#table-1{
	background-color:#CCCCFF;
	border:2px solid black;
	text-align: center;
	}
	table#table-1 h4{
	color:red;
	display: block;
	margin-bottom:
	}
	
	



</style>





</head>
<body bgcolor='white'>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->

<table id="table-1">
	<tr><td>
		<h3>所有員工資料</h3>
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

<table class="table table-hover">
<thead>
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>員工性別</th>
		<th>員工建立日期</th>
		<th>員工離職日期</th>
		<th>員工職位</th>
		<th>員工狀態</th>
		<th>員工密碼</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
</thead>
 <tbody>	
	<%@ include file = "page1.file" %>
	<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
	<tr>
		<th  scope="row">${empVO.employee_no}</th>
		<td>${empVO.employee_name}</td>
		<td>${empVO.employee_sex eq 0?"女":"男"}</td>
		<td>${empVO.employee_builddate}</td>
		<td>${empVO.employee_quitdate }</td>
		<td>${empVO.employee_ability}</td>
		<td>${empVO.employee_status eq 0?"已離職":"在職中"}</td>
		<td>${empVO.employee_password}</td>
		<td>
			<FORM METHOD = "post" ACTION="<%=request.getContextPath()%>/Back_end/emp/emp.do" style="margin-bottom: 0px;">
				<input type = "submit" value="修改">
				<input type = "hidden" name="employee_no" value="${empVO.employee_no}">
				<input type = "hidden" name="action" value="getOne_for_Update">
			</FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_end/emp/emp.do" style="margin-bottom:0px;">
					<input type = "submit" value="刪除">
					<input type = "hidden" name="employee_no" value="${empVO.employee_no}">
					<input type = "hidden" name="action" value="delete">
				</FORM>
			</td>		
	   </tr>
	</c:forEach>
</tbody>
</table>
<%@ include file="page2.file" %>


	<!-- 工作區結束 -->
	
		<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>