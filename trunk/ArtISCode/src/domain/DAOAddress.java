/**
 * 
 */
package domain;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
public class DAOAddress implements IDAOAddress {
	/** 
	 * (non-Javadoc)
	 * @see IDAOAddress#addAddress(Address address)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Session session = OpenSession.getSession();;
	//private Transaction tx;
	
	public DAOAddress(){
	}
	
	public void addAddress(Address ad) {
		// begin-user-code

		Transaction tx = session.beginTransaction();
		session.save(ad);
		tx.commit();
		
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOAddress#updateAddress(Integer id_address, Address address)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void updateAddress(Address ad) {
		// begin-user-code
		
		Transaction tx = session.beginTransaction();
		session.update(ad);
		tx.commit();
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOAddress#getAllAddress()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@SuppressWarnings("unchecked")
	public List<Address> getAllAddress() {
		// begin-user-code

		String queryString="from Address";
		Query queryObject=session.createQuery(queryString);
		return (List<Address>) queryObject.list();
		// end-user-code
	}
}