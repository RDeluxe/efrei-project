package com.myspace.myspaceid;

import java.io.PrintStream;

import org.json.simple.JSONObject;


public class PortableContactsTest extends Test {
	public static void testGetPerson(PortableContacts pc) {
		JSONObject data = null; 
		
		printTitle("getPerson(String) 1");
		data = pc.getPerson(null);
		out.println("getPerson(String) 1: " + data);
		
		printTitle("getPerson(String) 2");
		data = pc.getPerson("age,children,drinker");
		out.println("getPerson(String) 2: " + data);
	}
	
	public static void testGetFriends(PortableContacts pc) {
		JSONObject data = null; 
		
		printTitle("getFriends(int, int) 1");
		data = pc.getFriends();
		out.println("getFriends(int, int) 1: " + data);
		
		printTitle("getFriends(int, int) 2");
		data = pc.getFriends(1, 10);
		out.println("getFriends(int, int) 2: " + data);
	}
	
	public static void main(String[] args) throws Exception {
		setUpForOffsiteTests();
		PortableContacts pc = new PortableContacts(c2);
		testGetPerson(pc);
		testGetFriends(pc);
	}	
}
