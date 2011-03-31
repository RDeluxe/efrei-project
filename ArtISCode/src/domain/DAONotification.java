package domain;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAONotification implements IDAONotification {

	private Session session = OpenSession.getSession();
	
	@Override
	public void addNotification(Notification n) {
		Transaction tx = session.beginTransaction();
		session.save(n);
		tx.commit();
	}

	@Override
	public void removeNotification(Notification n) {
		Transaction tx = session.beginTransaction();
		session.delete(n);
		tx.commit();
	}

}
