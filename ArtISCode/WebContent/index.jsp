<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="controller.*" import="domain.*" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  
 Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.
-->
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
<link rel="stylesheet" type="text/css" href="bx_styles/bx_styles.css" />
<script src="jquery.bxSlider.min.js" type="text/javascript"></script>
<script type="text/javascript"
    src="http://maps.google.com/maps/api/js?libraries=geometry&sensor=true">
</script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">
function GetThis(T, C, U)
{
    var targetUrl = 'http://www.myspace.com/index.cfm?fuseaction=postto&t=' + encodeURIComponent(T)
    + '&c=' + encodeURIComponent(C) + '&u=' + encodeURIComponent(U);
    window.open(targetUrl, 'ptm', 'height=450,width=440').focus();
}
</script>
<script type="text/javascript">
  $(document).ready(function(){
    $('#slider1').bxSlider({
    	controls : false,
    	auto : true,
    	});
    document.getElementById('slider1').setAttribute('style', 'width: 999999px; position: relative; left: -890px;');
    
  });
</script>
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
		Home</td>
		<%
		
			if (sessionlog == null) {
				request.getSession().invalidate();
		%>

		<td id="menubutton" onclick='document.location.href="register.jsp"'>
		Register</td>
		<td id="menubutton" onclick='document.location.href="oauth-demo.jsp"' style="width:150px">Register from Myspace</td>
		<td>
		<input id="login" name="login" type="text" value="login" onfocus='if (this.value=="login") {this.value=""}' onblur='if (this.value=="") {this.value="login"}' width="70" />
		</td>
		<td>
		<input id="pass" name="pass" type="password" value="password" onfocus='if (this.value=="password") {this.value=""}' onblur='if (this.value=="") {this.value="password"}' width="50" />
		</td>
		<td id="menubutton" onclick='$.post("Login", { login:document.getElementById("login").value, pass:document.getElementById("pass").value }, function(data) { document=""; document.write(data);});'>
		Login
		</td>


		<%
			} else {
		%>
		<td id="menubutton" onClick="document.location='news.jsp'">News</td>
		<td id="menubutton" onclick="javascript:displayProfileReq()">Profile</td>
		<td id="menubutton" onClick="document.location='eventCalendar.jsp'">Manage Events</td>
		<td id="menubutton" onClick="document.location='deconnexion.jsp'">Deconnexion</td>
		<td><a href="javascript:GetThis('Tell your friends what are you doing','SUMMARY_GOES_HERE', 'URL_GOES_HERE')">
            <img src="http://cms.myspacecdn.com/cms//ShareOnMySpace/Myspace_btn_ShareOnMyspace.png" border="0" alt="Share on Myspace" /></a></td>
		<%
			}
		%>

	</tr>
</table>
<div id="searchBox">
<form action="javascript:result();"><input type="text"
	id="txtSearch" name="txtSearch" alt="Search Criteria"
	onkeyup="searchSuggest();" autocomplete="off" value="Search" onfocus='if (this.value=="Search") {this.value=""}' onblur='if (this.value=="") {this.value="Search"}' /> <input
	id="menubuttonform" type="hidden" onClick="result()" name="cmdSearch"
	value="Search" alt="Run Search" /></form>
<div id="search_suggest"></div>
</div>



</div>
<% 	controller.Search search = new controller.Search();
	List<Artist> artists = search.searchRandomArtists();
%>

<div id="contenu">

<%
	if (result != null) {
%> <%
 	if (result.equalsIgnoreCase("ko") == true) {
 %>
<p>Wrong Login or Password</p>
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
 <div id="sliderbox">
<ul id="slider1">

<%for (Artist artist : artists) { 
%>
	<li onclick="getProfileReq('<%= artist.getLogin()%>')"  style='margin:0 0 0 0;' >
	<%if (artist.getPhoto()!=null) { %><img src="<%= artist.getPhoto()%>" width="219" height="218"> <%} else { %> <img src="Img/default_big.gif" width="219" height="218"><%} %>
	<h2 style="cursor : pointer;"><%= artist.getFirstname() + " " + artist.getLastname() %></h2>
<% 
	if (artist.getDescription().length()>500) {
%>
		<p><%= artist.getDescription().substring(0, 499) + "..." %></p>
<% } else { %> 
		<p><%= artist.getDescription() %></p>
<% } %>
	</li>
<% } %>
</ul>
</div>
 <div id="left" style="width : 300px;">
<div id="advancedsearch">
<h3>Search for an artist</h3>
<form id="form2" action="javascript:advancedsearch();">
	<p><label for="city">City</label><input name="city" id="city" type="text" style="width : 50%"/></p>
	<p><label for="dist">Distance max</label><input name="dist" id="dist" type="text"style="width : 50%"/></p>
	<p><label for="tag">Tag</label>
	<SELECT id="tag" name="tag1"style="width : 30%">
		<OPTION VALUE="rock">Rock</OPTION>
		<OPTION VALUE="pop">Pop</OPTION>
		<OPTION VALUE="classic">Classic</OPTION>
		<OPTION VALUE="folk">Folk</OPTION>
		<OPTION VALUE="soloist">Soloist</OPTION>
		<OPTION VALUE="band">Band</OPTION>
		<OPTION VALUE="celtic">Celtic</OPTION>
		<OPTION VALUE="hard">Hard rock</OPTION>	
	</SELECT>
	<input type="submit" value="Search">
	
</form>
<div id="pied"></div>
</div>

<object type="application/x-shockwave-flash" data="player/dewplayer-playlist.swf" width="240" height="200" id="dewplayer" name="dewplayer">
	<param name="wmode" value="transparent" />
	<param name="movie" value="dewplayer-playlist.swf" />
	<param name="flashvars" value="xml=TrackList" />
</object>
</div>
<div id="news">
<h3>News</h3>

<% 	DAONews daoN = new DAONews();
	List<News> news = daoN.getLastNews(); 
	for (News n : news) {
		%>

<div id="new">
<h4><%= n.getTitle() %></h4>
<img src="<%= n.getImage() %>"/><p><%= n.getText() %></p>
<div id="pied"></div>
</div>
<% } %>
<div id="pied"></div>
</div>
</div>
<div id="pied"></div>
</div>



</body>
</html>