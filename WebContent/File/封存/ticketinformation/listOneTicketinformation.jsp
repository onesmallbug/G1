<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.ticketinformation.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	TicketinformationVO ticketinformationVO = (TicketinformationVO) request.getAttribute("TicketinformationVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>


<html>
<head>
<title>��� - listOneTicketinformation.jsp</title>

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
	width: 600px;
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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>

<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneTicketinformation.jsp</h3>
		 <h4><a href="select_page_ticketinformation.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>

	<tr>
		<th>���ؽs��</th>
		<th>���ئW��</th>
		<th>���ز���</th>
	</tr>
	
	<tr>
			<td>${ticketinformationVO.ti_no}</td>
			<td>${ticketinformationVO.ti_name}</td>
			<td>${ticketinformationVO.ti_price}</td>
	</tr>



</table>


</body>


</html>