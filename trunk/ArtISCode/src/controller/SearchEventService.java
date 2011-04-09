package controller;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
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
