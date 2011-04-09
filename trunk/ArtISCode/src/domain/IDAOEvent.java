package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.util.List;

public interface IDAOEvent {

	public void addEvent(Event e);
	public void updateEvent(Event e);
	public List<Event> getAllEvent();
	public void removeEvent(Event e);
}
