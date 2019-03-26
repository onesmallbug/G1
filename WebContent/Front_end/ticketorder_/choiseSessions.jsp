<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="com.movieinfo.*"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS start-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->
<title>訂票</title>
<style type="text/css">
p{
 margin-bottom:10px;
}
</style>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->

	<jsp:useBean id="movieinfoService" class="com.movieinfo.model.MovieInfoService" />
	<%
		pageContext.setAttribute("now", new java.sql.Date(System.currentTimeMillis()));
	%>

	<div class="container">
		<div class="row justify-content-center h4">
			<div class="col-4 text-center">
				<p>選擇電影</p>
				<br> 
				<select class="h6" id="movie_no" name="movie_no" style="overflow:hidden; text-overflow:ellipsis;white-space:nowrap;width:106px;">
						<option value="">請選擇電影</option>
					<c:forEach var="movieinfo" items="${movieinfoService.all}">
						<c:if test="${movieinfo.movie_in.before(now) && now.before(movieinfo.movie_out)}">
							<option value="${movieinfo.movie_no}">${movieinfo.movie_name}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="col-4 text-center">
				<p>選擇日期</p>
				<br> 
				<select class="h6" id="date" name="date" style="overflow:hidden; text-overflow:ellipsis;white-space:nowrap;width:106px;">
				<option value='-1'>請選擇</option>
				</select>
			</div>
			<div class="col-4 text-center">
				<p>選擇場次</p><br> 
				<select class="h6" id="sessions" name="sessions" style="overflow:hidden; text-overflow:ellipsis;white-space:nowrap;width:106px;">
				<option value='-1'>請選擇</option>
				</select>
			</div>
		</div>
		<hr style="border-top: 5px solid rgba(0,0,0,0.15);">
		<div class="row justify-content-center h5">
			<div class="col-4 text-center">
			<p>電影海報</p>
			<img class="rounded" id="movie_pic" src="" style="width:300px;">
			</div>
			<div class="col-4 text-center h4">
				<p>片名</p>
				<p class="h5" id="movie_name">　</p>
				<br>
				<p>敘述</p>
				<P class="h5" id="movie_intro"></P>
			</div>
			<div class="col-4 text-center">
				<p>剩餘座位</p>
				<p id="sessions_remaining" style="hight:20px">　</p>
				<p>電影票價加價</p>
				<p id="movie_ticket" style="color:#E74C3C"><strong>0</strong></p>
				<p>影廳票價加價</p>
				<p id="cinema_correct" style="color:#E74C3C"><strong>0</strong></p>
				<br>
				<form action="<%= request.getContextPath()%>/Front_end/ticketorder_/chooseSeatS.jsp">
					<input type="hidden" id="sessions_no" name="sessions_no" value="">
					<input id="submit" class="btn btn-primary" type="submit" value="送出">
				</form>
			</div>

		</div>

	</div>
<script	src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		 $('#movie_no').change(function(){
			 if($(this).val()==""){
				 clearSelect();
				 $('#submit').attr("disabled", true);
				 return
			 }
			 $.ajax({
				 type: "GET",
				 url: "<%= request.getContextPath()%>/ticketorder/TicketorderServlet_",
				 data: creatQueryString('getdate', $(this).val(), "", ""),
				 dataType: "json",
				 success: function (data){
					clearSelect();
					 $('#movie_pic').attr("src",data[0].path);
					 $('#movie_name').text(data[0].movie_name);
					 $('#movie_ticket').text(data[0].movie_ticket);
					 $('#movie_intro').text(data[0].movie_intro);
					$.each(data[1], function(i, item){
						$('#date').append("<option value='"+item.date+"'>"+item.date+"</option>");
					});
					$('#submit').attr("disabled", true);
			     },
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
	         })
		 })
		 $('#date').change(function(){
			 if($(this).val()==-1){
				 clearSelect2();
				 return
			 }
			
			$.ajax({
				 type: "GET",
				 url: "<%= request.getContextPath()%>/ticketorder/TicketorderServlet_",
				 data: creatQueryString('getsessions', $('#movie_no').val(), $(this).val(), ""),
				 dataType: "json",
				 success: function (data){
					 clearSelect2();
					 $.each(data, function(i, item){
						 $('#sessions').append("<option value='"+data[i].sessions_no+"'>"+data[i].sessions_start.substring(11,16)+"</option>");
					 });
					 
			     },
	            error: function(){alert("AJAX-class發生錯誤囉!")}
	        })
		})
		 $('#sessions').change(function(){
			 if($(this).val()==-1){
				 clearSelect3();
				 return
			 }
			 $('#sessions_no').val($(this).val());
			$.ajax({
				 type: "GET",
				 url: "<%= request.getContextPath()%>/ticketorder/TicketorderServlet_",
				 data: creatQueryString('getsessions_remaining', "", "", $(this).val()),
				 dataType: "json",
				 success: function (data){
					 clearSelect3();
					 $('#sessions_remaining').text(data.sessions_remaining);
					 $('#cinema_correct').text(data.cinema_correct);
					 $('#submit').attr("disabled", false);					
			     },
	            error: function(){alert("AJAX-class發生錯誤囉!")}
	        })
		})
	})
	
	function creatQueryString(action, movie_no, date, sessions_no){
		console.log("movie_no:"+movie_no+"; date:"+date+";sessions_no"+sessions_no);
		var queryString= {"action":action, "movie_no":movie_no, "date":date, "sessions_no":sessions_no};
		return queryString;
	}
	function clearSelect(){
		$('#date').empty();
		$('#date').append("<option value='-1'>請選擇</option>");
		$('#sessions').empty();
		$('#sessions').append("<option value='-1'>請選擇</option>");
		$('#sessions_remaining').text("　");
		$('#movie_name').text("");
		$('#movie_intro').text("");
		
	}
	function clearSelect2(){
		$('#submit').attr("disabled", true);
		$('#sessions').empty();
		$('#sessions').append("<option value='-1'>請選擇</option>");
		$('#sessions_remaining').text("　");
	}
	function clearSelect3(){
		$('#submit').attr("disabled", true);
		$('#sessions_remaining').text("　");
	}
	
	function init() {
		$('#submit').attr("disabled", true);
	}
	
	window.onload = init;
	</script>
	<!-- 工作區結束 -->

	<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->

	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>