package domain;

import java.util.List;

public interface IDAOEvent {

	public void addEvent(Event e);
	public void updateEvent(Event e);
	public List<Event> getAllEvent();
}
