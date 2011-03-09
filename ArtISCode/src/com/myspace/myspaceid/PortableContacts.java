package com.myspace.myspaceid;

import java.util.HashMap;

import com.myspace.myspaceid.oauth.OAuthServer;
import org.json.simple.JSONObject;

/**
 * Wraps MySpace's PortableContacts REST API.
 */
public class PortableContacts extends RestAPI {
	protected static final String API_USER_URL       = "http://api.myspace.com/v2/people/@me/@self"; 
	protected static final String API_FRIENDS_URL    = "http://api.myspace.com/v2/people/@me/@friends";

	/**
	 * Creates a wrapper object for the MySpace PortableContacts API.
	 * Note that this requires a context that contains an authorized user, e.g., {@link OffsiteContext}. 
	 * @param securityContext The security context to use
	 */
    public PortableContacts(SecurityContext securityContext) {
    	super(securityContext);
    }

    /**
     * Returns the person in the security context associated with this object.
     * @param fields Fields of interest, e.g., age, married.
     * @return
     */
    public JSONObject getPerson(String fields) {
    	securityContext.checkIfAuthorized();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("format", "json");
		map.put("fields", "@all");
		if (fields != null)
			map.put("fields", fields);
		return (JSONObject) getUserData(API_USER_URL, map);
    }

    /**
     * Returns the friends of the current person.
     * @return Friends of the current person.
     */
    public JSONObject getFriends() {
    	return getFriends(-1, -1);
    }
    
    /**
     * Returns the friends of the current person.
     * @param startIndex Index of first friend to return
     * @param count Number of friends to return
     * @return the friends of the current person.
     */
    public JSONObject getFriends(int startIndex, int count) {
//    	if (startIndex == -1)
//    		startIndex = 1;
//    	if (count == -1)
//    		count = 10;
    	
    	securityContext.checkIfAuthorized();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("format", "json");
		if (startIndex != -1)
			map.put("startIndex", new Integer(startIndex).toString());
    	if (count != -1)
    		map.put("count", new Integer(count).toString());
		return (JSONObject) getUserData(API_FRIENDS_URL, map);
    }
}
