<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="domain.*" %>
    <%@ page import="java.util.*" %>
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
  <a href="oauth-demo.jsp"><img border="0" src="Img/Blue_150_Loginwithmyspaceid.png"/></a>
  </td>
  <td>
  
  <form method="post" action="Login">
  <label id="menulabel" for="login">Login : </label> <input id="login" name="login" type="text" width="70" />
  <label id="menulabel" for="pass">Password : </label> <input id="pass" name="pass" type="password" width="50" />
  
  
  <input id="menubuttonform" type="submit" value="log in"/>

 
  </form>
  
  
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
  
  <td>
  	<form action="javascript:result();">
    <input type="text" id="txtSearch"  name="txtSearch" alt="Search Criteria" onkeyup="searchSuggest();" autocomplete="off" />
    
    <input id="menubuttonform" type="hidden" onClick="result()"   name="cmdSearch" value="Search" alt="Run Search" />
     
	</form>
  
</td>

</tr>
</table>
<div id="search_suggest"></div>
 

</div>
<div id="contenu">
<div id="add">
<form id="addEvent" method="post" action="AddEvent">
  <fieldset>
  <legend>Add en Event</legend>
    <fieldset>
    <legend>Basic Info</legend><br/>
    Date<br/>
     <SELECT name="Month">
		<OPTION VALUE="01">01</OPTION>
		<OPTION VALUE="02">02</OPTION>
        <OPTION VALUE="03">03</OPTION>
        <OPTION VALUE="04">04</OPTION>
        <OPTION VALUE="05">05</OPTION>
        <OPTION VALUE="06">06</OPTION>
        <OPTION VALUE="07">07</OPTION>
        <OPTION VALUE="08">08</OPTION>
        <OPTION VALUE="09">09</OPTION>
        <OPTION VALUE="10">10</OPTION>
        <OPTION VALUE="11">11</OPTION>
        <OPTION VALUE="12">12</OPTION>
	</SELECT>
    <SELECT name="Day">
		<OPTION VALUE="01">01</OPTION>
		<OPTION VALUE="02">02</OPTION>
        <OPTION VALUE="03">03</OPTION>
        <OPTION VALUE="04">04</OPTION>
        <OPTION VALUE="05">05</OPTION>
        <OPTION VALUE="06">06</OPTION>
        <OPTION VALUE="07">07</OPTION>
        <OPTION VALUE="08">08</OPTION>
        <OPTION VALUE="09">09</OPTION>
        <OPTION VALUE="10">10</OPTION>
        <OPTION VALUE="11">11</OPTION>
        <OPTION VALUE="12">12</OPTION>
        <OPTION VALUE="13">13</OPTION>
		<OPTION VALUE="14">14</OPTION>
        <OPTION VALUE="15">15</OPTION>
        <OPTION VALUE="16">16</OPTION>
        <OPTION VALUE="17">17</OPTION>
        <OPTION VALUE="18">18</OPTION>
        <OPTION VALUE="19">19</OPTION>
        <OPTION VALUE="20">20</OPTION>
        <OPTION VALUE="21">21</OPTION>
        <OPTION VALUE="22">22</OPTION>
        <OPTION VALUE="23">23</OPTION>
        <OPTION VALUE="24">24</OPTION>
        <OPTION VALUE="25">25</OPTION>
		<OPTION VALUE="26">26</OPTION>
        <OPTION VALUE="27">27</OPTION>
        <OPTION VALUE="28">28</OPTION>
        <OPTION VALUE="29">29</OPTION>
        <OPTION VALUE="30">30</OPTION>
        <OPTION VALUE="31">31</OPTION>
	</SELECT>
    <SELECT name="Year">
		<OPTION VALUE="User" onclick="javascript:registerUser();">User</OPTION>
		<OPTION VALUE="Artist" onclick="javascript:registerArtist();">Artist</OPTION>
		<OPTION VALUE="Professional" onclick="">Professional</OPTION>	
	</SELECT>
    <br/>
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
    <label for="login">Your login:</label>
    <input type="text" id="loginBox" name="login" size="25" /><div id="loginBoxTip" style="width:250px"></div><br/>
    <label for="pass1">Your password:</label>
    <input type="password" id="pass1" name="pass1" size="25" /><div id="pass1Tip" style="width:250px"></div><br/>
    <label for="pass2">Password Check:</label>
    <input type="password" id="pass2" name="pass2" size="25" /><div id="pass2Tip" style="width:250px"></div><br/>
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
    <div id="kindform"></div>
    <p></p>
    <input type="submit"
value="Submit" />
    <input type="reset"
value="Reset" />
 </fieldset>
  </form>
 

</div>

</div>
</div>
</body>
</html>
