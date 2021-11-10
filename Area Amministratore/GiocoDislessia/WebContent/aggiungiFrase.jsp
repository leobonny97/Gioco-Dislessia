<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.* "%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aggiungi frase</title>
</head>
<body>

<%@ include file="SchemaPagina.jsp" %>

<h1> Nuova frase</h1>

<% 
String nparole=request.getParameter("nparole");
if(nparole==null)
{
	nparole="1";
}
%>


<form  action="aggiungiFrase.jsp" method="post" name="login">
<p> Numero parole: <input type="number" min="1" max="20" name="nparole" value="<%=Integer.parseInt(nparole) %>"> </p>
<p> <input type="submit" value="mostra" name="mostra"> </p>
</form>

<form  action="AggiungiFrase" method="post" name="login">
<% 
for(int c=0;c<Integer.parseInt(nparole);c++)
{
%>
	<p> Parola: <input type="text" name="parola<%=c%>" required> Distrattore: <input type="text" name="dis<%=c%>"> Distrattore: <input type="text" name="dist<%=c%>"> </p>
<%
}
%>
<p> Livello: <input type="number" min="1" max="4" name="livello" value="livello" required> </p>
<p> <input type="submit" value="inserisci" name="inserisci"> </p>
</form>


<%@ include file="FinePagina.jsp" %>


</body>
</html>