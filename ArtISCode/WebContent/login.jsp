<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  
 Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.
-->
<html>
<head>
<link href="habillage.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="suggest.js"></script>
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="uploadify/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="uploadify/swfobject.js"></script>
<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>
<script src="jquery-1.5.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<div id="page">

<div id="entete"><a href="index.jsp"><img src="Img/banner.jpg"
	width="950" height="100" border=no></a></div>

<div id="menu">
<table id="menutab">
	<tr>
		<td id="menubutton" onClick='document.location.href="index.jsp"'>
		Home</td>
		<td id="menubutton" onclick='document.location.href="register.jsp"'>
		Register</td>
		<td id="menubutton" onclick='document.location.href="oauth-demo.jsp"' style="width:150px">Register from Myspace</td>
		<td>
		<input id="login" name="login" type="text" value="login" onfocus='if (this.value=="login") {this.value=""}' onblur='if (this.value=="") {this.value="login"}' width="70" />
		</td>
		<td>
		<input id="pass" name="pass" type="password" value="password" onfocus='if (this.value=="password") {this.value=""}' onblur='if (this.value=="") {this.value="password"}' width="50" />
		</td>
		<td id="menubutton" onclick='$.post("Login", { login:document.getElementById("login").value, pass:document.getElementById("pass").value }, function(data) { document.documentElement.innerHTML=data;});'>
		Login
		</td>
		</tr>
</table>
<div id="searchBox">
<form action="javascript:result();"><input type="text"
	id="txtSearch" name="txtSearch" alt="Search Criteria"
	onkeyup="searchSuggest();" autocomplete="off" value="Search" onfocus='if (this.value=="Search") {this.value=""}' onblur='if (this.value=="") {this.value="Search"}' /> <input
	id="menubuttonform" type="hidden" onClick="result()" name="cmdSearch"
	value="Search" alt="Run Search" /></form>
<div id="search_suggest"></div>
</div>
</div>
<div id="contenu">
<div id="loginpage">
<h2>Already registered ?</h2>
<h6>Please connect using your login and password, provided when you've register</h6>
<form action="Login" method="post">
<div class="formlabel"><label for="login">Login : </label></div><div class="forminput"> <input id="login" name="login" type="text" width="100" /></div>
<div class="formlabel"><label for="pass">Password : </label></div><div class="forminput"> <input id="pass" name="pass" type="password" width="80" /></div>
<div class="forminput"><input type="submit" value="Connect"/></div>
<div id="pied"></div>
</form>

</div>
</div>
</div>
<div id="pied"></div>
</body>
</html>