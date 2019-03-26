 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<% response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires",0);
	%>

<!doctype html>
<html lang="en">
	<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
	<title></title>
	<style>
	    input[type="submit"] {padding:5px 15px; background:white; border:1 none; color:#969696;
	cursor:pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px; }
	
	.nav-link active{
	
	color:#969696;border:2px #ccc solid;padding:10px;text-align:center;
	}
	
	.col-2 .nav-link{
	
	color:#969696;border:2px #ccc solid;padding:10px;text-align:center;
	}
	
	.row {
	  display: flex;
	  flex-wrap: wrap;
	  margin-right: -100px;
	  margin-left: -100px;
	}
	
	</style>
	
	
	
	</head>
<body>
	<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->


<div class="container-fluid">
	<div class="row" >
	  <div class="col-2" style="margin-top:70px;">
	    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical" >
	      <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">會員首頁</a>
	      <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="false">會員資料查看</a>
	      <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab" aria-controls="v-pills-messages" aria-selected="false">個人資料修改</a>
	      <a class="nav-link" id="v-pills-order-tab" data-toggle="pill" href="#v-pills-order" role="tab" aria-controls="v-pills-settings" aria-selected="false">購票紀錄查詢</a>
	      <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill"  href="#v-pills-settings" role="tab" aria-controls="v-pills-settings" aria-selected="false" >Join儲值錢包查詢</a>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Front_end/dep/dep.do">
			<font color = '#007bff'><input type = "submit" value="Join儲值錢包新增" style=" width:290px; height:42px;"></font>
			<input type = "hidden" name="member_no" value="${memVO.member_no}">
			<input type = "hidden" name="action" value="insert_Token"></FORM> 
	    </div>
	  </div>
	  <div class="col-1">
		</div>
	  <div class="col-9">
	    <div class="tab-content" id="v-pills-tabContent">
	      <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab"><div style="margin-left:90px;"><jsp:include page="/Front_end/mem/memNotice.jsp" /></div></div>
	      <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab"><jsp:include page="/Front_end/mem/listOneMem.jsp" /></div>
	      <div class="tab-pane fade" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab"><jsp:include page="/Front_end/mem/update_mem_input2.jsp" /></div>
	      <div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab"><jsp:include page="/Front_end/dep/listOneDep_Mem.jsp" /></div>
	      <div class="tab-pane fade" id="v-pills-order" role="tabpanel" aria-labelledby="v-pills-order-tab"><jsp:include page="/Front_end/ticketorder_/listOneTic_Mem2.jsp" /></div>
	    </div>
	  </div>
	</div>
</div>


<!-- 	工作區結束 -->
	
	<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Footer.jsp" />

</body>
</html>