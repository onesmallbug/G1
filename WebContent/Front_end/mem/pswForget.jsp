
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

<style>
input[type="submit"] {
	padding: 5px 15px;
	background: #ccc;
	border: 0 none;
	cursor: pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	font-family: fantasy;
}

input[type="reset"] {
	padding: 5px 15px;
	background: #ccc;
	border: 0 none;
	cursor: pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	font-family: fantasy;
}

.container {
	margin-top: 100px;
}

#fontA {
	font-size: x-large;
	font-family: fantasy;
}
</style>



</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->

	<div class="container">
		<div class="row justify-content">
			<div class="col-4"></div>
			<div class="col-6" id='fontA'>


				<%--錯誤表列 --%>
				<c:if test="${not empty errorMsgs }">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<form method="post" action="loginHandler.do" name="form1">

					<p>
						帳號: <input type="text" name="member_account" id="member_account">
					</p>
					<p>
						信箱: <input type="email" name="member_email" id="member_email">
					</p>

					<p>
						<input type="hidden" name="action" value="getone_for_pswForget">
						<input type="submit" value="送出"> <input type="reset"
							value="取消"> <img src="img/popcorn.jpg" height="20"
							width="20" onclick="idwrite(this)">
					</p>

				</form>



				<!-- 工作區結束 -->
			</div>
		</div>
	</div>


	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script
		src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>

<script>
	function idwrite(name) {
		form1.member_account.value = "peter520";
		form1.member_email.value = "zxxl3617@gmail.com";

	}
</script>





</html>