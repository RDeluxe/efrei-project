<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="controller.*" import="domain.*" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News</title>
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
 <jsp:forward page="login.jsp">
<jsp:param name="msg" value="msg" />
</jsp:forward>
		<%
			} else {
		%>
		<td id="menubutton" onClick="document.location='news.jsp'">News</td>
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
 <div id="sliderbox">
<ul id="slider1">

<%for (Artist artist : artists) { 
%>
	<li onclick="getProfileReq('<%= artist.getLogin()%>')"  style='margin:0 0 0 0;' >
	<img src="<%= artist.getPhoto()%>" width="219" height="218">
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
<div id="news2">
<h3>News</h3>

<% 	DAONews daoN = new DAONews();
	List<News> news = daoN.getAllNews(); 
	for (News n : news) {
		%>

<div id="new2">
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