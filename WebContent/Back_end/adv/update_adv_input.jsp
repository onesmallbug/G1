<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adv.model.*"%>

<%
  AdvVO advVO = (AdvVO) request.getAttribute("advVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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


<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> -->
<title>廣告資料修改</title>

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
/* 	width: 450px; */
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<jsp:include page="/BackHeaderFooter/Header.jsp" />



<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>廣告資料修改</h3> -->
<!-- 		 <h4><a href="select_page_adv.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->
<br>
<h3>廣告資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="adv.do" name="form1" enctype="multipart/form-data" enctype="multipart/form-data">



<table class="table table-hover">
	<tr>
		<th scope="col">編號:<font color=red><b>*</b></font></th>
		<th scope="col"><%=advVO.getAd_no()%></th>
	</tr>
	<tr>
		<th scope="col">廣告名稱:</th>
		<td><input type="TEXT" name="ad_name" size="90" 
			 value="<%= (advVO==null)? "吳永志" : advVO.getAd_name()%>" /></td>
	</tr>
	
	<tr>
		<th scope="col">廣告圖片:</th>
	<%--	<td><input type="TEXT" name="ad_pic" size="45" 
			 value="<%= (advVO==null)? "" : advVO.getAd_pic()%>" /></td> --%>
			 <td>
			 
			    <c:if test="${empty advVO.ad_pic}" var="condition">
				<img id="preview_progressbarTW_img" src="<%=request.getContextPath()%>/Back_end/adv/images/no_pic.jpg" width="200" height="200"/>
				</c:if>
			    <c:if test="${not empty advVO.ad_pic}" var="condition">
			    <img id="preview_progressbarTW_img" src='<%=request.getContextPath()%>/Back_end/adv/adv.do?ad_no=${advVO.ad_no}' width='300' height='200' />
				</c:if>
				
<%-- 				<input type="TEXT" name="ad_pic" size="45"  value="${advVO.ad_pic}" id="img" />  --%>
			 
<!-- 			 <img id="preview_progressbarTW_img" src="#"  width="100px"   height="100px"  style = "display:none"/> -->

			 <input type="file" id="progressbarTWInput" name="ad_pic" id="progressbarTWInput" name="ad_pic" size="25" accept="image/gif, image/jpeg, image/png" value="${advVO.ad_pic}"/>

<!-- 			 <input type="file" id="progressbarTWInput" name="ad_pic" id="progressbarTWInput" name="ad_pic" size="25" accept="image/gif, image/jpeg, image/png" value="preview_progressbarTW_img"/> -->
			 
			 </td> 
	</tr>
	
	<tr>
		<th scope="col">廣告內容:</th>
		<td> 
		<textarea name="content" id="content" rows="10" cols="80" ></textarea></td>
		<td style = "display:none"><input type="TEXT" name="ad_cont" size="45"  id="ad_cont" style = "display:none" /></td>
	</tr>
	
	<tr>
		<th scope="col">廣告起始時間:</th>
		<td><input name="ad_start" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<th scope="col">廣告終止時間:</th>
		<td><input name="ad_end" id="f_date2" type="text"></td>
	</tr>
	<tr>
		<th scope="col">廣告狀態:</th>
		<td>
<!-- 		<input type="TEXT" name="ad_type" size="45"  -->
<%-- 			 value="<%= (advVO==null)? "" : advVO.getAd_type()%>" /> --%>
       		<select size="1" name="ad_type">
       			
       			<c:if test="${advVO.ad_type==0}" var="condition">
				<option value="0" selected>下架  無版型</option>
				<option value="1">上架  1.版型</option>
				<option value="2">上架  2.版型</option>
<!-- 				<option value="3">上架  3.版型</option> -->
				</c:if>
				<c:if test="${advVO.ad_type==1}" var="condition">
				<option value="0">下架  無版型</option>
				<option value="1" selected>上架  1.版型</option>
				<option value="2">上架  2.版型</option>
<!-- 				<option value="3">上架  3.版型</option> -->
				</c:if>
				<c:if test="${advVO.ad_type==2}" var="condition">
				<option value="0">下架  無版型</option>
				<option value="1">上架  1.版型</option>
				<option value="2" selected>上架  2.版型</option>
<!-- 				<option value="3">上架  3.版型</option> -->
				</c:if>
<%-- 				<c:if test="${advVO.ad_type==3}" var="condition"> --%>
<!-- 				<option value="0">下架  無版型</option> -->
<!-- 				<option value="1">上架  1.版型</option> -->
<!-- 				<option value="2">上架  2.版型</option> -->
<!-- 				<option value="3" selected>上架  3.版型</option> -->
<%-- 				</c:if> --%>
				
<!--        			<option value="0">下架</option> -->
<!--           		<option value="1">上架</option> -->
       		</select>
			 </td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ad_no" value="<%=advVO.getAd_no()%>">
<input type="submit" value="送出修改" onclick="sendMessage();" class="btn btn-primary iframe_contruler"></FORM>




	<!-- 工作區結束 -->
	
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->









</body>






<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script src="<%=request.getContextPath()%>/ckeditor4/ckeditor.js"></script>

<script>
			CKEDITOR.replace( 'content', {});
</script>






<script>
function sendMessage() {
	var message = CKEDITOR.instances.content.getData()
	document.getElementById("ad_cont").value=message;

}
</script>


<script>

// 	alert("!");
	var ad_cont = '<%= (advVO.getAd_cont()==null)? "" : advVO.getAd_cont()%>';
// 	alert(ad_cont);
	CKEDITOR.instances.content.setData(ad_cont);
	

</script>







<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Timestamp ad_start = null;
  try {
	    ad_start = advVO.getAd_start();
   } catch (Exception e) {
	    ad_start = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Timestamp ad_end = null;
  try {
	  ad_end = advVO.getAd_end();
   } catch (Exception e) {
	   ad_end = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=ad_start%>', // value:   new Date(),
		   
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
        $('#f_date2').datetimepicker({
 	       theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=ad_end%>', // value:   new Date(),
            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
            //startDate:	            '2017/07/10',  // 起始日
            minDate:              '-1970-01-01', // 去除今日(不含)之前
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




</html>





