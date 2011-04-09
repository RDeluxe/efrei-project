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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
<link href="habillage.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="suggest.js"></script>
  <script type="text/javascript" src="script.js"></script>
 
</head>
<body>
<div id="page">

<div id="entete"><a href="index.jsp"><img src="banner.jpg" width="947" height="186" border=no></a></div>

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
  if(sessionlog==null){%>

  <td id="menubutton" onclick='document.location.href="register.jsp"'>
 Register
  </td>
  <td>
  <form method="post" action="Login">
  <label for="login">Login : </label> <input id="login" name="login" type="text" width="100" />
  <label for="pass">Password : </label> <input id="pass" name="pass" type="password" width="80" />
  
  
  <input id="send"  type="submit" value="log in"/>

 
  </form>
  
  
  <%request.getSession().invalidate();
  }else{%>
  <td id="menubutton" onclick="javascript:displayProfileReq()">
  Profile
  </td>
   <td>
<input type="button" value="Deconnexion" onClick="document.location='deconnexion.jsp'">
  </td>
  <%} %>
  <%if(result!=null){ %>
  <%if(result.equalsIgnoreCase("ko")==true){ %>
  <p> No user with this Login</p>
  <%} %>
  <%if(result.equalsIgnoreCase("upok")==true){ %>
  <p> Profile correctly updated !</p>
  <%} %>
  <%} %>
  <td>
  	<form>
    <input type="text" id="txtSearch"  name="txtSearch" alt="Search Criteria" onkeyup="searchSuggest();" autocomplete="off" />
    <input type="button" onClick="result()"   name="cmdSearch" value="Search" alt="Run Search" /><br />
    <div id="search_suggest"></div>
     
	</form>
  
</td>

</tr>
</table>
 

</div>
<div id="contenu">
<div id="left">
<div id="info">
</div>
<div id="desc">
</div>
</div>
<div id="right" >
<div id="pic">
</div>
<div id="events">
</div>
</div>
</div>
<div id="pied">
</div>
</div>
5
</body>
</html>