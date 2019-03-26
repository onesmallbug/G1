<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Bootstrap CSS start-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
	<!-- Bootstrap CSS end-->
	<title></title>
	
	<style>
		input{
		margin-bottom:0.6rem;
		}
	</style>
	
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	<div class="container">
		<div class="row justify-content">
		
			<div class="col-3">
				<c:forEach var="message" items="${errorMessage}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</div>
			
			<div class="col-6">
				<form action="<%=request.getContextPath()%>/farediscount/FarediscountServlet" method="post" class="row justify-content">
					
					<div class="col-3">
						<p>優惠名稱</p>
						<p>優惠折扣</p>
						<p>優惠起始時間</p>
						<p>優惠結束時間</p>
						<p>優惠人數上限</p>
						<p>優惠人數下限</p>
						<input type="submit" value="新增" class="btn btn-primary">
					</div>
						
					<div class="col-6">
						<input type="text" name="fd_name" placeholder="20字以內" style="width:178px;">
						<input type="number" name="fd_offer" min="-100" max="0" placeholder="0~-100" list="defaultlist" style="width:178px;">
						<input type="text" name="fd_start" id="fd_start" style="width:178px;">
						<input type="text" name="fd_end" id="fd_end"  placeholder="請先選優惠起始時間" style="width:178px;">
						<input type="number" name="fd_upper" min="1" max="30" placeholder="1~30" list="defaultNumbers" style="width:178px;">
						<input type="number" name="fd_lower" min="1" max="30" placeholder="1~30" list="defaultNumbers" style="width:178px;">
						<datalist id="defaultNumbers">
						  <option value="1">
						  <option value="4">
						  <option value="5">
						  <option value="10">
						  <option value="11">
						  <option value="20">
						  <option value="21">
						  <option value="30">
						</datalist>
						<datalist id="defaultlist">
						  <option value="-10">
						  <option value="-20">
						  <option value="-30">
						</datalist>
					</div>
					
					<input type="hidden" name="action" value="insert">
				
				</form>
			</div>
			
			<div class="col-3">
			</div>

		</div>
	</div>
	
	<% 
	  java.sql.Date hiredate =new java.sql.Date(System.currentTimeMillis());
	%>
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Back_end/farediscount/datetimepicker/jquery.datetimepicker.css"/>
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/Back_end/farediscount/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/Back_end/farediscount/datetimepicker/jquery.datetimepicker.full.js"></script>
	
	<style>
	  .xdsoft_datetimepicker .xdsoft_datepicker {width:  300px;   /* width:  300px; */}
	  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {height: 151px;   /* height:  151px; */}
	</style>
	
	<script>
	        
			$(document).ready(init);
	        $.datetimepicker.setLocale('zh');
			
			function init(){
				$('#fd_start').change(
					function()
						{
							$('#fd_end').val($('#fd_start').val());
							$('#fd_end').removeAttr('ondatetimepicker');
							creat_fd_end_datetimepicker();
						});
			}
	        
	        function creat_fd_end_datetimepicker(){
	        	 $('#fd_end').datetimepicker({
	 				theme: '',				//theme: 'dark',
	 				timepicker:false,		//timepicker:true,
	 				format:'Y-m-d',			//format:'Y-m-d H:i:s',
	 				minDate: $('#fd_start').val(),	// 去除今日(不含)之前
	 		     });
	        }
	        
	        $('#fd_start').datetimepicker({
				theme: '',					//theme: 'dark',
				timepicker:false,			//timepicker:true,
				format:'Y-m-d',				//format:'Y-m-d H:i:s',
				value: '<%=hiredate%>',		// value:   new Date(),
				minDate: '<%=hiredate%>',	// 去除今日(不含)之前
	        });
	        
	</script>
	<!-- 工作區結束 -->
	
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>
<%
if(request.getAttribute("errorMessage") == null){
request.getSession().setAttribute("insert_fd_key", new Object());
}
%>
