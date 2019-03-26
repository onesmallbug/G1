<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.sessions.model.*"%>
<%@ page import="ToolClasses.StatusOfSit"%>
<!doctype html>
<html>
<head>
<!-- Bootstrap CSS start-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->
</head>
<body>

	<!-- 工作區開始 -->
	
<jsp:useBean id="sesSer" class="com.sessions.model.SessionsService" scope="page"/>
	

	<div class="container-fluid">
		<div class="row justify-content">
			<div class="col-12">
			<nav class="row justify-content">
							<!-- ---------------------以上現在使用type---------------------- -->


					<%
						List<List<Integer>> sitList = new ArrayList<List<Integer>>();
						String cinema_type = sesSer.getOneSes(request.getParameter("sessions_no")).getSessions_status();

						for (int i = 0; i < 20; i++) {
							List<Integer> list = new ArrayList<Integer>();
							for (int j = 0; j < 20; j++) {
								list.add(new Integer(cinema_type.charAt(i * 20 + j)-48));
							}
							sitList.add(list);
						}
					%>
					<jsp:useBean id="statusOfSitList" scope="application" class="java.util.ArrayList" />
					<%
						int count1 =1;
						int count2 =2;
						int outerSize = sitList.size();
						for (int i = 0; i < outerSize; i++) {
							List<Integer> sitInnerList = sitList.get(i);
							int innerSize = sitInnerList.size();
							for (int j = 0; j < innerSize; j++) {
								StatusOfSit cs = (StatusOfSit) (statusOfSitList.get(sitInnerList.get(j)));
					%>

					<div 
						class="btn  disabled " 
						data-sitType = "<%=cs.getType()%>"
						id="sit<%= i * 20 + j%>"
						style="padding-top:0;padding-bottom:0;
						
						<%if(count1>9 && ((i * 20 + j)%25)==0){ %>
						
						padding-left:3.5px;padding-right:3.5px;
						
						<% }else if(count1<=9 && ((i * 20 + j)%25)==0){%>
						
						padding-left:7.75px;padding-right:7.75px;
						
						<%} %>
						
						<%if((i * 20 + j)>375 && count2>=10){ %>
						
						padding-left:3.43px;padding-right:3.43px;
						
						<% }else if((i * 20 + j)>375 && count2<10){%>
						
						padding-left:7.7px;padding-right:7.7px;
						
						<%} %>
						
						background-color:<%=cs.getColler()%>; border-color:#000; margin-left:5px; margin-top:2px">
						
						<%						
						if(((i * 20 + j)%25)==0){ %>
						<font style="color:white"><%=count1++ %></font>
						<%} %>
						
						<%						
						if((i * 20 + j)>375){ %>
						<font style="color:white"><%=count2++ %></font>
						<%} %>
						
					</div>
					<%
						}
					%>

					<br>

					<%
						}
					%>
							<!-- ---------------------以上現有座位---------------------- -->
	      		</nav>
			</div>
		</div>
	</div>
	<!-- 工作區結束 -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script	src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script  src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>