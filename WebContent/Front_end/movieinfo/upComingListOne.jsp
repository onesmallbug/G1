<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movieinfo.model.*"%>
<%@ page import="com.moviegenre.model.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>

<%
  MovieInfoVO movieinfoVO = (MovieInfoVO) request.getAttribute("movieinfoVO"); 
  
  MovieGenreService moviegenreSvc = new MovieGenreService();
  pageContext.setAttribute("msc",moviegenreSvc);
%>    
    
    
    
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Movie Info-Soon Be On</title>

	<style>
		#movie_pic{
			width:320px;
			height:450px;
		}
		
		#level{
			width:30px;
			height:30px;
		}
	</style>
	
	<!--     Favicon -->
    <link rel="icon" href="<%=request.getContextPath()%>/Front_end/Home/img/core-img/favicon.ico">

	<!--     Core Stylesheet -->
    <link href="<%=request.getContextPath()%>/Front_end/Home/style.css" rel="stylesheet">

	<!--     Responsive CSS -->
    <link href="<%=request.getContextPath()%>/Front_end/Home/css/responsive/responsive.css" rel="stylesheet">
	
</head>

<body>
<!-- ****** Top Header Area Start ****** -->
	<div class="top_header_area">
		<div class="container">
			<div class="row">
				<div class="col-5 col-sm-6">
					<!--  Top Social bar start -->
					<div class="top_social_bar">
						<a href="https://www.facebook.com/profile.php?id=100000279160092"><i
							class="fa fa-facebook" aria-hidden="true"></i></a> <a href="#"><i
							class="fa fa-twitter" aria-hidden="true"></i></a> <a href="#"><i
							class="fa fa-linkedin" aria-hidden="true"></i></a> <a href="#"><i
							class="fa fa-skype" aria-hidden="true"></i></a> <a href="#"><i
							class="fa fa-dribbble" aria-hidden="true"></i></a>
					</div>
				</div>
				<!--  Login Register Area -->
				<div class="col-7 col-sm-6">
					<div
						class="signup-search-area d-flex align-items-center justify-content-end">
						<div class="login_register_area d-flex">
						
<!-- 						20190325-子傑更新 -->


						
						<c:if test="${empty memVO}">
						
						
							<button style="background-color:white ; color:black; border:0px"  id="openloginDescription" type="button"
								class="btn btn-primary" data-toggle="modal"
								data-target="#loginDescription">sign in</button>
								
								
							<button style="background-color:white ; color:black; border:0px"  id="openloginDescription" type="button"
								class="btn btn-primary"  onclick="location.href='<%=request.getContextPath()%>/Front_end/mem/member_regestinfomation.jsp'">
								sign up</button>
								
						</c:if>	
						
						
						<c:if test="${not empty memVO}">
						
						<li><a>Hello: <font color=#ea7500 size=4px >${memVO.member_name}</font>您好
							</a></li>
							
						
