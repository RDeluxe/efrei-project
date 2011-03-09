package com.myspace.myspaceid;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.myspace.myspaceid.oauth.OAuthToken;

/**
 * Security context for off-site apps.  Provides a series of calls that performs an OAuth login workflow.  
 * After you have obtained the access token for the user, you can create a MySpace API wrapper object 
 * (e.g., {@link RestV1} or {@link PortableContacts}) and start making calls to fetch and store data.    
 */
public class OffsiteContext extends SecurityContext {
	protected static final String OAUTH_REQUEST_TOKEN_URL = "http://api.myspace.com/request_token";
	protected static final String OAUTH_AUTHORIZATION_URL = "http://api.myspace.com/authorize";
	protected static final String OAUTH_ACCESS_TOKEN_URL  = "http://api.myspace.com/access_token";
	protected static final String API_USERINFO_URL   	  = "http://api.myspace.com/v1/user.json";

//	protected OAuthToken requestToken;
//	protected OAuthToken accessToken;
	
	/**
	 * Creates a security context for an offsite application.  For an offsite app, use this constructor to first get the 
	 * request token and the authorization URL.
	 * @param consumerKey
	 * @param consumerSecret
	 */
	public OffsiteContext(String consumerKey, String consumerSecret) {
		super(consumerKey, consumerSecret);
	}

	/**
	 * Creates a security context that contains the OAuth request token.  Use this in the callback after the user has been authorized.
	 * Then get the access token, after which you are good to start getting user data using one of our REST API wrappers, e.g., {@link RestV1}
	 * @param consumerKey
	 * @param consumerSecret
	 * @param requestTokenKey 
	 * @param requestTokenSecret 
	 */
	public OffsiteContext(String consumerKey, String consumerSecret, String requestTokenKey, String requestTokenSecret) {
		super(consumerKey, consumerSecret);
		OAuthToken requestToken = new OAuthToken(requestTokenKey, requestTokenSecret);
		server.setRequestToken(requestToken);
	}
	
	/**
	 * Returns the request token.  After this, allow the user to authenticate himself by redirecting 
	 * to the URL found by calling {@link #getAuthorizationURL}.
	 * @param callbackURL The callback URL that MySpace redirects the user to after she authenticates successfully.
	 * @return A request token.
	 */
    public OAuthToken getRequestToken(String callbackURL) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (callbackURL != null)
			map.put("oauth_callback", callbackURL);
		String reqUrl = server.generateRequestUrl(OAUTH_REQUEST_TOKEN_URL, map);
		String response = server.doHttpReq(reqUrl);
		OAuthToken token = new OAuthToken(response);
//System.out.println("Request token = " + token);
		return token;
	}

    /**
	 * Returns the authorization URL that you should redirect to for the user to authenticate.  After successful authentication, the user's 
	 * browser is redirected back to the callback URL.  The callback URL receives an oauth_token parameter that you need to pick up and then 
	 * call {@link #getAccessToken}.
	 * @param requestToken The request token obtained by calling {@link #getRequestToken}.
	 * @return the authorization URL that you should redirect to for the user to authenticate.
	 */
    public String getAuthorizationURL(OAuthToken requestToken) { 
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("oauth_token", java.net.URLDecoder.decode(requestToken.getKey()));
		String result = server.generateRequestUrl(OAUTH_AUTHORIZATION_URL, map);
		return result;
	}
 
	/**
	 * Returns the access token for the user.  The side effect of this call is to set the access token in this OffsiteContext object.
	 * Make sure you call this using the constructor that sets the (authorized) request token.
	 * so that you can now use it to create a {@link RestV1} (or other REST wrapper object) to start fetching user data.   
	 * @return An access token; you can now start getting user data with this access token.
	 */
    public OAuthToken getAccessToken() {
		return getAccessToken(null);
	}
	
	/**
	 * Returns the access token for the user.  The side effect of this call is to set the access token in this OffsiteContext object.
	 * Make sure you call this using the constructor that sets the (authorized) request token.
	 * so that you can now use it to create a {@link RestV1} (or other REST wrapper object) to start fetching user data.   
	 * @param oauthVerifier oauth_verifier parameter returned in the callback for OAuth 1.0a workflow
	 * @return An access token; you can now start getting user data with this access token.
	 */
    public OAuthToken getAccessToken(String oauthVerifier) {
    	if (server.getRequestToken() == null || server.getRequestToken().getSecret() == null)
    		throw new MySpaceException("requestToken is null.  Must have authorized request token to get the access token");
    	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("oauth_token", java.net.URLDecoder.decode(server.getRequestToken().getKey()));
		if (oauthVerifier != null)
			map.put("oauth_verifier", oauthVerifier);
		String reqUrl = server.generateRequestUrl(OAUTH_ACCESS_TOKEN_URL, map);
//System.out.println("+++++ " + reqUrl);
		String response = server.doHttpReq(reqUrl);

		// Set access token as side effect
		OAuthToken accessToken = new OAuthToken(response);
		server.setAccessToken(accessToken);
//System.out.println("accessToken = " + accessToken);		

		return accessToken;
	}

	/**
	 * Sets the access token to use for subsequent REST API calls.
	 * @param accessToken The access token to use.
	 */
	public void setAccessToken(OAuthToken accessToken) {
		server.setAccessToken(accessToken);
	}
	
	/**
	 * Returns the id of the user for whom we have an access token.
	 * This method requires that the access token has been stored. 
	 * @return the id of the user for whom we have an access token.
	 */
    public String getUserId() {
//    	checkIfAuthorized();
//		HashMap<String, String> map = new HashMap<String, String>();
//		String reqUrl = server.generateRequestUrl(API_USERINFO_URL, accessToken == null ? "" : accessToken.getSecret(), map);
//		String response = server.doHttpReq(reqUrl);
//
//		JSONParser parser = new JSONParser();
//		JSONObject obj = null;
//		try
//		{
//			obj = (JSONObject) parser.parse(response);
//		}
//		catch (ParseException pe)
//		{
//			throw new MySpaceException(pe.getMessage(), MySpaceException.REMOTE_ERROR);
//		}

    	JSONObject obj = getUser();
		Long id = (Long) obj.get("userId");
		return id.toString();
	}

	/**
	 * Returns the current user as an object.  For most calls, you would first need to call {@link #getUserId} 
	 * to obtain the current user's id before you can access data.
	 * @return the current user.
	 */
	public JSONObject getUser() {
		checkIfAuthorized();
		HashMap<String, String> map = new HashMap<String, String>();
		String reqUrl = server.generateRequestUrl(API_USERINFO_URL, map);
		String response = server.doHttpReq(reqUrl);
		
		JSONParser parser = new JSONParser();
		Object obj = null;
		try
		{
			obj = parser.parse(response);
		}
		catch (ParseException e)
		{
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			throw new MySpaceException(sw.toString(), MySpaceException.REMOTE_ERROR);
		}

		return (JSONObject) obj;
	}

	/**
	 * Checks that an access token has been set up (done by using the constructor with 4 arguments).  Throws an exception if not.
	 */
	public void checkIfAuthorized() {
		if (server.getAccessToken() == null) {
			StringBuffer sb = new StringBuffer();
			sb.append("Access token not set.  ");
			sb.append("You are using an OffsiteContext, which requires an access token for data access.  ");
			sb.append("If you are running an onsite application, use an OnsiteContext.  ");
			throw new MySpaceException(sb.toString(), MySpaceException.TOKEN_REQUIRED);
		}
	}
}
