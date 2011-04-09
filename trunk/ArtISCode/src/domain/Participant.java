package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
public class Participant {

	private long id;
	private Artist member;
	private Event event;
	private String artistState, userState;
	
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public Artist getMember() {
		return member;
	}
	public void setMember(Artist member) {
		this.member = member;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public void setArtistState(String artistState) {
		this.artistState = artistState;
	}
	public String getArtistState() {
		return artistState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getUserState() {
		return userState;
	}
	
}
