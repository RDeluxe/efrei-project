package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
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

	@Override
	public Notification getById(long id) {
		return (Notification) session.get(Notification.class, id);
	}

}
