<%@page import="java.util.stream.Collectors"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.farediscount.model.*"%>
<%@ page import="java.util.*"%>
<html lang="en">
	<head>
	</head>
	
	<body>
		<jsp:useBean id="fdSer"  class="com.farediscount.model.FarediscountService" scope="page"/>
		<%
		
		Date now = new Date();
		
		List<FarediscountVO> list = 
		fdSer.getAll().stream()
// 		.filter(farediscountVO -> !farediscountVO.getFd_start().after(now))
		.filter(farediscountVO -> !farediscountVO.getFd_end().before(now))
		.sorted(FarediscountVO :: compareTo_bystart)
		.collect(Collectors.toList());
		
		pageContext.setAttribute("list", list);
		%>		

 <div class="container-fluid">
		    <div class="row">
			    <table class="table table-sm">
				    <thead>
					    <tr>
					    	<th scope="col">名稱</th>
					    	<th scope="col">優惠價格</th>
					    	<th scope="col">適用人數</th>
					    	<th scope="col">活動時間</th>
					    </tr>
				    </thead>
				    <tbody>
					    	<c:forEach var="farediscountVO" items="${list}">
					    <tr>
					    	<td>${farediscountVO.fd_name}</td>
					    	<td>${farediscountVO.fd_offer}</td>
					    	<td>${farediscountVO.fd_lower}-${farediscountVO.fd_upper}</td>
					    	<td>${farediscountVO.fd_start}-${farediscountVO.fd_end}</td>
					    </tr>
					    	</c:forEach>
				    </tbody>
			    </table>
		    </div>
	    </div>
	</body>
</html>