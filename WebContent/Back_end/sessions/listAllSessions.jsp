<%@page import="java.util.Comparator"%>
<%@page import="com.movieinfo.model.MovieInfoVO"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.stream.*"%>
<%@ page import="java.util.function.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.sessions.model.*"%>
<%@ page import="com.cinema.model.*"%>
<!doctype html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<title>瀏覽場次</title>

<style>
.table th, td {
	padding: 0.5rem;
	text-align: center;
}
.table td {
	height:73px
}
</style>
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1>瀏覽場次</h1>

	<!-- 工作區開始 -->

	<jsp:useBean id="sessionsSvc" scope="page" class="com.sessions.model.SessionsService" />
	<jsp:useBean id="movieInfoSvc" scope="page" class="com.movieinfo.model.MovieInfoService" />
	<jsp:useBean id="cinemaSvc" class="com.cinema.model.CinemaService" scope="page" />

	<%
		List<SessionsVO> list;
		List<MovieInfoVO> list_movieInfoVO;

		Date now = new Date();

		Stream<SessionsVO> stream = sessionsSvc.getAll().stream();
		Predicate<SessionsVO> predicate1 = null;
		Predicate<SessionsVO> predicate2 = null;
		Comparator<SessionsVO> comparator_sessions_start = null;
		Comparator<SessionsVO> comparator_sessions_remaining = null;
		Comparator<SessionsVO> comparator1 = null;
		Comparator<SessionsVO> comparator2 = null;

		if (request.getParameter("movie_no") != null && !request.getParameter("movie_no").isEmpty()) {
			predicate1 = (sessionsVO -> sessionsVO.getMovie_no().equals(request.getParameter("movie_no")));
		} else {
			predicate1 = (sessionsVO -> true);
		}

		if (request.getParameter("cinema_no") != null && !request.getParameter("cinema_no").isEmpty()) {
			predicate2 = (sessionsVO -> sessionsVO.getCinema_no().equals(request.getParameter("cinema_no")));
		} else {
			predicate2 = (sessionsVO -> true);
		}

		if (request.getParameter("sessions_start") != null
				&& request.getParameter("sessions_start").equals("down")) {
			comparator_sessions_start = (SessionsVO::compareTo_reverse);
		} else {
			comparator_sessions_start = (SessionsVO::compareTo);
		}

		if (request.getParameter("sessions_remaining") != null
				&& request.getParameter("sessions_remaining").equals("up")) {
			comparator_sessions_remaining = (SessionsVO::compareTo_bysessions_remaining);
		} else {
			comparator_sessions_remaining = (SessionsVO::compareTo_bysessions_remaining_reverse);
		}

		if (request.getParameter("sessions_start") != null) {
			comparator2 = comparator_sessions_start;
			comparator1 = comparator_sessions_remaining;
		} else {
			comparator1 = comparator_sessions_start;
			comparator2 = comparator_sessions_remaining;
		}

		if ("all".equals(request.getParameter("action"))) {

			list = stream.filter(predicate1).filter(predicate2).sorted(comparator1).sorted(comparator2)
					.collect(Collectors.toList());
			list_movieInfoVO = movieInfoSvc.getAll();

		} else {

			list = stream.filter(sessionsVO -> !sessionsVO.getSessions_start().before(now)).filter(predicate1)
					.filter(predicate2).sorted(comparator1).sorted(comparator2).collect(Collectors.toList());

			list_movieInfoVO = movieInfoSvc.getAll().stream()
					.filter(movieInfoVO -> !movieInfoVO.getMovie_out().before(now)).collect(Collectors.toList());

		}

		pageContext.setAttribute("list", list);
		pageContext.setAttribute("list_movieInfoVO", list_movieInfoVO);
	%>


	<div class="container-fluid">
		<div class="row justify-content">
			<div class="col-6">
				<form id="form" method="post" action="<%=request.getRequestURI()%>">
					<input type="hidden" name="movie_no" value="${param.movie_no}">
					<input type="hidden" name="cinema_no" value="${param.cinema_no}">
					<input type="hidden" name="action" value="${param.action}">
					<input id="input" type="hidden" name="" value="">
				</form>
				<%@ include file="/Back_end/sessions/page1.file"%>
				<table class="table table-hover table-bordered"
					style="line-height: 1.0; padding: 0.5rem;">
					<tr>
						<th scope="col" style="width: 165px;">場次編號</th>
						<th scope="col" style="width: 160px;">電影名稱</th>
						<th scope="col" style="width: 100px;">廳院名稱</th>
						<th scope="col" style="width: 120px;">
							<i data-name="sessions_start" data-value="up" class="fas fa-angle-up" style="cursor: pointer;"></i> 開始時間 
							<i data-name="sessions_start" data-value="down" class="fas fa-angle-down" style="cursor: pointer;"></i>
						</th>
						<th scope="col" style="width: 120px;">
							<i data-name="sessions_remaining" data-value="up" class="fas fa-angle-up" style="cursor: pointer;"></i> 剩餘座位
							<i data-name="sessions_remaining" data-value="down" class="fas fa-angle-down" style="cursor: pointer;"></i>
						</th>
						<th scope="col" style="width: 95px;">查看座位</th>
					</tr>
					<c:forEach var="sessionsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

						<tr>

							<td scope="row">${sessionsVO.sessions_no}</td>
							<td>${movieInfoSvc.getOneMovieInfo(sessionsVO.movie_no).movie_name }</td>
							<td>${cinemaSvc.getOneCin(sessionsVO.cinema_no).cinema_name}</td>
							<td>${sessionsVO.sessions_start.toString().substring(0,16)}</td>
							<td>${sessionsVO.sessions_remaining}</td>
							<td><div class="btn btn-primary iframe_contruler"
									data-sessions_no="${sessionsVO.sessions_no}">查看</div></td>

						</tr>

					</c:forEach>
				</table>

				<!--//page2 開始 -->
				<FORM METHOD="post" ACTION="<%=request.getRequestURI()%>" style="width:717px;">
					<div class="form-group row">
					
						<div class="col-sm-7">
							<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
								<div class="btn-group mr-2" role="group" aria-label="First group">
							<% if (rowsPerPage < rowNumber) { %>
								<% if (pageIndex >= rowsPerPage) { %>
									<button type="button" class="page btn btn-secondary" data-name="whichPage" data-value="1">至第一頁</button>
									<button type="button" class="page btn btn-secondary" data-name="whichPage" data-value="<%=whichPage - 1%>">上一頁</button>
								<% }else{ %>
									<button type="button" class="btn btn-secondary disabled">至第一頁</button>
									<button type="button" class="btn btn-secondary disabled">上一頁</button>
								<% } %>
								<% if (pageIndex < pageIndexArray[pageNumber - 1]) { %>
									<button type="button" class="page btn btn-secondary" data-name="whichPage" data-value="<%=whichPage + 1%>">下一頁</button>
									<button type="button" class="page btn btn-secondary" data-name="whichPage" data-value="<%=pageNumber%>">至最後一頁</button>
								<% } else { %>
									<button type="button" class="btn btn-secondary disabled">下一頁</button>
									<button type="button" class="btn btn-secondary disabled">至最後一頁</button>
								<% } %>
							<% } %>
		
								</div>
							</div>
						</div>
						
						<div class="col-sm-4">
							<% if (pageNumber > 1) { %>
							<select size="1" name="whichPage" class="form-control" id="exampleFormControlSelect1">
								<% for (int i = 1; i <= pageNumber; i++) { %>
									<option value="<%=i%>">跳至第<%=i%>頁
								<% } %>
							</select> 
						</div>
						
						<div class="col-sm-1">
							<input type="submit" value="確定" class="btn btn-primary">
							<input type="hidden" name="movie_no" value="${param.movie_no}">
							<input type="hidden" name="cinema_no" value="${param.cinema_no}">
							<input type="hidden" name="action" value="${param.action}">
							<c:if test="${param.sessions_start != null}">
								<input type="hidden" name="sessions_start" value="${param.sessions_start}">
							</c:if>
							<c:if test="${param.sessions_remaining != null}">
								<input type="hidden" name="sessions_remaining" value="${param.sessions_remaining}">
							</c:if>
							
							<% } %>
						</div>
						
					</div>
				</FORM>
				<!--//page2 結束 -->


			</div>
			<div class="col-6">
				<iframe id="iframe"
					src="<%=request.getContextPath()%>/Back_end/sessions/checkOneSessions.jsp?sessions_no=SESSIONS00000001"
					style="width: 800px; border-width: 0px; height: 450px"> </iframe>
				<nav class="row justify-content">
					<div class="col-12">
						<c:forEach var="var" items="${statusOfSitList}">
								${var.str}
								<div class="btn disabled " 
								data-sitType="${var.type}"
								data-sitStr="${var.str}" 
								id="type${var.type}"
								style="background-color:${var.coller}; border-color:#000; margin-right:10px">
							</div>
						</c:forEach>
					</div>
				</nav>
			</div>
		</div>
	</div>
	<form id="form2" method="post" action="<%=request.getRequestURI()%>">
		<input type="hidden" name="movie_no" value="${param.movie_no}">
		<input type="hidden" name="cinema_no" value="${param.cinema_no}">
		<input type="hidden" name="action" value="${param.action}">
		<c:if test="${param.sessions_start != null}">
			<input type="hidden" name="sessions_start" value="${param.sessions_start}">
		</c:if>
		<c:if test="${param.sessions_remaining != null}">
			<input type="hidden" name="sessions_remaining" value="${param.sessions_remaining}">
		</c:if>
		<input id="input2" type="hidden" name="" value="">
	</form>


	<!-- 工作區結束 -->

	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>


	<!-- js開始 -->
	<script>
		$(document).ready(init);
		function init() {
			$('.iframe_contruler').click(
					function(e) {
						var sessions_no = $(this).attr('data-sessions_no');
						var lastIndex = $('#iframe').attr('src').lastIndexOf(
								'=');
						var src = $('#iframe').attr('src').substring(0,
								lastIndex + 1)
								+ sessions_no;
						$('#iframe').attr('src', src);
					});

			$('.iframe_contruler')[0].click();
			$('.fas').click(function(e) {
				$('#input').attr('name', $(this).attr('data-name'));
				$('#input').val($(this).attr('data-value'));
				$('#form').submit();
			});

			$('.page').click(function(e) {
				$('#input2').attr('name', $(this).attr('data-name'));
				$('#input2').val($(this).attr('data-value'));
				$('#form2').submit();
			});
			$('.disabled').unbind();
		}
	</script>

	<!-- js結束 -->

	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>