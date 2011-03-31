package controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import domain.Artist;
import domain.DAOArtist;
import domain.DAOEvent;
import domain.DAOParticipant;
import domain.Event;
import domain.Participant;
import domain.User;

public class ManageEvent implements ManageEventService {

	DAOEvent daoE = new DAOEvent();
	DAOParticipant daoP = new DAOParticipant();
	DAOArtist daoA = new DAOArtist();

	@Override
	public void createEvent(Event e) {
		daoE.addEvent(e);
	}

	@Override
	public void modifyEvent(Event e) {
		daoE.updateEvent(e);
	}

	@Override
	public void acceptEvent(Event e, Artist a) {
		Participant p = e.getParticipant(a);
		p.setArtistState("OK");
		daoE.updateEvent(e);
	}

	@Override
	public void refuseEvent(Event e, Artist a) {
		e.getParticipant(a).setArtistState("REFUSED");
		daoE.updateEvent(e);
	}

	@Override
	public void cancelEvent(Event e, User u) {
		Set<Participant> parts = e.getArtists();
		
		while (parts!=null && parts.size()>0) {
			Participant p = parts.iterator().next();
			parts.remove(p);
			e.setArtists(parts);
			daoP.deleteParticipant(p);
		}
		daoE.removeEvent(e);
	}

	@Override
	public void inviteArtist(Event e, Artist a) {
		Participant p = new Participant();
		p.setEvent(e);
		if (e.getArtists()==null) e.setArtists(new HashSet<Participant>());
		e.getArtists().add(p);
		p.setMember(a);
		p.setUserState("OK");
		p.setArtistState("WAITING");
		daoP.addParticipant(p);
	}

	@Override
	public void cancelArtist(Event e, Artist a) {
		Set<Participant> parts = a.getParticipants();
		boolean stop = false;
		Participant p = null;
		Iterator<Participant> it = parts.iterator();
		while (!stop && it.hasNext()) {
			p = it.next();
			if (p.getEvent().getId() == e.getId()) stop = true;
		}
		parts.remove(p);
		e.getArtists().remove(p);
		daoP.deleteParticipant(p);
	}

}
