package com.myspace.myspaceid;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.myspace.myspaceid.oauth.OAuthConsumer;
import com.myspace.myspaceid.oauth.OAuthServer;
import com.myspace.myspaceid.oauth.OAuthToken;

/**
 * Wraps MySpace's V1 REST API.
 * @author kchoo
 */
public class RestV1 extends RestAPI {
		protected static final String API_USER_URL       = "http://api.myspace.com/v1/users/%s.json"; 
		protected static final String API_ALBUMS_URL     = "http://api.myspace.com/v1/users/%s/albums.json";
		protected static final String API_ALBUM_URL      = "http://api.myspace.com/v1/users/%s/albums/%s/photos.json";
		protected static final String API_ALBUMPHOTO_URL = "http://api.myspace.com/v1/users/%s/albums/%s/photos/%s.json";
		protected static final String API_ALBUMINFO_URL  = "http://api.myspace.com/v1/users/%s/albums/%s.json";
		protected static final String API_FRIENDS_URL    = "http://api.myspace.com/v1/users/%s/friends.json";
		protected static final String API_FRIENDSLIST_URL = "http://api.myspace.com/v1/users/%s/friendslist/%s.json";
		protected static final String API_FRIENDSHIP_URL = "http://api.myspace.com/v1/users/%s/friends/%s.json";
		protected static final String API_MOOD_URL       = "http://api.myspace.com/v1/users/%s/mood.json";
		protected static final String API_MOODS_URL      = "http://api.myspace.com/v1/users/%s/moods.json";
		protected static final String API_PHOTOS_URL     = "http://api.myspace.com/v1/users/%s/photos.json";
		protected static final String API_PHOTO_URL      = "http://api.myspace.com/v1/users/%s/photos/%s.json";
		protected static final String API_PROFILE_URL    = "http://api.myspace.com/v1/users/%s/profile.json";
		protected static final String API_FRIENDS_STATUS_URL = "http://api.myspace.com/v1/users/%s/friends/status.json";
		protected static final String API_STATUS_URL     = "http://api.myspace.com/v1/users/%s/status.json";
		protected static final String API_PUT_STATUS_URL = "http://api.myspace.com/v1/users/%s/status";
		protected static final String API_PUT_MOOD_URL = "http://api.myspace.com/v1/users/%s/mood";
		protected static final String API_VIDEOS_URL     = "http://api.myspace.com/v1/users/%s/videos.json";
		protected static final String API_VIDEO_URL      = "http://api.myspace.com/v1/users/%s/videos/%s.json";
		protected static final String API_ACTIVITIES_URL = "http://api.myspace.com/v1/users/%s/activities.atom";
		protected static final String API_FRIENDSACTIVITIES_URL = "http://api.myspace.com/v1/users/%s/friends/activities.atom";
		protected static final String API_GLOBAL_APP_DATA_URL	 = "http://api.myspace.com/v1/appdata/global.json";
		protected static final String API_GLOBAL_APP_DATA_KEYS_URL	 = "http://api.myspace.com/v1/appdata/global/%s.json";
		protected static final String API_GLOBAL_APP_DATA_XML_URL	 = "http://api.myspace.com/v1/appdata/global";
		protected static final String API_GLOBAL_APP_DATA_KEYS_XML_URL	 = "http://api.myspace.com/v1/appdata/global/%s";
		protected static final String API_APP_DATA_URL	 = "http://api.myspace.com/v1/users/%s/appdata.json";
		protected static final String API_APP_DATA_KEYS_URL	 = "http://api.myspace.com/v1/users/%s/appdata/%s.json";
		protected static final String API_APP_DATA_XML_URL	= "http://api.myspace.com/v1/users/%s/appdata";
		protected static final String API_APP_DATA_KEYS_XML_URL	 = "http://api.myspace.com/v1/users/%s/appdata/%s";
		protected static final String API_FRIENDS_APP_DATA_URL	 = "http://api.myspace.com/v1/users/%s/friends/appdata.json";
		protected static final String API_FRIENDS_APP_DATA_KEYS_URL	 = "http://api.myspace.com/v1/users/%s/friends/appdata/%s.json";
		protected static final String API_COMMENTS_URL   = "http://api.myspace.com/v1/users/%s/comments.json";
		protected static final String API_INDICATORS_URL   = "http://api.myspace.com/v1/users/%s/indicators.json";
		protected static final String API_PREFERENCES_URL   = "http://api.myspace.com/v1/users/%s/preferences.json";
		protected static final String API_NOTIFICATIONS_URL   = "http://api.myspace.com/v1/applications/%s/notifications";

