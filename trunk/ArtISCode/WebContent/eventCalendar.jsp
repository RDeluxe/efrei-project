<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="habillage.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="suggest.js"></script>
  <script type="text/javascript" src="script.js"></script>
<link rel='stylesheet' type='text/css' href='./fullcalendar/fullcalendar.css' />
<link rel='stylesheet' type='text/css' href='./fullcalendar/fullcalendar.print.css' media='print' />
<script type='text/javascript' src='./jquery/jquery-1.5.min.js'></script>
<script type='text/javascript' src='./jquery/jquery-ui-1.8.9.custom.min.js'></script>
<script type='text/javascript' src='./fullcalendar/fullcalendar.min.js'></script>
<script type='text/javascript'>

	$(document).ready(function() {
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			editable: false,
			eventSources: [{
			                   url: 'EventJson?kind=waiting',
			                   color: 'orange',
			                   textColor: 'black'
			               },
			               {
			                   url: 'EventJson?kind=ok',
			                   color: 'green',
			                   textColor: 'black'
			               },
			               {
			                   url: 'EventJson?kind=no',
			                   color: 'red',
			                   textColor: 'black'
			               },
			               {
			                   url: 'EventJson?kind=yours',
			                   color: 'blue',
			                   textColor: 'white'
			               }]
		});
		
	});

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

<jsp:forward page="login.jsp">
<jsp:param name="msg" value="msg" />
</jsp:forward>
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
<div id="calendar"></div>
<br/>
<input type="button" value="Add en Event" onclick="document.location='addEvent.jsp'">
<div id="pied">
</div>
</div>
</body>
</html>