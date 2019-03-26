<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
            opacity: 0.6;
			}
			
			
			
		</style>
	
	</head>
	<body>
	<div >
		<img src="<%=request.getContextPath()%>/Back_end/img/background3.jpg" class="card-img" alt="" style="">
	</div>
		<header class="container-fluid">
			<nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-left: -15px; margin-right: -15px;">
				<a class="navbar-brand" href="#">Join管理介面</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
	
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle ${empVO eq null ? "disabled" : ""}' href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						廳院管理</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/cinema/ListAllCinema.jsp">瀏覽廳院</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/cinema/insertCinema.jsp">新增廳院</a>
							</div> </li> </ul> </div>
<!-- 以上管理廳院 -->
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle ${empVO eq null ? "disabled" : ""}' href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						場次管理</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown2">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/sessions/listAllSessions.jsp?action=all">瀏覽所有場次</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/sessions/listAllSessions.jsp?action=effect">瀏覽有效場次</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/sessions/select_date.jsp">新增場次</a>
							</div> </li> </ul> </div>
<!-- 以上管理場次-->
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle ${empVO eq null ? "disabled" : ""}' href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						電影種類管理</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown3">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/moviegenre/listAllMovieGenre.jsp">瀏覽所有電影種類</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/moviegenre/addMovieGenre.jsp">新增電影種類</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/moviegenre/select_page.jsp">電影種類管理</a>
							</div> </li> </ul> </div>
<!-- 以上管理電影種類-->
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle ${empVO eq null ? "disabled" : ""}' href="#" id="navbarDropdown4" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						電影資訊管理</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown4">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/movieinfo/listAllMovieInfo.jsp">瀏覽所有電影</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/movieinfo/listAllMovieIn.jsp">瀏覽有效電影</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/movieinfo/addMovieInfo.jsp">新增電影</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/movieinfo/select_page.jsp">電影管理</a>
							</div> </li> </ul> </div>
<!-- 以上管理電影資訊-->
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle ${empVO eq null ? "disabled" : ""}' href="#" id="navbarDropdown5" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						影視新聞管理</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown5">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/newsinfo/listAllNewsInfo.jsp">瀏覽所有新聞</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/newsinfo/addNewsInfo.jsp">新增新聞</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/newsinfo/select_page.jsp">新聞管理</a>
							</div> </li> </ul> </div>
<!-- 以上管理影視新聞-->
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle ${empVO eq null ? "disabled" : ""}' href="#" id="navbarDropdown6" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						廣告管理</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown6">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/adv/listAllAdv.jsp">瀏覽所有廣告</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/adv/addAdv.jsp">新增廣告</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/adv/select_page_adv.jsp">廣告管理</a>
							</div> </li> </ul> </div>
<!-- 以上管理廣告-->
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle ${empVO eq null ? "disabled" : ""}' href="#" id="navbarDropdown7" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						員工管理</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown7">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/emp/select_page.jsp">管理首頁</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/emp/listAllEmp.jsp">員工瀏覽</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/emp/addEmp.jsp">新增員工</a>
							</div> </li> </ul> </div>
<!-- 以上員工管理-->
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle ${empVO eq null ? "disabled" : ""}' href="#" id="navbarDropdown8" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						會員管理</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown8">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/mem/select_page.jsp">管理首頁</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/mem/listAllMem.jsp">會員瀏覽</a>
							</div> </li> </ul> </div>
<!-- 以上會員管理-->
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle ${empVO eq null ? "disabled" : ""}' href="#" id="navbarDropdown9" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						儲值管理</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown9">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/dep/select_page.jsp">管理首頁</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/dep/listAllDep.jsp">儲值明細瀏覽</a>
							</div> </li> </ul> </div>
<!-- 以上儲值管理-->
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
						<a class='nav-link dropdown-toggle ${empVO eq null ? "disabled" : ""}' href="#" id="navbarDropdown10" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						${empVO eq null ? "請先登入" : empVO.employee_name} ${empVO eq null ? "" : "您好"}</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown10">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/emp/homeIndex.jsp">後台管理首頁</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/Back_end/emp/logoutHandler.do?action=logout">員工登出</a>
							</div> </li> </ul> </div>
<!-- 以上員工登出-->




			</nav>
		</header>
		<div style="height: 80px;"></div>
	</body>
</html>