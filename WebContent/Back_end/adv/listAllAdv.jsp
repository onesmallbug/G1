 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adv.model.*"%>


<%
	AdvService advSvc = new AdvService();
    List<AdvVO> list = advSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!doctype html>
<html  lang="en">
<head>


<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS start-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->


<title>瀏覽所有廣告</title>

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
/*  	width: 800px;  */
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
<body bgcolor='white'>

<jsp:include page="/BackHeaderFooter/Header.jsp" />


<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
		<br>
		 <h3>瀏覽所有廣告</h3>
<!-- 		 <h4><a href="select_page_adv.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<table class="table table-hover"  >
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
		<th scope="col">刪除</th>
	</tr>
</thead>
	<%@ include file="page1.file" %> 
	<c:forEach var="advVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
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
				
				
<%-- 				<img  src='<%=request.getContextPath()%>/Back_end/adv/adv.do?ad_no=${advVO.ad_no}' width='200' height='200' /> --%>
			</td>
			<td width="600px">
			<div style=overflow:auto;height:300px;width:600px >   
			${advVO.ad_cont}
			</div>

			
			
			</td>
			<td>${advVO.getAd_start().toString().substring(0,19)}</td>
			<td>${advVO.getAd_end().toString().substring(0,19)}</td> 
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

<!-- 				<input type="TEXT" name="ad_cont" size="45"  -->
<%-- 			 value="<%= (advVO.getAd_cont()==null)? "" : advVO.getAd_cont()%>" /> --%>

			</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_end/adv/adv.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="btn btn-primary iframe_contruler">
			     <input type="hidden" name="ad_no"  value="${advVO.ad_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_end/adv/adv.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn btn-primary iframe_contruler">
			     <input type="hidden" name="ad_no"  value="${advVO.ad_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>




	</c:forEach>
</table>
<%@ include file="page2.file" %>




<!-- <table border="1" width="400"> -->
<!--   <tr> -->
<!--     <th>Month</th> -->
<!--     <th>Savings</th> -->
<!--   </tr> -->
<!--   <tr> -->
<!--     <td>January</td> -->
<!--     <td>$100</td> -->
<!--   </tr> -->
<!-- </table> -->



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
