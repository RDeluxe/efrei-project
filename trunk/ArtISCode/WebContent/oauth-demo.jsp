<%@ page
	import="com.myspace.myspaceid.*,com.myspace.myspaceid.oauth.*,org.json.simple.*,controller.*,domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MySpace Information Confirm</title>
<link href="habillage.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=UTF-8" />
<style type="text/css">
<!--
h1 {
	font-family: cursive;
	font-size: 14pt;
	color:#5F9F9F;
}
-->
</style>
<style type="text/css">
.button {
   border-top: 1px solid #96d1f8;
   background: #65a9d7;
   background: -webkit-gradient(linear, left top, left bottom, from(#3e779d), to(#65a9d7));
   background: -moz-linear-gradient(top, #3e779d, #65a9d7);
   padding: 8.5px 17px;
   -webkit-border-radius: 10px;
   -moz-border-radius: 10px;
   border-radius: 10px;
   -webkit-box-shadow: rgba(0,0,0,1) 0 1px 0;
   -moz-box-shadow: rgba(0,0,0,1) 0 1px 0;
   box-shadow: rgba(0,0,0,1) 0 1px 0;
   text-shadow: rgba(0,0,0,.4) 0 1px 0;
   color: white;
   font-size: 19px;
   font-family: 'Lucida Grande', Helvetica, Arial, Sans-Serif;
   text-decoration: none;
   vertical-align: middle;
   }
.button:hover {
   border-top-color: #28597a;
   background: #28597a;
   color: #ccc;
   }
.button:active {
   border-top-color: #1b435e;
   background: #1b435e;
   }
</style>

</head>
<body>
<div id="page">
	<div id="entete">
		<a href="index.jsp"><img src="Img/banner.jpg" width="950"
			height="100" border=no>
		</a>
	</div>
	
	<div id="menu">
<table id="menutab">
	<tr>
		<td id="menubutton" onClick='document.location.href="index.jsp"'>
		Home</td>


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
	
	<h1>If you click to continue, we will then store your information</h1>
	
	<%
		String callback = request.getParameter("callback");

		String key = "18bed0f4247a4f79bc9941bfed5b534c";
		String secret = "2e8ebdafbef844a5929086e659e4188c";
		String aid = "";

		// If callback parameter not supplied, redirect to go do login.
		if (callback == null || callback.equals("")) {

			request.getSession().setAttribute("accessTokenKey", null);
			request.getSession().setAttribute("accessTokenSecret", null);

			OffsiteContext c1 = new OffsiteContext(key, secret);

			OAuthToken requestToken = c1.getRequestToken(request
					.getRequestURL() + "?callback=true");
			request.getSession().setAttribute("requestTokenSecret",
					requestToken.getSecret());
			System.out.println(requestToken);
			String authorizeUrl = c1.getAuthorizationURL(requestToken);
			response.sendRedirect(authorizeUrl);
		} else {
			// Check if already authorized
			String accessTokenKey = (String) request.getSession()
					.getAttribute("accessTokenKey");
			String accessTokenSecret = (String) request.getSession()
					.getAttribute("accessTokenSecret");

			if (accessTokenKey == null || accessTokenSecret == null) {
				String requestTokenKey = request
						.getParameter("oauth_token");
				String oauthVerifier = request
						.getParameter("oauth_verifier");
				String requestTokenSecret = (String) request.getSession()
						.getAttribute("requestTokenSecret");

				OffsiteContext c2 = new OffsiteContext(key, secret,
						requestTokenKey, requestTokenSecret);
				OAuthToken accessToken = c2.getAccessToken(oauthVerifier); // Side effect: sets access token in OffsiteContext object

				accessTokenKey = accessToken.getKey();
				accessTokenSecret = accessToken.getSecret();
				aid = c2.getUserId();

				request.getSession().setAttribute("accessTokenKey",
						accessTokenKey);
				request.getSession().setAttribute("accessTokenSecret",
						accessTokenSecret);
			}

			// Since we have the access token from before, set it into the OffsiteContext object
			OffsiteContext c = new OffsiteContext(key, secret);
			c.setAccessToken(new OAuthToken(accessTokenKey,
					accessTokenSecret));

			// Fetch and display user ID.
			String id = c.getUserId();
			//out.println("<br/><br/>User id = " + id + "<br/>");

			// Fetch and display user's name.
			RestV1 r = new RestV1(c);

			JSONObject profile = r.getProfile(id);
			JSONObject profile_exr = r.getProfile(id, "full");
			String activity = r.getActivitiesAtom(id);
			String activity_friend = r.getFriendsActivitiesAtom(id);
			JSONObject friends = r.getFriends(id);
			JSONObject photo = r.getPhotos(id, -1, -1);

			//JSONObject photos=r.getPhotos(id,-1,-1);
			//out.println("qsd"+photos.get("")+"sdf");

			JSONObject obj = (JSONObject) friends.get("user");
			//String name=obj.get("name")+"";
			//System.out.println(aid);
	%>


	<form name="input" action="registerMySpace.jsp" method="get">
		<p>
			<input type="hidden" name="name" value=<%=obj.get("name")%> />
		</p>
		<p>
			<input type="hidden" name="city" value=<%=profile.get("city")%> />
		</p>
		<p>
			<input type="hidden" name="country"
				value=<%=profile.get("country")%> />
		</p>
		<p>
			<input type="hidden" name="postalcode"
				value=<%=profile.get("postalcode")%> />
		</p>
		<p>
			<input type="hidden" name="region" value=<%=profile.get("region")%> />
		</p>
		<p>
			<input type="hidden" name="age" value=<%=profile.get("age")%> />
		</p>
		<input class=button type="submit" value="Continue" />
		<input class=button type="button" value="Cansel" name="cansel" onClick="document.location='index.jsp'">
	</form>



	<%
		}
	%>

</div>

</body>

</html>