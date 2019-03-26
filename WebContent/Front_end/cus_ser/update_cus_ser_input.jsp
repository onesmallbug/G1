 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cus_ser.model.*" %>

<%
	Cus_serVO cus_serVO = (Cus_serVO)request.getAttribute("cus_serVO");  
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
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs }">
	<font style = "color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
		<li style="color:red">${message}</li>
		</c:forEach>	
	</ul>
</c:if>

<FORM METHOD="post" ACTION="cus_ser.do" name="form1">
<table>
	<tr>
		<td>客服處理單據編號:<font color=red><b>*</b></font></td>
		<td><%=cus_serVO.getCustomer_service_event_no() %></td>
	</tr>

	<tr>
		<td>會員編號:</td>
		<td><input type = "TEXT" name="member_no" value="<%= (cus_serVO==null)? "" : cus_serVO.getMember_no()%>" /></td>
	</tr>
	
	<% 
  		java.sql.Date customer_service_start_date = null;
  		try {
  			customer_service_start_date = cus_serVO.getCustomer_service_start_date();
   		} catch (Exception e) {
   			customer_service_start_date = new java.sql.Date(System.currentTimeMillis());
   		}
	%>
	
	<tr>
		<td>客服處理開始日期:</td>
		<td><input  name="customer_service_start_date"  id="f_date1" type="DATE" value="<%= (cus_serVO==null)? customer_service_start_date : cus_serVO.getCustomer_service_start_date()%>"></td>
	</tr>
	
	
	<tr>
		<td>客服處理狀態:</td>
		<td>
		<input type="radio" name="customer_service_status" value="3" ${(cus_serVO.customer_service_status==3)? "checked": ""}>已回復
		<input type="radio" name="customer_service_status" value="2" ${(cus_serVO.customer_service_status==2)? "checked": ""}>處理中
		<input type="radio" name="customer_service_status" value="1" ${(cus_serVO.customer_service_status==1)? "checked": ""}>已接單
		<input type="radio" name="customer_service_status" value="0" ${(cus_serVO.customer_service_status==0)? "checked": ""}>未處理
		</td>
	</tr>
</table>
<br>
<input type = "hidden" name = "action" value = "update">
<input type = "hidden" name = "customer_service_event_no" value ="<%=cus_serVO.getCustomer_service_event_no()%>">
<input type = "submit" value= "送出修改">
</FORM>



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