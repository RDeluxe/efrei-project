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

</tr>
</table>
<div id="searchBox">
<form action="javascript:result();">
    <input type="text" id="txtSearch"  name="txtSearch" alt="Search Criteria" onkeyup="searchSuggest();" autocomplete="off" />
    
    <input id="menubuttonform" type="hidden" onClick="result()"   name="cmdSearch" value="Search" alt="Run Search" />
     
	</form>
	<div id="search_suggest"></div>
</div>

</div>
<div id="contenu">
<div id="add">
<form id="form1" method="post" action="AddEvent">
  <fieldset>
  <legend>Event Info</legend>
   <p><label>Name</label><input type="text" name="name"  size="60" /></p>
	<p><label>Date</label>
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
      &nbsp;
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
      &nbsp;
      <SELECT name="Year">
        <OPTION VALUE="2011">2011</OPTION>
        <OPTION VALUE="2012">2012</OPTION>
        <OPTION VALUE="2013">2013</OPTION>
        <OPTION VALUE="2014">2014</OPTION>
        <OPTION VALUE="2015">2015</OPTION>
      </SELECT>
      </p>
    <p>
     <label>Duration (in days)</label> <input type="text"  name="duration" size="25" />
    </p>
    <p>
     <label>Description</label> <textarea name="description" ></textarea>
    </p>
    </fieldset>
    <fieldset>
		 <legend>Address</legend>
		 <p><label for="street">Street:</label>
		 <input type="text" name="street" size="60" /></p>
		 <p><label for="city">City:</label>
		 <input type="text" name ="city" size="25" /></p>
		 <p><label for="zip">Zip code:</label>
		 <input type="text" name="zip" size="10" /></p>
		 <p><label for="country">Country:</label>
		 <input type="text" name="country" size="25" /></p>
		 </fieldset>
    <br/>
   <input type="submit" value="Submit" /><input type="reset" value="Reset" /></form>
 

</div>

</div>
</div>
</body>
</html>
