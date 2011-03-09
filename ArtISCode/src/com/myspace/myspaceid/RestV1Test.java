package com.myspace.myspaceid;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RestV1Test extends Test {
	public static void testGetFriendsAppData() throws Exception {
		String key = "http://perisphere.1939worldsfair.com/app.xml";
		String secret = "eda0c62773234093bea92645eea0493d";
			
		JSONArray data = null;
//		JSONObject data = null;

		OnsiteContext c = new OnsiteContext(key, secret);
		RestV1 r = new RestV1(c);

		// First put something
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("colour", "red");
		map.put("flavour", "spicy");
		r.putAppData("146617378", map);
		Thread.sleep(1000);
		
		printTitle("getFriendsAppData(String) 1");
//		data = ms.getFriendsAppData("146617378");
		data = r.getUserFriendsAppData("28568917");
//		data = ms.getFriendsAppData("456073223");
		out.println("getFriendsAppData() 1: " + data);

		printTitle("getFriendsAppData(String, String) 1");
		data = r.getUserFriendsAppData("28568917", "colour");
		out.println("getFriendsAppData(String, String) 1: " + data);

		printTitle("getFriendsAppData(String, String) 2");
		data = r.getUserFriendsAppData("28568917", "colour;flavour");
		out.println("getFriendsAppData(String, String) 2: " + data);
	}

	public static void testPutGlobalAppData() {
		printTitle("testPutGlobalAppData(Map)");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("animal", "cat");
		map.put("plant", "rose");
		Object appData = r.putGlobalAppData(map);

		out.println("testPutGlobalAppData(Map) 1: " + appData);
	}

	public static void testGetGlobalAppData() {
		printTitle("testGetGlobalAppData()");
		JSONObject appData = r.getGlobalAppData();

		out.println("testGetGlobalAppData() 1: " + appData);
	}

	public static void testGetGlobalAppData2() {
		printTitle("testGetGlobalAppData2()");
		JSONObject appData = r.getGlobalAppData("animal;plant;name");

		out.println("testGetGlobalAppData2() 1: " + appData);
	}

	public static void testClearGlobalAppData() {
		printTitle("testClearGlobalAppData()");
		Object appData = r.clearGlobalAppData("animal;plant");

		out.println("testClearGlobalAppData() 1: " + appData);
	}

	public static void globalTests() throws Exception {
		testPutGlobalAppData();
		
		// Put data may become available only after a delay, so sleep first
		Thread.sleep(1000);
		
		testGetGlobalAppData();
		testGetGlobalAppData2();
		testClearGlobalAppData();
		testGetGlobalAppData();
	}

	public static void testGetAlbums() {
		// Run tests
		JSONObject data = null;
		JSONObject obj = null;

		// getAlbums(userId) 
		//  - test with valid id
		//  - test invalid id

		// First test with valid id
		printTitle("getAlbums(String) with valid user id");
		data = r.getAlbums(id);
		out.println("getAlbums(String) 1: " + data);
//		obj = (JSONObject) data.get("user");

		// 2nd test with invalid id
		printTitle("getAlbums(String) with invalid user id 100");
		try
		{
			data = r.getAlbums("100");
			out.println("getAlbums(String) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getAlbums(String) 2nd test");
		}

		// 3rd test with invalid id
		printTitle("getAlbums(String) with invalid user -1");
		try
		{
			data = r.getAlbums("-1");
			out.println("getAlbums(String) 3: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getAlbums(String) 3rd test");
		}
	}


	public static void testGetAlbums2() {
		JSONObject data = null;
		JSONObject obj = null;
		String rc = null;

		printTitle("getAlbums(String, int, int) with valid user id and -1 for last 2 params");
		data = r.getAlbums(id, -1, -1);
		out.println("getAlbums(String, int, int) 1: " + data);

		printTitle("getAlbums(String, int, int) with 1 for last 2 params");
		data = r.getAlbums(id, 1, 1);
		out.println("getAlbums(String, int, int) 2: " + data);

		printTitle("getAlbums(String, int, int) with invalid page size");
		try
		{
			data = r.getAlbums(id, 1, -2);
			out.println("getAlbums(String, int, int) 3: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getAlbums(String, int, int) 1st test");
		}

		printTitle("getAlbums(String, int, int) with invalid page");
		try
		{
			data = r.getAlbums(id, 0, 1);
			out.println("getAlbums(String, int, int) 4: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getAlbums(String, int, int) 2nd test");
		}
	}

	public static void testGetAlbumPhoto() {
		JSONObject data = null;
		JSONObject obj = null;

		// Fetch album id to use
		data = r.getAlbums(id, -1, -1); // First get an album id to use
		JSONArray albums = (JSONArray) data.get("albums");
		Long idLong = (Long) ((JSONObject) albums.get(0)).get("id");
		int albumId = idLong.intValue(); // Got the album id!  Now use it...
		
		// Fetch photo id to use
		data = r.getAlbum(id, albumId);
		JSONArray photos = (JSONArray) data.get("photos");
		idLong = (Long) ((JSONObject) photos.get(0)).get("id");
		int photoId = idLong.intValue();
		
		printTitle("getAlbumPhoto(String, int, int) with valid user id, valid album id and valid photo id");
		data = r.getAlbumPhoto(id, albumId, photoId);
		out.println("getAlbumPhoto(String, int, int) 1: " + data);
	}
	
	public static void testGetAlbum() {
		JSONObject data = null;
		JSONObject obj = null;

		data = r.getAlbums(id, -1, -1); // First get an album id to use
		JSONArray albums = (JSONArray) data.get("albums");
		Long idLong = (Long) ((JSONObject) albums.get(0)).get("id");
		int albumId = idLong.intValue(); // Got the album id!  Now use it...
		
		printTitle("getAlbum(String, int) with valid user id and valid album id");
		data = r.getAlbum(id, albumId);
		out.println("getAlbum(String, int) 1: " + data);

		printTitle("getAlbum(String, int) with valid user id and invalid album id");
		try
		{
			data = r.getAlbum(id, -1111);
			out.println("getAlbum(String, int) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getAlbum(String, int) 2nd test");
		}

		printTitle("getAlbum(String, int) with invalid user id and valid album id");
		try
		{
			data = r.getAlbum("-1", albumId);
			out.println("getAlbum(String, int) 3: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getAlbum(String, int) 3rd test");
		}
	}

	public static void testGetAlbumInfo() {
		JSONObject data = null;
		JSONObject obj = null;

		data = r.getAlbums(id, -1, -1); // First get an album id to use
		JSONArray albums = (JSONArray) data.get("albums");
		Long idLong = (Long) ((JSONObject) albums.get(0)).get("id");
		int albumId = idLong.intValue(); // Got the album id!  Now use it...
		
		printTitle("getAlbumInfo(String, int) with valid user id and valid album id");
		data = r.getAlbumInfo(id, albumId);
		out.println("getAlbumInfo(String, int) 1: " + data);

		printTitle("getAlbumInfo(String, int) with valid user id and invalid album id");
		try
		{
			data = r.getAlbumInfo(id, -1111);
			out.println("getAlbumInfo(String, int) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getAlbumInfo(String, int) 2nd test");
		}

		printTitle("getAlbumInfo(String, int) with invalid user id and valid album id");
		try
		{
			data = r.getAlbumInfo("-1", albumId);
			out.println("getAlbumInfo(String, int) 3: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getAlbumInfo(String, int) 3rd test");
		}
	}

	public static void testGetFriends() {
		JSONObject data = null;
		JSONObject obj = null;

		// First test with valid id
		printTitle("getFriends(String) with valid user id");
		data = r.getFriends(id);
		out.println("getFriends(String) 1: " + data);

		// 2nd test with invalid id
		printTitle("getFriends(String) with invalid user id 100");
		try
		{
			data = r.getFriends("100");
			out.println("getFriends(String) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getFriends(String) 2nd test");
		}
	}

	public static void testGetFriends2() {
		JSONObject data = null;
		JSONObject obj = null;

		// Test with valid id, page, pageSize, list = top, show = mood
		printTitle("getFriends(String, int, int, String, String) with valid user id, page, pageSize, list = top, show = mood");
		data = r.getFriends(id, 1, 1, "top", "mood");
		out.println("getFriends(String, int, int, String, String) 1: " + data);

		// Test with valid id, page, pageSize, list = top, show = mood|status
		printTitle("getFriends(String, int, int, String, String) with valid user id, page, pageSize, list = top, show = mood|status");
		data = r.getFriends(id, 1, 1, "top", "mood|status");
		out.println("getFriends(String, int, int, String, String) 2: " + data);

		// Test with valid id, page, pageSize, list = top, show = mood|status|online
		printTitle("getFriends(String, int, int, String, String) with valid user id, page, pageSize, list = top, show = mood|status|online");
		data = r.getFriends(id, 1, 1, "top", "mood|status|online");
		out.println("getFriends(String, int, int, String, String) 3: " + data);

		// Test with valid id, page, pageSize, list = top, show = mood | status | online
		printTitle("getFriends(String, int, int, String, String) with valid user id, page, pageSize, list = top, show = mood | status | online");
		try
		{
			data = r.getFriends(id, 1, 1, "top", "mood | status | online");
			out.println("getFriends(String, int, int, String, String) 4: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getFriends(String) 4th test");
		}

		// Test with valid id, page, pageSize, list = online, show = status
		printTitle("getFriends(String, int, int, String, String) with valid user id, page, pageSize, list = online, show = status");
		data = r.getFriends(id, 1, 1, "online", "status");
		out.println("getFriends(String, int, int, String, String) 5: " + data);

		// Test with valid id, page, pageSize, list = app, show = status
		printTitle("getFriends(String, int, int, String, String) with valid user id, page, pageSize, list = app, show = status");
		data = r.getFriends(id, 1, 1, "app", "status");
		out.println("getFriends(String, int, int, String, String) 6: " + data);

		// Test with valid id, page, pageSize, list = top, show = online|status|mood
		printTitle("getFriends(String, int, int, String, String) with valid user id, page, pageSize, list = top, show = online|status|mood");
		data = r.getFriends(id, 1, 1, "top", "online|status|mood");
		out.println("getFriends(String, int, int, String, String) 7: " + data);

		// Test with valid id, page, pageSize, list = top|app, show = status (list must be single-valued)
		printTitle("getFriends(String, int, int, String, String) with valid user id, page, pageSize, list = top|app, show = status");
		try
		{
			data = r.getFriends(id, 1, 1, "top|app", "status");
			out.println("getFriends(String, int, int, String, String) 8: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getFriends(String) 8th test");
		}
	}


	public static void testGetFriendsList() {
		JSONObject data = null;
		JSONObject obj = null;
		String friends = "6221;457099758;36452044";
		String invalidFriends = "6221;457099758;100";

		// First test with valid id
		printTitle("getFriendsList(String, String) with valid user id");
		data = r.getFriendsList(id, friends);
		out.println("getFriendsList(String, String) 1: " + data);

		// 2nd test with invalid id but valid friends
		printTitle("getFriendsList(String, String) with invalid user id 100");
		try
		{
			data = r.getFriendsList("100", friends);
			out.println("getFriendsList(String, String) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getFriendsList(String, String) 2nd test");
		}

		// 3rd test with invalid id but invalid friends
		printTitle("getFriendsList(String, String) with invalid friend id 100");
		try
		{
			data = r.getFriendsList(id, invalidFriends);
			out.println("getFriendsList(String, String) 3: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getFriendsList(String, String) 3rd test");
		}
	}
	
	public static void testGetFriendsList2() {
		JSONObject data = null;
		JSONObject obj = null;
		String friends = "6221;457099758;36452044";
		String invalidFriends = "6221;457099758;100";
		String show = "mood|status|online";
		String invalidShow = "mood|status|onlinewrongstring";

		// 1st test with valid show string
		printTitle("getFriendsList(String, String, String) with valid user id");
		data = r.getFriendsList(id, friends, show);
		out.println("getFriendsList(String, String, String) 1: " + data);
		
		// 2nd test with invalid show string
		printTitle("getFriendsList(String, String, String) with invalid show string");
		try
		{
			data = r.getFriendsList(id, friends, invalidShow);
			out.println("getFriendsList(String, String, String) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getFriendsList(String, String, String) 2nd test");
		}
	}

	public static void testGetFriendship() {
		// getFriendship(userId, friendIds)
		//  - test with valid id, valid friend id
		//  - test with valid id, invalid friend id

		JSONObject data = null;
		JSONObject obj = null;

		data = r.getFriends(id); // First get friend id's to use
		JSONArray friends = (JSONArray) data.get("Friends");
		Long idLong = (Long) ((JSONObject) friends.get(0)).get("userId");
		int friendId1 = idLong.intValue();
		idLong = (Long) ((JSONObject) friends.get(1)).get("userId");
		int friendId2 = idLong.intValue(); // Got the friend ids'!  Now use it...
		String friendIds = "" + friendId1 + ";" + friendId2 + ";457099751"; // This last one isn't a friend

		// Test with valid id, valid friend ID's
		printTitle("getFriendship(String, String) with valid user id, friend Id's");
		data = r.getFriendship(id, friendIds);
		out.println("getFriendship(String, String) 1: " + data);

		// Test with valid id, invalid friend ID
		printTitle("getFriendship(String, String) with valid user id, friend Id's");
		try
		{
			data = r.getFriendship(id, friendIds + ";-1");
			out.println("getFriendship(String, String) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getFriends(String) 2nd test");
		}
	}

	public static void testGetMoods() {
		// getMoods(userId) 
		//  - test with valid id
		//  - test invalid id
		JSONObject data = null;
		JSONObject obj = null;

		// First test with valid id
		printTitle("getMoods(String) with valid user id");
		data = r.getMoods(id);
		out.println("getMoods(String) 1: " + data);

		// 2nd test with invalid id
		printTitle("getMoods(String) with invalid user id 100");
		try
		{
			data = r.getMoods("100");
			out.println("getMoods(String) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getMoods(String) 2nd test");
		}
	}

	public static void testGetMood() {
		// getMood(userId) 
		//  - test with valid id
		//  - test invalid id
		JSONObject data = null;
		JSONObject obj = null;

		// First test with valid id
		printTitle("getMood(String) with valid user id");
		data = r.getMood(id);
		out.println("getMood(String) 1: " + data);

		// 2nd test with invalid id
		printTitle("getMood(String) with invalid user id 100");
		try
		{
			data = r.getMood("100");
			out.println("getMood(String) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getMood(String) 2nd test");
		}
	}

	public static void testGetPhotos() {
		// getPhotos(userId, page, pageSize)
		//  - test with valid id, -1 for both page and pageSize
		//  - test with valid id, page and pageSize
		//  - test with valid id, invalid page, valid pageSize
		//  - test with valid id, invalid page, invalid pageSize
		//  - test with invalid id, valid page and pageSize

		JSONObject data = null;
		JSONObject obj = null;

		//  Test with valid id, -1 for both page and pageSize
		printTitle("getPhotos(String, int, int) with valid user id, -1 for both page and pageSize");
		data = r.getPhotos(id, -1, -1);
		out.println("getPhotos(String, int, int) 1: " + data);

		//  Test with valid id, page and pageSize
		printTitle("getPhotos(String, int, int) with valid user id, page and pageSize");
		data = r.getPhotos(id, 1, 10);
		out.println("getPhotos(String, int, int) 2: " + data);
	}

	public static void testGetPhoto() {
		//  - test with valid user id, valid photo id
		//  - test with valid user id, invalid photo id
		//  - test with invalid user id, valid photo id

		JSONObject data = null;
		JSONObject obj = null;
	
		data = r.getPhotos(id, 1, 10); // First get a photo id to use
		JSONArray photos = (JSONArray) data.get("photos");
		Long idLong = (Long) ((JSONObject) photos.get(0)).get("id");
		int photoId = idLong.intValue(); // Got the photo id!  Now use it...

		//  Test with valid id, valid photo id
		printTitle("getPhoto(String, int) with valid user id, valid photo id");
		data = r.getPhoto(id, photoId);
		out.println("getPhoto(String, int) 1: " + data);

		//  Test with valid id, invalid photo id
		printTitle("getPhoto(String, int) with valid user id, invalid photo id");
		try
		{
			data = r.getPhoto(id, -12345);
			out.println("getPhotos(String, int) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getPhoto(String, int) 2nd test");
		}	
	}

	public static void testGetProfile() {
		//  - test with valid id
		//  - test invalid id

		JSONObject data = null;
		JSONObject obj = null;

		// First test with valid id
		printTitle("getProfile(String) with valid user id");
//		id = "28568917";
		data = r.getProfile(id);
		out.println("getProfile(String) 1: " + data);

		// 2nd test with invalid id
		printTitle("getProfile(String) with invalid user id 100");
		try
		{
			data = r.getProfile("100");
			out.println("getProfile(String) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getProfile(String) 2nd test");
		}
	}

	public static void testGetProfile2() {
		//  - test with valid id and detailType = basic
		//  - test with valid id and detailType = full
		//  - test with valid id and detailType = extended

		JSONObject data = null;
		JSONObject obj = null;

		// Test with valid id
		printTitle("getProfile(String) with valid user id and detailType = basic");
		data = r.getProfile(id, "basic");
		out.println("getProfile(String) 1: " + data);

		// Test with valid id
		printTitle("getProfile(String) with valid user id and detailType = full");
		data = r.getProfile(id, "full");
		out.println("getProfile(String) 2: " + data);

		// Test with valid id
		printTitle("getProfile(String) with valid user id and detailType = extended");
		data = r.getProfile(id, "extended");
		out.println("getProfile(String) 3: " + data);
	}

	public static void testGetStatus() {
		//  - test with valid id
		//  - test invalid id

		JSONObject data = null;
		JSONObject obj = null;

		// First test with valid id
		printTitle("getStatus(String) with valid user id");
		data = r.getStatus(id);
		out.println("getStatus(String) 1: " + data);

		// 2nd test with invalid id
		printTitle("getStatus(String) with invalid user id 100");
		try
		{
			data = r.getStatus("100");
			out.println("getStatus(String) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getStatus(String) 2nd test");
		}
	}

	public static void testGetStatusHistory() {
		//  - test with valid id
		//  - test invalid id

		String data = null;

		// First test with valid id
		printTitle("getStatusHistory(String) with valid user id");
		data = r.getStatusHistory(id);
		out.println("getStatusHistory(String) 1: " + data);

		// 2nd test with invalid id
		printTitle("getStatusHistory(String) with invalid user id 100");
		try
		{
			data = r.getStatusHistory("100");
			out.println("getStatusHistory(String) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getStatusHistory(String) 2nd test");
		}
	}

	public static void testPostStatus() {
		Object data = null;
		JSONObject obj = null;

		printTitle("postStatus(String) with valid user id");
//		data = r.postStatus(id, "abcdef 今天天气好 哈哈 --  ~!@#$%^&*()_+{}:\"<>?`-=[];',./");
		data = r.postStatus(id, "what a rainy day");
		out.println("postStatus(String) 1: '" + (String) data + "'");
	}

	public static void testPostMood() {
		Object data = null;
		JSONObject obj = null;

		printTitle("postMood(String) with valid user id");
		data = r.postMood(id, 3);
		out.println("postMood(String) 1: '" + (String) data + "'");
	}

	public static void testPostStatusMood() {
		Object data = null;
		JSONObject obj = null;

		printTitle("postStatusMood(String) with valid user id");
		data = r.setStatusMood(id, "I'm updating my status at the same time as my mood", 5);
		out.println("postStatusMood(String) 1: '" + (String) data + "'");
	}

	public static void testGetFriendsStatus() {
		//  - test with valid id
		//  - test invalid id

		JSONObject data = null;
		JSONObject obj = null;

		// First test with valid id
		printTitle("getStatus(String) with valid user id");
		data = r.getFriendsStatus(id);
		out.println("getStatus(String) 1: " + data);

		// 2nd test with invalid id
		printTitle("getStatus(String) with invalid user id 100");
		try
		{
			data = r.getFriendsStatus("100");
			out.println("getStatus(String) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getStatus(String) 2nd test");
		}
	}

	public static void testGetVideos() {
		// getVideos(userId, page, pageSize)
		//  - test with valid id
		//  - test invalid id

		JSONObject data = null;
		JSONObject obj = null;

		// First test with valid id
		printTitle("getVideos(String) with valid user id");
		data = r.getVideos(id);
		out.println("getVideos(String) 1: " + data);

		// 2nd test with invalid id
		printTitle("getVideos(String) with invalid user id 100");
		try
		{
			data = r.getVideos("100");
			out.println("getVideos(String) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getVideo(String) 2nd test");
		}
	}

	public static void testGetVideo() {
		//  - test with valid user id, valid Video id
		//  - test with valid user id, invalid Video id
		//  - test with invalid user id, valid Video id

		JSONObject data = null;
		JSONObject obj = null;
	
		data = r.getVideos(id); // First get a Video id to use
		JSONArray videos = (JSONArray) data.get("videos");
		Long idLong = (Long) ((JSONObject) videos.get(0)).get("id");
		int videoId = idLong.intValue(); // Got the Video id!  Now use it...

		//  Test with valid id, valid Video id
		printTitle("getVideo(String, int) with valid user id, valid Video id");
		data = r.getVideo(id, videoId);
		out.println("getVideo(String, int) 1: " + data);

		//  Test with valid id, invalid Video id
		printTitle("getVideo(String, int) with valid user id, invalid Video id");
		try
		{
			data = r.getVideo(id, -12345);
			out.println("getVideos(String, int) 2: " + data);
		}
		catch (Exception e)
		{
			out.println("Exception occurred with getVideo(String, int) 2nd test");
		}	
	}

	public static void testGetUser() {
		JSONObject data = null;
		JSONObject obj = null;

		printTitle("getUser()");
		data = c2.getUser();
		out.println("getUser() 1: " + data);
	}

	public static void testGetUser2() {
		JSONObject data = null;
		JSONObject obj = null;

		printTitle("getUser2()");
		data = r.getUser(id);
		out.println("getUser2() 1: " + data);
	}

	public static void testGetActivitiesAtom() {
		// First test with valid id
		printTitle("getActivitiesAtom(String) with valid user id");
		String activities = r.getActivitiesAtom(id);

		// Note: this prints out to System.out because it will change with time; not suitable for automatic verification
		System.out.println("getActivitiesAtom(String) 1: " + activities);

		// Second test with valid id and activity type
		printTitle("getActivitiesAtom(String, String, String, String) with valid user id and activityTypes");
		activities = r.getActivitiesAtom(id, null, null, "PhotoAdd|FriendAdd");

		// Note: this prints out to System.out because it will change with time; not suitable for automatic verification
		System.out.println("getActivitiesAtom(String) 2: " + activities);
	}

	public static void testGetFriendsActivitiesAtom() {
		// First test with valid id
		printTitle("getFriendsActivitiesAtom(String) with valid user id");
		String activities = r.getFriendsActivitiesAtom(id);

		// Note: this prints out to System.out because it will change with time; not suitable for automatic verification
		System.out.println("getFriendsActivitiesAtom(String) 1: " + activities);

		// Second test with valid id and activity type
		printTitle("getFriendsActivitiesAtom(String, String, String, String) with valid user id and activityTypes");
		activities = r.getFriendsActivitiesAtom(id, null, null, "PhotoAdd|FriendAdd");

		// Note: this prints out to System.out because it will change with time; not suitable for automatic verification
		System.out.println("getFriendsActivitiesAtom(String) 2: " + activities);
	}
	
	public static void testPutAppData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("solid", "carbon");
		map.put("liquid", "mercury");
		map.put("gas", "helium");

		printTitle("putAppData()");
		Object data = r.putAppData(id, map);
		out.println("putAppData() 1: " + data);
	}

	public static void testGetAppData() {
		JSONObject data = null;
		JSONObject obj = null;

		printTitle("getAppData()");
		data = r.getAppData(id);
		out.println("getAppData() 1: " + data);
	}

	public static void testGetAppData2() {
		JSONObject data = null;
		JSONObject obj = null;

		printTitle("getAppData2()");
		data = r.getAppData(id, "solid;liquid");
		out.println("getAppData2() 1: " + data);
	}

	public static void testClearAppData() {
		Object data = null;

		printTitle("clearAppData()");
		data = r.clearAppData(id, "solid;liquid");
		out.println("clearAppData() 1: " + data);
	}
