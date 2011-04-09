package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import controller.SearchEventEngine; 
import controller.Search;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EventPage
 */
public class EventPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);	
		String login = (String) session.getAttribute("login");
		Search searchuser = new Search();
		User user = searchuser.SearchByLogin(login);
		SearchEventEngine search = new SearchEventEngine();
		List<Event> events = search.searchByUser(user);
		
		try {
			Artist a = (Artist) user;
			Set<Participant> parts = a.getParticipants();
			request.setAttribute("Artist_events", parts);
		} catch (Exception e) {
			request.setAttribute("Artist_events", new HashSet<Participant>());
		}
		request.removeAttribute("events");
		request.setAttribute("events", events);
		RequestDispatcher dispatcher = request.getRequestDispatcher("eventCalendar.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
		
	}

}
