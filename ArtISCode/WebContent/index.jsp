<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
<link href="habillage.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="suggest.js"></script>
</head>
<body>
<div id="page">

<div id="entete"><a href="index.jsp"><img src="banner.jpg" width="947" height="186" border=no></a></div>
<div id="menu">
  <div id="loginbox">
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
  
  <form method="post" action="Login">
  <label for="login">Login : </label> <input id="login" name="login" type="text" width="100" />
  <label for="pass">Password : </label> <input id="pass" name="pass" type="password" width="80" />
  
  <label for="kind">Kind :</label> <SELECT name="kind">
		<OPTION VALUE="1">User</OPTION>
		<OPTION VALUE="2">Artist</OPTION>
		<OPTION VALUE="3">Pro</OPTION>
        </SELECT>
  <br/>
  <a href="register.jsp" style="font-size:10px; padding-top:10px; margin-left:16px; margin-right:20px;  font-style:italic"> register </a>
  
 <input id="send"  type="submit" value="log in"/>
  </form>
  <a href="oauth-demo.jsp"><img border="0" src="Blue_150_Loginwithmyspaceid.png"/></a>
  
  <%request.getSession().invalidate();
  }else{%>
  <p> Hi, <%= session.getAttribute("login")  %></p>
  <p> Modify your<a href="Profile" >profile</a>  </p>
  <p> <input type="button" value="Deconnexion" onClick="document.location='deconnexion.jsp'"></p>
  <%} %>
  <%if(result!=null){ %>
  <%if(result.equalsIgnoreCase("ko")==true){ %>
  <p> No user with this Login</p>
  <%} %>
  <%if(result.equalsIgnoreCase("upok")==true){ %>
  <p> Profile correctly updated !</p>
  <%} %>
  <%} %>
  </div> 
  
  <div id="menubox">
 
	<form>
    <input type="text" id="txtSearch"  name="txtSearch" alt="Search Criteria" onkeyup="searchSuggest();" autocomplete="off" />
    <input type="button" onClick="result()"   name="cmdSearch" value="Search" alt="Run Search" /><br />
    <div id="search_suggest"></div>
     
	</form>
   


  </div>
</div>

<div id="contenu">
  <div id="result"></div>
</div>

<div id="pied">
</div>
</div>
</body>
</html>