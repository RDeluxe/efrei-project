<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="domain.*" %>
    <%@ page import="controller.*" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="habillage.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="suggest.js"></script>
  <script type="text/javascript" src="script.js"></script>
  <script src="jquery-1.5.2.js" type="text/javascript"></script>
 <script type="text/javascript"
    src="http://maps.google.com/maps/api/js?libraries=geometry&sensor=true">
</script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<title>Manage Event</title>
</head>
<%String eventid = (String) request.getParameter("eventid"); 
String login = (String) request.getSession().getAttribute("login");
controller.Search search = new controller.Search();
SearchEventEngine searchE = new SearchEventEngine();
User u = search.SearchByLogin(login);
Set<Event> events = u.getEvents();
Event event = searchE.searchById(Long.parseLong(eventid)); 
	
%>
<body onload="initializeMap();calcRoute('<%= u.getAddress().toString()%>', '<%=event.getAddress().toString()%>');">
<div id="page">

<div id="entete"><a href="index.jsp"><img src="Img/banner.jpg" width="950" height="100" border=no></a></div>

<div id="menu">
<table id="menutab">
<tr>
  <td id="menubutton" onClick='document.location.href="index.jsp"'>
  	Accueil
  </td>
  <%
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
<div id="table">
 <% 
	if (events.contains(event)) {
 %>
<fieldset>
<legend>Modify the event</legend>
<table id="events" border="1">
  
  <thead>
  <tr>
       <th>Name</th>
       <th>Date</th>
       <th>Duration</th>
       <th>Participants OK</th>
       <th>Participants WAITING</th>
       <th>Participants NOT PRESENT</th>
   </tr>
   </thead>
   <tbody>
   		<%
       Set<Participant> participants = event.getArtists();
       %>
       <tr>
       <td align=center><%= event.getName() %></td>
       <td align=center><%= event.getDate()%></td>
       <td align=center><%= event.getDuration() %></td>
       <td align=left><% System.out.println(participants); if (participants !=null) { %><ul type="none"> <%for(Iterator it2=participants.iterator(); it2.hasNext();)
       {  Participant participant = (Participant) it2.next(); if (participant.getArtistState().equalsIgnoreCase("ok")) {%>
       <li> <a onclick="getProfileReq('<%= participant.getMember().getLogin() %>')" href="javascript:"> <%= participant.getMember().getLogin() %></a> <a class="notiflink" onclick="javascript:removeArtist('<%= participant.getMember().getLogin() %>', '<%= event.getId() %>');this.parentNode.setAttribute('style', 'display:none');return false;" href="#"><img src="./Img/croix_rouge.png"/></a></li>
       <%} } %></ul><% } %></td>
       <td align=left><% System.out.println(participants); if (participants !=null) { %><ul type="none"> <%for(Iterator it2=participants.iterator(); it2.hasNext();)
       {  Participant participant = (Participant) it2.next(); if (participant.getArtistState().equalsIgnoreCase("waiting")) {%>
       <li> <a onclick="getProfileReq('<%= participant.getMember().getLogin() %>')" href="javascript:"> <%= participant.getMember().getLogin() %></a> <a class="notiflink" onclick="javascript:removeArtist('<%= participant.getMember().getLogin() %>', '<%= event.getId() %>');this.parentNode.setAttribute('style', 'display:none');return false;" href="#"><img src="./Img/croix_rouge.png"/></a> </li>
       <%}} %></ul><% } %></td>
       <td align=left><% System.out.println(participants); if (participants !=null) { %><ul type="none"> <%for(Iterator it2=participants.iterator(); it2.hasNext();)
       {  Participant participant = (Participant) it2.next(); if (participant.getArtistState().equalsIgnoreCase("cancel") || participant.getArtistState().equalsIgnoreCase("no")) {%>
       <li> <a onclick="getProfileReq('<%= participant.getMember().getLogin() %>')" href="javascript:"> <%= participant.getMember().getLogin() %></a> <a class="notiflink" onclick="javascript:removeArtist('<%= participant.getMember().getLogin() %>', '<%= event.getId() %>');this.parentNode.setAttribute('style', 'display:none');return false;" href="#"><img src="./Img/croix_rouge.png"/></a> </li>
       <%}} %></ul><% } %></td>
       <td id="eventbutton" onclick="document.location.href='UpdateEvent?event=<%= event.getId() %>'" align=center> Update </td>
       <td id="eventbutton" onclick="document.location.href='DeleteEvent?event=<%= event.getId() %>'" align=center> Delete </td>
       </tr>
   </tbody>

</table>
</fieldset>
<%} else {
	Artist a = (Artist) u;
	Set<Participant> parts = a.getParticipants();
	Iterator<Participant> it = parts.iterator();
	Participant part = null;
	boolean stop = false;
	while (it.hasNext() && !stop) {
		part = it.next();
		if (part.getEvent().getId()==event.getId()) stop = true;
	}
	Address artistA = a.getAddress();
	Address eventA = event.getAddress();
 %>
<fieldset>
<legend>Modify the event</legend>
<table id="events">
  
  <thead>
  <tr>
       <th>Name</th>
       <th>Date</th>
       <th>Duration</th>
       <th>Your Status</th>
       <th>Update</th>
   </tr>
   </thead>
   <tbody>
       <tr>
       <td align=center><%= event.getName() %></td>
       <td align=center><%= event.getDate()%></td>
       <td align=center><%= event.getDuration() %></td>
       <td align=center>
       		<select id="artist_status<%= event.getId() %>" name="artist_status<%= event.getName() %>">
       			<option value="<%= part.getArtistState() %>"><%= part.getArtistState() %></option>
       			<option value="WAITING">WAITING</option>
       			<option value="OK">OK</option>
       			<option value="CANCEL">CANCEL</option>
       			<option value="NO">NO</option>
       		</select></td>
       <td id="eventbutton" onclick="javascript:updateEvent('<%= event.getId() %>');" align=center>Update</td>
       </tr>
	</tbody>

</table>
</fieldset>

<br/>
<% if (request.getAttribute("updatedone") != null) { %>
<h4>Update Done !!!!</h4>
<%} %>
</div>
<script type="text/javascript">
calcRoute('<%=eventA.toString()%>', '<%=artistA.toString()%>');
 </script>
<div id="map_container"><div id="map_canvas"></div></div>
<%} %>
</div>
<div id="pied">
</div>

</div>
</body>
</html>