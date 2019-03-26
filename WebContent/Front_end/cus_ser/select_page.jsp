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
<body bgcolor = 'white'>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
<table id = "table-1">
	<tr><td><h3>JOIN影城 客服管理首頁 </h3></td></tr>
</table>

<h3>資料查詢:</h3>

<%--錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
 <li><a href ='listAllCus_ser.jsp'>客服管理瀏覽列表</a> <br><br></li>

<li>
	<FORM METHOD = "post" ACTION="cus_ser.do">
	<b>請輸入客服編號(1~15)</b>
	<input type = "text" name = "customer_service_event_no">
	<input type = "hidden" name="action" value="getOne_For_Display">
	<input type = "submit" value="送出">
	</FORM>
</li>

<jsp:useBean id = "cus_serSvc"  scope = "page"  class="com.cus_ser.model.Cus_serService" />

<li>
<FORM METHOD = "post" ACTION="cus_ser.do">
<b>選擇客服編號</b>
<select size="1"  name="customer_service_event_no">
	<c:forEach var="cus_serVO" items="${cus_serSvc.all}" >
	<option value="${cus_serVO.customer_service_event_no}">${cus_serVO.customer_service_event_no }
	</c:forEach>
</select>
<input type="hidden" name = "action" value="getOne_For_Display">
<input type = "submit" value = "送出">
</FORM>

</li>


<jsp:useBean id = "memSvc"  scope = "page"  class="com.mem.model.MemService" />
<li>
	<FORM METHOD = "post" ACTION="cus_ser.do">
	<b>選擇會員編號:</b>
	<select size="1" name="customer_service_event_no">
		<c:forEach var="cus_serVO" items="${cus_serSvc.all}">
		<option value="${cus_serVO.customer_service_event_no}">${cus_serVO.member_no}
		</c:forEach>
	</select>
	<input type="hidden" name="action" value="getOne_For_Display">
	<input type="submit" value="送出">
	</FORM>
</li>
</ul>

<h3>客服管理:</h3>

<ul>
<li><a href='addCus_ser.jsp'>新增</a>客服管理資料</li>
</ul>

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