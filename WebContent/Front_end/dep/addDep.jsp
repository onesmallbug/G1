 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.dep.model.*" %>

<%
 DepVO depVO = (DepVO) request.getAttribute("depVO");
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
<title>會員儲值明細新增</title>



<style>

.

.table9_11 table {
	width:200%;
	margin:15px 0
}
.table9_11 th {
	background-color:#d92031;
	background:-o-linear-gradient(90deg, #d92031, #efeffa);
	background:-moz-linear-gradient( center top, #d92031 55%, #efeffa 100% );
	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #d92031), color-stop(1, #efeffa) );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#d92031', endColorstr='#efeffa');
	color:#FFFFFF
}
.table9_11,.table9_11 th,.table9_11 td
{
	font-size:0.95em;
	text-align:center;
	padding:4px;
	border:1px solid #2020bc;
	border-collapse:collapse
}
.table9_11 td {

	border:1px solid #efeffa;
	border-collapse:collapse
	
}
.table9_11 tr:nth-child(odd){
	background-color:#aaaaee;
	background:-o-linear-gradient(90deg, #aaaaee, #eeeefb);
	background:-moz-linear-gradient( center top, #aaaaee 5%, #eeeefb 100% );
	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #aaaaee), color-stop(1, #eeeefb) );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#aaaaee', endColorstr='#eeeefb');
}
.table9_11 tr:nth-child(even){
	background-color:#fdfdfd;
}
</style>

</head>
<body bgcolor='white'>
	<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	
	<div class="container" >
		<div class="row justify-content">
			<div class="col-3"></div>
			<div class="col-6">
	
<table id = "table-1">
	<tr><td>
		<h3>JOIN儲值頁面</h3>
		
	</td></tr>

</table>




<%--錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>	
	</ul>
</c:if>
<br><br>
<FORM METHOD="post" ACTION="dep.do" name="form1">
<table class="table">

	<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
	<tr>
	
	
	<td>
	<input type = "hidden" name="deposit_change_no" 
			value="<%= (depVO==null)?"":depVO.getDeposit_change_no()%>" />
		
		</td>
		
		<td>
		
		<%-- <select size="1" name="member_no">
			<c:forEach var="memVO" items="${memSvc.all}">
				<option value="${depVO.deposit_member_no}" ${(depVO.deposit_member_no==memVO.member_no)? 'selected':''} >${memVO.member_name}       
			</c:forEach>
		</select> --%>
		
		<input type = "hidden" name="deposit_member_no" 
			value="<%= (depVO==null)?"":depVO.getDeposit_member_no()%>" />
		
		</td>
	</tr>		
	
	<tr>
		<th scope="row" >請輸入儲值金額</th>
		<td><input type = "text" name="deposit_change_money" id="deposit_change_money" size="45"
			value="<%= (depVO==null)?"":""%>" /></td>
	</tr>
	<tr>
		<th scope="row">信用卡密碼</th>
		<td><input type = "text" name="member_credit_number" id="member_credit_number" size="45"></td>
	</tr>
	
	<tr>
		<th scope="row">驗證末三碼</th>
		<td><input type = "text" name="member_back_verification" id="member_back_verification" size="45"></td>
	</tr>
	<tr>
	<td><input type = "hidden" name="deposit_change_date" value="<%=(depVO==null)?"":depVO.getDeposit_change_date()%>" /></td>
	
	</tr>
	
	<tr>
	<th scope="row">信用卡類別</th>
	<td>
<select >
　<option value="Taipei">VISA</option>
　<option value="Taoyuan">PayPal</option>
　<option value="Hsinchu">WebATM</option>
　<option value="Miaoli">MasterCard</option>
</select>
</td></tr>

</table>


<br>
<input type ="hidden" name="action" value="mem_insert">
<input type = "submit" style="padding: 5px 15px; background: white; border: 1 none; color: #969696; cursor: pointer; -webkit-border-radius: 5px; border-radius: 5px;" value="送出新增">
<input type = "button" value="回首頁" style="padding: 5px 15px; background: white; border: 1 none; color: #969696; cursor: pointer; -webkit-border-radius: 5px; border-radius: 5px;" onclick="location.href='<%=request.getContextPath()%>/Front_end/mem/select_page.jsp'">
<img src = "img/popcorn.jpg" height="20" width="20" onclick="iddo(this)">
</FORM>


<br><br>

<table class=table9_11>
<tr>
	<th>訊息提示</th>
</tr>
<tr>
	<td>[系統]儲值系統每周維護時間:每周一07:00~08:00</td>
</tr>
<tr>
	<td style="text-align:left;">[系統]請勿使用非法管道進行儲值</td>
</tr>
<tr>
	<td>[系統]JOIN影城期待與你們揪影相看電影的每一天</td>
</tr>
</table>

</div></div></div>





<script>

	function iddo(name){
		
		form1.deposit_change_money.value="2000";
		form1.member_credit_number.value="139687495312";
		form1.member_back_verification.value="398";
		
	}


</script>




	<!-- 工作區結束 -->
	
	<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>