package com.myspace.myspaceid.oauth;

/**
 * Class that encapsulates an OAuth consumer.
 */
public class OAuthConsumer
{
	protected String key;
	protected String secret;

	public OAuthConsumer(String key, String secret) {
		this.key = key;
		this.secret = secret;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String toString() {
		return "OAuthConsumer: key = " + key + " , secret = " + secret;
	}
}