package controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import domain.Artist;
import domain.DAOEvent;
import domain.Event;
import domain.User;

public class SearchEventEngine implements SearchEventService {

	DAOEvent daoE = new DAOEvent();
	
	@Override
	public List<Event> searchByArtist(Artist a) {
		List<Event> events = daoE.getAllEvent();		
		Iterator<Event> it = events.iterator();
		while (it.hasNext()) {
			Event e = it.next();
			if (e.getParticipant(a) != null) events.remove(e);
		}
		return events;
	}

	@Override
	public List<Event> searchByUser(User u) {
		List<Event> events = daoE.getAllEvent();		
		Iterator<Event> it = events.iterator();
		while (it.hasNext()) {
			Event e = it.next();
			if (e.getOwner() != u) events.remove(e);
		}
		return events;
	}

	@Override
	public List<Event> searchByDate(Date d) {
		List<Event> events = daoE.getAllEvent();		
		Iterator<Event> it = events.iterator();
		while (it.hasNext()) {
			Event e = it.next();
			if (e.getDate() != d) events.remove(e);
		}
		return events;
	}

	@Override
	public Event searchById(long id) {
		List<Event> events = daoE.getAllEvent();
		Iterator<Event> it = events.iterator();
		while (it.hasNext()) {
			Event e = it.next();
			if (e.getId()==id) return e;
		}
		return null;
	}

}