<!-- 						<button style="background-color:white ; color:black; border:0px"  id="openloginDescription" type="button" -->
<%-- 								class="btn btn-primary"  onclick="location.href='<%=request.getContextPath()%>/Front_end/Login.jsp'"> --%>
<!-- 								Logout</button> -->

						<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/Front_end/mem/logoutHandler.do">
							<input type="submit" value="logout">
							<input type="hidden" name="logout" value="logout">
						</FORM>
						
						</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ****** Top Header Area End ****** -->

	<!-- ****** Header Area Start ****** -->
	<header class="header_area">
		<div class="container">
			<div class="row">
				<!-- Logo Area Start -->
				<div class="col-12">
					<div class="logo_area text-center">
						<a href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp"
							class="yummy-logo">Join Theater</a>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12">
					<nav class="navbar navbar-expand-lg">
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#yummyfood-nav"
							aria-controls="yummyfood-nav" aria-expanded="false"
							aria-label="Toggle navigation">
							<i class="fa fa-bars" aria-hidden="true"></i> Menu
						</button>
						<!-- Menu Area Start -->
						<div class="collapse navbar-collapse justify-content-center"
							id="yummyfood-nav">
							<ul class="navbar-nav" id="yummy-nav">
								<li class="nav-item active"><a class="nav-link"
									href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp">Home
										<span class="sr-only">(current)</span>
								</a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="yummyDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">Movie Info</a>
									<div class="dropdown-menu" aria-labelledby="yummyDropdown">
										<a class="dropdown-item"
											href="<%=request.getContextPath()%>/Front_end/movieinfo/upComingListAll.jsp">Soon
											Be On</a> <a class="dropdown-item"
											href="<%=request.getContextPath()%>/Front_end/movieinfo/listAllMovieInfo.jsp">In
											Theaters</a> <a class="dropdown-item"
											href="<%=request.getContextPath()%>/Front_end/movieinfo/rankMovieList.jsp">Top
											Ten</a>
									</div></li>
								<!--電影資訊 -->
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="yummyDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">Buy Tickets</a>
									<div class="dropdown-menu" aria-labelledby="yummyDropdown">
										<a class="dropdown-item" href="#">Special Offer</a> <a
											class="dropdown-item"
											href="<%=request.getContextPath()%>/Front_end/ticketorder_/prompt.jsp">Ticket
											Info</a> <a class="dropdown-item"
											href="<%=request.getContextPath()%>/Front_end/ticketorder_/choiseSessions.jsp">Buy
											Tickets</a>
									</div></li>
								<!--訂票功能 -->
								<li class="nav-item"><a class="nav-link"
									href="<%=request.getContextPath()%>/Front_end/newsinfo/listAllNewsInfo.jsp">Movie
										News</a></li>
								<!--影視新聞 -->
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="yummyDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">Member</a>
									<div class="dropdown-menu" aria-labelledby="yummyDropdown">
										<a class="dropdown-item"
											href="<%=request.getContextPath()%>/Front_end/mem/member_regestinfomation.jsp">Sign
											Up</a> <a class="dropdown-item"
											href="<%=request.getContextPath()%>/Front_end/mem/member_regestinfomation2.jsp">Member
											Notice</a>
									</div></li>
								<!--會員功能 -->
								<li class="nav-item"><a class="nav-link" href="#">About</a>
								</li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</header>
	<!-- ****** Header Area End ****** -->	
    <!-- Preloader Start -->
    <div id="preloader">
        <div class="yummy-load"></div>
    </div>
    
    <!-- ****** Breadcumb Area Start ****** -->
    <div class="breadcumb-area" style="background-image: url(<%=request.getContextPath()%>/Front_end/Home/img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>Soon Be On</h2>
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
                            <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                            <li class="breadcrumb-item"><a href="#">Movie Info</a></li>
                            <li class="breadcrumb-item"><a href="#">Soon Be On List</a></li>
                            <li class="breadcrumb-item"><a href="#">Soon Be On</a></li>
                            
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Single Blog Area Start ****** -->
    <section class="single_blog_area section_padding_80">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="row no-gutters">

                        <!-- Single Post Share Info -->
                        <div class="col-2 col-sm-1">
                            <div class="single-post-share-info mt-100">
                             <!-- 新增movie_level -->			
												<c:set var="movie_level" value="${movieinfoVO.movie_level}"></c:set>
												<%
													byte c[]= (byte[])pageContext.getAttribute("movie_level");
													String encode1 = null;
													if(c != null){
														encode1 = Base64.encode(c);
												%>
												<a href="#"><img id="level" src="data:image/jpg;base64,<%=encode1%>"></a>
												<%}else{%> <%}%>
                                <a href="#" class="facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                                <a href="#" class="twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                                <a href="#" class="googleplus"><i class="fa fa-google-plus" aria-hidden="true"></i></a>
                                <a href="#" class="instagram"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                                <a href="#" class="pinterest"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                            </div>
                        </div>

                        <!-- Single Post -->
                        <div class="col-10 col-sm-11">
                            <div class="single-post">
                                <!-- Post Thumb -->
                                <div class="post-thumb">
                                    <iframe width="660" height="400" src="${movieinfoVO.movie_trailer}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></iframe>
                                </div>
                                <!-- Post Content -->
                                <div class="post-content">
                                    <div class="post-meta d-flex">
                                        <div class="post-author-date-area d-flex">
                                         	<!-- Post Genre -->
                                            <div class="post-author">
                                                <a href="#">${msc.getOneGenre(movieinfoVO.genre_no).genre_name}</a>
                                            </div>
                                            <!-- Post movie_length -->
                                            <div class="post-author">
                                                <a href="#">${movieinfoVO.movie_length}</a>
                                            </div>
                                            <!-- Post Director -->
                                            <div class="post-author">
                                                <a href="#">By ${movieinfoVO.movie_director}</a>
                                            </div>
                                             <!-- Post Director -->
                                            <div class="movie_ticket">
                                                <a href="#">+${movieinfoVO.movie_ticket}</a>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="#">
                                        <h2 class="post-headline">${movieinfoVO.movie_name}</h2>
                                    </a>
                                    <p style="font-size:20px;">${movieinfoVO.movie_intro}</p>
                                </div>
                            </div>                           
                       </div>
                    </div>
                </div>

                <!-- ****** Blog Sidebar ****** -->
                <div class="col-12 col-sm-8 col-md-6 col-lg-4">
                    <div class="blog-sidebar mt-5 mt-lg-0">
                        <!-- Single Widget Area -->
                        <div class="single-widget-area about-me-widget text-center">
                            <div class="widget-title">
                                <h6>Cover</h6>
                            </div>
                            <div >
                            	<!-- 新增movie_pic-->
								<c:set var="movie_pic" value="${movieinfoVO.movie_pic}"></c:set>
								<%
									byte b[]= (byte[])pageContext.getAttribute("movie_pic");
									String encode = null;
									if(b != null){
										encode = Base64.encode(b);
								%>
								<img  id="pic" class="movie_pic" src="data:image/jpg;base64,<%=encode%>" alt="">
								<%}else{%> <%}%>
								<div></div>
								
								<h4 class="font-shadow-into-light"> </h4>
								<div class="widget-title text-center">
	                                <h6>Expect Vote</h6>
	                            	</div>
										<tr>
											<td>
											     <input type="button" class="expect" id="exp2" value="期待">
											     <input type="hidden" name="movie_no"  value="${movieinfoVO.movie_no}">
											     <input type="hidden" name="action"	value="exp">
											</td>
											<td>
											     <input type="button" class="noexpect" id="noexp2" value="不期待">
											     <input type="hidden" name="movie_no"  value="${movieinfoVO.movie_no}">
											     <input type="hidden" name="action"	value="noexp">
											</td>
										</tr>
	                            </div>
	                        </div>

                        <!-- Single Widget Area -->
                        <div class="single-widget-area popular-post-widget">
                            <div class="widget-title text-center">
                                <h6>Populer Movie</h6>
                            </div>
                            <!-- Single Popular Post -->
                            <div class="single-populer-post d-flex">
                                <img class="populer_pic" src="<%=request.getContextPath()%>/Front_end/Home/img/upComing/01.jpg" alt="">
                                <div class="post-content">
                                    <a href="#">
                                        <h6>AVENGERS:INFINITY WAR</h6>
                                    </a>
                                    <p>HERO FILM</p>
                                </div>
                            </div>
                            <!-- Single Popular Post -->
                            <div class="single-populer-post d-flex">
                                <img class="populer_pic" src="<%=request.getContextPath()%>/Front_end/Home/img/upComing/02.jpg" alt="">
                                <div class="post-content">
                                    <a href="#">
                                        <h6>Shazan!</h6>
                                    </a>
                                    <p>HERO FILM</p>
                                </div>
                            </div>
                            <!-- Single Popular Post -->
                            <div class="single-populer-post d-flex">
                                <img class="populer_pic" src="<%=request.getContextPath()%>/Front_end/Home/img/upComing/03.jpg" >
                                <div class="post-content">
                                    <a href="#">
                                        <h6>HELLBOY</h6>
                                    </a>
                                    <p>HERO FILM</p>
                                </div>
                            </div>
                            <!-- Single Popular Post -->
                            <div class="single-populer-post d-flex">
                                <img class="populer_pic" src="<%=request.getContextPath()%>/Front_end/Home/img/upComing/04.jpg">
                                <div class="post-content">
                                    <a href="#">
                                        <h6>DUMBO</h6>
                                    </a>
                                    <p>FANTASY</p>
                                </div>
                            </div>
                            <!-- Single Popular Post -->
                            <div class="single-populer-post d-flex">
                                <img class="populer_pic" src="<%=request.getContextPath()%>/Front_end/Home/img/upComing/05.jpg" alt="">
                                <div class="post-content">
                                    <a href="#">
                                        <h6>RESIDE</h6>
                                    </a>
                                    <p>HORROR</p>
                                </div>
                            </div>
                        </div>                        
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ****** Single Blog Area End ****** -->  
    
    <!-- ****** Footer Social Icon Area Start ****** -->
	<div class="social_icon_area clearfix">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="footer-social-area d-flex">
						<div class="single-icon">
							<a href="https://www.facebook.com/profile.php?id=100000279160092"><i
								class="fa fa-facebook" aria-hidden="true"></i><span>facebook</span></a>
						</div>
						<div class="single-icon">
							<a href="#"><i class="fa fa-twitter" aria-hidden="true"></i><span>Twitter</span></a>
						</div>
						<div class="single-icon">
							<a href="#"><i class="fa fa-google-plus" aria-hidden="true"></i><span>GOOGLE+</span></a>
						</div>
						<div class="single-icon">
							<a href="#"><i class="fa fa-linkedin-square"
								aria-hidden="true"></i><span>linkedin</span></a>
						</div>
						<div class="single-icon">
							<a href="https://www.instagram.com/illilliili/"><i
								class="fa fa-instagram" aria-hidden="true"></i><span>Instagram</span></a>
						</div>
						<div class="single-icon">
							<a href="#"><i class="fa fa-vimeo" aria-hidden="true"></i><span>VIMEO</span></a>
						</div>
						<div class="single-icon">
							<a
								href="https://www.youtube.com/channel/UClhecf7eOGHwbKW5e7l_pTA"><i
								class="fa fa-youtube-play" aria-hidden="true"></i><span>YOUTUBE</span></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ****** Footer Social Icon Area End ****** -->

	<!-- ****** Footer Menu Area Start ****** -->
	<footer class="footer_area">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="footer-content">
						<!-- Logo Area Start -->
						<div class="footer-logo-area text-center">
							<a href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp"
								class="yummy-logo">Join Theater</a>
						</div>
						<!-- Menu Area Start -->
						<nav class="navbar navbar-expand-lg">
							<button class="navbar-toggler" type="button"
								data-toggle="collapse" data-target="#yummyfood-footer-nav"
								aria-controls="yummyfood-footer-nav" aria-expanded="false"
								aria-label="Toggle navigation">
								<i class="fa fa-bars" aria-hidden="true"></i> Menu
							</button>
							<!-- Menu Area Start -->
							<div class="collapse navbar-collapse justify-content-center"
								id="yummyfood-footer-nav">
								<ul class="navbar-nav">
									<li class="nav-item active"><a class="nav-link"
										href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp">Home
											<span class="sr-only">(current)</span>
									</a></li>
									<li class="nav-item"><a class="nav-link" href="#">Features</a>
									</li>
									<li class="nav-item"><a class="nav-link" href="#">Categories</a>
									</li>
									<li class="nav-item"><a class="nav-link" href="#">Archive</a>
									</li>
									<li class="nav-item"><a class="nav-link" href="#">About</a>
									</li>
									<li class="nav-item"><a class="nav-link" href="#">Contact</a>
									</li>
								</ul>
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-12">
					<!-- Copywrite Text -->
					<div class="copy_right_text text-center">
						<p>
							Copyright @2019 All rights reserved | This web is made with <i
								class="fa fa-heart-o" aria-hidden="true"></i> by <a href="#"
								target="_blank">CA106-G1</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</footer>

	<!-- ****** Footer Menu Area End ****** -->   

