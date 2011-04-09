/**
 * 
 */
package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
	private Session session=OpenSession.getSession();
	
	public DAOUser()
	{
	}
	
	public void addUser(User u) {
		// begin-user-code

		Transaction tx = session.beginTransaction();
		session.save(u);
		tx.commit();
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOUser#updateUser(Integer id_user, User user)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void updateUser(User u) {
		// begin-user-code

		Transaction tx = session.beginTransaction();
		session.update(u);
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

		User newuser = (User)session.createCriteria(User.class).add(Restrictions.like("login", login)).uniqueResult();
		
		return newuser;
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOUser#getAllArtists()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		// begin-user-code
		
		String queryString="from User";
		Query queryObject=session.createQuery(queryString);
		return (List<User>) queryObject.list();
	//	return null;
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOUser#searchArtistByName(String firstname, String lastname)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@SuppressWarnings("unchecked")
	public List<User> searchUserByName(String firstname, String lastname) {
		// begin-user-code
		// TODO Module de remplacement de m閠hode auto-g閚閞�
		List<User> users=session.createCriteria(User.class).add(Restrictions.like("firstname", firstname)).add(Restrictions.like("lastname",lastname)).list();
		return users;
		/*return null;*/
		// end-user-code
	}

	@Override
	public User searchUserById(long id) {
		return (User) session.get(User.class, id);
	}
}