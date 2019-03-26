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
<title>JOIN影城登入系統</title>

<style>

table#table-1{
	width: 450px;
	background-color: #CCCCFF;
	margin-top:5px;
	margin-bottom:10px;
	border:3px ridge Gray;
	height: 80px;
	text-align: center;
	background-color:#3C5A91;
	background:-o-linear-gradient(90deg, #3C5A91, #8fa0c0);
	background:-moz-linear-gradient( center top, #3C5A91 5%, #8fa0c0 100% );
	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #3C5A91), color-stop(1, #8fa0c0) );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#3C5A91', endColorstr='#8fa0c0');
	color:#FFFFFF
}
table#table-1 h4{

color:red;
display:block;
margin-bottom:1px;
}
h4{
color:blue;
display: inline;
}



.container{

margin-top: 50px;

}



    input[type="submit"]  {padding:5px 15px; background:#ccc; border:0 none;
		cursor:pointer;
		-webkit-border-radius: 5px;
		border-radius: 5px; }


	input[type="reset"]  {padding:5px 15px; background:#ccc; border:0 none;
		cursor:pointer;
		-webkit-border-radius: 5px;
		border-radius: 5px; }

	input[type="text"]{padding:5px 15px; border:2px black solid;
		cursor:pointer;
		-webkit-border-radius: 5px;
		border-radius: 5px; }

	input[type="password"]{padding:5px 15px; border:2px black solid;
		cursor:pointer;
		-webkit-border-radius: 5px;
		border-radius: 5px; }



</style>



</head>
<body>
	<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	<div class="container" >
		<div class="row justify-content">
			<div class="col-3"></div>
			<div class="col-6">
	
	<table id = "table-1">

		<tr><td><h3>Join揪影影城登入系統</h3></td></tr>

	</table>

<%--錯誤表列 --%>
<c:if test="${not empty errorMsgs }">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var = "message"  items="${errorMsgs}">
			<li style="color:red">${message}</li>
			</c:forEach>
	</ul>
</c:if >
	

<form  method = "post"   action = "loginHandler.do" name="form1"  >

<p>帳號: <input type = "text"  name = "member_account" id = "member_account" ></p>
<p>密碼: <input type = "password"  name = "member_password" id = "member_password" ></p>
<p><input type = "hidden" name="action" value="getone_for_login">
   <input type = "submit"  value = "送出">
   <input type = "reset"   value = "取消">
   <a href="<%=request.getContextPath()%>/Front_end/mem/pswForget.jsp">忘記密碼</a>
</p>
</form>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

*如果您還未註冊會員 請點擊<a href="<%=request.getContextPath()%>/Front_end/mem/member_regestinfomation.jsp">註冊</a>

<!-- <神奇小按鈕> -->

<img src="img/popcorn.jpg" height="20" width="20"
						onclick="idwrite(this)">
						
<img src="img/popcorn.jpg" height="20" width="20"
						onclick="idwrite2(this)">
						
<img src="img/popcorn.jpg" height="20" width="20"
						onclick="idwrite3(this)">						
</div>
</div>
</div>
						

<script>

function idwrite(name){
	form1.member_account.value="peter520";
	form1.member_password.value="1314520";
	
}


function idwrite2(name){
	form1.member_account.value="A123456";
	form1.member_password.value="123456";
	
}


function idwrite3(name){
	form1.member_account.value="nico520";
	form1.member_password.value="777";
	
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