/**
 * 
 */
package controller;

import java.util.Set;

import domain.DAOTag;
import domain.Tag;
import domain.User;
import domain.Artist;
import domain.DAOArtist;
import domain.DAOUser;


/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Benjamin BOZOU
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Search implements SearchService {
	private DAOArtist daoA = new DAOArtist();
	private DAOUser daoU = new DAOUser();
	private DAOTag daoT = new DAOTag();
	/** 
	 * (non-Javadoc)
	 * @see SearchService#SearchByName(String name)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<User> SearchByName(String name) {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré
		return null;
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see SearchService#SearchByTags(String... tags)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<User> SearchByTags(String... tags) {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré
		return null;
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see SearchService#SearchByLogin(String login)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public User SearchByLogin(String login) {
		// begin-user-code
		Artist a = (Artist) daoA.searchByLogin(login);
        if (a == null) {
                User u = daoU.searchByLogin(login);
                return u;
        }
        return a;
				
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see SearchService#SearchById(Integer user_id)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public User SearchById(Integer user_id) {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré
		return null;
		// end-user-code
	}
	
	public Tag SearchTagByName(String name){
		return daoT.searchTagByName(name);
	}
}