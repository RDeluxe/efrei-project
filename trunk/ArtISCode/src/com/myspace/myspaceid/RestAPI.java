package com.myspace.myspaceid;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.myspace.myspaceid.oauth.OAuthServer;

/**
 * Base class for classes wrapping MySpace REST API's.
 */
public class RestAPI {

	// Member variables
	protected SecurityContext securityContext;
	protected OAuthServer server; // This is not set directly by the developer.  Represents the MySpace server viewed as an OAuthServer.
	protected String dateFormat;
	protected int timeZone;

	private RestAPI() {
	}

    /**
	 * Constructor.  Use this to construct an object that wraps a MySpace REST API.
	 * @param consumerKey Consumer key to use.  You should have obtained the consumer key and consumer secret from developer.myspace.com.
	 * @param consumerSecret Consumer secret to use.
	 */
    public RestAPI(SecurityContext securityContext) {
    	this.securityContext = securityContext;
    	server = securityContext.getServer();
    }
    
    /**
	 * Sends REST request to get user data.
	 * @param url URL prefix to use, e.g., http://api.myspace.com/v1/users/123456/albums.json
	 * @param map HashMap of additional parameters to send that are specific to this request
	 * @return user data in a {@link UserData} object.
	 */
	protected Object getUserData(String url, HashMap<String, String> map) {
		// If specified, set date format and time zone for all REST calls.
		if (getDateFormat() != null) {
			map.put("dateFormat", getDateFormat());
			map.put("timeZone", "" + getTimeZone());
		}
		
		String reqUrl = server.generateRequestUrl(url, map);
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
	
		return obj;
	}

	/**
	 * Sends REST request to put user data.
	 * @param url URL prefix to use, e.g., http://api.myspace.com/v1/users/28568917/status
	 * @param map HashMap of additional parameters to send that are specific to this request
	 * @param appParams HashMap of params to send in body of request
	 * @return user data in a {@link UserData} object.
	 */
	protected Object putUserData(String url, HashMap<String, String> map, Map<String, String> appParams) {
		return putUserData("PUT", url, map, appParams);
	}

	/**
	 * Sends REST request to put user data.
	 * @param method Method to use, e.g., PUT
	 * @param url URL prefix to use, e.g., http://api.myspace.com/v1/users/28568917/status
	 * @param map HashMap of additional parameters to send that are specific to this request
	 * @param appParams HashMap of params to send in body of request
	 * @return user data in a {@link UserData} object.
	 */
	protected Object putUserData(String method, String url,
			HashMap<String, String> map, Map<String, String> appParams) {

		// if (putParam == null || putValue == null)
		// throw new MySpaceException("Put parameter or value cannot be null.");

		if (appParams != null)
			map.putAll(appParams);

		//
		// Note: for signing, the base string has to include the value being
		// put, but the value is
		// taken out of the parameter string itself and sent in the body of the
		// request. For instance,
		// the below is a working HTTP conversation.
		// 
		// POST
		// /v1/users/28568917/status?oauth_consumer_key=77f44916a5144c97ad1ddc9ec53338cc&oauth_nonce=8783759987300271273&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1237853013&oauth_token=8QLGnqFugwmCbIz6pcFbNEMPkG%252FCsZrg4fdqIzXpj88FsZaysd7wJ4eBonbvpAG7MOCFhzDjcM1yp6wvO%252BRaeyruy95QdfpFIHQaHvHL7ak%253D&oauth_version=1.0&oauth_signature=TvlXbt%2FNS0U7SrtUvUfu%2BfJ3kyo%3D
		// HTTP/1.1
		// X-HTTP-Method-Override: PUT
		// User-Agent: Java/1.6.0_12
		// Host: api.myspace.com
		// Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2
		// Proxy-Connection: keep-alive
		// Content-type: application/x-www-form-urlencoded
		// Content-Length: 264
		// 
		// status=Hello%20World%20%20%E7%BB%88%E4%BA%8E%E6%88%90%E5%8A%9F%E4%BA%86%EF%BC%81%21%21%20Can%20you%20believe%20that%20I%27m%20now%20posting%20status%20updates%20successfully%21%3F%20~%21%40%23%24%25%5E%26%2A%28%29_%2B%7B%7D%3A%22%3C%3E%3F%60-%3D%5B%5D%3B%27%2C.%2F
		//

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-type", "application/x-www-form-urlencoded");
		// String reqUrl = server.generateRequestUrl(url, accessToken == null ?
		// "" : accessToken.getSecret(), map, "PUT", putParam);
		String reqUrl = server.generateRequestUrl(url, map, method, appParams.keySet());
		String response = null;
		try {
			// String bodyStr = putParam == null ? "" : putParam + "=" +
			// OAuthServer.encode(putValue);
			String bodyStr = OAuthServer.buildParams(appParams);
			response = server.doHttpMethodReq(reqUrl, method, bodyStr, headerMap);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			throw new MySpaceException(sw.toString(),
					MySpaceException.REMOTE_ERROR);
		}

		return (String) response;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public int getTimeZone() {
		return timeZone;
	}

	public void setDateFormatTimeZone(String dateFormat, int timeZone) {
		this.dateFormat = dateFormat;
		this.timeZone = timeZone;
	}
}