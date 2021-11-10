<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login admin</title>
</head>
<body>



<h1>Effettuare l'accesso da amministratore</h1>
<form  action="LoginAdmin" method="post" name="login">
<p> Username: <input type="text" name="username" onchange="controlloUsername()"> </p>
<p> Password: <input type="password" name ="password" onchange="controlloPassword()"> </p>
<div id="infoDiv" class="alert alert-danger form-group d-none" role="alert"> </div>
<p> <input type="button" value="invia" name="invia" onclick="validazione()">
    <input type="reset" value="reset" name="reset">
</p>
</form>



<script src="JS/jquery.js"></script>
<script src="JS/ValidazioneLogin.js"></script>

</body>
</html>