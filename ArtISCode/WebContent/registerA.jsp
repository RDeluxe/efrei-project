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
  <form method="post" action="Login">
  <label for="login">Login : </label> <input id="login" name="login" type="text" width="100" />
  <label for="pass">Password : </label> <input id="pass" name="pass" type="password" width="80" />
  
  
  <input id="send"  type="submit" value="log in"/>

 
  </form>
  
  
  <%}else{%>
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
<div id="kind">
<p>
<a href="register.jsp">User Register</a> &nbsp; &nbsp; &nbsp; &nbsp;   
<a href="registerA.jsp">Artist Register</a> &nbsp; &nbsp; &nbsp; &nbsp;     
<a href="registerP.jsp">Professional Register</a>
</p>
</div>

  
  <form method="post" action="Register">
  <fieldset>
  <legend>Registration page for Artist</legend>
    <fieldset>
    <legend>Basic Info</legend><br/>
    Kind of account:<br/>
    <input type="text" name="kind" value="Artist" readonly="readonly" /><br/>
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
    <legend>Artist Information</legend><br/>
    <label for="description">Your Description</label><br />
       <textarea name="description" id="description"></textarea><br/>
       Choose Tags (3 maximum):<br/>
       <SELECT name="tag1">
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