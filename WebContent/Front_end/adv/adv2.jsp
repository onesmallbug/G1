<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adv.model.*"%>
<%@ page import="java.util.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	AdvVO advVO = (AdvVO) request.getAttribute("advVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<!doctype html>
<html lang="en">
<head>

<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS start-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->



<title>JOIN廣告</title>
		
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets2/css/main.css" />



</head>
<body bgcolor='white' >

<jsp:include page="/FrontHeaderFooter/Header.jsp" />




<!-- Header -->
			<header id="header" class="alt">
				<div class="inner">
					<h1>${advVO.ad_name}</h1>
					<font size="10px">活動期間: ${advVO.getAd_start().toString().substring(0,10)} ~ ${advVO.getAd_end().toString().substring(0,10)}</font>
<!-- 					<p>A free responsive site template by <a href="https://templated.co">TEMPLATED</a></p> -->
				</div>
			</header>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Banner -->
					<section id="intro" class="main">
<%-- 					<img id='${advVO.ad_no}' src='<%=request.getContextPath()%>/Back_end/adv/adv.do?ad_no=${advVO.ad_no}'  height='600'/> --%>
						<img id='${advVO.ad_no}' src='<%=request.getContextPath()%>/Front_end/adv/adv.do?ad_no=${advVO.ad_no}'  height='600'/>
						<span class="icon fa-diamond major"></span>
						
						<div id="messagesArea" class="panel message-area"  ></div> 
<!-- 						<h2>Magna porta maximus</h2> -->
<!-- 						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae<br /> -->
<!-- 						malesuada turpis. Nam pellentesque in ac aliquam. Aliquam tempor<br /> -->
<!-- 						mi porta egestas maximus lorem ipsum dolor.</p> -->
<!-- 						<ul class="actions"> -->
<!-- 							<li><a href="#" class="button big">Learn More</a></li> -->
<!-- 						</ul> -->
					</section>

				

				<!-- Footer -->
					<footer id="footer">
						<ul class="icons">
							<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
							<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
							<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
							<li><a href="#" class="icon fa-linkedin"><span class="label">LinkedIn</span></a></li>
							<li><a href="#" class="icon fa-envelope"><span class="label">Email</span></a></li>
						</ul>
						<p class="copyright">&copy; Untitled. Design: <a href="https://templated.co">TEMPLATED</a>. Images: <a href="https://unsplash.com">Unsplash</a>.</p>
					</footer>

			</div>

		<!-- Scripts -->
			<script src="assets2/js/jquery.min.js"></script>
			<script src="assets2/js/skel.min.js"></script>
			<script src="assets2/js/util.js"></script>
			<script src="assets2/js/main.js"></script>










	
	



	<!-- 工作區結束 -->
	
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->



</body>


<script>

// 	alert("!");
	var ad_cont = '<%= (advVO.getAd_cont()==null)? "" : advVO.getAd_cont()%>';
// 	alert(ad_cont);
	
	messagesArea.innerHTML = messagesArea.innerHTML + ad_cont;
	

</script>

</html>



