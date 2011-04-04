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
  	Home
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
		Register</td>
		<td id="menubutton" onclick='document.location.href="oauth-demo.jsp"' style="width:150px">Register from Myspace</td>
		<td>
		<input id="login" name="login" type="text" value="login" onfocus='if (this.value=="login") {this.value=""}' onblur='if (this.value=="") {this.value="login"}' width="70" />
		</td>
		<td>
		<input id="pass" name="pass" type="password" value="password" onfocus='if (this.value=="password") {this.value=""}' onblur='if (this.value=="") {this.value="password"}' width="50" />
		</td>
		<td id="menubutton" onclick='$.post("Login", { login:document.getElementById("login").value, pass:document.getElementById("pass").value }, function(data) { document=""; document.write(data);});'>
		Login
		</td>
  
  <%}else{%>
  <td id="menubutton" onclick="javascript:displayProfileReq()">
  Profile
  </td>
   <td id="menubutton" onClick="document.location='EventPage'">
  Manage Events
  </td>
   <td id="menubutton" onClick="document.location='deconnexion.jsp'">
	Deconnexion
  </td>
  <%} %>

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
  <%String resultreg = (String) request.getAttribute("resultreg");
  if(resultreg.equalsIgnoreCase("ok"))
	  {%>
	  Welcome, <%=request.getAttribute("login") %>. You can now login
	  <%}else if(resultreg.equalsIgnoreCase("ko"))
			  {%>
			  Registering failed. Password check failed or/and another user with the same login. Please try again (use the forward function of your browser to get your information)
<%} %>
</div>

<div id="pied">
</div>
</div>
</body>
</html>