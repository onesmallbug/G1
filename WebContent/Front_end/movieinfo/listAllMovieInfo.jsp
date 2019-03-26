<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>
<%@ page import="com.movieinfo.model.*"%>
<%@ page import="com.moviegenre.model.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Movie Info-In Theaters</title>
    <% 
	    MovieInfoService movieinfoSvc = new MovieInfoService();
	   	MovieGenreService moviegenreSvc = new MovieGenreService();
	   	pageContext.setAttribute("msc",moviegenreSvc);
   	
   // 篩選是否為上映中電影	
       List<MovieInfoVO> lista = movieinfoSvc.getAll();
       List<MovieInfoVO> list = new ArrayList<MovieInfoVO>();
       java.util.Date now = new java.util.Date();
       java.sql.Date sqlDate = new java.sql.Date(now.getTime());
       for(MovieInfoVO movVO:lista){
       	if(sqlDate.after(movVO.getMovie_in()) && sqlDate.before(movVO.getMovie_out())){
       		list.add(movVO);
       	}
       }
       pageContext.setAttribute("list",list);
    %>
    
    <style>
    	.movie2_pic{
    		width:300px;
    		height:500px;
    	}
    </style>
</head>

<body>
<jsp:include page="/FrontHeaderFooter/Header.jsp" />

    <!-- ****** Breadcumb Area Start ****** -->
    <div class="breadcumb-area" style="background-image: url(<%=request.getContextPath()%>/Front_end/Home/img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>In Theaters</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp"><i class="fa fa-home" aria-hidden="true"></i>Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Movie Info</li>
                            <li class="breadcrumb-item active" aria-current="page">In Theaters List</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80">
    <%@ include file="page1.file" %> 
        <div class="container">
            <div class="row">
				<c:forEach var="movieinfoVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				
                <!-- Single Post -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-post wow fadeInUp" data-wow-delay="0.1s">
                        <!-- Post Thumb -->
                        <div class="post-thumb">
                        	<c:set var="mimg" value="${movieinfoVO.movie_pic}" />
					            <%
					            	byte b[] = (byte[])pageContext.getAttribute("mimg");
					            	String movie_pic = Base64.encode(b);
					            %>
	            <a href="<%=request.getContextPath()%>/Front_end/movieinfo/movieinfo.do?action=getOne_For_Display_Front&movie_no=${movieinfoVO.movie_no}"><img class="movie2_pic" src="data:image/jpg;base64,<%=movie_pic%>"></a>
	            <!-- Overlay Text -->
                        </div>
                        <!-- Post Content -->
                        <div class="post-content">
                            <div class="post-meta d-flex">
                                <div class="post-author-date-area d-flex">
                                    <!-- Post Author -->
                                    <div class="post-author">
                                        <a href="#">By ${movieinfoVO.movie_director}</a>
                                    </div>
                                    <!-- Post Date -->
                                    <div class="post-date">
                                        <a href="<%=request.getContextPath()%>/Front_end/movieinfo/movieinfo.do?action=getOne_For_Display_Front_Chatroom&movie_no=${movieinfoVO.movie_no}">Chat Room</a>
                                    </div>
                                </div>
                            </div>
                            <a href="<%=request.getContextPath()%>/Front_end/movieinfo/movieinfo.do?action=getOne_For_Display_Front&movie_no=${movieinfoVO.movie_no}">
                                <h4 class="post-headline">${movieinfoVO.movie_name}</h4>
                            </a>
                        </div>
                    </div>
                </div>
             </c:forEach>

<!--以下為頁碼-->
                <div class="col-12">
                    <div class="pagination-area d-sm-flex mt-15">
                        <nav aria-label="#">
                            <ul class="pagination">
                                <%@ include file="page2.file" %>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </section>
    	
  		<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
  
</body>
</html>