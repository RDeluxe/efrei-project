<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="controller.*" import="domain.Notification" import="java.util.Set" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
<link href="habillage.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="suggest.js"></script>
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="uploadify/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="uploadify/swfobject.js"></script>
<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>
<script src="jquery-1.5.2.js"></script>

</head>
<body>
			<%
			//checking session
			String result = (String) request.getAttribute("result");
			if ((result != null) && (result.equalsIgnoreCase("ok"))) {

				String login = request.getParameter("login");
				String kind = request.getParameter("kind");
				session.setAttribute("login", login);
				session.setAttribute("kind", kind);
			}
			String sessionlog = (String) session.getAttribute("login");
			 %>
<div id="notifications" onmouseover="document.getElementById('close').setAttribute('style', 'display:block');" onmouseout="document.getElementById('close').setAttribute('style', 'display:none');"><%
		
			if (sessionlog != null) {
				controller.Search searchEngine = new controller.Search();
				Set<Notification> nots = searchEngine.SearchByLogin(sessionlog).getMessages();
				if (nots!= null) {
					for (Notification n : nots) {
						%>
						<div id="not">
						<a class="notiflink" onclick="javascript:closeNotification('<%= sessionlog%>','<%= n.getId()%>');this.parentNode.setAttribute('style', 'display:none');return false;" href="#"><img src="./Img/growlclose.gif"/></a>
						<h3>Notification</h6>
						<a class="notiflink" onclick="javascript:closeNotification('<%= sessionlog%>','<%= n.getId()%>');" href="eventCalendar.jsp"><%= n.getMessage() %></a>
						</div>
						
						<%
					}
					%>
					<div id="close" style="display : none"><a href="#" onclick="javascript:closeAllNotification('<%= sessionlog%>');return false;">close all notification</a></div>
					<%
				}
			}
		%></div>
<div id="page">

<div id="entete"><a href="index.jsp"><img src="Img/banner.jpg"
	width="950" height="100" border=no></a></div>

<div id="menu">
<table id="menutab">
	<tr>
		<td id="menubutton" onClick='document.location.href="index.jsp"'>
		Accueil</td>
		<%
		
			if (sessionlog == null) {
				request.getSession().invalidate();
		%>

		<td id="menubutton" onclick='document.location.href="register.jsp"'>
		Register</td>
		<td><a href="oauth-demo.jsp"><img border="0" src="Img/Blue_150_Loginwithmyspaceid.png" /></a></td>
		<td>
		<input id="login" name="login" type="text" value="login" onfocus='if (this.value=="login") {this.value=""}' onblur='if (this.value=="") {this.value="login"}' width="70" />
		</td>
		<td>
		<input id="pass" name="pass" type="password" value="password" onfocus='if (this.value=="password") {this.value=""}' onblur='if (this.value=="") {this.value="password"}' width="50" />
		</td>
		<td id="menubutton" onclick='$.post("Login", { login:document.getElementById("login").value, pass:document.getElementById("pass").value }, function(data) { document.documentElement.innerHTML=data;});'>
		Login
		</td>


		<%
			} else {
		%>
		
		<td id="menubutton" onclick="javascript:displayProfileReq()">Profile</td>
		<td id="menubutton" onClick="document.location='eventCalendar.jsp'">Manage Events</td>
		<td id="menubutton" onClick="document.location='deconnexion.jsp'">Deconnexion</td>
		<%
			}
		%>

	</tr>
</table>
<div id="searchBox">
<form action="javascript:result();"><input type="text"
	id="txtSearch" name="txtSearch" alt="Search Criteria"
	onkeyup="searchSuggest();" autocomplete="off" /> <input
	id="menubuttonform" type="hidden" onClick="result()" name="cmdSearch"
	value="Search" alt="Run Search" /></form>
<div id="search_suggest"></div>
</div>



</div>
<div id="contenu">
<%
	if (result != null) {
%> <%
 	if (result.equalsIgnoreCase("ko") == true) {
 %>
<p>No user with this Login</p>
<%
	}
%> <%
 	if (result.equalsIgnoreCase("upok") == true) {
 %>
<p>Profile correctly updated !</p>
<%
	}
%> <%
 	}
 %>
</div>
<div id="pied"></div>
</div>



</body>
</html>