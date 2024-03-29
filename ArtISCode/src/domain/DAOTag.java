/**
 * 
 */
package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.FlushMode;
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
	
	public void updateTagList(Set<Tag> tags) {
		Transaction tx = session.beginTransaction();
		Iterator<Tag> it = tags.iterator();
		while (it.hasNext()) {
			session.update(it.next());
		}
		tx.commit();
	}
	
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
		Tag newtag=(Tag)session.createCriteria(Tag.class).add(Restrictions.like("name", name)).uniqueResult();
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