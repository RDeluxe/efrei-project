<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Register</title>
<link href="habillage.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="page">
<%@ page  import="java.sql.*"%>
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
<div id="kind">
<p>
<a href="register.jsp">User Register</a> &nbsp; &nbsp; &nbsp; &nbsp;   
<a href="registerA.jsp">Artist Register</a> &nbsp; &nbsp; &nbsp; &nbsp;     
<a href="registerP.jsp">Professional Register</a>
</p>
</div>

  
  <form method="post" action="Register">
  <fieldset>
  <legend>Registration page for User</legend>
    <fieldset>
    <legend>Basic Info</legend><br/>
    Kind of account:<br/>
    <input type="text" name="kind" value="User" readonly="readonly" /><br/>
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
    <input type="text" name="login" size="25" /><br/>
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
    <fieldset>
    <legend>Your Professionnal Info</legend><br/>
    <label for="description">Your Description</label><br />
       <textarea name="description" id="description"></textarea><br/>
       </fieldset>
       
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