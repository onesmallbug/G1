<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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




<title>�s�i�޲z</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
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

</head>
<body bgcolor='white'>


<jsp:include page="/BackHeaderFooter/Header.jsp" />



<!-- <table id="table-1"> -->
<!--    <tr><td><h3>IBM Adv: Home</h3><h4>( MVC )</h4></td></tr> -->
<!-- </table> -->

<!-- <p>This is the Home page for IBM Adv: Home</p> -->
<br>
<h3>�s�i�޲z</h3>
<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllAdv.jsp'>List</a> all Advs.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="adv.do" >
        <b>��J�s�� (�pA000001):</b>
        <input type="text" name="ad_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X" class="btn btn-primary iframe_contruler">
    </FORM>
  </li>

  <jsp:useBean id="advSvc" scope="page" class="com.adv.model.AdvService" />
   
  <li>
     <FORM METHOD="post" ACTION="adv.do" >
       <b>��ܽs��:</b>
       <select size="1" name="ad_no">
         <c:forEach var="advVO" items="${advSvc.all}" > 
          <option value="${advVO.ad_no}">${advVO.ad_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X" class="btn btn-primary iframe_contruler">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="adv.do" >
       <b>��ܩm�W:</b>
       <select size="1" name="ad_no">
         <c:forEach var="advVO" items="${advSvc.all}" > 
          <option value="${advVO.ad_no}">${advVO.ad_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X" class="btn btn-primary iframe_contruler">
     </FORM>
  </li>
</ul>



<h3>�s�W�s�i</h3>

<ul>
  <li><a href='addAdv.jsp'>Add</a> a new Adv.</li>
</ul>



	<!-- �u�@�ϵ��� -->
	
	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->



</body>
</html>