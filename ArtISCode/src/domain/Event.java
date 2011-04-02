package domain;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class Event {

	private String name;
	private long id;
	private Date date;
	private int duration;
	private User owner;
	private Set<Participant> artists;
	private Address address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public User getOwner() {
		return owner;
	}
	public void setArtists(Set<Participant> artists) {
		this.artists = artists;
	}
	public Set<Participant> getArtists() {
		return artists;
	}
	
	public Participant getParticipant(Artist a) {
		Iterator<Participant> it = artists.iterator();
		boolean found = false;
		Participant p = null;
		while (!found && it.hasNext()) {
			p = it.next();
			if (p.getMember().getLogin().equals(a.getLogin())) {
				found = true;
			}
		}
		return p;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Address getAddress() {
		return address;
	}
	
}
