package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ManageEvent;
import controller.SearchEventEngine;

/**
 * Servlet implementation class ModifyEvent
 */
public class ModifyEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyEvent() {
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
		HttpSession session = request.getSession(true);	
		String login = (String) session.getAttribute("login");
		String name = request.getParameter("name");
		String sid = request.getParameter("id");
		Long id = Long.parseLong(sid);
		SearchEventEngine s = new SearchEventEngine();
		Event event = s.searchById(id);
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
		Address address= event.getAddress();
		address.setCity(city);
		address.setCountry(country);
		address.setStreet(street);
		address.setZip(zip);
		ManageEvent manager = new ManageEvent();
		
		
		event.setAddress(address);
		event.setDate(date);
		event.setDuration(duration);
		event.setName(name);
		event.setDescription(description);
		manager.modifyEvent(event);
		request.getRequestDispatcher("EventPage").forward(request, response);
	}

}
