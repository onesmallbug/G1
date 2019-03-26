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



<title>廣告資料</title>

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
/* 	width: 600px; */
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

<jsp:include page="/BackHeaderFooter/Header.jsp" />



<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
		<br>
		 <h3>廣告資料</h3>
<!-- 		 <h4><a href="select_page_adv.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->


<table class="table table-hover" >
<thead>
	<tr>
		<th scope="col">廣告編號</th>
		<th scope="col">廣告名稱</th>
		<th scope="col">廣告圖片</th>
		<th scope="col">廣告內容</th>
		<th scope="col">廣告起始時間</th>
		<th scope="col">廣告終止時間</th>
		<th scope="col">廣告狀態</th>
		<th scope="col">修改</th>
		<th scope="col">預覽</th>
		<th scope="col">推播</th>
	</tr>
</thead>

	
	<tr>
			<th scope="col">${advVO.ad_no}</th>
			<td width="100px">${advVO.ad_name}</td>
			<td width="300px">
				<c:if test="${empty advVO.ad_pic}" var="condition">
				<img src="<%=request.getContextPath()%>/Back_end/adv/images/no_pic.jpg" width="200" height="200"/>
				</c:if>
			    <c:if test="${not empty advVO.ad_pic}" var="condition">
			    <img  src='<%=request.getContextPath()%>/Back_end/adv/adv.do?ad_no=${advVO.ad_no}' width='300' height='200' />
				</c:if>
			</td>
			<td width="600px">
				<div style=overflow:auto;height:300px;width:600px > 
				${advVO.ad_cont}
				</div>
				</td>
			<td>${advVO.ad_start}</td>
			<td>${advVO.ad_end}</td> 
			<td>
<%-- 			${advVO.ad_type} --%>
				 <c:if test="${advVO.ad_type==0}" var="condition">
				下架  無版型
				</c:if>
				<c:if test="${advVO.ad_type==1}" var="condition">
				上架  1.版型
				</c:if>
				<c:if test="${advVO.ad_type==2}" var="condition">
				上架  2.版型
				</c:if>
<%-- 				<c:if test="${advVO.ad_type==3}" var="condition"> --%>
<!-- 				上架  3.版型 -->
<%-- 				</c:if> --%>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_end/adv/adv.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="btn btn-primary iframe_contruler">
			     <input type="hidden" name="ad_no"  value="${advVO.ad_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			 <c:if test="${advVO.ad_type>0}" var="condition">
			 <FORM METHOD="post" ACTION="adv.do" >
				<input type="submit" value="預覽" name="submit" id='${advVO.ad_no}' src="<%=request.getContextPath()%>/Back_end/adv/adv.do?ad_no=${advVO.ad_no}" alt="Submit"  width='200' height='200' class="btn btn-primary iframe_contruler"/>
				<input type="hidden" name="ad_no" value="${advVO.ad_no}">  
			    <input type="hidden" name="action" value="getOne_For_Display_HTML_Back">    
<!-- 			    <input type="submit" value="送出"> -->
			    </FORM>
			    </c:if>
			</td>
			<td>
					<button type='button' id='me' onclick="sendMessage();" class="btn btn-primary iframe_contruler">推播</button>
			</td>
	</tr>
	
	
</table>



<h3 id="statusOutput" class="statusOutput"></h3>



	<!-- 工作區結束 -->
	
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->



</body>
</html>

<script>



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
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
// 			updateStatus("WebSocket 成功連線");
			updateStatus("");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	
// 	        alert(message);
// 	        messagesArea.innerHTML = messagesArea.innerHTML + message;
	

	        
// 	        messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	
	var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	
	function sendMessage() {
// 	    var userName = inputUserName.value.trim();
// 	    if (userName === ""){
// 	        alert ("使用者名稱請勿空白!");
// 	        inputUserName.focus();	
// 			return;
// 	    }
	    
 
		var message = '${advVO.ad_name}';
// 		alert(message);
// 	    var message = CKEDITOR.instances.content.getData()
	    
	    
// 	    CKEDITOR.instances.content.setData('');
	    
	    
	    if (message === ""){
	        alert ("請勿空白!");
	        inputMessage.focus();	
	    }else{
// 	        var jsonObj = {"userName" : userName, "message" : message};
	        var jsonObj = {"message" : message};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	    }
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