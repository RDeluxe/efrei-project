/**
 * 
 */
package domain;

import java.util.HashSet;
import java.util.Set;

import java.util.Arrays;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Benjamin BOZOU
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Artist extends User {

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String description;

	/** 
	 * @return description
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getDescription() {
		// begin-user-code
		return description;
		// end-user-code
	}

	/** 
	 * @param description description à définir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setDescription(String description) {
		// begin-user-code
		this.description = description;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<Tag> tag = new HashSet<Tag>();

	/** 
	 * @return tag
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<Tag> getTag() {
		// begin-user-code
		return tag;
		// end-user-code
	}

	/** 
	 * @param tag tag à définir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTag(Set<Tag> tag) {
		// begin-user-code
		this.tag = tag;
		// end-user-code
	}
	
	public void addTag(Tag t) {
		tag.add(t);
	}

	private Set<Participant> participants;

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public Set<Participant> getParticipants() {
		return participants;
	}
}