<!--     Jquery-2.2.4 js -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/jquery/jquery-2.2.4.min.js"></script>
<!--     Popper js -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/bootstrap/popper.min.js"></script>
<!--     Bootstrap-4 js -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/bootstrap/bootstrap.min.js"></script>
<!--     All Plugins JS -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/others/plugins.js"></script>
<!--     Active JS -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/active.js"></script>

<script>
		$(".expect").click(function(){
			var element = $(this);
			$.ajax({
				type:"POST",
				url:"<%=request.getContextPath()%>/Front_end/movieinfo/movieinfo.do",
				data:{
					"action":"exp",
					"movie_no":$(this).next().attr("value")
				},
				dataType:"json",
				success:function(response){
					var sum = parseInt(response.exp)+parseInt(response.noexp);
					console.log((parseInt(response.exp)/sum)*100);
					element.parent().text(parseInt((parseInt(response.exp)/sum)*100)+"%");
					$('#exp2').remove();
					$('#noexp2').remove();
									
				},
				error:function(data, textStatus, jqXHR){
					console.log('Ajax Error');
					console.log('data: ' + data);
					console.log('textStatus: '+ textStatus);
					console.log('jqXHR: ' + jqXHR);
				}
			});	
		});
	</script>
	
	<script>
		$(".noexpect").click(function(){
			var element = $(this);
			$.ajax({
				type:"POST",
				url:"<%=request.getContextPath()%>/Front_end/movieinfo/movieinfo.do",
				data:{
					"action":"noexp",
					"movie_no":$(this).next().attr("value")
				},
				dataType:"json",
				success:function(response){
					var sum = parseInt(response.exp)+parseInt(response.noexp);
					console.log((parseInt(response.exp)/sum)*100);
					element.parent().text(parseInt((parseInt(response.exp)/sum)*100)+"%");
					$('#exp2').remove();
					$('#noexp2').parent().remove();
					$('#noexp2').remove();
									
				},
				error:function(data, textStatus, jqXHR){
					console.log('Ajax Error');
					console.log('data: ' + data);
					console.log('textStatus: '+ textStatus);
					console.log('jqXHR: ' + jqXHR);
				}
			});	
		});
	</script>
</body>
</html>