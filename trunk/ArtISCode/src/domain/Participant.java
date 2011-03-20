package domain;

public class Participant {

	private long id;
	private Artist member;
	private Event event;
	private boolean acceptedByUser, acceptedByArtist, UserCancelationRequest, ArtistCancelationRequest;
	
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
	public boolean isAcceptedByUser() {
		return acceptedByUser;
	}
	public void setAcceptedByUser(boolean acceptedByUser) {
		this.acceptedByUser = acceptedByUser;
	}
	public boolean isAcceptedByArtist() {
		return acceptedByArtist;
	}
	public void setAcceptedByArtist(boolean acceptedByArtist) {
		this.acceptedByArtist = acceptedByArtist;
	}
	public boolean isUserCancelationRequest() {
		return UserCancelationRequest;
	}
	public void setUserCancelationRequest(boolean userCancelationRequest) {
		UserCancelationRequest = userCancelationRequest;
	}
	public boolean isArtistCancelationRequest() {
		return ArtistCancelationRequest;
	}
	public void setArtistCancelationRequest(boolean artistCancelationRequest) {
		ArtistCancelationRequest = artistCancelationRequest;
	}
	
}
