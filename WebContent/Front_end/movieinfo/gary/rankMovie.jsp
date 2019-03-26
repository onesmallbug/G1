<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movieinfo.model.*"%>
<%@ page import="java.util.stream.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.moviegenre.model.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	
MovieInfoService movieInfoService = new MovieInfoService();

java.sql.Date date_in = java.sql.Date.valueOf(request.getParameter("year_in"));
java.sql.Date date_out = java.sql.Date.valueOf(request.getParameter("year_out"));

//  List<MovieInfoVO> list = 
//		  movieInfoService
//		  .getAll()
//		  .stream()
//		  .filter(vo -> vo.getMovie_in().after(date_in))
//		  .filter(vo -> vo.getMovie_out().before(date_out))
//		  .collect(Collectors.toList()); 
// pageContext.setAttribute("list",list);
  
   MovieInfoService movieinfoSvc = new MovieInfoService();
  
   pageContext.setAttribute("list",movieinfoSvc.getAllByScore(date_in, date_out));
%>
<jsp:useBean id="movieGenreService" class="com.moviegenre.model.MovieGenreService" />

<html>
	<head>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
		<title>listoneMovieInfo</title>
	</head>
	<body bgcolor='white'>
			<h1></h1><br>
			
	<!-- 工作區開始 -->
	
		<div class="container">
			<div class="row justify-content">
				<div class="col-1"></div>
				<div class="col-10">
				<div class="row justify-content">
				<table>
				<tr><th>排名</th><th>電影名稱</th><th>種類名稱</th><th>分數</th></tr>
				<c:forEach var="movieInfoVO" items="${list}" varStatus="s">
				<tr><th>${s.count}</th><th>${movieInfoVO.movie_name}</th><th>${movieGenreService.getOneGenre(movieInfoVO.genre_no).genre_name}類名稱</th><th>${movieInfoVO.movie_touch}</th></tr>
				</c:forEach>
				</table>
				</div>
				</div>
				<div class="col-1"></div>
			</div>
		</div>
	
	<!-- 工作區結束 -->
			
			<!-- Optional JavaScript -->
			<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
			<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
			<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
			<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
			<!-- jQuery first, then Popper.js, then Bootstrap JS end-->
	</body>
</html>