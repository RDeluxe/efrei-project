package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
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
	}

	@Override
	public void updateParticipant(Participant p) {
		Transaction tx = session.beginTransaction();
		session.update(p);
		tx.commit();
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
	}
}
