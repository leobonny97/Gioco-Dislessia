<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.* "%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza errori</title>
</head>
<body>


<%@ include file="SchemaPagina.jsp" %>

<h1> Elenco errori</h1>

<table>
<tr>
<th>Frase</th>
<th>Parola</th>
<th>Distrattore</th>
<th>Username</th>
</tr>


<% 
	ArrayList<Errore> errori=(ArrayList<Errore>)session.getAttribute("errori");
	if(errori!=null)
	{
		   for(Errore c : errori)
		   {
%>
				<tr>
    			<td><%=c.getFrase() %></td>
    			<td><%=c.getParola_stringa() %></td>
    			<td><%=c.getDistrattore_stringa() %></td>
    			<td><%=c.getGiocatore() %></td>
    			</tr>
<%
		   }
    } 
%>
</table>

<br>

<%@ include file="FinePagina.jsp" %>




</body>
</html>