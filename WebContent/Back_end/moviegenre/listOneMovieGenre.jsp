<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.moviegenre.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MovieGenreVO moviegenreVO = (MovieGenreVO) request.getAttribute("moviegenreVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>listOneMovieGenre</title>
<link rel="stylesheet"	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>

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
</style>

<style>
  table {
	width: 600px;
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
		 <h3>電影種類查詢</h3>
		 <h4><a href="<%=request.getContextPath()%>/Back_end/moviegenre/select_page.jsp">
		 	 <img src="<%=request.getContextPath()%>/Back_end/movieinfo/images/popcorn.jpg" width="52" height="62" border="0">回首頁</a>
		 </h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>電影種類編號</th>
		<th>電影種類名稱</th>
	</tr>
	<tr>
		<td><%=moviegenreVO.getGenre_no()%></td>
		<td><%=moviegenreVO.getGenre_name()%></td>
	</tr>
</table>

			</div>
		</div>
	</div>

<!-- 工作區結束 -->

	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	
</body>
</html>