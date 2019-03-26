<%@page import="java.util.stream.Collector"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=Big5" pageEncoding="UTF-8"%>
<%@ page import="com.movieinfo.model.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.stream.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<HTML>
<HEAD>
<TITLE> 查詢員工資料 </TITLE>
<style>

a:visited, a:link {
	color: blue;
	text-decoration: none;
}
a:hover, a:active {
	color: red;
	text-decoration: none;
}
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/Back_end/sessions/resources/font-awesome-4.5.0/css/font-awesome.css">
</HEAD>
<BODY>
<jsp:useBean id="movieInfoSvc" scope="page" class="com.movieinfo.model.MovieInfoService"/>
<%
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date theDate = df.parse(request.getParameter("theDate"));
			
			List<MovieInfoVO> list = 
			movieInfoSvc.getAll()
			.stream()
			.filter(movieInfoVO -> movieInfoVO.getMovie_in().before(theDate))
			.filter(movieInfoVO -> movieInfoVO.getMovie_out().after(theDate))
			.collect(Collectors.toList());
			
			pageContext.setAttribute("list", list);
%>
<%--  <a href='<%= request.getContextPath()%>/Back_end/sessions/insertSessions.jsp?theDate=${param.theDate}' target="blank"></a>  --%>
 <span>
          <select id="movie_no" size="1" style="overflow:hidden; text-overflow:ellipsis;white-space:nowrap;width:110px;">
          
					<option value="#" >請選擇
	          <c:forEach var="movieInfoVO" items="${list}">
					<option value="<%= request.getContextPath()%>/Back_end/sessions/insertSessions.jsp?theDate=${param.theDate}&movie_no=${movieInfoVO.movie_no}" >${movieInfoVO.movie_name}
			</c:forEach>
          </select>
          <i class="fa fa-cog fa-spin fa-1x"></i>
          <b><font size="1px">共<%= list.size()%>部電影</font></b>
 </span>
 <script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	 $('#movie_no').change(function(){
		 window.top.location.href=$('#movie_no').val();
	 }
	 )})
</script>
</BODY>
</HTML>