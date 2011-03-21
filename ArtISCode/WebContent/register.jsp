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
 
</head>
<body>
<div id="page">

<div id="entete"><a href="index.jsp"><img src="Img/banner.jpg" width="950" height="100" border=no></a></div>

<div id="menu">
<table id="menutab">
<tr>
  <td id="menubutton" onClick='document.location.href="index.jsp"'>
  	Accueil
  </td>
  <%String result=(String) request.getAttribute("result");
  System.out.println(result);
  if((result!=null)&&(result.equalsIgnoreCase("ok"))){
  
	  String login =request.getParameter("login");
	  String kind = request.getParameter("kind");
  session.setAttribute( "login", login );
  session.setAttribute("kind", kind);
  System.out.println(kind);
  }
   String sessionlog=(String) session.getAttribute("login");
  if(sessionlog==null){
	    request.getSession().invalidate();%>

  <td id="menubutton" onclick='document.location.href="register.jsp"'>
 Register
  </td>
  <td>
  <a href="oauth-demo.jsp"><img border="0" src="Img/Blue_150_Loginwithmyspaceid.png"/></a>
  </td>
  <td>
  
  <form method="post" action="Login">
  <label id="menulabel" for="login">Login : </label> <input id="login" name="login" type="text" width="70" />
  <label id="menulabel" for="pass">Password : </label> <input id="pass" name="pass" type="password" width="50" />
  
  
  <input id="menubuttonform" type="submit" value="log in"/>

 
  </form>
  
  
  <%}else{%>
  <td id="menubutton" onclick="javascript:displayProfileReq()">
  Profile
  </td>
   <td id="menubutton" onClick="javascript:displayEventsReq()">
  Manage Events
  </td>
   <td id="menubutton" onClick="document.location='deconnexion.jsp'">
	Deconnexion
  </td>
  <%} %>
  
  <td>
  	<form action="javascript:result();">
    <input type="text" id="txtSearch"  name="txtSearch" alt="Search Criteria" onkeyup="searchSuggest();" autocomplete="off" />
    
    <input id="menubuttonform" type="hidden" onClick="result()"   name="cmdSearch" value="Search" alt="Run Search" />
     
	</form>
  
</td>

</tr>
</table>
<div id="search_suggest"></div>
 

</div>
<div id="contenu">
  <form method="post" action="Register">
  <fieldset>
  <legend>Registration page for User</legend>
    <fieldset>
    <legend>Basic Info</legend><br/>
    Kind of account:<br/>
     <SELECT name="kind">
		<OPTION VALUE="User" onclick="javascript:registerUser();">User</OPTION>
		<OPTION VALUE="Artist" onclick="javascript:registerArtist();">Artist</OPTION>
		<OPTION VALUE="Professional" onclick="">Professional</OPTION>	
	</SELECT><br/>
    Firstname:<br/>
    <input type="text" name="firstname"  size="25" /><br/>
    Lastname:<br/>
    <input type="text" name="lastname"  size="25" /><br/>
    Mail:<br/>
    <input type="text" name="mail"  /><br/>
    <br/>
    </fieldset>
    <fieldset>
    <legend>Connection Info</legend><br/>
    Your login:<br/>
    <input type="text" id="loginBox" name="login" size="25" /><br/>
    Your password:<br/>
    <input type="password" name="pass1" size="25" /><br/>
    Password Check:<br/>
    <input type="password" name="pass2" size="25" /><br/>
    </fieldset>
    <fieldset>
    <legend>Your Address</legend><br/>
    Street:<br/>
    <input type="text" name="street"  size="60" /><br/>
    City:<br/>
    <input type="text" name ="city"  size="25" /><br/>
    Zip code:<br/>
    <input type="text" name="zip"  size="10" /><br/>
    Country:<br/>
    <input type="text" name="country"  size="25" /><br/>
    </fieldset>
    <br/>
    <div id="kindform"></div>
    <p></p>
    <input type="submit"
value="Submit" />
    <input type="reset"
value="Reset" />
 </fieldset>
  </form>
 
</div>
<div id="pied">
  
</div>
</div>
</body>
</html>