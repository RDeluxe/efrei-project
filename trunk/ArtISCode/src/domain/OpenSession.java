package domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OpenSession {

	private static Session session = null;
	
	private OpenSession() {
		
		
	}
	
	public static Session getSession(){
		if(session==null) {
			try {
				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				session = sessionFactory.openSession();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return session;
	}
	
	public static void clearSession()
	{
		session.clear();
	}
}
