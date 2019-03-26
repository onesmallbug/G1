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
input {
	padding: 5px 15px;
	background: white;
	cursor: pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
}

.flex {
	display: flex;
	height: 500px;
	padding: 15px;
	
	flex-wrap: wrap; // 修改以下值試試看 flex-start | flex-end | center |
	space-between | space-around;
	justify-content: center;
	//
	修改以下值試試看
	flex-start
	|
	flex-end
	|
	center
	|
	space-between
	|
	space-around
	|
	stretch
	align-content
	:
	flex-start;
}

.item {
	width: 22%;
	height: 80px;
	margin: 5px;
	display: flex;
	justify-content: center;
	align-items: center;
	color: white;
	font-size: 2rem;
}

.flex-2 {
	width: 40%;
	height: 120px;
	background-color: #f08bc3;
}
</style>


</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	<div class="container">
		<div class="flex">
			<div class="item">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/Back_end/emp/select_page.jsp">
					<input type="submit" value="員工資料">
				</FORM>
			</div>
			<div class="item ">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/Back_end/mem/select_page.jsp">
					<input type="submit" value="會員資料">
				</FORM>
			</div>
			<div class="item">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/Back_end/dep/select_page.jsp">
					<input type="submit" value="儲值資料">
				</FORM>
			</div>

			<div class="item">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/Back_end/cinema/ListAllCinema.jsp">
					<input type="submit" value="廳院管理">
				</FORM>
			</div>
			<div class="item">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/Back_end/sessions/listAllSessions.jsp?action=all">
					<input type="submit" value="場次管理">
				</FORM>
			</div>

			<div class="item">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/Back_end/moviegenre/listAllMovieGenre.jsp">
					<input type="submit" value="電影種類">
				</FORM>
			</div>

			<div class="item">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/Back_end/movieinfo/listAllMovieInfo.jsp">
					<input type="submit" value="電影資訊">
				</FORM>
			</div>


			<div class="item">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/Back_end/newsinfo/listAllNewsInfo.jsp">
					<input type="submit" value="影視新聞">
				</FORM>
			</div>


			<div class="item">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/Back_end/adv/listAllAdv.jsp">
					<input type="submit" value="廣告管理">
				</FORM>
			</div>
<!-- 		<div class="item "> -->
<%-- 			<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/Back_end/emp/logoutHandler.do"> --%>
<!-- 				<input type="submit" value="登出"> <input type="hidden" -->
<!-- 					name="logout" value="logout"> -->
<!-- 			</FORM> -->
<!-- 		</div> -->
		</div>

	</div>


	<!-- 工作區結束 -->

	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script
		src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->
</body>
</html>