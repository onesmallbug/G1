<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movieinfo.model.*"%>
<%@ page import="com.moviegenre.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>

<%
	MovieInfoVO movieinfoVO = (MovieInfoVO) request.getAttribute("movieinfoVO");

	MovieGenreService moviegenreSvc = new MovieGenreService();
	List<MovieGenreVO> listgenre = moviegenreSvc.getAll();
	pageContext.setAttribute("listgenre",listgenre);
%>

<html>
<head>

<link	rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Back_end/movieinfo/Expansion/jquery.datetimepicker.css" />
<link rel="stylesheet" 	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/Back_end/movieinfo/Expansion/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/Back_end/movieinfo/Expansion/jquery.datetimepicker.full.js"></script>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>addMovieInfo</title>

<style>
table#table-1 {
	background-color: #00caca;
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

  img, #level{
  	width:50px;
  	hight:50px;
  }
  
  #pic{
  	width:270px;
  	hight:400px;
  }
  
  #popcorn{
  	 width:52;
  	 height:62;
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

#button{
	hight:20;
	width:20;
}

</style>

</head>
<body bgcolor='white'>
		<jsp:include page="/BackHeaderFooter/Header.jsp" />
		<h1></h1><br>
		
<!-- 工作區開始 -->

	<div class="container">
		<div class="row justify-content">
			<div class="col-1"></div>
			<div class="col-4">

	<table id="table-1">
		<tr>
			<td><h3>電影資料新增</h3></td>
			<td><h4>
					<a href="select_page.jsp"><img id="popcorn" src="<%=request.getContextPath()%>/Back_end/movieinfo/images/popcorn.jpg"
					   width="52" height="62" border="0">回首頁</a>
			</h4></td>
		</tr>
	</table>


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<!-- 寫入圖片步驟2 傳送方式用Post 並設定傳送格式enctype-->
	<br>
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/Back_end/movieinfo/movieinfo.do"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td nowrap="nowrap">電影種類:<font color=red size="2"><b>*</b></font></td>
<!-- 				利用varStatus="s" 將value變成數字(index從0開始,count從1開始) -->
				<td><select size="1" name="genre_no">		
						<c:forEach var="genrename" items="${listgenre}" varStatus="s"> 
	          				<option value="${s.count}">${genrename.genre_name}</option>
	          			</c:forEach> 
          			</select>
         		</td>
			</tr>		
			<tr>
				<td nowrap="nowrap">電影名稱:<font color=red size="2"><b>*</b></font></td>
				<td><input type="TEXT" id="movie_name" name="movie_name" size="45"
					value="<%=(movieinfoVO == null) ? "請輸入電影名稱" : movieinfoVO.getMovie_name()%>" /></td>
			</tr>
			<tr>
				<td nowrap="nowrap">電影分數:</td>
				<td><input type="TEXT" id="movie_score" name="movie_score" size="45"
					placeholder="ex:8.0" /></td>
			</tr>
			<tr>
				<td nowrap="nowrap">電影分級:<font color=red size="2"><b>*</b></font></td>
				<td><input type="file" id="movie_level" name="movie_level" onchange='readURL(this)'>
					<img id="level" class='pic' src='data:img/png;base64,${encodeText}'
					${(movieinfoVO.movie_level==null) ? 'style="display:none"' : ''}></td>
			</tr>
			<tr>
				<td nowrap="nowrap">電影導演:<font color=red size="2"><b>*</b></font></td>
				<td><input type="TEXT" id="movie_director" name="movie_director" size="45"
					value="<%=(movieinfoVO == null) ? "請輸入電影導演" : movieinfoVO.getMovie_director()%>" /></td>
			</tr>
			<tr>
				<td nowrap="nowrap">電影演員:<font color=red size="2"><b>*</b></font></td>
				<td><input type="TEXT" id="movie_cast" name="movie_cast" size="45"
					value="<%=(movieinfoVO == null) ? "請輸入電影演員" : movieinfoVO.getMovie_cast()%>" /></td>
			</tr>
			
			<tr>
				<td nowrap="nowrap">電影片長:<font color=red size="2"><b>*</b></font></td>
				<td><input type="TEXT" id="movie_length" name="movie_length" size="45"
					value="<%=(movieinfoVO == null) ? "請輸入電影片長" : movieinfoVO.getMovie_length()%>" /></td>
			</tr>
			<tr>
				<td nowrap="nowrap">電影預告:</td>
				<td><input type="TEXT" name="movie_trailer" size="45"
					value='<%=(movieinfoVO == null) ? "請輸入預告網址" : movieinfoVO.getMovie_trailer()%>' /></td>
			</tr>
			<tr>
				<td nowrap="nowrap">電影上映時間:<font color=red size="2"><b>*</b></font></td>
				<td><input name="movie_in" id="movie_in" class="f_date1" type="text"
					value="<%=(movieinfoVO == null) ? "請輸入上映時間" : movieinfoVO.getMovie_in()%>"></td>
			</tr>
			<tr>
				<td nowrap="nowrap">電影下映時間:<font color=red size="2"><b>*</b></font></td>
				<td><input name="movie_out" id="movie_out" class="f_date1" type="text" 
					value="<%=(movieinfoVO == null) ? "請輸入下映時間" : movieinfoVO.getMovie_out()%>"></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>電影票房:</td> -->
