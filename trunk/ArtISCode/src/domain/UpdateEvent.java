package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ManageEvent;
import controller.SearchEventEngine;

/**
 * Servlet implementation class UpdateEvent
 */
public class UpdateEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ev_id = request.getParameter("event");
		String artist_status = request.getParameter("artist_status"+ev_id);
		ManageEvent me = new ManageEvent();
		SearchEventEngine see = new SearchEventEngine();
		controller.Search s = new controller.Search();
		Event e = see.searchById(Long.parseLong(ev_id));
		Artist a = (Artist) s.SearchByLogin((String)request.getSession().getAttribute("login")); 
		Participant p = e.getParticipant(a);
		p.setArtistState(artist_status);
		User user = e.getOwner();
		Notification n = new Notification();
		n.setMessage("The status of " + a.getFirstname() + " " + a.getLastname() + " for the event " + e.getName() + " is now : " + artist_status);
		n.setUser(user);
		user.getMessages().add(n);
		me.modifyEvent(e);
		request.setAttribute("updatedone", "OK");
		request.getRequestDispatcher("EventPage").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
