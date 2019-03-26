<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.emp.model.*" %>

<jsp:useBean id="listEmps_ByCompositeQuery" scope="request" type="java.util.List<EmpVO>" />

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
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
<table id="table-1">
	<tr><td>
		<h3>所有員工資料</h3>
		<h4><a href="<%=request.getContextPath()%>/Back_end/emp/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>

	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>員工性別</th>
		<th>員工建立日期</th>
		<th>員工離職日期</th>
		<th>員工職位</th>
		<th>員工狀態</th>
		<th>員工密碼</th>
	</tr>
 <%@ include file="page1_ByCompositeQuery.file" %>
<c:forEach var="empVO" items="${listEmps_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	  <tr align='center' valign='middle' ${(empVO.employee_no==param.employee_no) ? 'bgcolor=#CCCCFF':''}> 
		<td>${empVO.employee_no}</td>
		<td>${empVO.employee_name}</td>
		<td>${empVO.employee_sex}</td>
		<td>${empVO.employee_builddate}</td>
		<td>${empVO.employee_quitdate}</td>
		<td>${empVO.employee_ability}</td>
		<td>${empVO.employee_status}</td>
		<td>${empVO.employee_password}</td>
		
		
		
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_end/emp/emp.do" style="margin-bottom: 0px;">
			   <input type="submit" value="修改"> 
			   <input type="hidden" name="employee_no"      value="${empVO.employee_no}">
			   <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			   <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			   <input type="hidden" name="action"	    value="getOne_for_Update"></FORM>
			</td>
			<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_end/emp/emp.do" style="margin-bottom: 0px;">
			   <input type="submit" value="刪除">
			   <input type="hidden" name="employee_no"      value="${empVO.employee_no}">
			   <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			   <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			   <input type="hidden" name="action"     value="delete"></FORM>
			</td>		
</c:forEach>
</table>
<%@ include file="page2_ByCompositeQuery.file" %>

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