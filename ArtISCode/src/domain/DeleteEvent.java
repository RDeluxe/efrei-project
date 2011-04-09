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
                this.doPost(request, response);
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
                for (Participant p : event.getArtists()) {
                	if (!p.getArtistState().equalsIgnoreCase("no")) {
                		Notification n = new Notification();
                		n.setMessage("The event "+ event.getName() +" which ");
                		if (p.getArtistState().equalsIgnoreCase("waiting")) n.setMessage(n.getMessage() + "was expectig for your answer ");
                		else if(p.getArtistState().equalsIgnoreCase("ok")) n.setMessage(n.getMessage() + "was expecting you ");
                		n.setMessage(n.getMessage() + "has been canceled");
                		n.setUser(p.getMember());
                		p.getMember().getMessages().add(n);
                	}
                }
                manager.cancelEvent(event, null);
                request.getRequestDispatcher("EventPage").forward(request, response);
        }

}