		//
	    // Wrappers for MySpace REST APIs
	    //

		/**
		 * Creates a RestV1 object. 
		 * @param securityContext A security context
		 */
		public RestV1(SecurityContext securityContext) {
			super(securityContext);
		}
		
		public Object postNotifications(String appId, Map<String, String> appParams) {
			String url = API_NOTIFICATIONS_URL.replaceFirst("%s", appId);
			return putUserData("POST", url, new HashMap<String, String>(), appParams);
		}
		
		/**
		 * Returns global app data for this app.
		 * @return app data.
		 */
	    public JSONObject getGlobalAppData() {
			String url = API_GLOBAL_APP_DATA_URL;
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}

		/**
		 * Returns global app data for this app under the given keys.
		 * @param keys semicolon-separated keys
		 * @return Values fetched for the given keys
		 */
	    public JSONObject getGlobalAppData(String keys) {
			String url = API_GLOBAL_APP_DATA_KEYS_URL.replaceFirst("%s", keys);
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}

		/**
		 * Sets the given key-value pairs into the global app data.
		 * @param appParams HashMap containing key-value pairs to store.
		 * @return 
		 */
	    public Object putGlobalAppData(Map<String, String> appParams) {
			String url = API_GLOBAL_APP_DATA_XML_URL;
			return putUserData(url, new HashMap<String, String>(), appParams);
		}

		/**
		 * Clears the global app data with the given keys.
		 * @param keys semicolon-separated keys
		 * @return 
		 */
	    public Object clearGlobalAppData(String keys) {
			String url = API_GLOBAL_APP_DATA_KEYS_XML_URL.replaceFirst("%s", keys);
			return putUserData("DELETE", url, new HashMap<String, String>(), new HashMap<String, String>());
		}

		/**
		 * Sets the given key-value pairs into a user's app data.
		 * @param userId ID of user to retrieve app data for.
		 * @param appParams HashMap containing key-value pairs to store.
		 * @return 
		 */
	    public Object putAppData(String userId, Map<String, String> appParams) {
			securityContext.checkIfAuthorized();
			String url = API_APP_DATA_XML_URL.replaceFirst("%s", userId);
			return putUserData(url, new HashMap<String, String>(), appParams);
		}

		/**
		 * Returns app data for specified user.
		 * @param userId ID of user to retrieve app data for.
		 * @return app data of specified user
		 */
	    public JSONObject getAppData(String userId) {
			securityContext.checkIfAuthorized();
//			String url = API_APP_DATA_URL.replaceFirst("%s", userId);
//			return getUserData(url, new HashMap<String, String>());
			return getAppData(userId, null);
		}

		/**
		 * Returns app data for specified user.
		 * @param userId ID of user to retrieve app data for.
		 * @param keys semicolon-separated keys
		 * @return app data corresponding to given keys
		 */
	    public JSONObject getAppData(String userId, String keys) {
			securityContext.checkIfAuthorized();
			String url = null;
			if (keys == null)
				url = API_APP_DATA_URL.replaceFirst("%s", userId);
			else
				url = API_APP_DATA_KEYS_URL.replaceFirst("%s", userId).replaceFirst("%s", keys);
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}


	    /**
		 * Sets the given key-value pairs into the global app data.
		 * @param keys semicolon-separated keys.
		 * @return 
		 */
	    public Object clearAppData(String userId, String keys) {
			securityContext.checkIfAuthorized();
			String url = API_APP_DATA_KEYS_XML_URL.replaceFirst("%s", userId).replaceFirst("%s", keys);
			return putUserData("DELETE", url, new HashMap<String, String>(), new HashMap<String, String>());
		}

