/**
 * 
 */
package domain;

import java.util.Set;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Benjamin BOZOU
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface IDAOArtist extends IDAOUser {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param tag
	 * @return
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<Artist> SearchArtistByTag(Tag tag);
}