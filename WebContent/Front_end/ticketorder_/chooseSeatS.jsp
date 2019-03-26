
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
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS start-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
<title>訂票</title>
<style>
p {
	margin: 0px;
	font-family: inherit;
}
.col-2>p {
margin-bottom:0px;
}
.table tr>th, .table tr>td{
padding:0px;
}
</style>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->
	<%
	String sessions_no = request.getParameter("sessions_no");
	request.setAttribute("sessions_no", sessions_no); 
	%>
	<jsp:useBean id="tfSer"  class="com.ticketinformation.model.TicketinformationService" scope="page"/>
	<jsp:useBean id="sesSer" class="com.sessions.model.SessionsService" scope="page"/>
	<jsp:useBean id="movSer" class="com.movieinfo.model.MovieInfoService" scope="page"/>
	<jsp:useBean id="cinSer" class="com.cinema.model.CinemaService" scope="page"/>
	<c:set var="sessionsVO" value="${sesSer.getOneSes(sessions_no)}" scope="page"></c:set>
	<c:set var="all" value="${tfSer.all}" scope="page"></c:set>
	<c:set var="movie_ticket" value="${movSer.getOneMovieInfo(sessionsVO.movie_no).movie_ticket}" scope="page"></c:set>
	<c:set var="cinema_correct" value="${cinSer.getOneCin(sessionsVO.cinema_no).cinema_correct}" scope="page"></c:set>
	<jsp:useBean id="sessionsVO" class="com.sessions.model.SessionsVO" scope="page"/>
	

	<div class="container">
		<div class="row justify-content">

			<div class="col-4">
				<table class="table table-sm">
				  <thead>
				    <tr>
				      <th scope="col">項目</th>
				      <th scope="col">內容</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row">電影名稱：</th>
				      <td>${movSer.getOneMovieInfo(sessionsVO.movie_no).movie_name}</td>
				    </tr>
				    <tr>
				      <th scope="row">日期：</th>
				      <td>${sessionsVO.sessions_start.toString().substring(0,10) }</td>
				    </tr>
				    <tr>
				      <th scope="row">時間：</th>
				      <td>${sessionsVO.sessions_start.toString().substring(10,16) }</td>
				    </tr>
				    <tr>
				      <th scope="row">餘額：</th>
				      <td id="member_point">${memVO.member_point }</td>
				    </tr>
				    <tr>
				      <th scope="row">總額：</th>
				      <td id="order_amount">0</td>
				    </tr>
				    <tr>
				      <th scope="row">折扣名稱：</th>
				      <td id="fd_name"></td>
				    </tr>
				    <tr>
				      <th scope="row">折扣內容：</th>
				      <td id="fd_offer"></td>
				    </tr>
				  </tbody>
				</table>
<!-- 				以上訂單基本資訊 -->
				
				<div class="row justify-content">
					<div class="col-4">
					
						<form id="form" method="post" action="<%= request.getContextPath()%>/ticketorder/TicketorderServlet_">
							<p id="ajax_ti_no" style="display:none;"></p>
							<input type="hidden" name="sessions_no" value="${sessions_no}">
							<input type="hidden" name="action" value="insert">
							<input id="submit" class="btn btn-primary" type="submit" value="結帳去">
						</form>
					
					</div>
<!-- 					以上結帳按鈕 -->
					
					<div class="col-4">
						<button id="openpriceDescription" type="button" class="btn btn-primary" data-toggle="modal" data-target="#priceDescription">
						 	 價格資訊
						</button>
					</div>
					
<!-- 					以上票價計算資訊 -->
					
					<div class="col-4">
						<button id="openfdDescription" type="button" class="btn btn-primary" data-toggle="modal" data-target="#fdDescription">
						 	 優惠資訊
						</button>
					</div>
					
<!-- 					以上優惠資訊 -->
				</div>
				
				<div class="row justify-content">
					<div class="col-2" id="ti1" style="padding-left: 0px;">
						<p>票種</p>
					</div>

					<div class="col-2" id="mt1" style="padding-left: 0px;">
						<p>位置</p>
					</div>
					<div class="col-2" id="ti2" style="padding-left: 0px;">
						<p>票種</p>
					</div>

					<div class="col-2" id="mt2" style="padding-left: 0px;">
						<p>位置</p>
					</div>
					<div class="col-2" id="ti3" style="padding-left: 0px;">
						<p>票種</p>
					</div>

					<div class="col-2" id="mt3" style="padding-left: 0px;">
						<p>位置</p>
					</div>
				</div>
				
