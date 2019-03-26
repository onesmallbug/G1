<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.Timestamp"%>
<%@ page import="com.cinema.model.*"%>
<%@ page import="com.movieinfo.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
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
<title>新增場次</title>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  div{
  margin-top:10px;
  }
</style>

</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<h1>新增場次</h1>

	<!-- 工作區開始 -->
	<jsp:useBean id="sessionsSvc" scope="page" class="com.sessions.model.SessionsService"/>
	<jsp:useBean id="movieInfoSvc" scope="page" class="com.movieinfo.model.MovieInfoService"/>
	<jsp:useBean id="movieInfoVOList" scope="page" class="java.util.ArrayList"/>
	<jsp:useBean id="cinemaSvc" class="com.cinema.model.CinemaService" scope="page"/>
	<%
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	Date theDate = df.parse(request.getParameter("theDate"));
	
	for(MovieInfoVO movieInfoVO : movieInfoSvc.getAll()){
		
		if(movieInfoVO.getMovie_in().before(theDate) && movieInfoVO.getMovie_out().after(theDate)){
			movieInfoVOList.add(movieInfoVO);
		}
	}
	%>


	<div class="container">
		<div class="row justify-content">
			<div class="col-4">
				<div style="height:50px">
					</div>
			
						<c:if test="${!empty errorMessage}">
						
							<p>錯誤訊息如下</p>
						
							<ul>
								<c:forEach var="message" items="${errorMessage}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						
						</c:if>
					
					<!-- 以上錯誤訊息處理 -->
						<div>
							<input name="hiredate" id="f_date1" type="text" value="${param.theDate}" disabled/>
						</div>
					
					<!-- 以上日期輸入 -->
						
						<div>
							<select size="1" id="upOrDown">
								<option value="0">上午
								<option value="12">下午
							</select>
						
						<select size="1" id="time">
						
						<%
						for(int i = 0;i<12;i++){						
							%>
								<option value="<%=i%>"><%=i%>點
							<%
						}
						%>
						
						</select>
						
						<select size="1" id="half">
							<option value="00">整點
							<option value="30">半點
						</select>
						</div>
						
					<!-- 以上時間輸入 -->
						<div>
						<select size="1" id="movie_no" style="overflow:hidden; text-overflow:ellipsis;white-space:nowrap;width:178px;">
							<c:forEach var="movieInfoVO" items="${movieInfoVOList}">
								<option value="${movieInfoVO.movie_no}" <c:if test="${param.movie_no eq movieInfoVO.movie_no}">selected</c:if>>${movieInfoVO.movie_name}
							</c:forEach>
						</select>
						</div>
					
					<!-- 以上選擇電影 -->
						<div>
						<select size="1" id="cinema_no" style="overflow:hidden; text-overflow:ellipsis;white-space:nowrap;width:178px;">
							<c:forEach var="cinemaVO" items="${cinemaSvc.all}">
								<c:if test="${cinemaVO.cinema_status eq '可用'}">
									<option value="${cinemaVO.cinema_no}">${cinemaVO.cinema_name}
								</c:if> 
							</c:forEach>
						</select>
						</div>
					
					<!-- 以上選擇影廳 -->
						<div>
						場次間格<input type="number" id="number" value="3" style="width:114px" min="3" max="6">
						</div>
					<!-- 場次間格 -->
						<div id="addSessions" class="btn btn-primary" >新增場次</div>
					
					<!-- 以上新增場次按鈕 -->
					
					
						<form id="form" action="<%=request.getContextPath()%>/sessions/SessionServlet" method="post">
		
							<input type="hidden" name="action" value="insertSessions"> 
							<input class="btn btn-primary" type="submit" value="送出">
							
						</form>
				
				<!-- 以上送出資料 -->
				
					</div>
			
			<div class="col-8">
			
				<table>
					<tbody id="table">
						<tr id="sessions0">
						
							<th>電影名稱</th>
							<th>影廳名稱</th>
							<th>開始日期</th>
							<th id="sessions_start_Timestamp0">開始時間</th>	
							<th></th>
								
						</tr>
					
					</tbody>
				</table>
			
			</div>
		</div>
	</div>

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
    
    function insertAfter(newNode, referenceNode) {
        referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
    }
    
    var count = 0;
    
    function delfunction(e){
    	
    	var _count = e.target.id.substring(7);
    	
    	$id("table").removeChild($id("sessions"+_count));
    	$id("form").removeChild($id("input"+_count));
    	
    	count = $id("table").lastChild.previousSibling.id.substring(8);
    	
    	if(count == 0 ){
    		
        	$id("upOrDown").disabled="";
        	$id("time").disabled="";
        	$id("half").disabled="";
        	$id("cinema_no").disabled="";
    		
    	}
    	
    }

    function addSessionsFunction(){
    	
    	var before = $id("sessions" + count);
    	var beforesessions_start_Timestamp = $id("sessions_start_Timestamp"+count);
    	//抓前一個元素
    	
    	var time;
    	var half;
    	var date = $id("f_date1").value;
    	
    	if(beforesessions_start_Timestamp.innerText == "開始時間"){
    		//如果前一個兄弟是第一個，時間就從所選的時間開始
    		
    	var ud = parseInt($id("upOrDown").value);
    	time = parseInt($id("time").value);
    	time = time + ud;
    	half = $id("half").value;    	
    	
    	
    	} else {
    		//如果前一個兄弟不是第一個，時間就從前一個加上間格
    		
    		var bti = beforesessions_start_Timestamp.innerText;
    		time = bti.substring(0 , bti.indexOf(":"));
    		half = bti.substring(bti.indexOf(":")+2);
    		time = parseInt(time)+parseInt($id("number").value);
    		
    		if(time >= 24){    			
    			
    			window.alert("請送出新增後接續新增場次");
    			return;
    			//為避免大小月與閏年問題，一次只能新增一日的場次
    			
    		}
    		
    	}    	
    	
    	$id("upOrDown").disabled="disabled";
    	$id("time").disabled="disabled";
    	$id("half").disabled="disabled";
    	$id("cinema_no").disabled="disabled";
    	//只要新增了資料，部分按鈕就必須失效
    	
    	count++;
    	//準備製作下一組元素
    	
    	var trE = document.createElement("tr");
    	trE.id = "sessions" + count;
    	trE.style.border = "2px ridge #ee3";
    	trE.style.borderRadius = "10px";
    	insertAfter(trE, before);
    	//建立下一列
    	
    	var movie_nameE = document.createElement("td");
    	movie_nameE.id = "movie_name" +count;    	
    	
    	var cinema_nameE = document.createElement("td");
    	cinema_nameE.id = "cinema_name" +count;
    	
    	var sessions_start_dateE = document.createElement("td");
    	sessions_start_dateE.id = "sessions_start_date" +count;
    	
    	var sessions_start_TimestampE = document.createElement("td");
    	sessions_start_TimestampE.id = "sessions_start_Timestamp" +count;
    	//以上四組程式碼建立四欄，以下建立刪除欄位
    	var delE = document.createElement("td");    	
    	
    	
    	trE.appendChild(delE);    	
    	//將建立的欄位引入新建的列，以下將刪除欄位引入列
    	trE.insertBefore(sessions_start_TimestampE, delE);
    	trE.insertBefore(sessions_start_dateE, sessions_start_TimestampE);
    	trE.insertBefore(cinema_nameE, sessions_start_dateE);
    	trE.insertBefore(movie_nameE, cinema_nameE);    	
    	
    	    	
    	var addmovie_name = document.createTextNode($id("movie_no").options[$id("movie_no").selectedIndex].text);
    	var addcinema_name = document.createTextNode($id("cinema_no").options[$id("cinema_no").selectedIndex].text);
    	var addsessions_start_date = document.createTextNode(date);
    	var addsessions_start_Timestamp = document.createTextNode(time+" : "+half);
    	//建立文字節點，以下建立刪除按鈕
    	var delbtnE = document.createElement("div");
    	delbtnE.id = "delbtnE" +count;
    	delbtnE.className = "btn";
    	delbtnE.style.backgroundColor  = "#e66";
    	
    	
    	movie_nameE.appendChild(addmovie_name);
    	cinema_nameE.appendChild(addcinema_name);
    	sessions_start_dateE.appendChild(addsessions_start_date);
    	sessions_start_TimestampE.appendChild(addsessions_start_Timestamp);
    	//將文字節點引入所對應的欄位，以下引入刪除按鈕
    	delE.appendChild(delbtnE);
    	
    	
    	$add(delbtnE,"click",delfunction);
    	//以上新增刪除的事件處理器
    	
    	var form = $id("form");
    	//準備生成要送進資料庫的資料
    	var input = document.createElement("input");
    	input.type = "hidden";
    	input.value = $id("movie_no").value + ";" + $id("cinema_no").value + ";" + date + " " + time+":"+half+":"+"00";
    	input.name = "sessions";
    	input.id = "input"+count;
    	
    	form.appendChild(input);  
    	//將input放進表單
    	
    	
    }
    
    
   function init() {
		
      	$add($id("addSessions"),"click",addSessionsFunction);      	
    	
   }

    window.onload = init;
	</script>
	
	<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date hiredate =new java.sql.Date(System.currentTimeMillis()+24*60*60*1000);
%>


	<!-- 工作區結束 -->

	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script	src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>
<%request.getSession().setAttribute("insert_sessions_key", new Object());%>
