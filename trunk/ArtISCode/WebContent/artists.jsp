<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Artist Page</title>
<link href="habillage.css" rel="stylesheet" type="text/css">
</head>


<body>
<%@ page import=domain.* %>
<% Artist artist = (Artist) request.getAttribute("artist"); %>
<div id="page">

<div id="entete"><a herf="www.index.html"><img src="banner.jpg" width="947" height="186" border=no></a></div>
<div id="menu">
<div id="entete"><img src="banner.jpg" width="947" height="186" /></div>
<div id="menu">
  <div id="loginbox">
  <%String result=(String) request.getAttribute("result");
  System.out.println(result);
  if((result!=null)&&(result.equalsIgnoreCase("ok"))){
  
	  String login =request.getParameter("login");
  session.setAttribute( "login", login );
  }
   String sessionlog=(String) session.getAttribute("login");
  if(sessionlog==null){%>
  
  <form method="post" action="Login">
  <label for="login">Login : </label> <input id="login" name="login" type="text" width="100" />
  <label for="pass">Password : </label> <input id="pass" name="pass" type="password" width="80" />
  
  <label for="kind">Artist ? :</label> <input type="checkbox" name="kind" value="1"  /><br/>
  
  <a href="register.jsp" style="font-size:10px; padding-top:10px; margin-left:16px; margin-right:20px;  font-style:italic"> register </a> 
  
 <input id="send"  type="submit" value="log in"/>
  </form>
  
  <%request.getSession().invalidate();
  }else{%>
  <p> Hi, <%= session.getAttribute("login")  %></p>
  <p> Modify your<a href="profile.jsp" >profile</a>  </p>
  <p> <input type="button" value="Deconnexion" onClick="document.location='deconnexion.jsp'"></p>
  <%} %>
  <%if(result!=null){ %>
  <%if(result.equalsIgnoreCase("ko")==true){ %>
  <p> No user with this Login</p>
  <%} %>
  <%} %>
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
 <p> <%= artist.getAddress().getCountry() %>
 <h3> Audio samples </h3>
 
<br> <br/>
 <h3> Tags </h3>
 <p> <%= artist.getTag() %>
 
 
 
 <p> 
 </div>
 
 <div id="text">
 <p><h1> <%= artist.getFirstname()%> </h1> </p>
 <p><%= artist.getDescription()%>
 </p>

 </div>
 
</div>
<div id="pied">Apex Corporation</div>
</div>

</body>
</html>