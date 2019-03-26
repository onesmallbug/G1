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
	
	
	 <!-- ****** Footer Social Icon Area Start ****** -->
    <div class="social_icon_area clearfix">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="footer-social-area d-flex">
                        <div class="single-icon">
                            <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i><span>facebook</span></a>
                        </div>
                        <div class="single-icon">
                            <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i><span>Twitter</span></a>
                        </div>
                        <div class="single-icon">
                            <a href="#"><i class="fa fa-google-plus" aria-hidden="true"></i><span>GOOGLE+</span></a>
                        </div>
                        <div class="single-icon">
                            <a href="#"><i class="fa fa-linkedin-square" aria-hidden="true"></i><span>linkedin</span></a>
                        </div>
                        <div class="single-icon">
                            <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i><span>Instagram</span></a>
                        </div>
                        <div class="single-icon">
                            <a href="#"><i class="fa fa-vimeo" aria-hidden="true"></i><span>VIMEO</span></a>
                        </div>
                        <div class="single-icon">
                            <a href="#"><i class="fa fa-youtube-play" aria-hidden="true"></i><span>YOUTUBE</span></a>
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
                            <a href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp" class="yummy-logo">Join Theater</a>
                        </div>
                        <!-- Menu Area Start -->
                        <nav class="navbar navbar-expand-lg">
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#yummyfood-footer-nav" aria-controls="yummyfood-footer-nav" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars" aria-hidden="true"></i> Menu</button>
                            <!-- Menu Area Start -->
                            <div class="collapse navbar-collapse justify-content-center" id="yummyfood-footer-nav">
                                <ul class="navbar-nav">
                                    <li class="nav-item active">
                                        <a class="nav-link" href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp">Home <span class="sr-only">(current)</span></a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">Features</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">Categories</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">Archive</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">About</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">Contact</a>
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
                        <p>Copyright @2018 All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">CA106-G1</a></p>
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- ****** Footer Menu Area End ****** -->
	
	
	
<!-- Jquery-2.2.4 js -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap-4 js -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins JS -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/others/plugins.js"></script>
    <!-- Active JS -->
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/active.js"></script>	
    <script>

		var MyPoint_footer = "/ForTicketorderServlet_ws/${memVO.member_no}";
		var host_footer = window.location.host;
		var path_footer = window.location.pathname;
		var webCtx_footer = path_footer.substring(0, path_footer.indexOf('/', 1));
		var endPointURL_footer = "ws://" + host_footer + webCtx_footer + MyPoint_footer;
		
		var webSocket_map;
		var isMe_footer = false;
		
		function connect_footer() {
			// create a websocket
			webSocket_map = new WebSocket(endPointURL_footer);
			webSocket_map.onopen = function(event) {
		// 			alert("i'm start.");
			};
		
			webSocket_map.onmessage = function(event) {
				if(isMe_footer){return;}
				var jsonArray = JSON.parse(event.data);
				var action = jsonArray[0].action;
					
				if(action == "amember"){
					//如果是給這個會員的
					alert(jsonArray[1].message);		
				}
			};
		
			webSocket_map.onclose = function(event) {};
		}
		
		$(document).ready(connect_footer);
	</script>
</body>
</html>