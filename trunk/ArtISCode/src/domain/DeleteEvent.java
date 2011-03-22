package domain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ManageEvent;
import controller.SearchEventEngine;

/**
 * Servlet implementation class DeleteEvent
 */
public class DeleteEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEvent() {
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
		String eventid =request.getParameter("event");
		HttpSession session = request.getSession(true);	
		long id =Long.parseLong(eventid);
		ManageEvent manager = new ManageEvent();
		SearchEventEngine search = new SearchEventEngine();
		Event event = search.searchById(id);
		
		manager.cancelEvent(event, null);
		request.getRequestDispatcher("EventPage").forward(request, response);
		
		
	}

}
