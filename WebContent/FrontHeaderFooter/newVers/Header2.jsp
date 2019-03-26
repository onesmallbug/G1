<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
 	<meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    

    <link rel="icon" href="<%=request.getContextPath()%>/Front_end/Home/img/core-img/favicon.ico">
    <link href="<%=request.getContextPath()%>/Front_end/Home/style.css" rel="stylesheet">
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
                        <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                        <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                        <a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
                        <a href="#"><i class="fa fa-skype" aria-hidden="true"></i></a>
                        <a href="#"><i class="fa fa-dribbble" aria-hidden="true"></i></a>
                    </div>
                </div>
                <!--  Login Register Area -->
                <div class="col-7 col-sm-6">
                    <div class="signup-search-area d-flex align-items-center justify-content-end">
                        <div class="login_register_area d-flex">
                            <div class="login" >
                                <a href="<%=request.getContextPath()%>/Front_end/Login.jsp">Sign in</a>
                            </div>
                            <div class="register">
                                <a href="register.html">Sign up</a>
                            </div>
                        </div>
                        <!-- Search Button Area -->
                        <div class="search_button">
                            <a class="searchBtn" href="#"><i class="fa fa-search" aria-hidden="true"></i></a>
                        </div>
                        <!-- Search Form -->
                        <div class="search-hidden-form">
                            <form action="#" method="get">
                                <input type="search" name="search" id="search-anything" placeholder="Search Anything...">
                                <input type="submit" value="" class="d-none">
                                <span class="searchBtn"><i class="fa fa-times" aria-hidden="true"></i></span>
                            </form>
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
                        <a href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp" class="yummy-logo">Join Theater</a>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <nav class="navbar navbar-expand-lg">
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#yummyfood-nav" aria-controls="yummyfood-nav" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars" aria-hidden="true"></i> Menu</button>
                        <!-- Menu Area Start -->
                        <div class="collapse navbar-collapse justify-content-center" id="yummyfood-nav">
                            <ul class="navbar-nav" id="yummy-nav">
                                <li class="nav-item active">
                                    <a class="nav-link" href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp">Home <span class="sr-only">(current)</span></a>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="yummyDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Movie Info</a>
                                    <div class="dropdown-menu" aria-labelledby="yummyDropdown">
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/movieinfo/upComingListAll.jsp">Soon Be On</a>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/movieinfo/listAllMovieInfo.jsp">In Theater</a>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/movieinfo/rankMovieList.jsp">Top Ten</a>
                                    </div>
                                </li>
					<!--電影資訊 -->
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="yummyDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Buy Tickets</a>
                                    <div class="dropdown-menu" aria-labelledby="yummyDropdown">
                                        <a class="dropdown-item" href="#">Special Offer</a>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/ticketorder_/prompt.jsp">Ticket Info</a>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/ticketorder_/choiseSessions.jsp">Buy Tickets</a>
                                    </div>
                                </li>
        			<!--訂票功能 -->
                                <li class="nav-item">
                                    <a class="nav-link" href="<%=request.getContextPath()%>/Front_end/newsinfo/listAllNewsInfo.jsp">Movie News</a>
                                </li>
					<!--影視新聞 -->
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="yummyDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Member</a>
                                    <div class="dropdown-menu" aria-labelledby="yummyDropdown">
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/member_regestinfomation.jsp">Sign Up</a>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/member_regestinfomation2.jsp">Member Notice</a>
                                    </div>
                                </li>
 					<!--會員功能 -->
                                <li class="nav-item">
                                    <a class="nav-link" href="#">About</a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- ****** Header Area End ****** -->
    
    








	<!-- Jquery-2.2.4 js -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap-4 js -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins JS -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/others/plugins.js"></script>
    <!-- Active JS -->
    <script src="<%=request.getContextPath()%>/Home/js/active.js"></script>	
</body>
</html>