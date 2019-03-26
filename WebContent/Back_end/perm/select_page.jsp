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
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
<table id = "table-1">
	<tr><td><h3>JOIN權限管理</h3></td></tr>
</table>

<h3>權限資料查詢:</h3>

<%--錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style = "color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
	<li><a href ='listAllPerm.jsp'>權限瀏覽列表</a> <br><br></li>
	
<li>
	<FORM METHOD ="post" ACTION="perm.do">
	<b>請輸入權限編號(1~15)</b>
	<input type = "text" name="permission_no">
	<input type = "hidden" name="action" value="getOne_For_Display">
	<input type = "submit" value="送出">
	</FORM>
</li>

<jsp:useBean id="permSvc" scope="page" class="com.perm.model.PermService" />

<li>
<FORM METHOD ="post" ACTION="perm.do">
<b>選擇權限編號</b>
<select size="1" name = "permission_no">
	<c:forEach var="permVO" items="${permSvc.all}">
	<option value="${permVO.permission_no}">${permVO.permission_no}
	</c:forEach>
</select>
<input type = "hidden" name="action" value="getOne_For_Display">
<input type = "submit" value="送出">
</FORM>
</li>

<li>
	<FORM METHOD="post" ACTION="perm.do">
	<b>選擇權限名稱:</b>
	<select size="1" name="permission_no">
		<c:forEach var="permVO" items="${permSvc.all}">
		<option value="${permVO.permission_no}">${permVO.permission_name}
		</c:forEach>
	
	</select>
	<input type="hidden" name="action" value="getOne_For_Display">
	<input type = "submit" value="送出">
	</FORM>
</li>
</ul>

<h3>權限管理:</h3>

<ul>
<li><a href = 'addPerm.jsp'>新增</a>權限資料</li>
</ul>





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