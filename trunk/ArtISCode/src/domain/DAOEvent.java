package domain;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAOEvent implements IDAOEvent {

	private Session session = OpenSession.getSession();
	
	@Override
	public void addEvent(Event e) {
		Transaction tx = session.beginTransaction();
		session.save(e);
		tx.commit();
	}

	@Override
	public void updateEvent(Event e) {
		Transaction tx = session.beginTransaction();
		session.update(e);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAllEvent() {
		List<Event> ev = (List<Event>) session.createQuery("from event");
		return ev;
	}

}
