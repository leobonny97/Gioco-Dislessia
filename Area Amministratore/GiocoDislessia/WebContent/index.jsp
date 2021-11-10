<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    




<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style/Style.css"	title="style" />
<title>Area admin gioco dislessia</title>
</head>
<body>

<%
String admin=(String)session.getAttribute("admin");
if(admin!=null)
{
	if(admin.equals("1"))
	{
		response.sendRedirect("./MenuAdmin.jsp");
		return;
	}
}

%>

<a href="index.jsp">
<header>
<img id="logo" src="logos.jpeg">
</header>
</a>

<%@ include file="LoginAdmin.jsp" %>

<a href="index.jsp">
<header>
<img id="logo" src="logos.jpeg">
</header>
</a>


</body>
</html>