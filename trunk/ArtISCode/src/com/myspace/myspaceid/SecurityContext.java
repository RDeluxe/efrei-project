package com.myspace.myspaceid;

import com.myspace.myspaceid.oauth.OAuthConsumer;
import com.myspace.myspaceid.oauth.OAuthServer;
import com.myspace.myspaceid.oauth.OAuthToken;

/**
 * This is the base security context class.  You wouldn't normally use this.  See {@link OffsiteContext} and {@link OnsiteContext}.
 */
public class SecurityContext {
	protected OAuthServer server; // This is not set directly by the developer.  Represents the MySpace server viewed as an OAuthServer.

	/**
	 * Creates a security context object.
	 * @param consumerKey
	 * @param consumerSecret
	 */
	public SecurityContext(String consumerKey, String consumerSecret) {
		OAuthConsumer consumer = new OAuthConsumer(consumerKey, consumerSecret);
		server = new OAuthServer(consumer);
	}

	/**
	 * Returns the OAuth server object associated with this object.  The underlying OAuth server object
	 * stores all the tokens in an authentication dialogue and has methods to communicate with the server.
	 * @return the OAuth server object associated with this object.
	 */
	protected OAuthServer getServer() {
		return server;
	}

	/**
	 * Checks to see if the current security context is authorized to fetch user data.  Throws a run time exception if not.
	 */
	public void checkIfAuthorized() {
	}
}
