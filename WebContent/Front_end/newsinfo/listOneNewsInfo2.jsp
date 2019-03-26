<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movieinfo.model.*"%>
<%@ page import="com.moviegenre.model.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>
<%@ page isELIgnored="false"%>

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
    <title>Movie Info-In Theaters</title>

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
                            <li class="breadcrumb-item"><a href="#">Movie News List</a></li>
                            <li class="breadcrumb-item"><a href="#">Movie News</a></li>
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
                                    <img src="<%=request.getContextPath()%>/Front_end/Home/img/newspic/2.jpg" alt="">
                                </div>
                                <!-- Post Content -->
                                <div class="post-content">
                                    <div class="post-meta d-flex">
                                        <div class="post-author-date-area d-flex">
                                            <!-- Post Author -->
                                            <div class="post-author">
                                                <a href="#">By David Seafood</a>
                                            </div>
                                            <!-- Post Date -->
                                            <div class="post-date">
                                                <a href="#">March 29, 2019</a>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="#">
                                        <h2 class="post-headline">從技術面討論為什麼《蜘蛛人：新宇宙》是近年最強的動畫電影</h2>
                                    </a>
                                    <p>
                                    	在剛剛結束的第 91 屆奧斯卡頒獎典禮上，來自索尼的《蜘蛛人：新宇宙》（Spider-Man: Into the Spider-Verse）拿下最佳動畫長片，是什麼原因可以讓這個作品在真人超級英雄浪潮下獲得如此高的注目？而它又憑什麼打敗動畫強權迪士尼以及魏斯安德森（Wes Anderson）的《犬之島》？其實別說今年，《蜘蛛人：新宇宙》從故事、敘事到呈現方式，都是動畫長片前所未見的規模與野心，如果你看過這部電影，一定可以瞭解其中吸引人且精緻的質感，同時那與過往動畫有所不同的畫面都是經過縝密的計畫和安排，而它到底有什麼特別之處？

										還原漫畫的一切本質
										真要說《蜘蛛人：新宇宙》最特別之處，就是在於他們盡可能地讓你在觀賞本片時如同閱讀一本漫畫書，本片的導演之一鮑伯珀爾西切蒂（Bob Persichetti）在接受 The Verge 訪問時就談到了如何利用「影格率」（Frame per Second，FPS）來製造觀眾的視覺差異：「過往多數的動畫電影都是 12 FPS，但傳統的電影則是 24 FPS，而那些手繪動畫則是先以 12 FPS 的規格再去透過電腦後製複製成 24 FPS；對於我們來說我們做了一件再簡單不過的事，就是把每一格都用 12 FPS 呈現，這樣的效果會讓畫面更『脆』、『銳利』，但純粹就是希望重現漫畫翻閱上的感受」簡單來說，傳統製作動畫上的思維，就是希望畫面的動態是柔順（Smooth）的，但鮑伯的想法則是相反過來，他們就是希望每一個動態（相對來說）都是有些遲緩的，進而達到更有張力的效果，當然這樣重回傳統的決定在製作上碰到許多問題，最棘手的莫過於長期依賴電腦的動畫師，「我很幸運我有一群很棒的夥伴為這樣的概念打造全新的系統」
										
										擔任這部電影藝術總監的派翠克歐奇夫（Patrick O’Keefe）在接受 polygon 網站訪問時談論了大量如何建構他們想要建構的全新動畫世界：「從一開始我們就打算走「還原漫畫」這條路，同時我們也將現實世界與漫畫融為一體，從設計的角度來看，它僅是關於形狀、結構與色調的選擇過程，目的就是希望讓觀眾在事物的觀賞上簡潔易懂，但它相當然爾的必須要花時間好好策劃；就某種意義上說，我們每天都在關注城市當中以及現實生活裡的細節，但對我來說，很多動畫電影都忽略了這些細節，比如那些塗鴉、粘在郵箱上的口香糖，以及紋理的複雜性。」
										
										若你仔細觀察《蜘蛛人：新宇宙》的每一個畫面，其中的細節是難以置信的，以這一幕來說，就可以感受到派翠克提到的「街道細節」
                                    </p>
                                    <blockquote class="yummy-blockquote mt-30 mb-30">
                                        <h5 class="mb-30">“Technology is nothing. What's important is that you have a faith in people, that they're basically good and smart, and if you give them tools, they'll do wonderful things with them.”</h5>
                                        <h6 class="text-muted">Steven Jobs</h6>
                                    </blockquote>                                    
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
                                <h6>About Me</h6>
                            </div>
                            <div class="about-me-widget-thumb">
                                <img src="<%=request.getContextPath()%>/Front_end/Home/img/newspic/David.png" alt="">
                            </div>
                            <h4 class="font-shadow-into-light">David Seafood</h4>
                            <p>	一個不吃海鮮的男子
					   			卻選擇踏上賣海鮮的路
								路上還會遇到什麼令他意想不到的事呢
								讓我們繼續看下去~~~</p>
                        </div>

                        <!-- Single Widget Area -->
                        <div class="single-widget-area popular-post-widget">
                            <div class="widget-title text-center">
                                <h6>Populer Post</h6>
                            </div>
                            <!-- Single Popular Post -->
                            <div class="single-populer-post d-flex">
                                <img src="<%=request.getContextPath()%>/Front_end/Home/img/newspic/1.jpg" alt="">
                                <div class="post-content">
                                    <a href="#">
                                        <h6>為何蜘蛛人-新宇宙 是最佳動畫片?</h6>
                                    </a>
                                    <p>March 3, 2019</p>
                                </div>
                            </div>
                            <!-- Single Popular Post -->
                            <div class="single-populer-post d-flex">
                                <img src="<%=request.getContextPath()%>/Front_end/Home/img/newspic/2.jpg" alt="">
                                <div class="post-content">
                                    <a href="#">
                                        <h6>小班證實不再飾演蝙蝠俠</h6>
                                    </a>
                                    <p>March 22, 2019</p>
                                </div>
                            </div>
                            <!-- Single Popular Post -->
                            <div class="single-populer-post d-flex">
                                <img src="<%=request.getContextPath()%>/Front_end/Home/img/newspic/3.jpg" alt="">
                                <div class="post-content">
                                    <a href="#">
                                        <h6>查克史奈德的正義聯盟呢</h6>
                                    </a>
                                    <p>March 24, 2019</p>
                                </div>
                            </div>
                            <!-- Single Popular Post -->
                            <div class="single-populer-post d-flex">
                                <img src="<%=request.getContextPath()%>/Front_end/Home/img/newspic/4.jpg" alt="">
                                <div class="post-content">
                                    <a href="#">
                                        <h6>咻咻咻 射向你的心</h6>
                                    </a>
                                    <p>March 25, 2019</p>
                                </div>
                            </div>
                            <!-- Single Popular Post -->
                            <div class="single-populer-post d-flex">
                                <img src="<%=request.getContextPath()%>/Front_end/Home/img/newspic/5.jpg" alt="">
                                <div class="post-content">
                                    <a href="#">
                                        <h6>《你殺我狗 我殺你全家3》 正式上線</h6>
                                    </a>
                                    <p>March 27, 2019</p>
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
</body>
</html>