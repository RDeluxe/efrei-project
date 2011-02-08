/**
 * 
 */
package controller;

import domain.Artist;
import domain.DAOArtist;
import domain.DAOUser;
import domain.User;

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
	private SearchService searchService = new Search();
	private DAOArtist daoA = new DAOArtist();
	private DAOUser daoU = new DAOUser();
	
	
	
	public void setDaoA(DAOArtist daoA) {
		this.daoA = daoA;
	}

	public DAOArtist getDaoA() {
		return daoA;
	}

	public void setDaoU(DAOUser daoU) {
		this.daoU = daoU;
	}

	public DAOUser getDaoU() {
		return daoU;
	}

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
	 * @throws Exception 
	 * @see ManagingUsersService#modifyUserName(String name, Integer user_id)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void modifyUserName(String name, Integer user_id) throws Exception {
		// begin-user-code

		User u = daoU.searchUserById(user_id);
		if (u==null) {
			u = daoA.searchUserById(user_id);
			if (u==null) throw new Exception("User doesn't exists");
		}
		u.setLastname(name);
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see ManagingUsersService#modifyUser(User user)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void modifyUser(User user) {
		// begin-user-code
	
		if (user instanceof Artist) {
			daoA.updateUser(user);
		}
		else daoU.updateUser(user);
		
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see ManagingUsersService#RegisteringUser(User user)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void RegisteringUser(User user) {
		// begin-user-code
		
		if (user instanceof Artist) {
			daoA.addUser(user);
		}
		else daoU.addUser(user);
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see ManagingUsersService#logIn(String login, String password)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Boolean logIn(String login, String password) {
		// begin-user-code
		User u = daoA.searchByLogin(login);
		if ()
		return null;
		// end-user-code
	}
}