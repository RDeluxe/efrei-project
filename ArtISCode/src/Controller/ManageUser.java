/**
 * 
 */
package Controller;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Benjamin BOZOU
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ManageUser implements ManagingUsersService {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private SearchService searchService;

	/** 
	 * @return searchService
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public SearchService getSearchService() {
		// begin-user-code
		return searchService;
		// end-user-code
	}

	/** 
	 * @param searchService searchService à définir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setSearchService(SearchService searchService) {
		// begin-user-code
		this.searchService = searchService;
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see ManagingUsersService#modifyUserName(String name, Integer user_id)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void modifyUserName(String name, Integer user_id) {
		// begin-user-code

		User u = searchService.SearchById(user_id);
		u.setName(name);
		//TODO add the connection to the DB and the update...

		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see ManagingUsersService#modifyUser(User user)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Boolean modifyUser(User user) {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré
		return null;
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see ManagingUsersService#RegisteringUser(User user)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Boolean RegisteringUser(User user) {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré
		return null;
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see ManagingUsersService#logIn(String login, String password)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Boolean logIn(String login, String password) {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré
		return null;
		// end-user-code
	}
}