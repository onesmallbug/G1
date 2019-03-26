<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
	<head>
	</head>
	
	<body>
		<jsp:useBean id="tfSer"  class="com.ticketinformation.model.TicketinformationService" scope="page"/>
		<jsp:useBean id="movSer" class="com.movieinfo.model.MovieInfoService" scope="page"/>
		<jsp:useBean id="cinSer" class="com.cinema.model.CinemaService" scope="page"/>
		<c:set var="all" value="${tfSer.all}" scope="page"></c:set>
		<c:set var="movie_ticket" value="${movSer.getOneMovieInfo(param.movie_no).movie_ticket}" scope="page"></c:set>
		<c:set var="cinema_correct" value="${cinSer.getOneCin(param.cinema_no).cinema_correct}" scope="page"></c:set>
		

        <div class="container-fluid">
		    <div class="row">
				<div class="col-md-4">
			      
				      <p>電影票價加價</p>
				      <p>影廳票價加價</p>
				      <P>　</P>
				      <P>原票價</P>
				      <c:forEach items="${all}" var="tfVO">
				      	<p>${tfVO.ti_name}</p>
				      </c:forEach>
			      
				</div>
			      
				<div class="col-md-3">
			      
				      <p>${movie_ticket}</p>
				      <p>${cinema_correct}</p>
				      <P>　</P>
				      <P>　</P>
				      <c:forEach items="${all}" var="tfVO">
				      	<p>${tfVO.ti_price}</p>
				      </c:forEach>
			      
				</div>
			      
				<div class="col-md-5">
				      	<c:forEach items="${all}" var="tfVO">
					      	<p>${tfVO.ti_name}</p>
					      	<center>${tfVO.ti_price + movie_ticket + cinema_correct}</center>
					     </c:forEach>
				</div>
		    </div>
	    </div>
	</body>
</html>