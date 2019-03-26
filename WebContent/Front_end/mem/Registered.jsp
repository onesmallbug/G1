
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mem.model.* , java.util.*"%>

<%
	MemVO memVO = (MemVO) request.getAttribute("_memVO");
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
<title>Join影城註冊頁面</title>

<style>
	table{table-layout: fixed;}
<th style="width:63px" ></th>
td{white-space: nowrap; overflow:hidden;word-break:break-all;} /*防止換行*/ 



 input[type="submit"]  {padding:5px 15px; background:#ccc; border:0 none;
		cursor:pointer;
		-webkit-border-radius: 5px;
		border-radius: 5px; }


	input[type="reset"]  {padding:5px 15px; background:#ccc; border:0 none;
		cursor:pointer;
		-webkit-border-radius: 5px;
		border-radius: 5px; }
		
	input[type="file"]  {padding:5px 15px; background:#ccc; border:0 none;
		cursor:pointer;
		-webkit-border-radius: 5px;
		border-radius: 5px; }
</style>



</head>
<body>
	<jsp:include page="/FrontHeaderFooter/Header.jsp" />
	<h1></h1>

	<!-- 工作區開始 -->

	<div class="container">
		<div class="row justify-content">
			<div class="col-1"></div>
			<div class="col-12">

				<div>
					<h1>Join揪影註冊頁面</h1>
				</div>

				<%--錯誤表列 --%>

				<c:if test="${not empty errorMsgs }">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>


				<form action="mem.do" method="post" name="form1"
					enctype="multipart/form-data">
				<table table-layout:fixed>
					

					<tr>
						<th >*帳號:</th> 
						<td><input type="text" name="member_account" id="member_account"
							value="<%= (memVO==null)?"":memVO.getMember_account()%>"
							autofocus>
						</td>
					</tr>

					<tr>
						<th>*密碼:</th>
						<td> <input type ="password" name="member_password"
							id="member_password" value="<%= (memVO==null)?"":""%>" required></td>
					</tr>
					

					<tr>
						<th>*再次確認密碼:</th>
						<td> <input type="password" name="member_password1"
							id="member_password1" value="<%= (memVO==null)?"":""%>" required></td>
					</tr>

					<tr>
						<th>*姓名:</th> 
						<td><input type="text" name="member_name" id="member_name"
							value="<%= (memVO==null)?"":memVO.getMember_name()%>"></td>
					</tr>

					<tr>
						<th>暱稱: </th>
						<td><input type="text" name="member_nick" id="member_nick"
							value="<%= (memVO==null)?"":memVO.getMember_nick()%>"></td>
					</tr>

					<tr>
						<th>*性別:</th>
						<td><input type="radio" name="member_sex" value="1"
							${(memVO.member_sex==1)? "checked": ""}>男性 <input
							type="radio" name="member_sex" value="0"
							${(memVO.member_sex==0)? "checked": ""}>女性</td>
					</tr>

					<%
	java.sql.Date member_birthday = null;
	try{
		member_birthday = memVO.getMember_birthday();
	}catch (Exception e){
		member_birthday = new java.sql.Date(System.currentTimeMillis());
	}
