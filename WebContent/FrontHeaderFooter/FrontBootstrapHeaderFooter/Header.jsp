<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<!DOCTYPE html>
<html>
	<head>
		<style>
			header {
				position: fixed;
				z-index: 999;
			}
			
			input [type="submit"] {
				padding: 5px 15px;
				background: white;
				cursor: pointer;
				-webkit-border-radius: 5px;
				border-radius: 5px;
			}
			
			.card-img{
            position: fixed;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            z-index: -999;
            min-height: 100%;
            width: 100%;
            opacity: 0.3;
			}
		</style>
	
	</head>
	<body>
	<div>
		<img src="<%=request.getContextPath()%>/FrontHeaderFooter/FrontBootstrapHeaderFooter/Front_end_img.jpg" class="card-img" alt="" style="">
	</div>
		<header class="container-fluid">
			<nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-left: -15px; margin-right: -15px;">
				<a class="navbar-brand" href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp">Join影城</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
	
				<div class="collapse navbar-collapse" id="navbarSupportedContent1">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle' href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						訂票</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/ticketorder_/choiseSessions.jsp">線上訂票</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/ticketorder_/prompt.jsp">訂票須知</a>
								<a class="dropdown-item" href="#" id="openfdDescriptioninheader" data-toggle="modal" data-target="#fdDescription-inheader">優惠訊息</a>
							</div> </li></ul></div>
<!-- 以上訂票 -->
				<div class="collapse navbar-collapse" id="navbarSupportedContent1">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle' href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						電影簡介</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown2">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/movieinfo/upComingListAll.jsp">即將上映</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/movieinfo/listAllMovieInfo.jsp">現正熱映</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/movieinfo/rankMovieList.jsp">年度電影推薦</a>
							</div> </li></ul></div>
<!-- 以上電影簡介 -->
				<div class="collapse navbar-collapse" id="navbarSupportedContent1">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle' href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						影視新聞</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown3">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/newsinfo/listAllNewsInfo.jsp">影視新聞</a>
							</div> </li></ul></div>
<!-- 以上影視新聞 -->
				<div class="collapse navbar-collapse" id="navbarSupportedContent1">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle' href="#" id="navbarDropdown4" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Home</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown4">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp">回影城首頁</a>
							</div> </li></ul></div>
<!-- 以上回影城首頁 -->
<c:if test="${empty memVO}">
				<div class="collapse navbar-collapse" id="navbarSupportedContent1">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle' href="#" id="navbarDropdown5" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						會員註冊</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown5">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/member_regestinfomation.jsp">會員註冊</a> 
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/member_regestinfomation2.jsp">會員註冊須知</a>
							</div> </li></ul></div>
</c:if>
<!-- 以上會員註冊 -->
<c:if test="${empty memVO}">
				<div class="collapse navbar-collapse" id="navbarSupportedContent1">
					<ul class="navbar-nav mr-auto">
						 <li class="nav-item">
							<a class="nav-link" href="#" id="openloginDescription" data-toggle="modal" data-target="#loginDescription">登入</a>
						</li></ul></div>
</c:if>

<c:if test="${not empty memVO}">
				<div class="collapse navbar-collapse" id="navbarSupportedContent1">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle' href="#" id="navbarDropdown5" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						會員個人資料</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown5">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/select_page.jsp">會員首頁</a> 
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/listOneMem.jsp">會員資料查看</a> 
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/update_mem_input.jsp">會員資料修改</a> 
							</div> </li></ul></div>
<!-- 以上會員個人資料 -->
</c:if>
<c:if test="${not empty memVO}">
				<div class="collapse navbar-collapse" id="navbarSupportedContent1">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item">
							<div class="nav-link">Hello：${memVO.member_name}　您好</div>
						</li></ul></div>
</c:if>
<!-- 以上顯示會員登入 -->
<c:if test="${not empty memVO}">
				<div class="collapse navbar-collapse" id="navbarSupportedContent1">
					<ul class="navbar-nav mr-auto">
						<li>
							<a id="logout_a" class="nav-link" >登出</a>
							<FORM id="logout_form" METHOD="POST" ACTION = "<%=request.getContextPath()%>/Front_end/mem/logoutHandler.do">
								<input type = "hidden" name="logout" value="logout"> 
							</FORM>
						</li></ul></div>
</c:if>
						
			</nav>
		</header>
			<div style="height: 80px;"></div>
				<div class="modal fade" id="fdDescription-inheader" tabindex="-1" role="dialog" aria-labelledby="fdDescriptionTitle-inheader" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="fdDescriptionTitle-inheader">優惠說明</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body" id="fdDescription_modal-body-inheader">
							</div>
							<div class="modal-footer">
								<button id="closeiframe" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<!-- Modal -->
				<div class="modal fade" id="loginDescription" tabindex="-1" role="dialog" aria-labelledby="loginDescriptionTitle" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="loginDescriptionTitle">Join揪影影城登入系統</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body" id="loginDescription_modal-body">
							</div>
							<div class="modal-footer">
								<button id="closeiframe" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
		
		<!-- 以上登入畫面 -->
		<script	src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
		<script>
			function openloginDescription(e){
				$('#loginDescription_modal-body').load('<%=request.getContextPath()%>/Front_end/Login2.jsp?myself=<%=request.getRequestURI()%>');
			}
			
			function openfdDescriptioninheader(e){
				$('#fdDescription_modal-body-inheader').load('<%=request.getContextPath()%>/Front_end/farediscount/fdDescription.jsp');
			}
			
			function logout(e){
				$('#logout_form').submit();
			}
					
			$(document).ready(
				function(){
					$('#openfdDescriptioninheader').click(openfdDescriptioninheader);
					$('#openloginDescription').click(openloginDescription);
					$('#logout_a').click(logout);
				}
			);
		</script>
	</body>
</html>