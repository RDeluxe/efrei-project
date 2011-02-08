<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
<link href="habillage.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="page">

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
 
  <label for="search"> Search </label> <input id="search" name="search" width="80" />
  </div>
</div>

<div id="contenu">
  <p>This is our website<br />
    Some functionalities are in construction<br />
    Coming soon ;)</p>
</div>

<div id="pied">
</div>
</div>
</body>
</html>