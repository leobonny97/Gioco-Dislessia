<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.* "%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza frasi</title>
</head>
<body>

<%@ include file="SchemaPagina.jsp" %>

<h1> Elenco frasi</h1>

<table>
<tr>
<th>Id</th>
<th>Livello</th>
<th>Azione</th>
</tr>


<% 
	ArrayList<Frase> frasi=(ArrayList<Frase>)session.getAttribute("frasi");
	if(frasi!=null)
	{
		   for(Frase c : frasi)
		   {
%>
				<tr>
    			<td><%=c.getId() %></td>
    			<td><%=c.getLivello() %></td>
    			<td><a href="VisualizzaFrase?id_frase=<%=c.getId()%>">Visualizza</a></td>
    			</tr>
<%
		   }
    } 
%>
</table>

<br>
<a href="aggiungiFrase.jsp" >Aggiungi frase</a>
<br>

<%@ include file="FinePagina.jsp" %>



</body>
</html>