package com.myspace.myspaceid;

import java.net.*;
import java.util.*;
import org.json.simple.JSONObject;

import com.myspace.myspaceid.oauth.*;

/**
 * Subclass of MySpace for Internal Partners only.
 */
public class InternalPartnerContext extends OffsiteContext
{
	public InternalPartnerContext(String consumerKey, String consumerSecret) {
		super(consumerKey, consumerSecret);
	}

	public OAuthToken getAccessToken(String userCookie) {
		HashMap<String, String> map = new HashMap<String, String>();
		String reqUrl = server.generateRequestUrl(OAUTH_ACCESS_TOKEN_URL, map);

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Cookie", userCookie);
		String response = server.doHttpMethodReq(reqUrl, null, "", headerMap);
		OAuthToken token = new OAuthToken(response);
		
		// Set access token as side effect
		server.setAccessToken(token);
//System.out.println("accessToken = " + token);		

		return token;
	}

	public static void main(String[] args) {
		String key = "http://www.myspace.com/myspaceid_sdk";
		String secret = "53effd0c5c6b48119e0e3880785165a0";

		InternalPartnerContext c = new InternalPartnerContext(key, secret);
		c.getAccessToken("USER=NufC%2bJKIHmwZ9HNKI7UKjkwkYBc2jIX3HMuruycWyMZApIRwCD85e9S262%2bEZBZaQtf045o6reFJsu4mM5VLJAGii191jxw1ZDU9l2CQnlCXpyo74OecnJNJBvJVIPbSAaHu%2btEJ15f1AswYLVfqGVr8EM9YVMes4ejAdRMSQCXCXOb5F839JXca904daxMUrIPANWF1mBhZByn27egDJkkz2mumXQSW5qm8fuM87NwIoMXj2r5dDzqh4tV4BkKb78LhH2sT0EYV1stKTfH%2fxYZTHApfyP0wy88XYD%2f2wA4UR756ZiIQ%2bSlxkTD0XRKl%7cWxwB39zhOEJCThftPiJX1kXP%2fv3z87flo0eFvcjz2KnSD2i42Yf%2fTYpqndUr6Ao0nqm2Wx3OlnEacPullmAb%2b0S9H4WvXQfGecxTM%2fX0qzY%3d");
		RestV1 r = new RestV1(c);

		String id = c.getUserId();
		System.out.println(">>> User id = " + id);
		JSONObject jobj = r.getFriends(id);
		System.out.println(">>> Friends: " + jobj);
	}
}