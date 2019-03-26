<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketinformation.model.*"%>


<%
	TicketinformationVO ticketinformationVO = (TicketinformationVO) request.getAttribute("ticketinformationVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>��ƭק� - update_ticketinformation_input.jsp</title>

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
		 <h3>��ƭק� - update_ticketinformation_input.jsp</h3>
		 <h4><a href="select_page_ticketinformation.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="ticketinformation.do" name="form1">
<table>
	<tr>
		<td>���ؽs��:</td>
		<td><%=ticketinformationVO.getTi_no()%></td>
	</tr>
	<tr>
		<td>���ئW��:</td>
		<td><input type="TEXT" name="ti_name" size="45" 
			 value="<%=ticketinformationVO.getTi_name()%>" /></td>
	</tr>
	<tr>
		<td>���ز���:</td>
		<td><input type="TEXT" name="ti_price" size="45" 
			 value="<%=ticketinformationVO.getTi_price()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ti_no" value="<%=ticketinformationVO.getTi_no()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>






</html>