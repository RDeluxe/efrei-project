<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Artist Page</title>
<script type="text/javascript" src="suggest.js"></script>
<link href="habillage.css" rel="stylesheet" type="text/css">
</head>


<body>
<%@ page import="domain.*" %>
<%@ page import="java.util.*" %>
<% Artist artist = (Artist) request.getAttribute("artist"); %>
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
     <div id="result"></div>
	</form>
   


  </div>
</div>
<div id="contenu">
<% User user =(User) request.getAttribute("user"); %>
  <p>Artist Page</p>
  
 <div id="left_column">
 <p>Infos colum </p>
 
 <img src="album-cocoon.jpg" width="180" height="180" />
 <h3> From </h3>
 <p> <%= user.getAddress().getCountry() %></p><br/>
 <p> <%= user.getAddress().getCity() %></p><br/>
 <p> <%= user.getAddress().getStreet() %></p><br/>
 <p> <%= user.getAddress().getZip() %></p><br/>
 
 <h3> Audio samples </h3>
 
<br> <br/>
 <h3> Tags </h3>
 <p> <% List<Tag> tags = new ArrayList<Tag>(((Artist)user).getTag()); %>
<%=tags.get(0).getName() %><br/>
<%=tags.get(1).getName() %><br/>
<%=tags.get(2).getName() %><br/>
</p>
 
 

 </div>
  <div id="text">
 <p><h1> <%= user.getFirstname()%> </h1> </p>
 <h3>Description ;</h3>
 <p><%= ((Artist)user).getDescription()%></p>

 </div>
 
</div>
<div id="pied">Apex Corporation</div>
</div>

</body>
</html>