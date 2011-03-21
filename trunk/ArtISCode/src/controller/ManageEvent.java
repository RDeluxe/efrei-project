package controller;

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
		
	}

	@Override
	public void inviteArtist(Event e, Artist a) {
		Participant p = new Participant();
		Set<Participant> ps = e.getArtists();
		ps.add(p);
		e.setArtists(ps);
		p.setMember(a);
		p.setUserState("OK");
		p.setArtistState("WAITING");
		daoP.addParticipant(p);
	}

}
