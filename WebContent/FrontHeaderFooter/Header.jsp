<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
    
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
					<div
						class="signup-search-area d-flex align-items-center justify-content-end">
						<div class="login_register_area d-flex">
						
<!-- 						20190325-子傑更新 -->
						<c:choose>
						    <c:when test="${empty memVO}">
							<button style="background-color:white ; color:black; border:0px"  id="openloginDescription" type="button"
								class="btn btn-primary" data-toggle="modal" data-target="#loginDescription">sign in</button>
							<button style="background-color:white ; color:black; border:0px"  id="openloginDescription" type="button"
								class="btn btn-primary"  onclick="location.href='<%=request.getContextPath()%>/Front_end/mem/member_regestinfomation.jsp'">
								sign up</button>
						    </c:when>
						    <c:when test="${not empty memVO}">
								<li><a>Hello: <font color=#ea7500 size=4px >${memVO.member_name}</font>您好</a></li>
								<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/Front_end/mem/logoutHandler.do">
									<input type="submit" value="logout">
									<input type="hidden" name="logout" value="logout">
								</FORM>
						    </c:when>
						    <c:otherwise>
						    </c:otherwise>
						</c:choose>
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
                                        <a class="dropdown-item" href="#" id="openfdDescriptioninheader">Special Offer</a>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/ticketorder_/prompt.jsp">Ticket Info</a>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/ticketorder_/choiseSessions.jsp">Buy Tickets</a>
                                    </div>
                                </li>
        			<!--訂票功能 -->
                                <li class="nav-item">
                                    <a class="nav-link" href="<%=request.getContextPath()%>/Front_end/newsinfo/listAllNewsInfo.jsp">Movie News</a>
                                </li>
					<!--影視新聞 -->
					<c:choose>
					    <c:when test="${empty memVO}">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="yummyDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Member</a>
                                    <div class="dropdown-menu" aria-labelledby="yummyDropdown">
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/member_regestinfomation.jsp">Sign Up</a>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/member_regestinfomation2.jsp">Member Notice</a>
                                    </div>
                                </li>
					    </c:when>
					    <c:when test="${not empty memVO}">
                     			<li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="yummyDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Member</a>
                                    <div class="dropdown-menu" aria-labelledby="yummyDropdown">
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/select_page.jsp">Member Home</a>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/listOneMem.jsp">Member Show</a>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/update_mem_input.jsp">Member Modify</a>
                                    </div>
                                </li>
					    </c:when>
					    <c:otherwise>
					    </c:otherwise>
					</c:choose>
                     
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
    <script src="<%=request.getContextPath()%>/Front_end/Home/js/active.js"></script>	
    
    
    
		<!-- 以下優惠活動 -->
    
    <div class="modal fade" id="fdDescription-inheader" tabindex="-1" role="dialog" aria-labelledby="fdDescriptionTitle-inheader" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		    	
		      <div class="modal-header">
		        <h5 class="modal-title" id="fdDescriptionTitle-inheader">優惠說明</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      
		      <div class="modal-body" id="fdDescription_modal-body-inheader">
		      </div>
		      
		      <div class="modal-footer">
		        <button id="closeiframe" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
<!--     子傑更新 20190325 -->
    
    <div class="modal fade" id="loginDescription" tabindex="-1" role="dialog" aria-labelledby="loginDescriptionTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
    	
      <div class="modal-header">
        <h5 class="modal-title" id="loginDescriptionTitle">Join揪影影城登入系統</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="loginDescription_modal-body">
      </div>
      <div class="modal-footer">
        <button id="closeiframe" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
	<!-- 子傑登入js -->
	<script>
		function openloginDescription(e){
			$('#loginDescription_modal-body').load('<%=request.getContextPath()%>/Front_end/Login2.jsp?myself=<%=request.getRequestURI()%>');
		}
		
		function openfdDescriptioninheader(e){
			$('#fdDescription_modal-body-inheader').load('<%=request.getContextPath()%>/Front_end/farediscount/fdDescription.jsp');
		}

		$(document).ready(function() {
			$('#openfdDescriptioninheader').click(openfdDescriptioninheader);
			$('#openloginDescription').click(openloginDescription);
		});
	</script>
    
    
    
    
    
</body>
</html>