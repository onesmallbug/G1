<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.ticketorder.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	TicketorderVO ticketorderVO = (TicketorderVO) request.getAttribute("ticketorderVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>


<html>
<head>
<title>��� - listOneTicketorder.jsp</title>

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
		 <h3>���u��� - ListOneTicketorder.jsp</h3>
		 <h4><a href="select_page_ticketorder.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>

	<tr>
		<th>�q��s��</th>
		<th>�|���s��</th>
		<th>�u�f�s��</th>
		<th>�����s��</th>
		<th>���u�s��</th>
		<th>���έq��</th>
		<th>�O�_���\</th>
		<th>�q��ɶ�</th>
		<th>�q����B</th>
	</tr>
	
	<tr>
			<td><%=ticketorderVO.getOrder_no()%></td>
			<td><%=ticketorderVO.getMember_no()%></td>
			<td><%=ticketorderVO.getFd_no()%></td>
			<td><%=ticketorderVO.getSession_no()%></td>
			<td><%=ticketorderVO.getEmployee_no()%></td>
			<td><%=ticketorderVO.getOrder_group()%></td> 
			<td><%=ticketorderVO.getOrder_takemeals()%></td>
			<td><%=ticketorderVO.getOrder_time()%></td>
			<td><%=ticketorderVO.getOrder_amount()%></td>
	</tr>



</table>


</body>


</html>