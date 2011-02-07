<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Document sans titre</title>
<link href="habillage.css" rel="stylesheet" type="text/css">
</head>


<body>
<%@ import domain.* %>
<% Artist artist = request.getAttribute("artist"); %>
<div id="page">

<div id="entete"><a herf="www.index.html"><img src="banner.jpg" width="947" height="186" border=no></a></div>
<div id="menu" ice:editable="*">
  <div id="loginbox">
  <FORM METHOD=POST ACTION="login">
  <label for="login">Login : </label> <input id="login" name="login" width="100" />
  <label for="pass">Password : </label> <input id="pass" name="pass" type="password" width="80" /><br/>
  <a href="register.jsp" style="font-size:10px; padding-top:10px; margin-left:16px; margin-right:20px;  font-style:italic"> register </a> 
  </label> <input id="send" type="button" value="log in"/>
  </FORM>
  </div>
  
  <div id="menubox">
 
  <label for="search"> Search </label> <input id="search" name="search" width="100" />
  </div>
</div>
<div id="contenu">
  <p>Artist Page</p>
  
 <div id="left_column">
 <p>Infos colum </p>
 
 <img src="album-cocoon.jpg" width="180" height="180" />
 <h3> From </h3>
 <p> <%= User.getAddress().getCountry() %>
 <h3> Audio samples </h3>
 
<br> <br/>
 <h3> Tags </h3>
 <p> <%= Artists.getTags() %>
 
 
 
 <p> 
 </div>
 
 <div id="text">
 <p><h1> <%= User.getName()%> </h1> </p>
 <p><%= User.getDescription()%>
 </p>

 </div>
 
</div>
<div id="pied">Apex Corporation</div>
</div>

</body>
</html>