/**
 * 
 */
package domain;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Benjamin BOZOU
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DAOTag implements IDAOTag {
	/** 
	 * (non-Javadoc)
	 * @see IDAOTag#addTag(Tag tag)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Session session=OpenSession.getSession();
	
	public DAOTag(){
	}
	public void addTag(Tag tag) {
		// begin-user-code
		// TODO Module de remplacement de m閠hode auto-g閚閞�
		
		Transaction tx = session.beginTransaction();
		session.save(tag);
		tx.commit();
		
		/*Tag newtag =new Tag();
		newtag.setId(tag.getId());
		newtag.setName(tag.getName());
		newtag.setArtist(tag.getArtist());*/
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOTag#updateTag(Integer id_tag, Tag tag)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void updateTag(Tag tag) {
		// begin-user-code
		// TODO Module de remplacement de m閠hode auto-g閚閞�
		Transaction tx = session.beginTransaction();
		session.update(tag);
		tx.commit();
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOTag#searchTagByName(String name)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Tag searchTagByName(String name) {
		// begin-user-code
		// TODO Module de remplacement de m閠hode auto-g閚閞�
		Tag newtag=(Tag)session.get(Tag.class, name);
		return newtag;
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see IDAOTag#getAllTags()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@SuppressWarnings("unchecked")
	public List<Tag> getAllTags() {
		// begin-user-code
		// TODO Module de remplacement de m閠hode auto-g閚閞�
		String queryString="from Tag";
		Query queryObject=session.createQuery(queryString);
		return (List<Tag>) queryObject.list();
		// end-user-code
	}
}