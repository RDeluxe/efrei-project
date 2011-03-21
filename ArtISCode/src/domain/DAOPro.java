package domain;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DAOPro implements IDAOPro {

	private Session session=OpenSession.getSession();
	
	@Override
	public void addUser(User user) {
		Entertainment_Pro pro=(Entertainment_Pro)user;
		Transaction tx = session.beginTransaction();
		session.save(pro);
		tx.commit();
	}

	@Override
	public void updateUser(User user) {
		
		Transaction tx = session.beginTransaction();
		Entertainment_Pro pro = (Entertainment_Pro) user;
		session.update(pro);
		tx.commit();
	}

	@Override
	public User searchByLogin(String login) {
		Entertainment_Pro pro = (Entertainment_Pro)session.createCriteria(Entertainment_Pro.class).add(Restrictions.like("login", login)).uniqueResult();
		
		return pro;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> searchUserByName(String firstname, String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User searchUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
