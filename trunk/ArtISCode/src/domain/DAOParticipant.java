package domain;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAOParticipant implements IDAOParticipant {

	private Session session = OpenSession.getSession();
	
	@Override
	public void addParticipant(Participant p) {
		Transaction tx = session.beginTransaction();
		session.save(p);
		tx.commit();
		session.clear();
	}

	@Override
	public void updateParticipant(Participant p) {
		Transaction tx = session.beginTransaction();
		session.update(p);
		tx.commit();
		session.clear();
	}

	@Override
	public List<Participant> getAllParticipant() {
		List<Participant> p = (List<Participant>) session.createQuery("from Participant");
		return p;
	}

	@Override
	public void deleteParticipant(Participant p) {
		Transaction tx = session.beginTransaction();
		session.delete(p);
		tx.commit();
		session.clear();
	}
}
