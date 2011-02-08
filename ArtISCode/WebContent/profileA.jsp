<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Profile</title>
<link href="habillage.css" rel="stylesheet" type="text/css" />
</head>
<body>
<body>
<div id="page">
<%@ page import="domain.*" %>
<%@ page import="java.util.*" %>
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
  <p> Modify your<a href="GetProfile" >profile</a>  </p>
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
  <% User user = (User) request.getAttribute("user"); %>
  <form method="post" action="Profile">
  <fieldset>
  <legend>Registration page for User</legend>
    <fieldset>
    <legend>Basic Info</legend><br/>
    Kind of account:<br/>
    <input type="text" name="kind" value="User" readonly="readonly" /><br/>
    Firstname:<br/>
    <input type="text" name="firstname" value="<%=user.getFirstname() %>" size="25" /><br/>
    Lastname:<br/>
    <input type="text" name="lastname" value="<%=user.getLastname() %>" size="25" /><br/>
    Mail:<br/>
    <input type="text" name="mail" value="<%=user.getEmail() %>" /><br/>
    <br/>
    </fieldset>
    <fieldset>
    <legend>Connection Info</legend><br/>
    Your login:<br/>
    <input type="text" name="login" value="<%=user.getLogin() %>" size="25" readonly="readonly" /><br/>
    Your password:<br/>
    <input type="password" name="pass1" value="<%=user.getPassword() %>" size="25" /><br/>
    Password Check:<br/>
    <input type="password" name="pass2" value="<%=user.getPassword() %>" size="25" /><br/>
    </fieldset>
    <fieldset>
    <legend>Your Address</legend><br/>
    Street:<br/>
    <input type="text" name="street" value="<%=user.getAddress().getStreet() %>" size="60" /><br/>
    City:<br/>
    <input type="text" name ="city" value="<%=user.getAddress().getCity() %>"  size="25" /><br/>
    Zip code:<br/>
    <input type="text" name="zip" value="<%=user.getAddress().getZip() %>"  size="10" /><br/>
    Country:<br/>
    <input type="text" name="country" value="<%=user.getAddress().getCountry() %>"  size="25" /><br/>
    </fieldset>
    <fieldset>
    <legend>Artist Information</legend><br/>
    <label for="description">Your Description</label><br />
       <textarea name="description" id="description"><%=((Artist)user).getDescription()%></textarea><br/>
       Choose Tags (3 maximum):<br/>
       <%
       List<Tag> tags = new ArrayList<Tag>(((Artist)user).getTag());
       %>
       <SELECT name="tag1">
        <OPTION VALUE="<%=tags.get(0).getName() %>"><%=tags.get(0).getName() %></OPTION>
		<OPTION VALUE="rock">Rock</OPTION>
		<OPTION VALUE="pop">Pop</OPTION>
		<OPTION VALUE="classic">Classic</OPTION>
		<OPTION VALUE="folk">Folk</OPTION>
		<OPTION VALUE="soloist">Soloist</OPTION>	
		<OPTION VALUE="band">Band</OPTION>	
		<OPTION VALUE="celtic">Celtic</OPTION>	
		<OPTION VALUE="hard">Hard rock</OPTION>		
	</SELECT><br/>
		<SELECT name="tag2">
		<OPTION VALUE="<%=tags.get(1).getName() %>"><%=tags.get(1).getName() %></OPTION>
		<OPTION VALUE="rock">Rock</OPTION>
		<OPTION VALUE="pop">Pop</OPTION>
		<OPTION VALUE="classic">Classic</OPTION>
		<OPTION VALUE="folk">Folk</OPTION>
		<OPTION VALUE="soloist">Soloist</OPTION>	
		<OPTION VALUE="band">Band</OPTION>	
		<OPTION VALUE="celtic">Celtic</OPTION>	
		<OPTION VALUE="hard">Hard rock</OPTION>	
	</SELECT>
 		<SELECT name="tag3"><br/>
 		<OPTION VALUE="<%=tags.get(2).getName() %>"><%=tags.get(2).getName() %></OPTION>
		<OPTION VALUE="rock">Rock</OPTION>
		<OPTION VALUE="pop">Pop</OPTION>
		<OPTION VALUE="classic">Classic</OPTION>
		<OPTION VALUE="folk">Folk</OPTION>
		<OPTION VALUE="soloist">Soloist</OPTION>	
		<OPTION VALUE="band">Band</OPTION>	
		<OPTION VALUE="celtic">Celtic</OPTION>	
		<OPTION VALUE="hard">Hard rock</OPTION>	
	</SELECT><br>
    </fieldset>
    <br/>
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