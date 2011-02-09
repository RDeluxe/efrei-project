/**
 * 
 */
package domain;

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


public class DAOArtist implements IDAOArtist {
	/** 
	 * (non-Javadoc)
	 * @see IDAOUser#addUser(User user)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Session session=OpenSession.getSession();;
	
	public DAOArtist(){
	}
	
	@Override
	public void addUser(User user) {
		// begin-user-code

		Artist artist=(Artist)user;
		Transaction tx = session.beginTransaction();
		session.save(artist);
		tx.commit();
		// end-user-code
	}

	@Override
	public void updateUser(User user) {
		// begin-user-code
		
		Transaction tx = session.beginTransaction();
		Artist a = (Artist) user;
		System.out.println(a.getDescription()+"**************************************");
		session.update(a);
		tx.commit();
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOUser#searchByLogin(String login)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Override
	public User searchByLogin(String login) {
		// begin-user-code

		Artist artist = (Artist)session.createCriteria(Artist.class).add(Restrictions.like("login", login)).uniqueResult();
		
		return artist;
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOUser#getAllArtists()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		// begin-user-code

		String queryString="from Artist";
		Query queryObject=session.createQuery(queryString);
		return (List<User>) queryObject.list();
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOUser#searchArtistByName(String firstname, String lastname)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Override
	public List<User> searchUserByName(String firstname, String lastname) {
		// begin-user-code

		@SuppressWarnings("unchecked")
		List<User> users=session.createCriteria(Artist.class).add(Restrictions.like("firstname", firstname)).add(Restrictions.like("lastname",lastname)).list();
		return users;
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOArtist#SearchArtistByTag(Tag tag)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Artist> SearchArtistByTag(Tag tag) {
		// begin-user-code
		// TODO Module de remplacement de m閠hode auto-g閚閞�
		String queryString="from Artist artist where artist.tag="+tag;
		Query queryObject=session.createQuery(queryString);
		return (List<Artist>) queryObject.list();
		// end-user-code
	}

	@Override
	public User searchUserById(int id) {
		return (User)session.get(Artist.class, id);
	}
}