	    //! To verify after server side is fixed
	    /**
	     * This apparently has a bug on the server side.
		 * Returns app data for specified user's friends.
		 * @param userId ID of user to retrieve app data for.
		 * @return app data of specified user's friends
		 */
	    public JSONArray getUserFriendsAppData(String userId) {
			securityContext.checkIfAuthorized();
//			String url = API_APP_DATA_URL.replaceFirst("%s", userId);
//			return getUserData(url, new HashMap<String, String>());
			return getUserFriendsAppData(userId, null);
		}

	    //! To test!
		/**
		 * This method is untested!
		 * Returns app data for specified user's friends
		 * @param userId ID of user to retrieve app data for.
		 * @param keys semicolon-separated keys
		 * @return app data corresponding to given keys
		 */
	    public JSONArray getUserFriendsAppData(String userId, String keys) {
			securityContext.checkIfAuthorized();
			String url = null;
			if (keys == null)
				url = API_FRIENDS_APP_DATA_URL.replaceFirst("%s", userId);
			else
				url = API_FRIENDS_APP_DATA_KEYS_URL.replaceFirst("%s", userId).replaceFirst("%s", keys);
			return (JSONArray) getUserData(url, new HashMap<String, String>());
		}

	    /**
	     * Returns a user given an Id.
		 * @param userId ID of user
		 * @return user object given his id
	     */
	    public JSONObject getUser(String userId) {
			securityContext.checkIfAuthorized();
			String url = API_USER_URL.replaceFirst("%s", userId);
			return (JSONObject) getUserData(url, new HashMap<String, String>());
	    }
	    
	    /**
		 * Returns comments for a user.  This call works only for certain partners.
		 * @param userId ID of user
		 * @return comments for a user.
		 */
	    public JSONObject getComments(String userId) {
			securityContext.checkIfAuthorized();
			String url = API_COMMENTS_URL.replaceFirst("%s", userId);
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}

		/**
		 * Returns indicators for a user.
		 * @param userId ID of user
		 * @return indicators for a user.
		 */
	    public JSONObject getIndicators(String userId) {
			securityContext.checkIfAuthorized();
			String url = API_INDICATORS_URL.replaceFirst("%s", userId);
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}

		/**
		 * Returns preferences for a user.  This applies to OpenSocial apps only.
		 * @param userId ID of user
		 * @return preferences for a user.
		 */
	    public JSONObject getPreferences(String userId) {
			securityContext.checkIfAuthorized();
			String url = API_PREFERENCES_URL.replaceFirst("%s", userId);
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}

		/**
		 * Returns the albums of the given user.
		 * @param userId ID of user to query.
		 * @return the albums of the given user.
		 */
	    public JSONObject getAlbums(String userId) {
			return getAlbums(userId, -1, -1);
		}

		/**
		 * Returns the albums of the given user.
		 * @param userId ID of user to query.
		 * @param page Which page.  Pass -1 to not specify.
		 * @param pageSize Number of items per page.  Pass -1 to not specify.
		 * @return the albums of the given user.
		 */
		public JSONObject getAlbums(String userId, int page, int pageSize) {
			//securityContext.checkIfAuthorized();
			HashMap<String, String> map = new HashMap<String, String>();
			if (page != -1)
				map.put("page", String.valueOf(page));
			if (pageSize != -1)
				map.put("page_size", String.valueOf(pageSize));
			String url = API_ALBUMS_URL.replaceFirst("%s", userId);
			return (JSONObject) getUserData(url, map);
		}

		/**
		 * Returns an album of the given user.
		 * @param userId ID of user to query.
		 * @param albumId Which album to return.
		 * @return an album of the given user.
		 */
		public JSONObject getAlbum(String userId, int albumId) {
			//securityContext.checkIfAuthorized();
			HashMap<String, String> map = new HashMap<String, String>();
			String url = API_ALBUM_URL.replaceFirst("%s", userId);
			url = url.replaceFirst("%s", Integer.toString(albumId));
			return (JSONObject) getUserData(url, map);
		}

