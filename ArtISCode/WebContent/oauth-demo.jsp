<%@ page import="com.myspace.myspaceid.*,com.myspace.myspaceid.oauth.*,org.json.simple.*,controller.*,domain.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>MySpaceID OAuth Sample</title>
	<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=UTF-8" />
	
</head>
<body>
<img src="Img/mdp_logo.jpg"/><br>
<%
  String callback = request.getParameter("callback");

  String key = "18bed0f4247a4f79bc9941bfed5b534c";
  String secret = "2e8ebdafbef844a5929086e659e4188c";
  String aid="";

  // If callback parameter not supplied, redirect to go do login.
  if (callback == null || callback.equals("")) {

	request.getSession().setAttribute("accessTokenKey", null);
	request.getSession().setAttribute("accessTokenSecret", null);

	OffsiteContext c1 = new OffsiteContext(key, secret);
	
	OAuthToken requestToken = c1.getRequestToken(request.getRequestURL() + "?callback=true");
	request.getSession().setAttribute("requestTokenSecret", requestToken.getSecret());
	System.out.println(requestToken);
	String authorizeUrl = c1.getAuthorizationURL(requestToken);
	response.sendRedirect(authorizeUrl);
  }
  else {
	// Check if already authorized
	String accessTokenKey = (String) request.getSession().getAttribute("accessTokenKey");
	String accessTokenSecret = (String) request.getSession().getAttribute("accessTokenSecret");

	if (accessTokenKey == null || accessTokenSecret == null) {
		String requestTokenKey = request.getParameter("oauth_token");
		String oauthVerifier = request.getParameter("oauth_verifier");
		String requestTokenSecret = (String) request.getSession().getAttribute("requestTokenSecret");
	
		OffsiteContext c2 = new OffsiteContext(key, secret, requestTokenKey, requestTokenSecret);
		OAuthToken accessToken = c2.getAccessToken(oauthVerifier); // Side effect: sets access token in OffsiteContext object
		
		accessTokenKey = accessToken.getKey();
		accessTokenSecret = accessToken.getSecret();
		aid=c2.getUserId();
		
		request.getSession().setAttribute("accessTokenKey", accessTokenKey);
		request.getSession().setAttribute("accessTokenSecret", accessTokenSecret);
	}

	// Since we have the access token from before, set it into the OffsiteContext object
	OffsiteContext c = new OffsiteContext(key, secret);
	c.setAccessToken(new OAuthToken(accessTokenKey, accessTokenSecret));

	// Fetch and display user ID.
	String id = c.getUserId();
	//out.println("<br/><br/>User id = " + id + "<br/>");

	// Fetch and display user's name.
	RestV1 r = new RestV1(c);
	
	JSONObject profile=r.getProfile(id);
	JSONObject profile_exr=r.getProfile(id,"extended");
	String activity=r.getActivitiesAtom(id);
	String activity_friend=r.getFriendsActivitiesAtom(id);
    JSONObject friends = r.getFriends(id);	
    JSONObject photo = r.getPhotos(id,-1,-1);
    
    
    
	//JSONObject photos=r.getPhotos(id,-1,-1);
    //out.println("qsd"+photos.get("")+"sdf");

	JSONObject obj = (JSONObject) friends.get("user");
	String name=obj.get("name")+"";
	//System.out.println(aid);
	
	%>
	
	
	<%=obj.get("name") %><br/>
    <%=profile.get("status")%><br/>
	<%=profile.get("basicprofile") %><br/>
	<%=profile.get("city") %><br/>
	<%=profile.get("country") %><br/>
    <%=profile.get("postalcode") %><br/>
	<%=profile.get("region") %><br/>
	<%=profile.get("age")%><br/>
	<%=((JSONObject) profile.get("basicprofile")).get("name") %><br/>
	<%=(String)((JSONObject) profile.get("basicprofile")).get("image") %><br/>

   
    
	
	<%
	
	
	/*  ManageUser service= new ManageUser();
	controller.Search search = new controller.Search();

	User user=new User();
	user.setLogin(name);
	user.setPassword(name);	
	user.setFirstname(name);
	Address address=new Address();
	user.setAddress(address);	
	Boolean check=service.RegisteringUser(user);
	if(check==true)
	{
	
		request.setAttribute("resultreg", "ok");
		request.setAttribute("login", name+"  your name is login and password");
		request.getRequestDispatcher("RegResult.jsp").forward(request, response);
	}else{
		request.setAttribute("resultreg", "ko");
		request.getRequestDispatcher("RegResult.jsp").forward(request, response);
	}
	
	
	
	// Fetch and display user's friends.
	Object f = friends.get("Friends");
	JSONArray fa = (JSONArray) f;
	for (int i = 0; i < fa.size(); i++) {
		JSONObject friend = (JSONObject) fa.get(i);
		out.println("<img height='75' src='" + friend.get("largeImage") + "'/><br/>");
		out.println(friend.get("name") + "<br/><br/>");
	}*/
  }
%>

</body>

</html>