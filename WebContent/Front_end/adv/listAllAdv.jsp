 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adv.model.*"%>


<%
	AdvService advSvc = new AdvService();
    List<AdvVO> list = advSvc.getAll();
//     for(AdvVO lists:list){
//     	System.out.println(AdvVO.getAd_no()+"");
    	
//     }
    pageContext.setAttribute("list",list);
%> 

<!doctype html>
<html lang="en">
<head>

<!-- 自動刷新 -->
<!-- <meta http-equiv="refresh" content="3" /> -->


<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS start-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->



<title>所有員工資料 - listAllAdv.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white' onload="connect();">
<jsp:include page="/FrontHeaderFooter/Header.jsp" />


<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
<table id="table-1">
	<tr><td>
		 <h3>所有廣告資料 - listAllAdv.jsp</h3>
<!-- 		 <h4><a href="select_page_adv.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<br>


	
	<br>
	<c:forEach var="advVO" items="${list}" >
	
	
	${advVO.ad_no}
	
	
	
<%-- 				<c:if test="${empty advVO.ad_pic}" var="condition"> --%>
<%-- 				<img src="<%=request.getContextPath()%>/Back_end/adv/images/no_pic.jpg" width="200" height="200"/> --%>
<%-- 				</c:if> --%>
			    
<%-- 			    <c:if test="${not empty advVO.ad_pic}" var="condition"> --%>
<%-- 			    <h4><a href="${advVO.ad_cont}"> <img  src='<%=request.getContextPath()%>/Back_end/adv/adv.do?ad_no=${advVO.ad_no}' width='200' height='200' /></a></h4> --%>
<%-- 				</c:if> --%>


				
				 <c:if test="${advVO.ad_type!=0}" var="condition">
				 <c:if test="${not empty advVO.ad_pic}" var="condition">
								
				
				 
				 <c:if test="${not empty advVO.ad_cont}" var="condition">
<%-- 				 <jsp:useBean id="advSvc" scope="page" class="com.adv.model.AdvService" /> --%>
				 <FORM METHOD="post" ACTION="adv.do" >
				 <input type="image" name="submit" id='${advVO.ad_no}' src="<%=request.getContextPath()%>/Front_end/adv/adv.do?ad_no=${advVO.ad_no}" alt="Submit"  width='200' height='200' />
<%-- 			    <h4><a href="templated-caminar/adv${advVO.ad_type}.html"> <img id='${advVO.ad_no}' src='<%=request.getContextPath()%>/Back_end/adv/adv.do?ad_no=${advVO.ad_no}' width='200' height='200'/></a></h4> --%>
<%-- 			    <c:forEach var="advVO" items="${advSvc.all}" >  --%>
<%--           			<option value="${advVO.ad_no}">${advVO.ad_no} --%>
<%--          		</c:forEach> --%>
<%-- 				${advVO.ad_no} --%>
<!-- 				<FORM METHOD="post" ACTION="adv.do" > -->
				<input type="hidden" name="ad_no" value="${advVO.ad_no}">  
			    <input type="hidden" name="action" value="getOne_For_Display_HTML">    
<!-- 			    <input type="submit" value="送出"> -->
			    </FORM>
				</c:if>
				
				
				 <c:if test="${empty advVO.ad_cont}" var="condition">
				 <FORM>
				<h4><img id='${advVO.ad_no}' src='<%=request.getContextPath()%>/Front_end/adv/adv.do?ad_no=${advVO.ad_no}' width='200' height='200'/></h4>
				</FORM>
				</c:if>
				
				<script>
				
				var ScheduleDate = "${advVO.getAd_start().toString().substring(0,19)}";
				var ScheduleEndDate = "${advVO.getAd_end().toString().substring(0,19)}"
				var CurrentDate = new Date();
// 				var ad_no = '${advVO.ad_no}';
				
				
				
// 				alert(ad_no);
// 				alert(ScheduleDate);
// 				alert(ScheduleEndDate);
// 				alert(CurrentDate);

// 				if ( (Date.parse(ScheduleDate)).valueOf() <= (Date.parse(CurrentDate)).valueOf() &&  (Date.parse(ScheduleEndDate)).valueOf() > (Date.parse(CurrentDate)).valueOf())
// 				{
// 					var ad_no = '${advVO.ad_no}';
// 					document.getElementById(ad_no).style.display = "none";	
// 					alert(ad_no);
// 					alert("ad_start比系統目前時間小");
							
							
// 				}else 
				if ((Date.parse(ScheduleDate)).valueOf() > (Date.parse(CurrentDate)).valueOf() || (Date.parse(ScheduleEndDate)).valueOf() <= (Date.parse(CurrentDate)).valueOf()){
						
// 					var ad_no = '${advVO.ad_no}';
					var ad_no = '${advVO.ad_no}';
					document.getElementById(ad_no).style.display = "none";	
					alert(ad_no);
					
// 					alert("ad_start比系統目前時間大");
// 						document.getElementById('ad_no').style.display = "none";
				}
				
				</script>		
<!-- 				style = "display:none" -->
				
				</c:if>
				</c:if>

	</c:forEach>



<!--        <img src="images/tomcat.png" width="100" height="100" border="0" style ="display"> -->




<!-- <h1> Chat Room </h1> -->
	    <h3 id="statusOutput" class="statusOutput"></h3>


<!--  <input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();"/> -->
<!-- 		    <input type="button" id="connect"     class="button" value="連線" onclick="connect();"/> -->
<!-- 		    <input type="button" id="disconnect"  class="button" value="離線" onclick="disconnect();"/> -->


<div id="showbox" ></div>
<!-- <input type="submit" id="sendMessage" class="button" value="送出" onclick="ShowTime()"/>  -->



	<!-- 工作區結束 -->
	
	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->



</body>
</html>


<script>

// alert("!");

// function ShowImg(){
// 				var ScheduleDate = "${advVO.getAd_start().toString().substring(0,19)}";
// 				var CurrentDate = new Date();
// 				var ad_no = '${advVO.ad_no}';
				
// 				alert(ad_no);
// 				alert(ScheduleDate);
// 				alert(CurrentDate);

// 				if ( (Date.parse(ScheduleDate)).valueOf() < (Date.parse(CurrentDate)).valueOf())
// 				{
// 							alert("ad_start比系統目前時間小");
// 				}else if ((Date.parse(ScheduleDate)).valueOf() > (Date.parse(CurrentDate)).valueOf()){
// 						alert("ad_start比系統目前時間大");
// 						document.getElementById('ad_no').style.display = "none";
// 				}
				
// }				


</script>





<script>
function ShowTime(){
	document.getElementById('showbox').innerHTML = new Date();
	setTimeout('ShowTime()',1000);
}
</script>




<script>

	

    
    var MyPoint = "/MyEchoServer/peter";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
	function connect() {
		
		ShowTime();
		
		
// 		document.getElementById('showbox').innerHTML = new Date();
// 		　setTimeout('ShowTime()',1000);
		
		
		
		
		
		
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			updateStatus("WebSocket 成功連線");
// 			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
// 	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	        var message = jsonObj.message + "\r\n";
	        alert(message + "上架了! 快來看!");
	        
	        
	        
// 	        messagesArea.innerHTML = messagesArea.innerHTML + message;
// 	        messagesArea.scrollTop = messagesArea.scrollHeight;
		
		
		
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	

	
	

	
	function disconnect () {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
	
	 
    
</script>