		/**
		 * Returns a photo in an album of the given user.
		 * @param userId ID of user to query.
		 * @param albumId Which album.
		 * @param photoId Which photo to return.
		 * @return a photo in an album of the given user.
		 */
		public JSONObject getAlbumPhoto(String userId, int albumId, int photoId) {
			//securityContext.checkIfAuthorized();
			HashMap<String, String> map = new HashMap<String, String>();
			String url = API_ALBUMPHOTO_URL.replaceFirst("%s", userId);
			url = url.replaceFirst("%s", Integer.toString(albumId)).replaceFirst("%s", Integer.toString(photoId));
			return (JSONObject) getUserData(url, map);
		}

		/**
		 * Returns an album's info for the given user.
		 * @param userId ID of user to query.
		 * @param albumId Which album to return.
		 * @return an album's info for the given user.
		 */
		public JSONObject getAlbumInfo(String userId, int albumId) {
			//securityContext.checkIfAuthorized();
			HashMap<String, String> map = new HashMap<String, String>();
			String url = API_ALBUMINFO_URL.replaceFirst("%s", userId);
			url = url.replaceFirst("%s", Integer.toString(albumId));
			return (JSONObject) getUserData(url, map);
		}

		/**
		 * Returns the friends of the given user.
		 * @param userId ID of user to query.
		 * @return an album of the given user.
		 */
	    public JSONObject getFriends(String userId) {
			return getFriends(userId, -1, -1, null, null);
		}

	    protected void validateShowString(String show) throws MySpaceException {
	    	int i = 0;
			String[] validShowValues = {"mood", "status", "online"};
			if (show != null) {
				String[] showParams = show.split("\\|");
				for (int j = 0; j < showParams.length; j++) {
	//System.out.println("j = " + j + ", " + showParams[j]);
					for (i = 0; i < validShowValues.length && !showParams[j].equals(validShowValues[i]); i++) {}
					if (i == validShowValues.length)
						throw new MySpaceException("Invalid value '" + showParams[j] + "' for show paramater.  Must be one of mood, status or online.");
				}
			}
	    }
	    
		/**
		 * Returns the friends of the given user.
		 * @param userId ID of user to query.
		 * @param page Which page.  First page is numbered '1'.  Pass -1 to not specify.
		 * @param pageSize Number of items per page.  Pass -1 to not specify.
		 * @param list Can be one of 'top', 'online' or 'app'
		 * @param show can be a combination of 'mood', 'status', 'online' separated by '|'.  Do not put spaces in this string.
		 * @return an album of the given user.
		 */
	    public JSONObject getFriends(String userId, int page, int pageSize, String list, String show) { 
			//securityContext.checkIfAuthorized();
			HashMap<String, String> map = new HashMap<String, String>();

			// Validate list and show parameters
			int i;
			String[] validListValues = {"top", "online", "app"};
			for (i = 0; list != null && i < validListValues.length && !list.equals(validListValues[i]); i++) {}
			if (i == validListValues.length)
				throw new MySpaceException("Invalid value '" + list + "' for list paramater.  Must be one of top, online or app.");

			validateShowString(show);

			// Prep params and then send request
			if (page != -1)
				map.put("page", String.valueOf(page));
			if (pageSize != -1)
				map.put("page_size", String.valueOf(pageSize));
			if (list != null)
				map.put("list", list);
			if (show != null)
				map.put("show", show);
			String url = API_FRIENDS_URL.replaceFirst("%s", userId);
			return (JSONObject) getUserData(url, map);
		}

	    /**
	     * Returns the specified friends of the given user.
	     * @param userId ID of user to query.
	     * @param friends List of friend ID's separated by semicolons.
	     * @return the specified friends of the given user.
	     */
	    public JSONObject getFriendsList(String userId, String friends) {
	    	return getFriendsList(userId, friends, null);
	    }
	    
