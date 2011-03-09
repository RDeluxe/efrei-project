package com.myspace.myspaceid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URLDecoder;

import com.myspace.myspaceid.oauth.OAuthToken;

public class Test {

	protected static String id = null;
	private static OffsiteContext c1 = null;
	protected static OffsiteContext c2 = null;
	protected static RestV1 r = null;
	protected static PrintStream out = System.err;
//	private static String key = "77f44916a5144c97ad1ddc9ec53338cc";
//	private static String secret = "51951d1f872c454d8932cd5f135623ae";
	private static String key = "18bed0f4247a4f79bc9941bfed5b534c";
	private static String secret = "2e8ebdafbef844a5929086e659e4188c";

	public static void setUpForOnsiteTests() throws Exception {
		OnsiteContext c = new OnsiteContext(key, secret);
		r = new RestV1(c);
	}

	public static void setUpForOffsiteTests() throws Exception {
			c1 = new OffsiteContext(key, secret);
			OAuthToken requestToken = c1.getRequestToken("http://localhost:8080/myspaceid-sample/oauth/oauth-demo.jsp?callback=true");
//			OAuthToken requestToken = c1.getRequestToken(null);
			System.out.println(requestToken);
			String str = c1.getAuthorizationURL(requestToken);
	
			System.out.println("\nAuthorization URL (copy and access this URL using a browser and log in): \n" + str);
			System.out.print("\nEnter oauth_verifier parameter in callback: ");
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String oauth_verifier = br.readLine();
			oauth_verifier = URLDecoder.decode(oauth_verifier);
			System.out.println();
			System.out.println("Using oauth_verifier: " + oauth_verifier);
	
			// To get access token, use the new request token returned in the Callback URL, and use the secret returned with the original request token
	//		OAuthToken token2 = new OAuthToken(newTokenKey, token.getSecret());
	//		OAuthToken token3 = ms.getAccessToken(token2);
	
			c2 = new OffsiteContext(key, secret, requestToken.getKey(), requestToken.getSecret());
	//		c2 = new OffsiteContext(key, secret, newTokenKey, requestToken.getSecret());
			c2.getAccessToken(oauth_verifier); // This remembers the access token inside the OffsiteContext as a side effect
			id = c2.getUserId();
			System.out.println(">>> User id = " + id);
			
			// Set static Myspace object and user id!
			r = new RestV1(c2);
		}

	public static void printTitle(String str) {
		out.println();
		out.println("********************************");
		out.println(str);
		out.println("********************************");
	}

	public Test() {
		super();
	}

}