<!-- 				以上訂單明細資訊 -->
				
			</div>

			<div class="col-8">
			<nav class="row justify-content">
				
					<div class="col-9">
						<c:forEach var="tf" items="${all}">
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
						String cinema_type = sessionsVO.getSessions_status();

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
								width:26px;height:26px;
								background-color:<%=cs.getColler()%>; border-color:#000; margin-left:5px; margin-top:2px">
								
								<%if(((i * 20 + j)%25) == 0){%>
									<p style="width:19px;color:white;margin-bottom: 0px;margin-left: -9px;"><%=count1++ %></p><%}%>
								<%if((i * 20 + j)>375){%>
									<p style="width:19px;color:white;margin-bottom: 0px;margin-left: -9px;"><%=count2++ %></p><%}%>
							</div>
						<%
							}
						%>
					<br>
					<%
						}
					%>
				</div>
				</nav>
							<!-- ---------------------以上現有座位---------------------- -->
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
				</nav>
								<!-- ---------------------以上影廳type---------------------- -->
			</div>
		</div>
	</div>
	<!-- Button trigger modal -->

<!-- Modal -->
<div class="modal fade" id="priceDescription" tabindex="-1" role="dialog" aria-labelledby="priceDescriptionTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
    	
      <div class="modal-header">
        <h5 class="modal-title" id="priceDescriptionTitle">票價說明</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="priceDescription_modal-body">
      
      </div>
      <div class="modal-footer">
        <button id="closeiframe" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- 以上票價計算資訊 -->
<!-- Modal -->
<div class="modal fade" id="fdDescription" tabindex="-1" role="dialog" aria-labelledby="fdDescriptionTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
    	
      <div class="modal-header">
        <h5 class="modal-title" id="fdDescriptionTitle">優惠說明</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="fdDescription_modal-body">
      </div>
      <div class="modal-footer">
        <button id="closeiframe" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- 以上優惠資訊 -->

	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
		
	<script>
	//這一個JS區段是用來控制websocket
	//websocket功能--------------開始------------------
	
	var MyPoint = "/ForTicketorderServlet_ws/${memVO.member_no}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + host + webCtx + MyPoint;

	var webSocket;
	
	var isMe = false;
		
	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
