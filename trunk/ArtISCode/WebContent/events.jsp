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
<div id="table">
<table id="events">
  
  <thead>
  <tr>
       <th>Name</th>
       <th>Date</th>
       <th>Duration</th>
       <th>Participants</th>
       <th>Update Event</th>
       <th>Delete Event</th>
   </tr>
   </thead>
   <tbody>
   <% List<Event> events = (List<Event>) request.getAttribute("events");
   for(Iterator it=events.iterator(); it.hasNext();)
   {
       Event event = (Event) it.next();
       Set<Participant> participants = event.getArtists();
       %>
       <tr>
       <td align=center><%= event.getName() %></td>
       <td align=center><%= event.getDate()%></td>
       <td align=center><%= event.getDuration() %></td>
       <td align=center><% for(Iterator it2=participants.iterator(); it2.hasNext();)
       {  Participant participant = (Participant) it2.next(); %>
       <%= participant.getMember().getFirstname() %>
       &nbsp
       <%} %></td>
       <td id="menubutton" onclick="document.location='EventPage'" align=center> Update </td>
       <td id="menubutton" onclick="document.location='EventPage'" align=center> Delete </td>
       </tr>
       
   <% }%>
   

   </tbody>

</table>
<br/>
<br/>
<input type="button" value="Add en Event" onclick="document.location='addEvent.jsp'">

</div>
 
</div>
<div id="pied">
</div>
</div>

</body>
</html>