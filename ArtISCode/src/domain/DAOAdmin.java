/**
 * 
 */
package domain;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Benjamin BOZOU
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DAOAdmin implements IDAOAdmin {
	/** 
	 * (non-Javadoc)
	 * @see IDAOAdmin#addAdmin(Admin admin)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Session session=OpenSession.getSession();;
	//private Transaction tx;
	
	public DAOAdmin() {
		
	}
	
	public void addAdmin(Admin admin) {
		// begin-user-code

		Transaction tx = session.beginTransaction();
		session.save(admin);
		tx.commit();
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOAdmin#updateAdmin(Integer admin_id, Admin admin)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void updateAdmin(Admin admin) {
		// begin-user-code
		
		Transaction tx = session.beginTransaction();
		session.update(admin);
		tx.commit();
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOAdmin#searchByLogin(String login)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Admin searchByLogin(String login) {
		// begin-user-code

		Admin newadmin=(Admin)session.createCriteria(Admin.class).add(Restrictions.like("login", login)).uniqueResult();
		
		return newadmin;
		// end-user-code
	}
}