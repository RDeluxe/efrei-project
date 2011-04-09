/**
 * 
 */
package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.util.Set;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Benjamin BOZOU
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class User {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private long id;

	/** 
	 * @return id
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public long getId() {
		// begin-user-code
		return id;
		// end-user-code
	}

	/** 
	 * @param id id � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setId(long id) {
		// begin-user-code
		this.id = id;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String email;

	/** 
	 * @return email
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getEmail() {
		// begin-user-code
		return email;
		// end-user-code
	}

	/** 
	 * @param email email � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setEmail(String email) {
		// begin-user-code
		this.email = email;
		// end-user-code
	}
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String login;

	/** 
	 * @return login
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getLogin() {
		// begin-user-code
		return login;
		// end-user-code
	}

	/** 
	 * @param login login � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setLogin(String login) {
		// begin-user-code
		this.login = login;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String firstname;

	/** 
	 * @return firstname
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getFirstname() {
		// begin-user-code
		return firstname;
		// end-user-code
	}

	/** 
	 * @param firstname firstname � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setFirstname(String firstname) {
		// begin-user-code
		this.firstname = firstname;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String lastname;

	/** 
	 * @return lastname
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getLastname() {
		// begin-user-code
		return lastname;
		// end-user-code
	}

	/** 
	 * @param lastname lastname � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setLastname(String lastname) {
		// begin-user-code
		this.lastname = lastname;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String password;

	/** 
	 * @return password
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getPassword() {
		// begin-user-code
		return password;
		// end-user-code
	}

	/** 
	 * @param password password � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setPassword(String password) {
		// begin-user-code
		this.password = password;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Address address;

	/** 
	 * @return address
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Address getAddress() {
		// begin-user-code
		return address;
		// end-user-code
	}

	/** 
	 * @param address address � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAddress(Address address) {
		// begin-user-code
		this.address = address;
		// end-user-code
	}

	private Set<Event> events;
	
	
	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Event> getEvents() {
		return events;
	}
	
	public void setMessages(Set<Notification> messages) {
		this.messages = messages;
	}

	public Set<Notification> getMessages() {
		return messages;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhoto() {
		return photo;
	}

	private Set<Notification> messages;
	
	private String photo;
	
}