<!-- 				<td><input type="TEXT" name="movie_count" size="45" -->
<%-- 					value="<%=(movieinfoVO == null) ? "0" : movieinfoVO.getMovie_exp()%>" /></td> --%>
<!-- 			</tr> -->
			<tr>
				<td nowrap="nowrap">電影期待度:</td>
				<td><input type="TEXT" id="movie_exp" name="movie_exp" size="45"
					value="<%=(movieinfoVO == null) ? "0" : movieinfoVO.getMovie_exp()%>" /></td>
			</tr>
			<tr>
				<td nowrap="nowrap">電影不期待度:</td>
				<td><input type="TEXT" id="movie_noexp" name="movie_noexp" size="45"
					value="<%=(movieinfoVO == null) ? "0" : movieinfoVO.getMovie_noexp()%>" /></td>
			</tr>
			<tr>
				<td nowrap="nowrap">電影片長加價:</td>
				<td><input type="TEXT" id="movie_ticket" name="movie_ticket" size="45"
					value="<%=(movieinfoVO == null) ? "0" : movieinfoVO.getMovie_touch()%>" /></td>
			</tr>
			<tr>
				<td nowrap="nowrap">電影封面:</td>
				<td><input type="file" name="movie_pic" onchange='readURL2(this)'>
				<img id="pic" class='pic2' src='data:img/png;base64,${encodeText2}'
					${(movieinfoVO.movie_pic==null) ? 'style="display:none"' : ''}>
				</td>
			</tr>
			<tr valign="top">
				<td id="movie_con" nowrap="nowrap">電影簡介:<font color=red size="2"><b>*</b></font></td>
				<td><textarea name="movie_intro" rows="10" cols="80"><%=(movieinfoVO == null) ? "請輸入電影簡介" : movieinfoVO.getMovie_intro()%></textarea></td>
			</tr>
			
		</table>
			<br>
			<input type="hidden" name="action" value="insert"> 
			<input type="submit" value="送出">
			<input type ="button" onclick="history.back()" value="取消"></input>
			<img id="button" width="20" height="20" src="<%=request.getContextPath()%>/Back_end/movieinfo/images/spiderman.jpg" onclick="movieinsert(this)">
			
	</FORM>
	
		</div>
		</div>
	</div>

<!-- 工作區結束 -->
		
		<jsp:include page="/BackHeaderFooter/Footer.jsp" />
		
	<script>
		function readURL(input) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$(".pic").attr('src', e.target.result).css("display", "");

			}
			reader.readAsDataURL(input.files[0]);
		}
		
		function readURL2(input) {
			var reader2 = new FileReader();
			reader2.onload = function(e) {
				$(".pic2").attr('src', e.target.result).css("display", "");

			}
			reader2.readAsDataURL(input.files[0]);
		}
		
		function movieinsert(name){
			form1.movie_name.value="資策會的秘密約會";
			form1.movie_score.value="9.9";
			form1.movie_director.value="賴柏松";
			form1.movie_cast.value="蔣家駿, 陳彥彰";
			form1.movie_intro.value="一段愛與勇氣的故事";
			form1.movie_length.value="1hr48min";
			form1.movie_trailer.value="https://www.youtube.com/embed/q6EoRBvdVPQ";
			form1.movie_in.value="2019-03-22";
			form1.movie_out.value="2019-04-22";
			form1.movie_count.value="0";
			form1.movie_exp.value="0";
			form1.movie_noexp.value="0";
			form1.movie_ticket.value="300";
		}
	</script>
	
	
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
// 	java.sql.Date movie_in = null;
// 	try {
// 		movie_in = movieinfoVO.getMovie_in();
// 	} catch (Exception e) {
// 		movie_in = new java.sql.Date(System.currentTimeMillis());
// 	}
%>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh');
	$('.f_date1').datetimepicker({
	   theme: '',              //theme: 'dark',
	   timepicker:false,       //timepicker:true,
	   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	   format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%-- 	   value: '<%=movie_in%>', // value:   new Date(), --%>
	   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
// 	   startDate:	            '2017/07/10',  // 起始日
	   //minDate:               '-1970-01-01', // 去除今日(不含)之前
	   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	
	

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>