<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.* "%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza frase</title>
</head>
<body>

<%@ include file="SchemaPagina.jsp" %>

<h1>Frase</h1>

<table>
<tr>
<th>Frase</th>
<th>Parola</th>
<th>Ordine</th>
<th>Distrattore 1</th>
<th>Distrattore 2</th>
</tr>


<% 
	String id_frase=(String)session.getAttribute("id_frase");
	ArrayList<Parola> parole=(ArrayList<Parola>)session.getAttribute("parole");
	if(parole!=null)
	{
		   for(Parola c : parole)
		   {
%>
				<tr>
    			<td><%=c.getFrase() %></td>
    			<td><%=c.getStringa() %></td>
    			<td><%=c.getOrdine() %></td>
    			<% 
    			if(c.getDistrattori()!=null)
    			{
				%>
    				<td><%=c.getDistrattori().get(0).getStringa() %></td>
    				<td><%=c.getDistrattori().get(1).getStringa() %></td>
    			<% 
    			}
    			else
    			{
				%>
					<td></td>
					<td></td>
    			<% 
    			}
				%>
    			</tr>
<%
		   }
    } 
%>
</table>

<br>
<a href="RimuoviFrase?id_frase=<%=id_frase%>">Elimina frase</a>
<br>

<%@ include file="FinePagina.jsp" %>


</body>
</html>