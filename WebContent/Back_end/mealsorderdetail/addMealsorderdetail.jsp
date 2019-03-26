<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mealsorderdetail.model.*"%>

<%
	MealsorderdetailVO mealsorderdetailVO = (MealsorderdetailVO) request.getAttribute("mealsorderdetailVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>��Ʒs�W - addMealsorderdetail.jsp</title>

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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>��Ʒs�W - addMealsorderdetail.jsp</h3></td><td>
		 <h4><a href="select_page_mealsorderdetail.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="mealsorderdetail.do" name="form1">

<table>
	<tr>
		<td>�q��s��:</td>
		<td><input type="TEXT" name="order_no" size="45" 
			 value="<%= (mealsorderdetailVO==null)? "20" : mealsorderdetailVO.getOrder_no()%>" /></td>
	</tr>
	<tr>
		<td>�\�I�s��:</td>
		<td><input type="TEXT" name="meals_no" size="45" 
			 value="<%= (mealsorderdetailVO==null)? "MEALS010" : mealsorderdetailVO.getMeals_no()%>" /></td>
	</tr>
	<tr>
		<td>�\�I�ƶq:</td>
		<td><input type="TEXT" name="mo_count" size="45" 
			 value="<%= (mealsorderdetailVO==null)? "100" : mealsorderdetailVO.getMo_count()%>" /></td>
	</tr>
	

</table>


<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>