// 			alert("i'm start.");
		};

		webSocket.onmessage = function(event) {
			
			if(isMe){return;}
			//如果是自己就不管
				
			var jsonArray = JSON.parse(event.data);
			var action = jsonArray[0].action;
				
			if(action == "amember"){
				//如果是給這個會員的
				$('#member_point').text(jsonArray[1].member_point);
				//設定新的剩餘點數
			}
			
			if(action == "everyone"){
				//如果是給所有會員的
				if(jsonArray[1].sessions_no != "<%=sessions_no%>"){return;}
				//如果不是這個場次就不管
				
				swal(jsonArray[1].message);
				
				 $.each(jsonArray[2], function(i, mt_no){
					 //逐筆更新
					 
					var sit =  mt_no.mt_no;
					var item = $('#sit'+sit);
					
					item.attr("data-sitType", 6);
					item.attr('class','btn disabled');
					item.css("background-color", "#fc6");
					item.unbind();
					$remove($id("sit"+sit),"click",setStyle);					
					//更新座位狀態
					
					if($id('mt_no'+sit) != null){
				       	$id('mt_no'+sit).remove();
				       	$id('ti_no'+sit).remove();
				       	$id('mt'+sit).remove();
				       	$id('ti'+sit).remove();
			       	}
			       	//刪除顯示的座位與送出的定位請求
			       	
		        	clickAjax(creatTi_no, event);
			       	//重新計算總額與優惠種類
					 
				 });
			}
			
		};

		webSocket.onclose = function(event) {
		};
	}
	
	//websocket功能--------------結束------------------
	</script>
		
	<script>
	//這一個JS區段是用來控制AJAX
	//AJAX功能--------------開始------------------
	
	function clickAjax(ti_no, e){
			 $.ajax({
				 type: "GET",
				 url: "<%= request.getContextPath()%>/ticketorder/TicketorderServlet_?action=calculat",
				 data: creatQueryString(ti_no),
				 dataType: "json",
				 success: function (data){
					$('#order_amount').text(data.order_amount);
					//刷新總金額點數
						$('#fd_name').text(data.fd_name);
					//刷新優惠種類
						$('#fd_offer').text(data.fd_offer);
					//刷新優惠內容
					testMember_pointOrder_amount();
					//依據剩餘點數與訂單總額檢查是否為可送出的訂單
			     },
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
	         })
		 }
	
	function creatQueryString(ti_no){
		var queryString= {"sessions_no":"<%=sessions_no%>", "ti_no":ti_no};
		return queryString;
	}
	
	
	
	//AJAX功能--------------開始------------------
	</script>
		
	<script>
	
	//這一個JS區段是用來控制購票基本功能
	function submit(){
		isMe = true;
		try{
			isMe_footer = true;
		}catch(e){
			console.log("isMe_footer is not defined");
		}
	}
    var count = 0;
	
	function creatTi_no(){
		
		var ti_no = '';
		var inputs = document.getElementsByName('ti_no');
		count = 0;
		for(var i = 0; i < inputs.length; i++){
    		ti_no += (inputs[i].value + ';');
    		if(inputs[i].value == 'a' || inputs[i].value == 'b' || inputs[i].value == 'c' || inputs[i].value == 'd'){
    			count++;
    		}
    	}
		return ti_no;
	}
	
    function $id(id) {
        return document.getElementById(id);
    }

    function $add(a, b, c) {
        return a.addEventListener(b, c, false);
    } //物件,事件,動作

    function $remove(a, b, c) {
        return a.removeEventListener(b, c, false);
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
    
    function testMember_pointOrder_amount(){
    	if( ( parseInt($('#member_point').text()) < parseInt($('#order_amount').text()) ) || parseInt($('#order_amount').text()) == 0){
    		$('#submit').attr("disabled", true);
    	} else {
    		$('#submit').attr("disabled", false);
    	}
    }

    
    function setStyle(e){
    	
    	if(e.target.getAttribute("data-sitType") == 1){
    		creatTi_no();
	    	if(count >= 24){
	    		swal('一次最多僅能購買24張票');
	    		return;
	    	}
	    	e.target.setAttribute("data-sitType", $id("color").getAttribute("data-sitType"));
	    	$bgc(e.target,$id("color").style.backgroundColor);
	    	$('#form').append('<input type="hidden" id=mt_no'+e.target.id.substring(3)+' name="mt_no" value='+e.target.id.substring(3)+'>');
	    	$('#form').append('<input type="hidden" id=ti_no'+e.target.id.substring(3)+' name="ti_no" value='+$id("color").getAttribute("data-sitType")+'>');
	    	if($('#mt1').children().length < 9){
		    	$('#mt1').append('<p id="mt'+e.target.id.substring(3)+'">'+Math.ceil(parseInt(e.target.id.substring(3))/25)+'-'+(parseInt(e.target.id.substring(3))%25+1)+'</p>');
		    	$('#ti1').append('<p id="ti'+e.target.id.substring(3)+'">'+$id("color").getAttribute("data-sitStr")+'</p>');
	    	} else if($('#mt2').children().length < 9){
	    		$('#mt2').append('<p id="mt'+e.target.id.substring(3)+'">'+Math.ceil(parseInt(e.target.id.substring(3))/25)+'-'+(parseInt(e.target.id.substring(3))%25+1)+'</p>');
		    	$('#ti2').append('<p id="ti'+e.target.id.substring(3)+'">'+$id("color").getAttribute("data-sitStr")+'</p>');
	    	} else if($('#mt3').children().length < 9){
	    		$('#mt3').append('<p id="mt'+e.target.id.substring(3)+'">'+Math.ceil(parseInt(e.target.id.substring(3))/25)+'-'+(parseInt(e.target.id.substring(3))%25+1)+'</p>');
		    	$('#ti3').append('<p id="ti'+e.target.id.substring(3)+'">'+$id("color").getAttribute("data-sitStr")+'</p>');
	    	}
	    	
	    	clickAjax(creatTi_no, e);
    	}else{
    		e.target.setAttribute("data-sitType", 1);
        	$bgc(e.target,'#3f9');
        	$id('mt_no'+e.target.id.substring(3)).remove();
        	$id('ti_no'+e.target.id.substring(3)).remove();
        	$id('mt'+e.target.id.substring(3)).remove();
        	$id('ti'+e.target.id.substring(3)).remove();
        	
	    	clickAjax(creatTi_no, e);
	    	
    	}
    	
    }
    
    function openpriceDescription(e){
    	$('#priceDescription_modal-body').load('<%=request.getContextPath()%>/Front_end/ticketorder_/priceDescription.jsp?movie_no=${sessionsVO.movie_no}&cinema_no=${sessionsVO.cinema_no}');
    }
    
    function openfdDescription(e){
    	$('#fdDescription_modal-body').load('<%=request.getContextPath()%>/Front_end/ticketorder_/fdDescription.jsp?sessions_no=${param.sessions_no}');
    }
    
    function init() {
		var statusOfSitList_size = ${all.size()};
		var sit_size = <%= cinema_type.length()%>;
    	
		$('#submit').attr("disabled", true);
		
       	for(var i = 0; i < sit_size; i++){
       		
       		if($id("sit"+i).getAttribute("data-sitType") == 1){
       			
       			$add($id("sit"+i),"click",setStyle);
       		
       		}
       		
       	}
       	
       	for(var i = 1; i <= statusOfSitList_size; i++){
       		$add($id("type"+i),"click",choosStyle);
       	}
       	
       	$('#submit').click(submit);
       	//送出之後設定接下來收到的伺服器推播是自己發出的
       	
       	$('#openpriceDescription').click(openpriceDescription);
       	$('#openfdDescription').click(openfdDescription);
       	
       	connect();
       	//ws連線
    }

    window.onload = init;
  //基本功能--------------結束------------------
	</script>
	<!-- 工作區結束 -->

	<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>
<%request.getSession().setAttribute("ticket_tx_key", new Object());%>
