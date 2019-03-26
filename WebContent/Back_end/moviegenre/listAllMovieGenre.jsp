<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.moviegenre.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MovieGenreService moviegenreSvc = new MovieGenreService();
    List<MovieGenreVO> list = moviegenreSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
	<head>
	
		<!-- Bootstrap CSS start-->
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
		<!-- Bootstrap CSS end-->
		
		<title>listAllMovieGenre</title>
		
		<style>
		  table#table-1 {
			background-color: #00caca;
		    border: 2px solid black;
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
		  
		  table {
			width: 800px;
			background-color: white;
			margin-top: 5px;
			margin-bottom: 5px;
		  }
		  table, th, td {
		    border: 1px solid #CCCCFF;
		  }
		  th, td {
		    padding: 5px;
		    text-align: center;
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
			<tr><td>
				 <h3>電影種類清單</h3>
				 <h4><a href="<%=request.getContextPath()%>/Back_end/moviegenre/select_page.jsp"><img src="<%=request.getContextPath()%>/Back_end/movieinfo/images/eatPopcorn.gif" width="125" height="72" border="0">  回首頁</a></h4>
			</td></tr>
		</table>
		
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<table>
			<tr>
				<th>電影種類編號</th>
				<th>電影種類名稱</th>
				<th colspan="2">編輯</th>
				
			</tr>
			<%@ include file="page1.file" %> 
			<c:forEach var="MovieGenreVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				
				<tr>
					<td>${MovieGenreVO.genre_no}</td>
					<td>${MovieGenreVO.genre_name}</td>
					
					<td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_end/moviegenre/moviegenre.do" style="margin-bottom: 0px;">
					     <input type="submit" value="修改">
					     <input type="hidden" name="genre_no"  value="${MovieGenreVO.genre_no}">
					     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
					</td>
					
					<td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_end/moviegenre/moviegenre.do" style="margin-bottom: 0px;">
					     <input type="submit" value="刪除">
					     <input type="hidden" name="genre_no"  value="${MovieGenreVO.genre_no}">
					     <input type="hidden" name="action" value="delete"></FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file" %> 
			</div>
		</div>
	</div>

<!-- 工作區結束 -->
		
		<jsp:include page="/BackHeaderFooter/Footer.jsp" />
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
		<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
		<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
		<!-- jQuery first, then Popper.js, then Bootstrap JS end-->
	</body>
</html>