	    /**
	     * Returns the specified friends of the given user.
	     * @param userId ID of user to query.
	     * @param friends List of friend ID's separated by semicolons.
	     * @param show can be a combination of 'mood', 'status', 'online' separated by '|'.  Do not put spaces in this string.
	     * @return the specified friends of the given user.
	     */
	    public JSONObject getFriendsList(String userId, String friends, String show) {
			//securityContext.checkIfAuthorized();
			HashMap<String, String> map = new HashMap<String, String>();

			validateShowString(show);

			// Prep params and then send request
			if (show != null)
				map.put("show", show);
			String url = API_FRIENDSLIST_URL.replaceFirst("%s", userId).replaceFirst("%s", friends);
			return (JSONObject) getUserData(url, map);
	    }
	    
		/**
		 * Returns the friendship of the given user with other users.
		 * @param userId ID of user to query.
		 * @param friendIds IDs of friends to check, separated by semicolons.
		 * @return the friendship of the given user with other users.
		 */
		public JSONObject getFriendship(String userId, String friendIds) {
			//securityContext.checkIfAuthorized();
			String url = API_FRIENDSHIP_URL.replaceFirst("%s", userId).replaceFirst("%s", friendIds);
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}

		/**
		 * Returns the mood of the given user.
		 * @param userId ID of user to query.
		 * @return the mood of the given user.
		 */
	    public JSONObject getMood(String userId) {
			//securityContext.checkIfAuthorized();
			String url = API_MOOD_URL.replaceFirst("%s", userId);
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}

		/**
		 * Returns the moods available to the given user.
		 * @param userId ID of user to query.
		 * @return the moods available to the given user.
		 */
	    public JSONObject getMoods(String userId) {
			//securityContext.checkIfAuthorized();
			String url = API_MOODS_URL.replaceFirst("%s", userId);
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}

		/**
		 * Returns the photos of the given user.
		 * @param userId ID of user to query.
		 * @param page Which page.  Pass -1 to not specify.
		 * @param pageSize Number of items per page.  Pass -1 to not specify.
		 * @return the photos of the given user.
		 */
		 public JSONObject getPhotos(String userId, int page, int pageSize) {
			//securityContext.checkIfAuthorized();
			HashMap<String, String> map = new HashMap<String, String>();
			if (page != -1)
				map.put("page", String.valueOf(page));
			if (pageSize != -1)
				map.put("page_size", String.valueOf(pageSize));
			String url = API_PHOTOS_URL.replaceFirst("%s", userId);
			return (JSONObject) getUserData(url, map);
		}

		/**
		 * Returns a photo of the given user.
		 * @param userId ID of user to query.
		 * @param photoId Id of photo to get.
		 * @return a photo of the given user.
		 */
	    public JSONObject getPhoto(String userId, int photoId) {
			//securityContext.checkIfAuthorized();
			String url = API_PHOTO_URL.replaceFirst("%s", userId).replaceFirst("%s", String.valueOf(photoId));
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}

		/**
		 * Returns the profile of a given user.
		 * @param userId ID of user to query.
		 * @return the profile of the given user.
		 */
	    public JSONObject getProfile(String userId) {
			return getProfile(userId, "full"); 
		}
	 
		/**
		 * Returns the profile of a given user.
		 * @param userId ID of user to query.
		 * @param detailType "basic", "full" or "extended"
		 * @return the profile of the given user.
		 */
	    public JSONObject getProfile(String userId, String detailType) {
			//securityContext.checkIfAuthorized();
			String url = API_PROFILE_URL.replaceFirst("%s", userId);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("detailtype", detailType);
			return (JSONObject) getUserData(url, map);
		} 
	 
		/**
		 * Returns the status of a given user.
		 * @param userId ID of user to query.
		 * @return the status of the given user.
		 */
	    public JSONObject getStatus(String userId) {
			//securityContext.checkIfAuthorized();
			String url = API_STATUS_URL.replaceFirst("%s", userId);
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}

		/**
		 * Returns the status history of a given user.
		 * @param userId ID of user to query.
		 * @return the status of the given user.
		 */
	    public String getStatusHistory(String userId) {
			String url = API_ACTIVITIES_URL.replaceFirst("%s", userId);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("activityTypes", "StatusMoodUpdate");
			String reqUrl = server.generateRequestUrl(url, map);
			String response = server.doHttpReq(reqUrl);
			return response;
		}

