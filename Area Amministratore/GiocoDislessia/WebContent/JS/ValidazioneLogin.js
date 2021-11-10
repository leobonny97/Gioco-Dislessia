function controlloUsername()
{
    var username=document.login.username.value;
	if(username.length<1 || username==" ")
	{
		document.login.username.style.borderColor="red";
		return false;
	}
	else{
		document.login.username.style.borderColor="green";
		return true;
	}
}

function controlloPassword()
{
    var password=document.login.password.value;
	if(password.length<1)
	{
		document.login.password.style.borderColor="red";
		return false;
	}
	else{
		document.login.password.style.borderColor="green";
		return true;
	}
}

function validazione()
{
	document.getElementById("infoDiv").className = "alert alert-danger form-group d-block";
	var div = $("#infoDiv");
	div.text("");
	if(!controlloUsername())
	{
		document.login.username.focus();
		div.append("Inserisci username.");
		return false;
	}
	else if(!controlloPassword())
	{
		document.login.password.focus();
		div.append("Inserisci password.");
		return false;
	}
	else
	{
		document.login.submit();
	}
}