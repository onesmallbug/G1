
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="ToolClasses.StatusOfSit"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cinema.model.*"%>
<%@ page import="com.sessions.model.*"%>
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
<title>訂票</title>
<style>
p {
	margin: 0px
}
</style>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	<jsp:useBean id="tfSer" class="com.ticketinformation.model.TicketinformationService"></jsp:useBean>
	
	<%
	String sessions_no = request.getParameter("sessions_no");
	request.setAttribute("sessions_no", sessions_no); %>
	<jsp:useBean id="sesSer" class="com.sessions.model.SessionsService" scope="page"/>
	<jsp:useBean id="movSer" class="com.movieinfo.model.MovieInfoService" scope="page"/>

	<div class="container">
		<div class="row justify-content">

			<div class="col-4">
				<div class="row justify-content">
					<div class="col-6">

						<h5>電影名稱：</h5>
						<h5>日 期：</h5>
						<h5>時 間：</h5>
					</div>

					<div class="col-6">
						<h5>${movSer.getOneMovieInfo(sesSer.getOneSes(sessions_no).movie_no).movie_name}</h5>
						<h5>${sesSer.getOneSes(sessions_no).sessions_start.toString().substring(0,10) }</h5>
						<h5>${sesSer.getOneSes(sessions_no).sessions_start.toString().substring(10,16) }</h5>
					</div>
				</div>
				
				<br>
				<div class="row justify-content">
					<div class="col-6">
						<h5>餘額</h5>
						<hr>
						<h5>總額</h5>
					</div>

					<div class="col-6">
						<h5>${memVO.member_point }</h5>
						<hr>
						<h5 id="order_amount">0</h5>
					</div>
				</div>
				<div class="col-6">
				
				<form id="form" method="post" action="<%= request.getContextPath()%>/movieticke/MovietickeServlet_">
				<input type="hidden" name="sessions_no" value="<%=sessions_no%>">
				<input type="hidden" name="action" value="insert">
				<input class="btn btn-primary" type="submit" value="結帳去">
				</form>
				
				</div>
				<div class="row justify-content">
					<div class="col-6" id="ti">
						<h5>票種</h5>
					</div>

					<div class="col-6" id="mt">
						<h5>位置</h5>
					</div>
				</div>

			</div>

			<div class="col-8">
			<nav class="row justify-content">
				
					<div class="col-9">
						<c:forEach var="tf" items="${tfSer.all}">
								${tf.ti_name}
								<div 
									class="btn"
									data-sitType = "${TicketTypeMap[tf.ti_no].no}"
									data-sitStr = "${tf.ti_name}"
									id = "type${tf.ti_no}"
									style="background-color:${TicketTypeMap[tf.ti_no].coller}; border-color:#000; margin-right:10px">
									
								</div>
						</c:forEach>
					</div>
								<!-- ---------------------以上所有type---------------------- -->
				<div class="col-3">
						<p style="margin:0px">現在使用</p>
						<p id="str" style="margin:0px">一般票</p>
						
						<div 
							class="btn disabled " 
							data-sitType = "a"  
							data-sitStr = "一般票"  
							id="color" 
							style="border-color:#000; margin:0px;background-color:#00e">
						</div>
						
				</div>
							
							<div class= "row">
							<!-- ---------------------以上現在使用type---------------------- -->


					<%
						List<List<Integer>> sitList = new ArrayList<List<Integer>>();
						String cinema_type = sesSer.getOneSes(sessions_no).getSessions_status();

						for (int i = 0; i < 20; i++) {
							List<Integer> list = new ArrayList<Integer>();
							for (int j = 0; j < 20; j++) {
								list.add(new Integer(cinema_type.charAt(i * 20 + j)-48));
							}
							sitList.add(list);
						}
					%>
					<jsp:useBean id="statusOfSitList" scope="application" class="java.util.ArrayList" />
					<%
						int count1 =1;
						int count2 =2;
						int outerSize = sitList.size();
						for (int i = 0; i < outerSize; i++) {
							List<Integer> sitInnerList = sitList.get(i);
							int innerSize = sitInnerList.size();
							for (int j = 0; j < innerSize; j++) {
								StatusOfSit cs = (StatusOfSit) (statusOfSitList.get(sitInnerList.get(j)));
					%>

					<div 
						class="btn <%if(!(cs.getType() == 1)){%> disabled <% }%> " 
						data-sitType = "<%=cs.getType()%>"
						id="sit<%= i * 20 + j%>"
						style="padding-top:0;padding-bottom:0;
						
						<%if(count1>9 && ((i * 20 + j)%25)==0){ %>
						
						padding-left:3.5px;padding-right:3.5px;
						
						<% }else if(count1<=9 && ((i * 20 + j)%25)==0){%>
						
						padding-left:7.75px;padding-right:7.75px;
						
						<%} %>
						
						<%if((i * 20 + j)>375 && count2>=10){ %>
						
						padding-left:3.43px;padding-right:3.43px;
						
						<% }else if((i * 20 + j)>375 && count2<10){%>
						
						padding-left:7.7px;padding-right:7.7px;
						
						<%} %>
						
						background-color:<%=cs.getColler()%>; border-color:#000; margin-left:5px; margin-top:2px">
						
						<%						
						if(((i * 20 + j)%25)==0){ %>
						<font style="color:white"><%=count1++ %></font>
						<%} %>
						
						<%						
						if((i * 20 + j)>375){ %>
						<font style="color:white"><%=count2++ %></font>
						<%} %>
						
					</div>
					<%
						}
					%>

					<br>

					<%
						}
					%>


				</div>
							
							
							<!-- ---------------------以上現有座位---------------------- -->
				</nav>
			<nav class="row justify-content">
				
					<div class="col-12">
						<c:forEach var="var" items="${statusOfSitList}">
								${var.str}
								<div 
									class="btn disabled "
									data-sitType = "${var.type}"
									data-sitStr = "${var.str}"
									id = "type${var.type}"
									style="background-color:${var.coller}; border-color:#000; margin-right:10px">
								</div>
						</c:forEach>
					</div>
								<!-- ---------------------以上影廳type---------------------- -->
				</nav>
			
			</div>

		</div>
	</div>



	<script
		src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script>
    function $id(id) {
        return document.getElementById(id);
    }

    function $add(a, b, c) {
        return a.addEventListener(b, c, false);
    } //物件,事件,動作

    function $bgc(a, b) {
        a.style.backgroundColor = b;
    } //物件,"顏色"
    
    function choosStyle(e){
    	$id("str").innerText = e.target.getAttribute("data-sitStr");
    	$bgc($id("color"), e.target.style.backgroundColor);
    	$id("color").setAttribute("data-sitType", e.target.getAttribute("data-sitType"));
    	$id("color").setAttribute("data-sitStr", e.target.getAttribute("data-sitStr"));
    }

    function setStyle(e){
    	
    	if(e.target.getAttribute("data-sitType") == 1){
	    	e.target.setAttribute("data-sitType", $id("color").getAttribute("data-sitType"));
	    	$bgc(e.target,$id("color").style.backgroundColor);
	    	$('#form').append('<input type="hidden" id=mt_no'+e.target.id.substring(3)+' name="mt_no" value='+e.target.id.substring(3)+'>');
	    	$('#form').append('<input type="hidden" id=ti_no'+e.target.id.substring(3)+' name="ti_no" value='+$id("color").getAttribute("data-sitType")+'>');
	    	$('#mt').append('<h6 id="mt'+e.target.id.substring(3)+'">'+Math.ceil(parseInt(e.target.id.substring(3))/25)+'-'+(parseInt(e.target.id.substring(3))%25+1)+'</h4>');
	    	$('#ti').append('<h6 id="ti'+e.target.id.substring(3)+'">'+$id("color").getAttribute("data-sitStr")+'</h4>');
	    	
    	}else{
    		e.target.setAttribute("data-sitType", 1);
        	$bgc(e.target,'#3f9');
        	$id('mt_no'+e.target.id.substring(3)).remove();
        	$id('ti_no'+e.target.id.substring(3)).remove();
        	$id('mt'+e.target.id.substring(3)).remove();
        	$id('ti'+e.target.id.substring(3)).remove();
        	
    	}
    	
    }
    
    function init() {
		var statusOfSitList_size = ${tfSer.all.size()};
		var sit_size = <%= cinema_type.length()%>;
    	
       	
       	for(var i = 0;i<sit_size;i++){
       		if($id("sit"+i).getAttribute("data-sitType")==1){
       		$add($id("sit"+i),"click",setStyle);
       		}
       	}
       	
       	for(var i = 1;i<=statusOfSitList_size;i++){
       		$add($id("type"+i),"click",choosStyle);
       	}
    }

    window.onload = init;
	</script>
	<!-- 工作區結束 -->

	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>