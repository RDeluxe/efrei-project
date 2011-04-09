package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ManageEvent;
import controller.SearchEventEngine;

/**
 * Servlet implementation class EventServlet
 */
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);	
		String login = (String) session.getAttribute("login");
		DAOEvent daoE = new DAOEvent();
		List<Event> events = daoE.getAllEvent();
		Writer out = response.getWriter();

		for (Event e : events) {
			if (e.getOwner().getLogin().equalsIgnoreCase(login)) out.write(e.getId() + "\n" + e.getName()+"\n");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String event = request.getParameter("event");
				
		SearchEventEngine see = new SearchEventEngine();
		controller.Search s = new controller.Search();
		Artist a = (Artist) s.SearchByLogin(login);
		Event e = see.searchById(Long.parseLong(event));
		User user = s.SearchByLogin((String) request.getSession().getAttribute("login"));
				
		ManageEvent me = new ManageEvent();
		Notification n = new Notification();
		n.setMessage("You have been invited by " + user.getFirstname() + " " + user.getLastname() + " to the event " + e.getName());
		n.setUser(a);
		a.getMessages().add(n);
		me.inviteArtist(e, a);
	}

}
