<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>
<%@ page import="com.newsinfo.model.*"%>
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
    <title>Movie News</title>
    <% 
	    NewsInfoService newsinfosvc = new NewsInfoService();
		List<NewsInfoVO> list = newsinfosvc.getAll();
	    pageContext.setAttribute("list",list);
    %>
    
    <style>
    	
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
                        <h2>Movie News</h2>
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
                            <li class="breadcrumb-item"><a href="#"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Movie News</li>
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
				<c:forEach var="newsinfoVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				
                <!-- Single Post -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-post wow fadeInUp" data-wow-delay="0.1s">
                        <!-- Post Thumb -->
                        <div class="post-thumb">
                        	<c:set var="mimg" value="${newsinfoVO.news_pic}" />
					            <%
					            	byte b[] = (byte[])pageContext.getAttribute("mimg");
					            	String news_pic = Base64.encode(b);
					            %>
	            <a href="<%=request.getContextPath()%>/Front_end/newsinfo/newsinfo.do?action=getOne_For_Display_Front&news_no=${newsinfoVO.news_no}"><img class="news2_pic" src="data:image/jpg;base64,<%=news_pic%>"></a>
	            <!-- Overlay Text -->
                        </div>
                        <!-- Post Content -->
                        <div class="post-content">
                            <div class="post-meta d-flex">
                                <div class="post-author-date-area d-flex">
                                    <!-- Post Author -->
                                    <div class="post-author">
                                        <a href="#">By ${newsinfoVO.news_auther}</a>
                                    </div>
                                    <!-- Post Date -->
                                    <div class="post-date">
                                        <a href="#">${newsinfoVO.news_times}</a>
                                    </div>
                                </div>
                            </div>
                            <a href="<%=request.getContextPath()%>/Front_end/newsinfo/newsinfo.do?action=getOne_For_Display_Front&news_no=${newsinfoVO.news_no}">
                                <h4 class="post-headline">${newsinfoVO.news_title}</h4>
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