	    /**
		 * Posts a status update.
		 * @param userId ID of user to query.
		 * @param Status update to post.
		 * @return the return string from the server.
		 * @deprecated Please use {@link #setStatus}
		 */
	    @Deprecated public Object postStatus(String userId, String status) {
			//securityContext.checkIfAuthorized();
			String url = API_PUT_STATUS_URL.replaceFirst("%s", userId);
			HashMap<String, String> map = new HashMap<String, String>();
			HashMap<String, String> appParams = new HashMap<String, String>();
			appParams.put("status", status);
			return putUserData(url, map, appParams);
		}
		
		/**
		 * Posts a mood update.
		 * @param userId ID of user to query.
		 * @param mood update to post.
		 * @return the return string from the server.
		 * @deprecated Please use {@link #setMood}
		 */
	    @Deprecated public Object postMood(String userId, int mood) {
			//securityContext.checkIfAuthorized();
			String url = API_PUT_MOOD_URL.replaceFirst("%s", userId);
			HashMap<String, String> map = new HashMap<String, String>();
			HashMap<String, String> appParams = new HashMap<String, String>();
			appParams.put("mood", "" + mood);
			return putUserData(url, map, appParams);
		}

		/**
		 * Posts a mood update.
		 * @param userId ID of user to query.
		 * @param mood Mood update to post.
		 * @return the return string from the server.
		 */
		public Object setMood(String userId, int mood) {
			return postMood(userId, mood);
		}

		/**
		 * Posts a status update.
		 * @param userId ID of user to query.
		 * @param status Status update to post.
		 * @return the return string from the server.
		 */
	    public Object setStatus(String userId, String status) {
			return postStatus(userId, status);
		}
	    
	    /**
		 * Posts a status and mood update at the same time.
		 * @param userId ID of user to query.
		 * @param status Status update to post.
		 * @param mood Mood update to post.
		 * @return the return string from the server.
	     */
	    public Object setStatusMood(String userId, String status, int mood) {
			String url = API_PUT_MOOD_URL.replaceFirst("%s", userId);
			HashMap<String, String> map = new HashMap<String, String>();
			HashMap<String, String> appParams = new HashMap<String, String>();
			appParams.put("status", status);
			appParams.put("mood", "" + mood);
			return putUserData(url, map, appParams);
		}
	    
		/**
		 * Returns the status of a given user's friends.
		 * @param userId ID of user to query.
		 * @return the status of the given user's friends.
		 */
	    public JSONObject getFriendsStatus(String userId) {
			//securityContext.checkIfAuthorized();
			String url = API_FRIENDS_STATUS_URL.replaceFirst("%s", userId);
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}

		
		/**
		 * Returns the videos of a given user.
		 * @param userId ID of user to query.
		 * @return the videos of the given user.
		 */
	    public JSONObject getVideos(String userId) {
			//securityContext.checkIfAuthorized();
			String url = API_VIDEOS_URL.replaceFirst("%s", userId);
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}

		/**
		 * Returns a video of the given user.
		 * @param userId ID of user to query.
		 * @param videoId Id of photo to get.
		 * @return a video of the given user.
		 */
	    public JSONObject getVideo(String userId, int videoId) {
			//securityContext.checkIfAuthorized();
			String url = API_VIDEO_URL.replaceFirst("%s", userId).replaceFirst("%s", String.valueOf(videoId));
			return (JSONObject) getUserData(url, new HashMap<String, String>());
		}

		/**
		 * Returns the activities of the user.
		 * @param userId The ID of the user
		 * @return the activity stream of the user.
		 */
	    public String getActivitiesAtom(String userId) {
			return getActivitiesAtom(userId, null, null, null);
		}

