<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
<link href="habillage.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="suggest.js"></script>
<script type="text/javascript" src="script.js"></script>
<script src="jquery_last.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="style/validator.css"></link>
<script src="formValidator.js" type="text/javascript" charset="UTF-8"></script>
<script src="formValidatorRegex.js" type="text/javascript"
	charset="UTF-8"></script>
<script language="javascript" src="DateTimeMask.js"
	type="text/javascript"></script>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		$.formValidator.initConfig({
			formid : "form1",
			onerror : function(msg) {
				alert(msg)
			},
			onsuccess : function() {
				return true;
			}
		});
		$("#loginBox").formValidator({
			onshow : "please input your name",
			onfocus : "6-10 letters",
			oncorrect : "you can register"
		}).inputValidator({
			min : 6,
			max : 10,
			onerror : "illegal user Name"
		}).regexValidator({
			regexp : "username",
			datatype : "enum",
			onerror : "not correct user name format"
		}).ajaxValidator({
			type : "post",
			url : "CheckLogin",
			datatype : "html",
			success : function(data) {
				var text = $(data).text();
				if (text == "1") {
					return true;
				} else {
					return false;
				}
			},
			buttons : $("#button"),
			error : function() {
				alert("the server is busy");
			},
			onerror : "the user name is not availabe, try another one",
			onwait : "check if the name is correct, please wait..."
		});
		$("#pass1").formValidator({
			onshow : "input password",
			onfocus : "password can't be empty",
			oncorrect : "ok"
		}).inputValidator({
			min : 1,
			empty : {
				leftempty : false,
				rightempty : false,
				emptyerror : "no blanks on the both sides of the password"
			},
			onerror : "password can't be empty"
		});
		$("#pass2").formValidator({
			onshow : "input again",
			onfocus : "two passwords must be the same",
			oncorrect : "ok"
		}).inputValidator({
			min : 1,
			empty : {
				leftempty : false,
				rightempty : false,
				emptyerror : "no blanks on the both sides of the password"
			},
			onerror : "password can't be empty"
		}).compareValidator({
			desid : "pass1",
			operateor : "=",
			onerror : "two passwords must be the same"
		});
	});
//-->
</script>
</head>
<body>
<div id="page">

<div id="entete"><a href="index.jsp"><img src="Img/banner.jpg"
	width="950" height="100" border=no></a></div>

<div id="menu">
<table id="menutab">
	<tr>
		<td id="menubutton" onClick='document.location.href="index.jsp"'>
		Accueil</td>
		<%
			String result = (String) request.getAttribute("result");
			System.out.println(result);
			if ((result != null) && (result.equalsIgnoreCase("ok"))) {

				String login = request.getParameter("login");
				String kind = request.getParameter("kind");
				session.setAttribute("login", login);
				session.setAttribute("kind", kind);
				System.out.println(kind);
			}
			String sessionlog = (String) session.getAttribute("login");
			if (sessionlog == null) {
				request.getSession().invalidate();
		%>

		<td id="menubutton" onclick='document.location.href="register.jsp"'>
		Register</td>
		<td><a href="oauth-demo.jsp"><img border="0"
			src="Img/Blue_150_Loginwithmyspaceid.png" /></a></td>
		<td>

		<form method="post" action="Login"><label id="menulabel"
			for="login">Login : </label> <input id="login" name="login"
			type="text" width="70" /> <label id="menulabel" for="pass">Password
		: </label> <input id="pass" name="pass" type="password" width="50" /> <input
			id="menubuttonform" type="submit" value="log in" /></form>


		<%
			} else {
		%>
		
		<td id="menubutton" onclick="javascript:displayProfileReq()">
		Profile</td>
		<td id="menubutton" onClick="document.location='EventPage'">
		Manage Events</td>
		<td id="menubutton" onClick="document.location='deconnexion.jsp'">
		Deconnexion</td>
		<%
			}
		%>


	</tr>
</table>
<div id="searchBox">
<form action="javascript:result();">
    <input type="text" id="txtSearch"  name="txtSearch" alt="Search Criteria" onkeyup="searchSuggest();" autocomplete="off" />
    
    <input id="menubuttonform" type="hidden" onClick="result()"   name="cmdSearch" value="Search" alt="Run Search" />
     
	</form>
	<div id="search_suggest"></div>
</div>

</div>
<div id="contenu">
<form id="form1" method="post" action="Register">
<fieldset><legend>Basic Info</legend>
<p><label for="kind">Kind of account:</label>
	<SELECT name="kind">
		<OPTION VALUE="User" onclick="javascript:registerUser();">User</OPTION>
		<OPTION VALUE="Artist" onclick="javascript:registerArtist();">Artist</OPTION>
		<OPTION VALUE="Professional" onclick="">Professional</OPTION>
	</SELECT></p>
<p><label for="form1_firstname">Firstname:</label><input type="text" id="form1_firstname" name="firstname" size="25" /></p>
<p><label for="form1_lastname">Lastname:</label><input type="text" id="form1_lastname" name="lastname" size="25" /></p>
<p><label for="form1_mail">Mail:</label><input type="text" id="form1_mail" name="mail" /></p>
</fieldset>
<fieldset><legend>Connection Info</legend>
<p><label for="login">Your login:</label><input type="text" id="loginBox"name="login" size="25"/></p><div id="loginBoxTip" style="width: 250px;float:right;"></div>
<p><label for="pass1">Your password:</label><input type="password" id="pass1" name="pass1" size="25"/></p><div id="pass1Tip" style="width: 200px;float:right;"></div>
<p><label for="pass2">Password Check:</label><input type="password"id="pass2" name="pass2" size="25"/></p><div id="pass2Tip" style="width: 200px;float:right;"></div>
</fieldset>
<fieldset><legend>Your Address</legend>
<p><label for="street">Street:</label><input type="text" name="street"size="60" /> </p>
<p><label for="city">City:</label><input type="text"name="city" size="25" /></p>
<p><label for="zip">Zip code:</label><input type="text" name="zip" size="10" /></p>
<p><label for="country">Country:</label><input type="text" name="country" size="25"/></p>
</fieldset>
<div id="kindform"></div>
<input type="submit" value="Submit" /><input type="reset" value="Reset" /></form>

</div>
<div id="pied"></div>
</div>
</body>
</html>