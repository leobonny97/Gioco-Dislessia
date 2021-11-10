<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.* "%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista giocatori</title>
</head>
<body>

<%@ include file="SchemaPagina.jsp" %>

<h1> Elenco giocatori</h1>

<table>
<tr>
<th>Username</th>
<th>Password</th>
<th>Progresso</th>
</tr>


<% 
	ArrayList<Giocatore> giocatori=(ArrayList<Giocatore>)session.getAttribute("giocatori");
	if(giocatori!=null)
	{
		   for(Giocatore c : giocatori)
		   {
%>
				<tr>
    			<td><%=c.getUsername() %></td>
    			<td><%=c.getPassword() %></td>
    			<td><%=c.getProgresso() %></td>
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