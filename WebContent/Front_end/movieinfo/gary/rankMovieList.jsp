<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap CSS start-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->
<meta charset="BIG5">
<title>rankMovieList</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
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

img, #level {
	width: 50px;
	hight: 50px;
}

img, #pic {
	width: 135px;
	hight: 200px;
}
</style>

<style>
table {
/* 	width: 600px; */
	width: 100%;
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
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h1></h1>
	<br>

	<!-- 工作區開始 -->
	<div class="container">
			<div class="row justify-content">
				<div class="col-1"></div>
				<div class="col-10">
					<table id="table-1">
						<tr>
							<td>
								<h3>年度電影排行</h3>
								<h4>
									<a href="<%=request.getContextPath()%>/Front_end/Home.jsp">
									<img src="<%=request.getContextPath()%>/Back_end/movieinfo/images/eatPopcorn.gif"
										width="125" height="72" border="0">回首頁</a>
								</h4>
							</td>
						</tr>
					</table>
				</div>
				<div class="col-1"></div>
			</div>
			
			<div class="row justify-content">
				<div class="col-1"></div>
				<div class="col-10">
					<div class="row justify-content">
						<%
						Calendar cal = new GregorianCalendar();
						cal.setTime(new java.util.Date());
						int now_year = cal.get(Calendar.YEAR);
						
						for(int i = 2008; i < now_year; i++){ 
						%>
							<div class="col-1">
								<div class="btn btn-primary btn-sm" id="<%=i %>"><%=i %>年</div>
							</div>	
						<%} %>		
					</div>
				</div>
				<div class="col-1"></div>
			</div>
	</div>
					<div id="load"></div>

	<!-- 工作區結束 -->

	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script
		src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->
</body>
<script
	src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>

<script>
$(document).ready(init);
	function init(){
		var years = <%=now_year%>;
		for(var i = 2008; i< years; i++){
			var element = $('#'+i);
			element.click(
				function(){
					$('#load').load(
						'<%=request.getContextPath()%>/Front_end/movieinfo/rankMovie.jsp?year_in='+$(this).attr('id')+'-01-01&year_out='+(parseInt($(this).attr('id'))+1)+'-01-01');
					}
				);
		}
	}
</script>

<script>
	$('#abc').on(
			'click',
			function() {
				var xhr = new XMLHttpRequest();
				xhr.load = function() {
					if (xhr.readyState == 4) {
						if (xhr.status == 200) {
							var abcd = JSON.parse(xhr.responseText);
							abcd.movename
						} else {
							alert(xhr.status);
						}
					}
				}

				xhr.open("post", "", true);
				xhr.setRequestHeader("content-type",
						"application/x-www-form-urlencoded");
				xhr.send();
			})
</script>

</html>