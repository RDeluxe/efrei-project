/**
 * 
 */
package controller;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.util.ArrayList;
import java.util.List;
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
		// TODO Module de remplacement de m�thode auto-g�n�r�
		return null;
		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see SearchService#SearchByTags(String... tags)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<Artist> SearchByTags(String tag) {
		List<User> artists = daoA.getAllUser();
		List<Artist> res = new ArrayList<Artist>();
		Tag t = daoT.searchTagByName(tag);
		for (User u : artists) {
			Artist a = (Artist) u;
			if (a.getTag().contains(t)) res.add(a);
		}
		return res;
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
		// TODO Module de remplacement de m�thode auto-g�n�r�
		return null;
		// end-user-code
	}
	
	public Tag SearchTagByName(String name){
		return daoT.searchTagByName(name);
	}

	@Override
	public List<Artist> searchRandomArtists() {
		
		return daoA.getRandomArtists();
	}
}