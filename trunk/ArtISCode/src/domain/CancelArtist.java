package domain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ManageEvent;
import controller.SearchEventEngine;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
/**
 * Servlet implementation class CancelArtist
 */
public class CancelArtist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelArtist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String artistlogin = request.getParameter("artist");
		String eventid = request.getParameter("event");
		
		controller.Search search = new controller.Search();
		SearchEventEngine searchE = new SearchEventEngine();
		ManageEvent me = new ManageEvent();
		
		Artist a = (Artist) search.SearchByLogin(artistlogin);
		Event e = searchE.searchById(Long.parseLong(eventid));
		
		Notification n = new Notification();
		n.setMessage("The event " + e.getName() + " where you were invited has canceled your invitation");
		n.setUser(a);
		a.getMessages().add(n);
		me.cancelArtist(e, a);
	}

}
