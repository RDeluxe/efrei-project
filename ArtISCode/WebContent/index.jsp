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

</head>
<body>
			<%
			//checking session
			String result = (String) request.getAttribute("result");
			System.out.println(result);
			if ((result != null) && (result.equalsIgnoreCase("ok"))) {

				String login = request.getParameter("login");
				String kind = request.getParameter("kind");
				session.setAttribute("login", login);
				session.setAttribute("kind", kind);
				System.out.println(kind);
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
						<a onclick="javascript:closeNotification('<%= sessionlog%>','<%= n.getId()%>');this.parentNode.setAttribute('style', 'display:none');return false;" href="#"><img src="./Img/growlclose.gif"/></a>
						<h3>Notification</h6>
						<%= n.getMessage() %>
						</div>
						
						<%
					}
					%>
					<div id="close" style="display : none"><a href="#" onclick="javascript:closeAllNotification('<%= sessionlog%>');return false;">close all notification</a></div>
					<%
				} else {
					System.out.println("Pas de messages");
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
		<td><a href="oauth-demo.jsp"><img border="0"
			src="Img/Blue_150_Loginwithmyspaceid.png" /></a></td>
		<td>

		<form method="post" action="Login"><label id="menulabel"
			for="login">Login : </label> <input id="login" name="login"
			type="text" width="70" /> <label id="menulabel" for="pass">Password
		: </label> <input id="pass" name="pass" type="password" width="50" /> <input
			id="menubuttonform" type="submit" value="log in" /></form>


		<%
			} else {
		%>
		
		<td id="menubutton" onclick="javascript:displayProfileReq()">
		Profile</td>
		<td id="menubutton" onClick="document.location='EventPage'">
		Manage Events</td>
		<td id="menubutton" onClick="document.location='deconnexion.jsp'">
		Deconnexion</td>
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