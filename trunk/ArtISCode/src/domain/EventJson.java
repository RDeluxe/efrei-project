package domain;

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
		SearchEventEngine search = new SearchEventEngine();
		List<Event> events = search.searchByUser(user);
		
		
		
		
		
		for (Event e : events) {
			JSONObject object=new JSONObject();
			object.put("title", e.getName());
			object.put("start", new java.sql.Date(e.getDate().getTime()).toString());
			object.put("end", new java.sql.Date(e.getDate().getTime()+e.getDuration()*1000*24*60*60).toString());
			object.put("allDay", true);
			object.put("url", "./event.jsp?eventid=" + e.getId());
			arrayObj.add(object);
		}
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