/*
	public static void testGetFriendsAppData() throws Exception {
		String key = "http://perisphere.1939worldsfair.com/app.xml";
		String secret = "eda0c62773234093bea92645eea0493d";
			
		JSONArray data = null;
//		JSONObject data = null;

		ms = new MySpace(key, secret, ApplicationType.ON_SITE);

		// First put something
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("colour", "red");
		map.put("flavour", "spicy");
		ms.putAppData("146617378", map);
		Thread.sleep(1000);
		
		printTitle("getFriendsAppData(String) 1");
//		data = ms.getFriendsAppData("146617378");
		data = ms.getUserFriendsAppData("28568917");
//		data = ms.getFriendsAppData("456073223");
		out.println("getFriendsAppData() 1: " + data);

		printTitle("getFriendsAppData(String, String) 1");
		data = ms.getUserFriendsAppData("28568917", "colour");
		out.println("getFriendsAppData(String, String) 1: " + data);

		printTitle("getFriendsAppData(String, String) 2");
		data = ms.getUserFriendsAppData("28568917", "colour;flavour");
		out.println("getFriendsAppData(String, String) 2: " + data);
	}
*/
/*
	public static void testFriendsGetAppData2() {
		JSONObject data = null;
		JSONObject obj = null;

		printTitle("getAppData2()");
		data = r.getAppData(id, "solid;liquid");
		out.println("getAppData2() 1: " + data);
	}
*/

	public static void testGetComments() {
		JSONObject data = null;
		JSONObject obj = null;

		printTitle("getComments()");
		data = r.getComments(id);
		out.println("getComments() 1: " + data);
	}

	public static void testGetIndicators() {
		JSONObject data = null;
		JSONObject obj = null;

		printTitle("getIndicators()");
		data = r.getIndicators(id);
		out.println("getIndicators() 1: " + data);
	}

	public static void testGetPreferences() {
		JSONObject data = null;
		JSONObject obj = null;

		printTitle("getPreferences()");
		data = r.getPreferences(id);
		out.println("getPreferences() 1: " + data);
	}

	public static void testSendNotification() {
		printTitle("sendNotification()");
		String key = "http://www.myspace.com/472447237";
		String secret = "92f481784c13461ab5c5ac325615b8c1";
		OnsiteContext c = new OnsiteContext(key, secret);
		RestV1 r = new RestV1(c);
		
		HashMap<String, String> templateParameters = new HashMap<String, String>();
		templateParameters.put("content", "Test notification content");
		templateParameters.put("button0_surface", "canvas");
		templateParameters.put("button0_label", "Go To App Canvas");
		templateParameters.put("button1_surface", "appProfile");
		templateParameters.put("button1_label", "Go To App Profile");
		Object obj = r.sendNotification("135455", "454304609,28568917", templateParameters, "http://api.myspace.com/v1/users/296768296");
		out.println("sendNotification() 1: " + obj);
	}

	public static void testSetDateFormatTimeZone() {
		JSONObject data = null;

		r.setDateFormatTimeZone("iso8601", 0);
		printTitle("getMood(String) with valid date format");
		data = r.getMood(id);
		out.println("getMood(String) 1: " + data);

		r.setDateFormatTimeZone("iso8601", 2);
		printTitle("getMood(String) with a different time zone");
		data = r.getMood(id);
		out.println("getMood(String) 1: " + data);


		r.setDateFormatTimeZone("abc", 3); // Case of invalid format
		printTitle("getMood(String) with a bad date format");
		data = r.getMood(id);
		out.println("getMood(String) 1: " + data);
	}

	public static void main(String[] args) throws Exception {

		testSendNotification();
		
		testGetFriendsAppData();
		setUpForOnsiteTests();
		globalTests();
		setUpForOffsiteTests();
		
		testGetAlbums();
		testGetAlbums2();
		testGetAlbum();
		testGetAlbumInfo();
		testGetAlbumPhoto();
		testGetFriends();
		testGetFriends2();
		testGetFriendsList();
		testGetFriendsList2();
		testGetFriendship();
		testGetMood();
		testGetMoods();
		testGetPhotos();
		testGetPhoto();
		testGetProfile();
		testGetProfile2();
		testGetStatus();
		testGetStatusHistory();
		testGetFriendsStatus();
		testGetVideos();
		testGetVideo();
		testGetUser();
		testGetUser2();
		testGetActivitiesAtom();
		testGetFriendsActivitiesAtom();

		testPutAppData();
		// Put data may become available only after a delay, so sleep first
		Thread.sleep(1000);
		testGetAppData();
		testGetAppData2();
		testClearAppData();
		Thread.sleep(1000);
		testGetAppData(); // Fetch again to verify deletion
//
//		testGetComments();
		testGetIndicators();

		testPostStatus();
		testPostMood();

		testPostStatusMood();
		
		// Test dateFormat and timeZone
		testSetDateFormatTimeZone();
	}
}
