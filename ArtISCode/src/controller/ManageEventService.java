package controller;

import domain.Artist;
import domain.Event;
import domain.Participant;
import domain.User;

public interface ManageEventService {

	public void createEvent(Event e);
	public void modifyEvent(Event e);
	public void acceptEvent(Event e, Artist a);
	public void refuseEvent(Event e, Artist a);
	public void cancelEvent(Event e, User u);
	public void inviteArtist(Event e, Artist a);
	public void cancelArtist(Event e, Artist a);
}
