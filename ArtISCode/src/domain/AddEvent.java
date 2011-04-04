package domain;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ManageEvent;

import java.text.DateFormat; 
import java.text.SimpleDateFormat; 
import java.text.ParseException; 


/**
 * Servlet implementation class AddEvent
 */
public class AddEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("EventPage").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);	
		String login = (String) session.getAttribute("login");
		String name = request.getParameter("name");
		String durations = request.getParameter("duration");
		int duration = Integer.parseInt(durations);
		String street =request.getParameter("street");
		String city =request.getParameter("city");
		String zip = (String) request.getParameter("zip");
		String country = request.getParameter("country");
		String day = request.getParameter("Day");
		String month = request.getParameter("Month");
		String year = request.getParameter("Year");
		String description = request.getParameter("description");
		String date_str = day + "/" + month + "/" + year ;
		DateFormat formatter ; 
	    Date date = null ; 
	          formatter = new SimpleDateFormat("dd/MM/yyyy");
	              try {
					date = (Date)formatter.parse(date_str);
				} catch (ParseException e) {
					System.out.println("ca merde !");
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		Address address=new Address();
		address.setCity(city);
		address.setCountry(country);
		address.setStreet(street);
		address.setZip(zip);
		DAOUser daoU = new DAOUser();
		User user = daoU.searchByLogin(login);
		long id = user.getId();
		ManageEvent manager = new ManageEvent();
		Event event = new Event();
		event.setAddress(address);
		event.setDate(date);
		event.setDuration(duration);
		event.setOwner(user);
		event.setName(name);
		event.setDescription(description);
		manager.createEvent(event);
		request.getRequestDispatcher("EventPage").forward(request, response);
	}

}
