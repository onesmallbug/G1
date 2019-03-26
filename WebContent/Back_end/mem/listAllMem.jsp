 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.mem.model.*" %>

<%
	MemService memSvc = new MemService();
	List<MemVO> list = memSvc.getAll();
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
<title>所有會員資料</title>
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	
<table id = "table-1">
	<tr><td>
		<h3>JOIN所有會員資料</h3>
		<h4><a href = "select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>


<%--錯誤表列 --%>
<c:if test="${not empty errorMsgs }">
	<font style = "color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var = "message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table class="table table-hover">
<thead>
	<tr>
		<th scope="col">會員編號</th>
		<th scope="col">會員帳號</th>
		<th scope="col">會員密碼</th>
		<th scope="col">會員姓名</th>
		<th scope="col">會員暱稱</th>
		<th scope="col">會員性別</th>
		<th scope="col">會員生日</th>
		<th scope="col">會員地址</th>
		<th scope="col">會員電話</th>
		<th scope="col">會員信箱</th>
		<th scope="col">會員圖片</th>
		<th scope="col">會員信用卡</th>
		<th scope="col">會員驗證碼</th>
		<th scope="col">會員建立日期</th>
		<th scope="col">會員儲值點數</th>
		<th scope="col">會員狀態</th>
	</tr>
	</thead>
	<tbody>
	<%@ include file = "page1.file" %>
	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end = "<%=pageIndex+rowsPerPage-1%>">
	
	<tr >
		<th scope="row">${memVO.member_no}</th>
		<td>${memVO.member_account}</td>
		<td>${memVO.member_password}</td>
		<td>${memVO.member_name}</td>
		<td>${memVO.member_nick}</td>
		<td>${memVO.member_sex eq 0?"女":"男"}</td>
		<td>${memVO.member_birthday}</td>
		<td>${memVO.member_address}</td>
		<td>${memVO.member_telephone}</td>
		<td>${memVO.member_email}</td>
		<td>
			
			<img src='<%= request.getContextPath() %>/Front_end/mem/mem.do?member_no=${memVO.member_no}' width='200' height = '200'/>
		</td>
		<td>${memVO.member_credit_number}</td>
		<td>${memVO.member_back_verification}</td>
		<td>${memVO.member_buildday}</td>
		<td>${memVO.member_point}</td>
		<td>${memVO.member_status eq 1?"已驗證":"未驗證"}</td>
		<img src ="<%= request.getContextPath() %>/memberServlet?member_no=${memVO.member_no}">
		
		<td>
			<FORM METHOD = "post" ACTION = "<%=request.getContextPath()%>/Front_end/mem/mem.do" style="margin-bottom:0px;">             
				<input type = "submit" value="修改">
				<input type = "hidden" name = "member_no" value="${memVO.member_no}">
				<input type = "hidden" name = "action" value="getOne_for_Update">
			</FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Front_end/mem/mem.do" style="margin-bottom:0px;">
					<input type = "submit" value="刪除">
					<input type = "hidden" name="member_no" value="${memVO.member_no}">
					<input type  ="hidden" name="action" value="delete">
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