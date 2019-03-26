<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
<title>MovieGenre: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #00caca;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

</style>

</head>
<body bgcolor='white'>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1></h1><br>

<!-- 工作區開始 -->

	<div class="container">
		<div class="row justify-content">
			<div class="col-1"></div>
			<div class="col-4">

	<table id="table-1">
		<tr>
			<td><h3>電影種類管理: Home</h3></td>
		</tr>
	</table>



	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li>
			Movie Genre<a href='<%=request.getContextPath()%>/Back_end/moviegenre/listAllMovieGenre.jsp'>    EDIT</a>.
		</li><br>
			
		<li>
			<a href='<%=request.getContextPath()%>/Back_end/moviegenre/addMovieGenre.jsp'>ADD</a>    a Movie Genre.
		</li><br>

		<jsp:useBean id="moviegenreSvc" scope="page" class="com.moviegenre.model.MovieGenreService" />


		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_end/moviegenre/moviegenre.do">
				<b>種類名稱查詢:</b> 
				<select size="1" name="genre_no">
					<c:forEach var="moviegenreVO" items="${moviegenreSvc.all}">
						<option value="${moviegenreVO.genre_no}">${moviegenreVO.genre_name}
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
				<input type ="button" onclick="history.back()" value="取消"></input>
			</FORM>
		</li>
	</ul>
	
				</div>
		</div>
	</div>

<!-- 工作區結束 -->

	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	
</body>
</html>