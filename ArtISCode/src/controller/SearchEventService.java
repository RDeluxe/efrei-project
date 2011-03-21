package controller;

import java.util.Date;
import java.util.List;

import domain.Artist;
import domain.Event;
import domain.User;

public interface SearchEventService {
	
	public List<Event> searchByArtist(Artist a);
	public List<Event> searchByUser(User u);
	public List<Event> searchByDate(Date d);
	public Event searchById(long id);
	public List<Event> searchByName(String name);
}
