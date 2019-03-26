 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movieinfo.model.*"%>
<%@ page import="com.moviegenre.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>

<%--  <%@ page import="java.util.*" %> --%>

<%
  MovieInfoVO movieinfoVO = new MovieInfoService().getOneMovieInfo(request.getParameter("movie_no")); 
  
  MovieGenreService moviegenreSvc = new MovieGenreService();
  pageContext.setAttribute("msc",moviegenreSvc);
  pageContext.setAttribute("movieinfoVO",movieinfoVO);
%>

<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
%>
<!doctype html>
<html lang="en">
<head>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> -->


<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS start-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->






<title>聊天室</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
	
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  .card-img{
  		position:fixed;
  		top:0;
  		left:0;
  		bottom:0;
  		right:0;
  		z-index:-999;
  		min-height:100%;
  		width:100%;
  }
  
  #movietable{
  		position: relative;
	border: 10px solid rgba(0,0,0,0.5);
	transition: all 0.5s linear;
  
  
  }
   
   #movietable:hover{
   border-radius: 25px;
	transition: all 0.5s linear; 
     
   
   }
   
   #chatroomarea{
   		position: relative;
	border: 10px solid #ffffff;
	transition: all 0.5s linear;
   }
   #chatroomarea:hover{
   		border-radius: 25px;
	transition: all 0.5s linear; 
     
   }
   
    #chatroomarea_2{
   		position: relative;
	border: 10px solid rgba(0,0,0,0.5);
	transition: all 0.5s linear;
   }
   #chatroomarea_2:hover{
   		border-radius: 25px;
	transition: all 0.5s linear; 
     
   }
   
   #chatroomarea_1{
   		position: relative;
	border: 10px solid rgba(0,0,0,0.5);
	transition: all 0.5s linear;
   }
   #chatroomarea_1:hover{
   		border-radius: 20px;
	transition: all 0.5s linear; 
     
   }
   
</style>


		
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Front_end/chatroom/css/styles.css" type="text/css"/>



</head>

<!-- 開始呼叫連線方法 -->
<body onload="connect();" onunload="disconnect();">

<!-- 這是Header -->
<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Header.jsp" />

<div>
	<img src="<%=request.getContextPath()%>/Front_end/chatroom/9986.jpg_wh1200.jpg" class="card-img" alt="..." style="">


</div>

<div class="row">

<div class="col-1">
</div>
<div class="col-7">


<!-- 這是聊天室 -->

		<br>
	    <h3 id="statusOutput" class="statusOutput"></h3>
        
	  <div id="chatroomarea_2">
      <div id="chatroomarea">
      <div id="chatroomarea_1">
<!--         這釋放聊天對話的地方 -->
  <div id="messagesArea" class="panel message-area"  style=overflow:auto;height:500px;background-color:#ffffff;opacity:0.7; ></div> 
        
        
        
        <div class="panel input-area">
<!--        這是使用者名子 -->
           <h5><font color="white" size="5"> ${memVO.member_name}</font></h5>
            
            
            
<!--             這是對話輸入視窗 -->
        <form name = 'form' action = '#' method='post'>
            <textarea name="content" id="content" rows="10" cols="80"></textarea>
 		</form>
            
            
            
<!--             這是送出 連線 離線 的按鈕 各自呼叫各自方法 -->
            <input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();"/>
		    <input type="button" id="connect"     class="button" value="連線" onclick="connect();"/>
		    <input type="button" id="disconnect"  class="button" value="離線" onclick="disconnect();"/>

	    </div>
	    
	    </div>
	    </div>
	    </div>
	    
	    
	</div>




<div class="col-1">
</div>

<!-- 使用電影資訊的資料 -->
<div class="col-3">
<br><br><br>

