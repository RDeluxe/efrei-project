package domain;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class DAONews implements IDAONews {

	Session session = OpenSession.getSession();
	
	@Override
	public void addNews(News n) {
		session.beginTransaction();
		session.save(n);
		session.getTransaction().commit();
	}

	@Override
	public void deleteNews(News n) {
		session.beginTransaction();
		session.delete(n);
		session.getTransaction().commit();
	}

	@Override
	public List<News> getAllNews() {
		return (List<News>) session.createCriteria(News.class).addOrder(Order.desc("id")).list();
	}

	@Override
	public List<News> getLastNews() {
		return (List<News>) session.createCriteria(News.class).addOrder(Order.desc("id")).setMaxResults(2).list();
	}

}
