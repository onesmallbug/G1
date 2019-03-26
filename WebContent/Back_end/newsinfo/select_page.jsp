<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM NewsInfo: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #00caca;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

		<!-- Bootstrap CSS start-->
		<link rel="stylesheet"
		href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
		<!-- Bootstrap CSS end-->

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
   <tr><td><h3>影視新聞管理: Home</h3></td></tr>
</table>

	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li>All Newsinfo    <a href='<%=request.getContextPath()%>/Back_end/newsinfo/listAllNewsInfo.jsp'>EDIT</a>.<br><br></li>
  <li><a href='<%=request.getContextPath()%>/Back_end/newsinfo/addNewsInfo.jsp'>Add</a> a new NewsInfo.</li><br>
  

<!-- 		  <li> -->
<%-- 		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_end/newsinfo/newsinfo.do" > --%>
<!-- 		       <b>選擇電影編號:</b> -->
<!-- 		       <select size="1" name="movie_no"> -->
<%-- 		         <c:forEach var="newsinfoVO" items="${newsinfoSvc.all}" >  --%>
<%-- 		          <option value="${newsinfoVO.news_no}">${newsinfoVO.movie_no} --%>
<%-- 		         </c:forEach>    --%>
<!-- 		       </select> -->
<!-- 		       <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		       <input type="submit" value="送出"> -->
<!-- 		    </FORM> -->
<!-- 		  </li> -->

  <jsp:useBean id="newsinfoSvc" scope="page" class="com.newsinfo.model.NewsInfoService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_end/newsinfo/newsinfo.do" >
       <b>選擇專欄編號:</b>
       <select size="1" name="news_no">
         <c:forEach var="newsinfoVO" items="${newsinfoSvc.all}" > 
          <option value="${newsinfoVO.news_no}">${newsinfoVO.news_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_end/newsinfo/newsinfo.do" >
       <b>選擇專欄名稱:</b>
       <select size="1" name="news_no">
         <c:forEach var="newsinfoVO" items="${newsinfoSvc.all}" > 
          <option value="${newsinfoVO.news_no}">${newsinfoVO.news_title}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

			</div>
		</div>
	</div>


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