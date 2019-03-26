<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="com.mem.model.*" %>
<!DOCTYPE html>
<html>
	<head>
		<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
		<style>
			header{
			position:fixed;
			z-index:999;
			}
		</style>
	</head>
	<body>
	<header class="container-fluid">
			<div class="container-fluid">
				<div class="row justify-content-center">
				
					<div class="col-1">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">訂票</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/ticketorder_/choiseSessions.jsp">線上訂票</a> 
							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/ticketorder_/prompt.jsp">訂票須知</a> 
							<a class="dropdown-item" href="#" id="openfdDescriptioninheader" data-toggle="modal" data-target="#fdDescription-inheader">優惠訊息</a>
						</div>
					</div>
	
					<!-- 以上是訂票功能 -->
	
				<div class="col-1">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">電影簡介</button>						
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/movieinfo/upComingListAll.jsp">即將上映</a> 
							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/movieinfo/listAllMovieInfo.jsp">現正熱映</a> 
<%-- 							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/movieinfo/hotMovie.jsp">熱門電影推薦</a> --%>
							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/movieinfo/rankMovieList.jsp">年度電影推薦</a> 
						</div>
				</div>
						<!-- 以上是電影簡介功能-->
	
				<div class="col-1">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">影視新聞</button>						
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/newsinfo/listAllNewsInfo.jsp">影視新聞</a> 
						</div>
				</div>
						<!-- 以上是影視新聞功能-->
						
				
				<!-- 子傑開始	 -->
			
				<div class="col-1">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMemIndexButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false" >Home</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMemIndexButton">
							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp">回影城首頁</a>  
						</div>
					</div>
	
	
						
				
				<c:if test="${empty memVO}">
				
					<div class="col-1">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMemButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false" >會員註冊</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMemButton">
							
							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/member_regestinfomation.jsp">會員註冊</a> 
							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/member_regestinfomation2.jsp">會員註冊須知</a> 
						</div>
					</div>
				
				
				<button id="openloginDescription" type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginDescription">
						 	登入
				</button>
				
				</c:if>
				
					<!-- 以上為登入 -->
					
					<c:if test="${not empty memVO}">
					
					
					
					<div class="col-1">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMemButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false" >會員個人資料</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMemButton">
							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/select_page.jsp">會員首頁</a> 
							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/listOneMem.jsp">會員資料查看</a> 
							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/mem/update_mem_input.jsp">會員資料修改</a> 
						</div>
					</div>
					
					
					<div class="col-1">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMemIndexButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false" >Home</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMemIndexButton">
							<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/Home/Home.jsp">回影城首頁</a>  
						</div>
					</div>
					
					
						<li><a>Hello:
						<font color=#ea7500>${memVO.member_name}</font>您好
						</a></li>
						
						
						<div class="col-1">
						
						<FORM METHOD="POST" ACTION = "<%=request.getContextPath()%>/Front_end/mem/logoutHandler.do">
							<td><input type = "submit" value="登出">
							<input type = "hidden" name="logout" value="logout"> 
							</td>
						</FORM>
						
					</div>
					
					</c:if>
					
					
					
					<!-- 以上為登出 -->
				
				
				</div>
			</div>
		</header>
		<div style="height:80px;">
		</div>
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
		<!-- Modal -->
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

<!-- 以上登入畫面 -->
<script>
	function openloginDescription(e){
		$('#loginDescription_modal-body').load('<%=request.getContextPath()%>/Front_end/Login2.jsp?myself=<%=request.getRequestURI()%>');
	}
	
	function openfdDescriptioninheader(e){
		$('#fdDescription_modal-body-inheader').load('<%=request.getContextPath()%>/Front_end/farediscount/fdDescription.jsp');
	}
			
	$(document).ready(
			function(){
				$('#openfdDescriptioninheader').click(openfdDescriptioninheader);
				$('#openloginDescription').click(openloginDescription);
				});
</script>
	
	</body>
</html>