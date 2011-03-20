<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Register</title>
<script type="text/javascript" src="suggest.js"></script>
<script type="text/javascript">
	function checkLoginBox() {
		if (searchReq.readyState == 4 || searchReq.readyState == 0) {
			var str = escape(document.getElementById('loginBox').value);
			searchReq.open("GET", 'CheckLogin?keyword=' + str, true);
			searchReq.onreadystatechange = handleCheckLoginBox;
			searchReq.send(null);
		}
	}
	
	function handleCheckLoginBox() {
		if (searchReq.readyState == 4) {
			var ss = document.getElementById('resultats');
			ss.innerHTML = '';
			var str = searchReq.responseText;
			var suggest = str;
			ss.innerHTML = suggest;
		}
	}
</script>
<link href="habillage.css" rel="stylesheet" type="text/css" />
</head>
<body>
<body>
<div id="page">

<div id="entete"><a href="index.jsp"><img src="banner.jpg" width="947" height="186" border=no></a></div>
<div id="menu">
 <div id="loginbox">
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
  if(sessionlog==null){%>
  
  <form method="post" action="Login">
  <label for="login">Login : </label> <input id="login" name="login" type="text" width="100" />
  <label for="pass">Password : </label> <input id="pass" name="pass" type="password" width="80" />
  
  <label for="kind">Kind :</label> <SELECT name="kind">
		<OPTION VALUE="1">User</OPTION>
		<OPTION VALUE="2">Artist</OPTION>
		<OPTION VALUE="3">Pro</OPTION>
        </SELECT>
  <br/>
  <a href="register.jsp" style="font-size:10px; padding-top:10px; margin-left:16px; margin-right:20px;  font-style:italic"> register </a>
  
 <input id="send"  type="submit" value="log in"/>
  </form>
  
  <%request.getSession().invalidate();
  }else{%>
  <p> Hi, <%= session.getAttribute("login")  %></p>
  <p> Modify your<a href="Profile" >profile</a>  </p>
  <p> <input type="button" value="Deconnexion" onClick="document.location='deconnexion.jsp'"></p>
  <%} %>
  <%if(result!=null){ %>
  <%if(result.equalsIgnoreCase("ko")==true){ %>
  <p> No user with this Login</p>
  <%} %>
  <%if(result.equalsIgnoreCase("upok")==true){ %>
  <p> Profile correctly updated !</p>
  <%} %>
  <%} %>
  </div> 
  
  <div id="menubox">
 
	<form>
    <input type="text" id="txtSearch"  name="txtSearch" alt="Search Criteria" onkeyup="searchSuggest();" autocomplete="off" />
    <input type="button" onClick="result()"   name="cmdSearch" value="Search" alt="Run Search" /><br />
    <div id="search_suggest"></div>
     <div id="result"></div>
	</form>
   


  </div>
</div>
<div id="contenu">
<div id="kind">
<p>
<a href="register.jsp">User Register</a> &nbsp; &nbsp; &nbsp; &nbsp;   
<a href="registerA.jsp">Artist Register</a> &nbsp; &nbsp; &nbsp; &nbsp;     
<a href="registerP.jsp">Professional Register</a>
</p>
</div>

  
  <form method="post" action="Register">
  <fieldset>
  <legend>Registration page for User</legend>
    <fieldset>
    <legend>Basic Info</legend><br/>
    Kind of account:<br/>
    <input type="text" name="kind" value="User" readonly="readonly" /><br/>
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
    Your login:<br/>
    <input type="text" id="loginBox" name="login" size="25" /><br/>
    Your password:<br/>
    <input type="password" name="pass1" size="25" /><br/>
    Password Check:<br/>
    <input type="password" name="pass2" size="25" /><br/>
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
    <p></p>
    <input type="submit"
value="Submit" />
    <input type="reset"
value="Reset" />
 </fieldset>
  </form>
 
</div>
<div id="pied">
  
</div>
</div>
</body>
</html>