		/**
		 * Returns the activities of the user.
		 * @param userId The ID of the user
		 * @param culture (not used yet)
		 * @param lastRetrievalTimeStamp (not used yet)
		 * @param activityTypes takes in piped (|) activity type parameters to filter result data; http://developerwiki.myspace.com/index.php?title=ActivityStream_Queries
		 * @return the activity stream of the user.
		 */
		public String getActivitiesAtom(String userId, String culture, String lastRetrievalTimeStamp, String activityTypes) {
			//securityContext.checkIfAuthorized();
			String url = API_ACTIVITIES_URL.replaceFirst("%s", userId);
			HashMap<String, String> map = new HashMap<String, String>();
			if (activityTypes != null) {
				map.put("activityTypes", activityTypes);
			}
			String reqUrl = server.generateRequestUrl(url, map);
			String response = server.doHttpReq(reqUrl);
			return response;
		}
		//! Test this

		/**
		 * Returns the activities of the user's friends.
		 * @param userId The ID of the user
		 * @return the activity stream of the user's friends.
		 */
	    public String getFriendsActivitiesAtom(String userId) {
			return getFriendsActivitiesAtom(userId, null, null, null);
		}

		/**
		 * Returns the activities of the user's friends.
		 * @param userId The ID of the user
		 * @param culture (not used yet)
		 * @param retrieveUntilDate (not used yet)
		 * @param activityTypes takes in piped (|) activity type parameters to filter result data; http://developerwiki.myspace.com/index.php?title=ActivityStream_Queries
		 * @return the activity stream of the user's friends.
		 */
		public String getFriendsActivitiesAtom(String userId, String culture, String lastRetrievalTimeStamp, String activityTypes) {
			//securityContext.checkIfAuthorized();
			String url = API_FRIENDSACTIVITIES_URL.replaceFirst("%s", userId);
			HashMap<String, String> map = new HashMap<String, String>();
			if (activityTypes != null) {
				map.put("activityTypes", activityTypes);
			}
			String reqUrl = server.generateRequestUrl(url, map);
			String response = server.doHttpReq(reqUrl);
			return response;
		}
		//! Test this

		/**
		 * Posts a notification to a list of recipients.  At most 1000 recipients can be specified.  You will need to pass in 
		 * a template, which specifies the text in the notification, the buttons, and where the buttons link to.  
		 * @param recipients A comma-separated list of recipients.
		 * @param templateParameters Parameters defining the template for the notification.  This is a Map.  Possible key values are:
		 *        <ol>  
		 *        <li> content (required) - Text content of the notification
		 *        <li> button0_surface (optional) - where button 0 should link to: "canvas" or "appProfile"
		 *        <li> button0_label (optional) - text label on button 0
		 *        <li> button1_surface (optional) - where button 1 should link to: "canvas" or "appProfile"
		 *        <li> button1_label (optional) - text label on button 1
		 *        </ol>
		 * @param mediaItems A URI to a MySpace image, either a profile image or an album photo. External images are not allowed. 
		 *        At this time, only one media item is supported. (optional; pass null to not specify).
		 */
		public Object sendNotification(String appId, String recipients, Map<String, String> templateParameters, String mediaItems) {
			if (templateParameters.get("content") == null)
				throw new MySpaceException("'content' key required in templateParameters Map");

			// Convert templateParameters to a string representation
			StringBuffer sb = new StringBuffer("{");
			Iterator it = templateParameters.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				sb.append("\"").append(key).append("\"").append(":").append("\"").append(templateParameters.get(key)).append("\"");
				sb.append(it.hasNext() ? "," : "}");
			}
			String templateParametersStr = sb.toString();
//			System.out.println(">>>>>> templateParametersStr = " + templateParametersStr);
			
			// Put mediaItems in braces, as required by the REST API
			mediaItems = "{\"" + mediaItems + "\"}";

			// Send request
			HashMap<String, String> appParams = new HashMap<String, String>();
			appParams.put("recipients", recipients);
			appParams.put("templateParameters", templateParametersStr);
			appParams.put("mediaItems", mediaItems);

			String url = API_NOTIFICATIONS_URL.replaceFirst("%s", appId);
			return putUserData("POST", url, new HashMap<String, String>(), appParams);
		}
		
		/**
		 * @param args Arguments passed in.
		 */
//		public static void main(String[] args) throws Exception {
//		}
}
