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
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets1/css/main.css" />

</head>
<body bgcolor='white'>

<jsp:include page="/BackHeaderFooter/Header_foradv.jsp" />




<!-- Header -->
			<header id="header">
				<div class="inner">
<!-- 					<a href="index.html" class="logo">introspect</a> -->
<!-- 					<nav id="nav"> -->
<!-- 						<a href="index.html">Home</a> -->
<!-- 						<a href="generic.html">Generic</a> -->
<!-- 						<a href="elements.html">Elements</a> -->
<!-- 					</nav> -->
				</div>
			</header>
			<a href="#menu" class="navPanelToggle"><span class="fa fa-bars"></span></a>

		<!-- Main -->
			<section id="main" >
				<div class="inner">
<!-- 					<header class="major special"> -->
<!-- 						<h1>Generic</h1> -->
<!-- 						<p>Lorem ipsum dolor sit amet nullam id egestas urna aliquam</p> -->
						<h1>${advVO.ad_name}</h1>
						<p>活動期間: ${advVO.getAd_start().toString().substring(0,10)} ~ ${advVO.getAd_end().toString().substring(0,10)}</p>
<!-- 					</header> -->
<!-- 					<a href="#" class="image fit"><img src="images/pic11.jpg" alt="" /></a> -->
					<img id='${advVO.ad_no}' src='<%=request.getContextPath()%>/Front_end/adv/adv.do?ad_no=${advVO.ad_no}'  height='600'/>
					
					<div id="messagesArea" class="panel message-area"  ></div> 
<!-- 					<p>Vis accumsan feugiat adipiscing nisl amet adipiscing accumsan blandit accumsan sapien blandit ac amet faucibus aliquet placerat commodo. Interdum ante aliquet commodo accumsan vis phasellus adipiscing. Ornare a in lacinia. Vestibulum accumsan ac metus massa tempor. Accumsan in lacinia ornare massa amet. Ac interdum ac non praesent. Cubilia lacinia interdum massa faucibus blandit nullam. Accumsan phasellus nunc integer. Accumsan euismod nunc adipiscing lacinia erat ut sit. Arcu amet. Id massa aliquet arcu accumsan lorem amet accumsan.</p> -->
<!-- 					<p>Amet nibh adipiscing adipiscing. Commodo ante vis placerat interdum massa massa primis. Tempus condimentum tempus non ac varius cubilia adipiscing placerat lorem turpis at. Aliquet lorem porttitor interdum. Amet lacus. Aliquam lobortis faucibus blandit ac phasellus. In amet magna non interdum volutpat porttitor metus a ante ac neque. Nisi turpis. Commodo col. Interdum adipiscing mollis ut aliquam id ante adipiscing commodo integer arcu amet Ac interdum ac non praesent. Cubilia lacinia interdum massa faucibus blandit nullam. Accumsan phasellus nunc integer. Accumsan euismod nunc adipiscing lacinia erat ut sit. Arcu amet. Id massa aliquet arcu accumsan lorem amet accumsan commodo odio cubilia ac eu interdum placerat placerat arcu commodo lobortis adipiscing semper ornare pellentesque.</p> -->
<!-- 					<p>Amet nibh adipiscing adipiscing. Commodo ante vis placerat interdum massa massa primis. Tempus condimentum tempus non ac varius cubilia adipiscing placerat lorem turpis at. Aliquet lorem porttitor interdum. Amet lacus. Aliquam lobortis faucibus blandit ac phasellus. In amet magna non interdum volutpat porttitor metus a ante ac neque. Nisi turpis. Commodo col. Interdum adipiscing mollis ut aliquam id ante adipiscing commodo integer arcu amet blandit adipiscing arcu ante.</p> -->
				</div>
			</section>

		

		<!-- Scripts -->
			<script src="assets1/js/jquery.min.js"></script>
			<script src="assets1/js/skel.min.js"></script>
			<script src="assets1/js/util.js"></script>
			<script src="assets1/js/main.js"></script>




	<!-- 工作區結束 -->
	
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
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



