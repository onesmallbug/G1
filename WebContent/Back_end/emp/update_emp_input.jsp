<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import =  "com.emp.model.*" %>

<%
 EmpVO empVO = (EmpVO) request.getAttribute("empVO");  
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

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs }">
	<font style = "color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
		<li style="color:red">${message}</li>
		</c:forEach>	
	</ul>
</c:if>


<FORM METHOD="post" ACTION="emp.do" name="form1">
<table>
	<tr>
		<td>員工編號:<font color=red><b>*</b></font></td>
		<td><%=empVO.getEmployee_no() %></td>
	
	</tr>

	<tr>
		<td>員工姓名:</td>
		<td><input type = "TEXT" name="employee_name" value="<%= (empVO==null)? "" : empVO.getEmployee_name()%>" /></td>
	</tr>
	

	<tr>
		<td>員工性別:</td>		
				
		<td>
		<input type="radio" name="employee_sex" value="1" ${(empVO.employee_sex==1)? "checked": ""}>男性
		<input type="radio" name="employee_sex" value="0" ${(empVO.employee_sex==0)? "checked": ""}>女性
		</td>
		
	</tr>
	
	<% 
  java.sql.Date employee_builddate = null;
  try {
	  employee_builddate = empVO.getEmployee_builddate();
   } catch (Exception e) {
	   employee_builddate = new java.sql.Date(System.currentTimeMillis());
   }
%>
	
	<tr>
		<td>員工雇用日期:</td>
		<td><input  name="employee_builddate"  id="f_date1" type="DATE" value="<%= (empVO==null)? employee_builddate : empVO.getEmployee_builddate()%>"></td>
	</tr>
	
	<tr>
		<td>員工離職日期:</td>
		<td><input  name="employee_quitdate"  id="f_date2" type="DATE" value="<%= empVO.getEmployee_quitdate()%>"></td>
	</tr>


	<tr>
		<td>員工職稱:</td>
		<td><input type="TEXT" name="employee_ability" value="<%= (empVO==null)? "" : empVO.getEmployee_ability()%>">
		</td>
	</tr>
	
	<tr>
		<td>員工狀態:</td>
		<td>
		<input type="radio" name="employee_status" value="1" checked>在職
		<input type="radio" name="employee_status" value="0" >已離職
		</td>
	</tr>
	
	<tr>
		<td>密碼:</td>
		<td>
		<input type="PASSWORD" name="employee_password" value="<%= (empVO==null)? "" : ""%>">
		</td>
	</tr>
	<tr>
		<td>再次確認密碼:</td>
		<td>
		<input type="PASSWORD" name="employee_password1" value="<%= (empVO==null)? "" : ""%>">
		</td>
	</tr>
	

</table>
<br>
<input type = "hidden" name = "action" value = "update">
<input type = "hidden" name = "employee_no" value ="<%=empVO.getEmployee_no()%>">
<input type = "submit" value= "送出修改">


<img src="img/popcorn.jpg" height="20" width="20" onclick="empdo(this)">

<script>
function empdo(name){
	form1.employee_name.value="DavidWu";
	form1.f_date1.value="2019-03-29";
	form1.employee_ability.value="經理";
	form1.employee_password.value="1314520";
	form1.employee_password1.value="1314520";
	
	
}



</script>


</FORM>























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