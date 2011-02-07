/**
 * 
 */
package domain;

import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Benjamin BOZOU
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DAOUser implements IDAOUser {
	/** 
	 * (non-Javadoc)
	 * @see IDAOUser#addUser(User user)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private User user;
	private SessionFactory sessionFactory;
	private Session session=null;
	private Transaction tx;
	
	public DAOUser()
	{
		try{
			sessionFactory=new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
		}catch(Exception e)
		{
		}
	}
	
	public void addUser(User u) {
		// begin-user-code
		// TODO Module de remplacement de m閠hode auto-g閚閞�
		User newuser =new User();
		
		newuser.setId(u.getId());
		newuser.setFirstname(u.getFirstname());
		newuser.setLastname(u.getLastname());
		newuser.setLogin(u.getLogin());
		newuser.setPassword(u.getPassword());
		
		// end-use-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOUser#updateUser(Integer id_user, User user)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void updateUser(Integer id_user, User u) {
		// begin-user-code
		// TODO Module de remplacement de m閠hode auto-g閚閞�

		User newuser=(User)session.get(User.class,id_user);
		newuser.setFirstname(u.getFirstname());
		newuser.setLastname(u.getLastname());
		newuser.setLogin(u.getLogin());
		newuser.setPassword(u.getPassword());
		
		tx.commit();
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOUser#searchByLogin(String login)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public User searchByLogin(String login) {
		// begin-user-code
		// TODO Module de remplacement de m閠hode auto-g閚閞�
		/*Vector result=new Vector(); */
		User newuser=(User)session.get(User.class, login);
		
		return newuser;
	//	return null;
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOUser#getAllArtists()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<User> getAllArtists() {
		// begin-user-code
		// TODO Module de remplacement de m閠hode auto-g閚閞�
		Set<User> users=null;
		String queryString="from User";
		Query queryObject=session.createQuery(queryString);
		return (Set<User>) queryObject.list();
	//	return null;
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOUser#searchArtistByName(String firstname, String lastname)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<User> searchArtistByName(String firstname, String lastname) {
		// begin-user-code
		// TODO Module de remplacement de m閠hode auto-g閚閞�
		List contacts=session.createCriteria(User.class).add(Restrictions.like("firstname", firstname)).add(Restrictions.like("lastname",lastname)).setMaxResults(3).list();
		return (Set<User>)contacts;
		/*return null;*/
		// end-user-code
	}
}