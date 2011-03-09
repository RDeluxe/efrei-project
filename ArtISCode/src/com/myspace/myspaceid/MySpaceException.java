package com.myspace.myspaceid;

/**
 * Class that encapsulates MySpace service exceptions.
 */
public class MySpaceException extends RuntimeException {
    public final static int TOKEN_REQUIRED = 1; // Calling a MySpace API without having a valid request/access token
    public final static int REMOTE_ERROR   = 2; // MySpace servers sent an error. For example, bad JSON
    public final static int REQUEST_FAILED = 3; // Unable to complete an HTTP request. For example, empty JSON response
    public final static int CONNECT_FAILED = 4; // Unable to connect to MySpace API servers
 
//    protected String message; // Descriptive error message
    protected int code;       // Internal error code
    protected String response; // HTTP response

    // public finalructor
    public MySpaceException(String message) {
       super(message);
    }

    // public finalructor
    public MySpaceException(String message, int code) {
       this(message);
       this.code = code;
    }

    // public finalructor
    public MySpaceException(String message, int code, String response) {
	   this(message, code);
       this.response = response;
    }
}
