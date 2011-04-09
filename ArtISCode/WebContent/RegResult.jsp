<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  
 Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.
-->
<html>
<head>
<META HTTP-EQUIV="Refresh" CONTENT="2;URL=index.jsp">
<title>Index</title>
<link href="habillage.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="suggest.js"></script>
  <script type="text/javascript" src="script.js"></script>
 
</head>
<body>
<div id="page">

<div id="entete"><a href="index.jsp"><img src="Img/banner.jpg" width="950" height="100" border=no></a></div>


<div id="contenu">
  <%String resultreg = (String) request.getAttribute("resultreg");
  if(resultreg.equalsIgnoreCase("ok"))
	  {%>
	  Welcome, <%=request.getAttribute("login") %>. You can now login
	  <%}else if(resultreg.equalsIgnoreCase("ko"))
			  {%>
			  Registering failed. Password check failed or/and another user with the same login. Please try again (use the forward function of your browser to get your information)
<%} %>
</div>

<div id="pied">
</div>
</div>
</body>
</html>