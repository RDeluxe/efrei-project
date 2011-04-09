package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Artist a = new Artist();
		a.setFirstname("Pierruck");
		a.setLastname("Bignet");
		a.setLogin("bignet");
		a.setPassword("passwd");
		a.setDescription("This is a real artist very known in the all world");
		Set<Tag> tags = new HashSet<Tag>();
		Set<Artist> artists = new HashSet<Artist>();
		artists.add(a);
		Tag tag1 = new Tag();
		Tag tag2 = new Tag();
		tag1.setName("1test");
		tag1.setArtist(artists);
		tag2.setName("2test");
		tag2.setArtist(artists);
		a.setTag(tags);
		Address ad = new Address();
		ad.setCity("Villejuif");
		ad.setCountry("France");
		ad.setStreet("Av de la République");
		ad.setZip("94300");
		ad.setUser(a);
		a.setAddress(ad);

		DAOTag daoT = new DAOTag();
		daoT.updateTag(1, tag1);
		daoT.updateTag(2, tag2);*/
		
		DAOArtist daoA = new DAOArtist();
		Artist a = (Artist) daoA.getAllUser().iterator().next();
		a.setFirstname("Pierrick");
		
		daoA.updateUser(a);

		RequestDispatcher dispatcher = request.getRequestDispatcher("indextest.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