%>


					<tr>
						<th>*生日:</th> 
						<td><input type="date" name="member_birthday" id="f_date"
							value="<%= (memVO==null)? member_birthday:memVO.getMember_birthday()%>"></td>
					</tr>


					<% String[] arrayCity  = new String[] {"台北市","基隆市","新北市","桃園市","新竹市","新竹縣","苗栗縣","台中市","彰化縣","南投縣","雲林縣","嘉義縣","台南市","高雄市","屏東縣","宜蘭縣","花蓮縣","台東縣","澎湖縣","金門縣","連江縣"};
 	List<String> listCity = Arrays.asList(arrayCity);  
 	pageContext.setAttribute("listCity", listCity);
 %>


					<tr>
					<th>*地址:</th>
					<td><div class="container">
						<div class="row">
							<div class="col">
								<div class="dropdown">

									<select id="twCityName">
										<option>--請選擇縣市--</option>
										<c:forEach var="city" items="${listCity}">
											<option value="${city}">${city}</option>
										</c:forEach>
									</select> <select id="CityAreaName">
										<option>--請選擇區域--</option>
									</select> <select id="AreaRoadName">
										<option>--請選擇路名--</option>
									</select> <input type="text" placeholder="請輸入門牌號碼" id="num"> <input
										type="button" value="確認" id="btnLoc">
								</div>
							</div>
						</div>
						<div class="row">
							<input id="addressTotal" type="text" name="member_address"
								size="50">
						</div>

					</div>
					</td>
					</tr>

					<tr>
						<th>*電話:</th> 
						<td><input type="tel" name="member_telephone"
							id="member_telephone"
							value="<%= (memVO==null)?"":memVO.getMember_telephone()%>"></td>
					</tr>

					<tr>
						<th>*信箱: </th>
						<td><input type="email" name="member_email" id="member_email"
							value="<%= (memVO==null)?"":memVO.getMember_email()%>"></td>
					</tr>

					<tr>
						<th>會員圖像:</th> 
						<td>
						<img id="preview_progressbarTW_img" src="#"  width="100px"   height="100px"  style = "display:none" />
						<input type="file" id="progressbarTWInput" name="member_picture" size="25" accept="image/gif, image/jpeg, image/png" value="${memVO.member_picture}"/></td>
					</tr>

					<tr>
						<th>信用卡號碼: </th>
						<td><input type="text" name="member_credit_number"
							id="member_credit_number"
							value="<%= (memVO==null)?"":memVO.getMember_credit_number()%>"></td>
					</tr>

					<tr>
						<th>背面後三碼: </th>
						<td><input type="text" name="member_back_verification"
							id="member_back_verification"
							value="<%= (memVO==null)?"":memVO.getMember_back_verification()%>"></td>
					</tr>

					<input type="hidden" name="member_status" id="member_status"
						value="0">

					</table>
						<input type="hidden" name="action" value="insert">
						 <input type="submit" id="send" value="送出"> &nbsp;&nbsp; 
						 <input type="reset" value="取消"> 
						 <a href="<%=request.getContextPath()%>/Front_end/Login.jsp">回到首頁</a>
					



					<img src="img/popcorn.jpg" height="20" width="20"
						onclick="idwrite(this)">

					<script>
	function idwrite(name){
		
		form1.member_account.value="peter520";
		form1.member_password.value="1314520";
		form1.member_password1.value="1314520";
		form1.member_name.value="大吳";
		form1.member_nick.value="吳神";
		form1.member_sex.value="1";
		form1.f_date.value="2018-03-29";
		form1.member_address.value="桃園市中壢區中大路1號";
		form1.member_telephone.value="0982102271";
		form1.member_email.value="zxxl3617@gmail.com";
		form1.member_credit_number.value="498231678597";
		form1.member_back_verification.value="798";
		
	}

</script>

				</form>
			</div>
		</div>
	</div>


	<!-- 工作區結束 -->


       <script>





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




	<script
		src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script> 

$(document).ready(function(){
	
	$("#twCityName").change(function(){
		$.ajax({
			 type: "POST",
			 url: "<%=request.getContextPath()%>/Json2Read",
			 data: {"action":"twCityName",
				 	"twCityName":$('#twCityName option:selected').val()},
			 dataType: "json",
			 success: function(result){
				 $("#CityAreaName").empty();
				
				 $("#CityAreaName").append("<option >--請選擇區域--</option>")
				 for(var i=0; i<result.length; i++){
				 	$("#CityAreaName").append('<option value="'+result[i]+'">'+result[i]+'</option>');
				 }
			 },
	         error: function(){
	        	 alert("AJAX-grade發生錯誤囉!")
	        	 }
	    });
	});
	
	$("#CityAreaName").change(function(){
		$.ajax({
			 type: "POST",
			 url: "<%=request.getContextPath()%>/Json2Read",
				data : {
						"action" : "CityAreaName",
						"twCityName" : $(
						'#twCityName option:selected')
						.val(),
						"CityAreaName" : $(
							'#CityAreaName option:selected')
								.val()
								},
								dataType : "json",
								success : function(
								result) {
								$(
								"#AreaRoadName")
								.empty();
								$(
								"#AreaRoadName")
								.append(
								"<option >--請選擇區域--</option>")
								for (var i = 0; i < result.length; i++) {
								$(
									"#AreaRoadName")
									.append(
								'<option value="'+result[i]+'">'
																							+ result[i]
																							+ '</option>');
																}
															},
															error : function() {
																alert("AJAX-grade發生錯誤囉!")
															}
														});
											});

							$("#btnLoc")
									.click(
											function() {

												var twCityName = ($(
														'#twCityName').get(0).selectedIndex) > 0 ? $(
														'#twCityName option:selected')
														.val()
														: '';

												var CityAreaName = ($(
														'#CityAreaName').get(0).selectedIndex) > 0 ? $(
														'#CityAreaName option:selected')
														.val()
														: '';

												var AreaRoadName = ($(
														'#AreaRoadName').get(0).selectedIndex) > 0 ? $(
														'#AreaRoadName option:selected')
														.val()
														: '';

												var num = $('#num').val()
														.trim().length != 0 ? $(
														'#num').val()
														+ "號"
														: '';

												var locTotal = twCityName
														+ CityAreaName
														+ AreaRoadName + num;
												$("#addressTotal").attr(
														"value", locTotal);

											});
						})
	</script>

	<jsp:include page="/FrontHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>