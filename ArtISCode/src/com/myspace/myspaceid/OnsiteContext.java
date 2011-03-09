package com.myspace.myspaceid;

/**
 * Security context for on-site apps.  After you have created an instance of this class, you can create 
 * a MySpace API wrapper object (e.g., {@link RestV1} or {@link PortableContacts}) and start making calls 
 * to fetch and store data.    
 */
public class OnsiteContext extends SecurityContext {
	public OnsiteContext(String consumerKey, String consumerSecret) {
		super(consumerKey, consumerSecret);
	}
}
