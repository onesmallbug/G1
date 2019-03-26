 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<tr><td><h3>儲值明細管理首頁</h3></td></tr>
</table>

<h4><a href = "<%=request.getContextPath()%>/Back_end/emp/homeIndex.jsp">回員工後台首頁</a></h4>

<h3>資料查詢:</h3>

<%--錯誤列表 --%>
<c:if test="${not empty errorMsgs }">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style = "color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
	<li><a href='listAllDep.jsp'>全部儲值明細清單</a><br><br></li>
	
	<li>
		<FORM METHOD = "post" ACTION="dep.do">
			<b>輸入儲值編號(如D000001):</b>
			<input type = "text" name = "deposit_change_no">
			<input type = "hidden" name="action" value="getOne_For_Display_Back">
			<input type = "submit" value="送出">
		</FORM>
	</li>
	
	<jsp:useBean id="depSvc" scope="page" class="com.dep.model.DepService" />
	
	<li>
		<FORM METHOD = "post" ACTION="dep.do">
			<b>選擇儲值明細編號:</b>
			<select size="1" name="deposit_change_no">
				<c:forEach var="depVO" items="${depSvc.all}">
					<option value="${depVO.deposit_change_no}">${depVO.deposit_change_no}
				</c:forEach>
			</select>
			<input type = "hidden" name="action" value="getOne_For_Display_Back">
			<input type = "submit" value="送出">	
		</FORM>
	</li>

	<li>
		<FORM METHOD = "post" ACTION="dep.do">
			<b>選擇會員編號:</b>
			<select size="1" name="deposit_change_no">
				<c:forEach var="depVO" items="${depSvc.all}">
					<option value="${depVO.deposit_change_no}">${depVO.deposit_member_no}
				</c:forEach>
			</select>
			<input type = "hidden" name="action" value="getOne_For_Display_Back">
			<input type = "submit" value="送出">
		</FORM>
	</li>
</ul>





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