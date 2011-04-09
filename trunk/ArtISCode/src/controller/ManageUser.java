/**
 * 
 */
package controller;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import domain.Artist;
import domain.DAOArtist;
import domain.DAONotification;
import domain.DAOPro;
import domain.DAOTag;
import domain.DAOUser;
import domain.Entertainment_Pro;
import domain.IDAOArtist;
import domain.IDAOUser;
import domain.Notification;
import domain.Tag;
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
	private IDAOArtist daoA = new DAOArtist();
	private IDAOUser daoU = new DAOUser();
	private DAOTag daoT = new DAOTag();
	private DAOPro daoP = new DAOPro();
	private DAONotification daoN = new DAONotification();
	
	
	
	public void setDaoA(IDAOArtist daoA) {
		this.daoA = daoA;
	}

	public IDAOArtist getDaoA() {
		return daoA;
	}

	public void setDaoU(IDAOUser daoU) {
		this.daoU = daoU;
	}

	public IDAOUser getDaoU() {
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
	
		daoU.updateUser(user);
		
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see ManagingUsersService#RegisteringUser(User user)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Boolean RegisteringUser(User user) {
		// begin-user-code
		if (this.checkLoginUser(user.getLogin())==null) {
			daoU.addUser(user);
			return true;
		}
		else return false;
		// end-user-code
	}

	@Override
	public Boolean logInUser(String login, String password) {
		User u = this.checkLoginUser(login);
		if (u==null) return false;
		else {
			if (u.getPassword().equals(password)) return true;
			else return false;
		}	
	}

	@Override
	public Boolean logInArtist(String login, String password) {
		Artist a = this.checkLoginArtist(login);
		if (a==null) return false;
		else {
			if (a.getPassword().equals(password)) return true;
			else return false;
		}
	}

	@Override
	public User checkLoginUser(String login) {
		return (User) daoU.searchByLogin(login);
	}

	@Override
	public Artist checkLoginArtist(String login) {
		return (Artist) daoA.searchByLogin(login);
	}

	@Override
	public Boolean RegisteringArtist(Artist artist) {
		if (this.checkLoginArtist(artist.getLogin())==null) {
			//daoT.updateTagList(artist.getTag());
			daoA.addUser(artist);
			return true;
		}
		else return false;
	}

	@Override
	public void modifyArtist(Artist artist) {
		daoA.updateUser(artist);
	}

	public Boolean RegisteringProfessional(Entertainment_Pro pro) {
		if (this.checkLoginArtist(pro.getLogin())==null) {
			daoP.addUser(pro);
			return true;
		}
		else return false;
	}

	@Override
	public void deleteNotifications(String u) {
		User user = searchService.SearchByLogin(u);
		Set<Notification> nots = user.getMessages();
		while (nots!=null && nots.size()>0) {
			Notification n = nots.iterator().next();
			user.getMessages().remove(n);
			daoN.removeNotification(n);
		}
		daoU.updateUser(user);
	}

	@Override
	public void deleteNotification(String u, long notId) {
		User user = searchService.SearchByLogin(u);
		Notification n = daoN.getById(notId);
		user.getMessages().remove(n);
		daoN.removeNotification(n);
		daoU.updateUser(user);
	}
}