<table   style=background-color:#f0f0f0; id="movietable">
	
		<tr><th class="table table-sm table-dark" >電影名稱</th></tr>
		<tr><th><font size="5">${movieinfoVO.movie_name}</font></th></tr>
		<tr><th class="table table-sm table-dark">電影封面</th></tr>
		<c:set var="movie_pic"  value="${movieinfoVO.movie_pic}"></c:set>
			<%
				byte b[]= (byte[])pageContext.getAttribute("movie_pic");
				String encode = null;
				if(b != null){
					encode = Base64.encode(b);
			%>
		<tr align="center"><th ><img id="pic" width='250' height='400' src="data:image/jpg;base64,<%=encode%>"></th>
			<%}else{%><th></th><%}%></tr>
		
		<tr><th class="table table-sm table-dark">電影分級</th></tr>
		<c:set var="movie_level" value="${movieinfoVO.movie_level}"></c:set>
			<%
				byte c[]= (byte[])pageContext.getAttribute("movie_level");
				String encode1 = null;
				if(c != null){
					encode1 = Base64.encode(c);
			%>
		<tr align="center"	><th><img id="level" src="data:image/jpg;base64,<%=encode1%>"></th>
			<%}else{%><th></th><%}%></tr>
		
		<tr><th class="table table-sm table-dark">電影導演</th></tr>
		<tr><th>${movieinfoVO.movie_director}</th></tr>
		<tr><th class="table table-sm table-dark">電影演員</th></tr>
		<tr><th>${movieinfoVO.movie_cast}</th></tr>
		<tr><th class="table table-sm table-dark">電影片長</th></tr>
		<tr><th>${movieinfoVO.movie_length}</th></tr>
		<tr><th class="table table-sm table-dark">電影簡介</th></tr>
		<tr><th>${movieinfoVO.movie_intro}</th></tr>
	
	

</table>


</div>

</div>



<!-- 這是Footer -->
<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Footer.jsp" />


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->


</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<!-- 呼叫CKEDITOR套件 -->
<script src="<%=request.getContextPath()%>/ckeditor4/ckeditor.js"></script>
<%-- <script src="<%=request.getContextPath()%>/ckeditor4/ckeditor.js"></script> --%>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>


<!-- CKEDITOR套件到指定欄位 -->
<script>
			CKEDITOR.replace( 'content', {});
</script>







<script>
         	//	CKEDITOR.replace('poscontent');
</script>





<script>


// 這是預覽上傳圖片


$("#progressbarTWInput").ready(function(){

	  readURL(this);

	});
	

$("#progressbarTWInput").change(function(){

  readURL(this);

});



function readURL(input){
	
  if(input.files && input.files[0]){

    var reader = new FileReader();

    reader.onload = function (e) {
    	
       $("#preview_progressbarTW_img").attr('src', e.target.result);
       $("#preview_progressbarTW_img").removeAttr("style");
    }

    reader.readAsDataURL(input.files[0]);

  }

}

</script>


<!-- webSocket 前端程式碼 -->
<script>
	
	

//     連線地址 用電影編號來方房
    var MyPoint = "/MyEchoServer/${param.movie_no}";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
	
// 	webSocket 連線
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			updateStatus("JOIN聊天室");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	
// 			把輸入的字串 回傳後 推播出來 放在<div>裡
			messagesArea.innerHTML = messagesArea.innerHTML + message;
	
// 	messagesArea.innerHTML = messagesArea.innerHTML + message + '<button>appendit</button>';

	        
	        messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	
	
	
	function sendMessage() {
// 取到會員名子
		var userName = "${memVO.member_name}"
	    if (userName === ""){
	        alert ("使用者名稱請勿空白!");
	        inputUserName.focus();	
			return;
	    }
	    

// 取到CKEDITOR裡輸入的資料 
	    var message = CKEDITOR.instances.content.getData()
	    
// 	    alert(message);
	    
// 	    清空CKEDITOR裡的資料
	    CKEDITOR.instances.content.setData('');
	    
// 	    判斷輸入資料不為空白後 送出
	    if (message === ""){
	        alert ("請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"userName" : userName, "message" : message};
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


</html>

