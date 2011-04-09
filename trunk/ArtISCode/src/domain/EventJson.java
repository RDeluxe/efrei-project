package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;

import controller.Search;
import controller.SearchEventEngine;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class EventJson
 */
public class EventJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray arrayObj = new JSONArray();
		Search searchuser = new Search();
		User user = searchuser.SearchByLogin((String)request.getSession().getAttribute("login"));
		String kind = request.getParameter("kind");
		SearchEventEngine search = new SearchEventEngine();
			if (kind.equals("yours")) {
				List<Event> events = search.searchByUser(user);
				for (Event e : events) {
					JSONObject object=new JSONObject();
					object.put("title", e.getName());
					object.put("start", new java.sql.Date(e.getDate().getTime()).toString());
					object.put("end", new java.sql.Date(e.getDate().getTime()+(e.getDuration()-1)*1000*24*60*60).toString());
					object.put("allDay", true);
					object.put("url", "./event.jsp?eventid=" + e.getId());
					arrayObj.add(object);
				}
			} else try {
				Artist a = (Artist) user;
				if (kind.equals("waiting")) {
					Set<Participant> participants = a.getParticipants();
					if (participants !=null) {
						for(Iterator<Participant> it2 = participants.iterator(); it2.hasNext();) {
							Participant participant = (Participant) it2.next(); 
							if (participant.getArtistState().equalsIgnoreCase("waiting")) {
								Event e = participant.getEvent();
								JSONObject object=new JSONObject();
								object.put("title", e.getName());
								object.put("start", new java.sql.Date(e.getDate().getTime()).toString());
								object.put("end", new java.sql.Date(e.getDate().getTime()+(e.getDuration()-1)*1000*24*60*60).toString());
								object.put("allDay", true);
								object.put("url", "./event.jsp?eventid=" + e.getId());
								arrayObj.add(object);
								} 
							}
						} 
				}
				if (kind.equals("ok")) {
					Set<Participant> participants = a.getParticipants();
					if (participants !=null) {
						for(Iterator<Participant> it2 = participants.iterator(); it2.hasNext();) {
							Participant participant = (Participant) it2.next(); 
							if (participant.getArtistState().equalsIgnoreCase("ok")) {
								Event e = participant.getEvent();
								JSONObject object=new JSONObject();
								object.put("title", e.getName());
								object.put("start", new java.sql.Date(e.getDate().getTime()).toString());
								object.put("end", new java.sql.Date(e.getDate().getTime()+(e.getDuration()-1)*1000*24*60*60).toString());
								object.put("allDay", true);
								object.put("url", "./event.jsp?eventid=" + e.getId());
								arrayObj.add(object);
								} 
							}
						} 
				}
				if (kind.equals("no")) {
					Set<Participant> participants = a.getParticipants();
					if (participants !=null) {
						for(Iterator<Participant> it2 = participants.iterator(); it2.hasNext();) {
							Participant participant = (Participant) it2.next(); 
							if (participant.getArtistState().equalsIgnoreCase("no")) {
								Event e = participant.getEvent();
								JSONObject object=new JSONObject();
								object.put("title", e.getName());
								object.put("start", new java.sql.Date(e.getDate().getTime()).toString());
								object.put("end", new java.sql.Date(e.getDate().getTime()+(e.getDuration()-1)*1000*24*60*60).toString());
								object.put("allDay", true);
								object.put("url", "./event.jsp?eventid=" + e.getId());
								arrayObj.add(object);
								} 
							}
						} 
				}
				
			} catch (Exception e) {
			}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(arrayObj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
