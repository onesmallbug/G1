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
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->

<table id = "table-1">
	<tr><td><h3>JOIN影城 員工管理首頁 </h3></td></tr>
</table>

<h4><a href = "<%=request.getContextPath()%>/Back_end/emp/homeIndex.jsp">回員工後台首頁</a></h4>

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
 <li><a href ='listAllEmp.jsp'>員工瀏覽列表</a> <br><br></li>

<li>
	<FORM METHOD = "post" ACTION="emp.do">
	<b>請輸入員工編號(1~15)</b>
	<input type = "text" name = "employee_no">
	<input type = "hidden" name="action" value="getOne_For_Display">
	<input type = "submit" value="送出">
	</FORM>
</li>

<jsp:useBean id = "empSvc"  scope = "page"  class="com.emp.model.EmpService" />

<li>
<FORM METHOD = "post" ACTION="emp.do">
<b>選擇員工編號</b>
<select size="1"  name="employee_no">
	<c:forEach var="empVO" items="${empSvc.all}" >
	<option value="${empVO.employee_no}">${empVO.employee_no }
	</c:forEach>
</select>
<input type="hidden" name = "action" value="getOne_For_Display">
<input type = "submit" value = "送出">
</FORM>

</li>

<li>
	<FORM METHOD = "post" ACTION="emp.do">
	<b>選擇員工姓名:</b>
	<select size="1" name="employee_no">
		<c:forEach var="empVO" items="${empSvc.all}">
		<option value="${empVO.employee_no}">${empVO.employee_name}
		</c:forEach>
	</select>
	<input type="hidden" name="action" value="getOne_For_Display">
	<input type="submit" value="送出">
	</FORM>
</li>
</ul>


<%--萬用複合查詢 --%>
<ul>
	<li>
		<FORM METHOD="post" ACTION="emp.do" name="form1">
		<b><font color=blue>萬用複合查詢:</font></b><br>
		<b>選擇員工性別:</b>
		<input type="radio" name="employee_sex" value="1" checked>男性
		<input type="radio" name="employee_sex" value="0" >女性<br>
		
		<b>輸入員工編號:</b>
		<input type="text" name="employee_no" value="1"><br>
		
		<b>輸入員工名稱:</b>
		<input type="text" name="employee_name" value="DAVID"><br>
		
		<b>輸入員工職位:</b>
		<input type="text" name="employee_ability" value="文書"><br>
		
		<b>選擇員工狀態:</b>
		<input type="radio" name="employee_status" value="1" checked>在職
		<input type="radio" name="employee_status" value="0" >已離職<br>
		
		<b>選擇員工到職日期:</b>
		<td><input  name="employee_builddate"  id="f_date1" type="DATE" value="2018-01-01"><br>
		
		<input type = "submit" value="送出">
		<input type = "hidden" name="action" value="listEmps_ByCompositeQuery">
		
		</FORM>	
	</li>
</ul>


<h3>員工管理:</h3>

<ul>
<li><a href='addEmp.jsp'>新增</a>員工資料</li>
</ul>


<FORM METHOD="POST" ACTION = "logoutHandler.do">
	<td><input type = "submit" value="登出">
		<input type = "hidden" name="logout" value="logout"> 
	</td>
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