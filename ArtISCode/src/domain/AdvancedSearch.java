package domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import controller.Search;

/**
 * Servlet implementation class AdvancedSearch
 */
public class AdvancedSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvancedSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray arrayObj = new JSONArray();
		Search searchuser = new Search();
		String tagname = request.getParameter("tagname");
		List<Artist> artists = searchuser.SearchByTags(tagname);
		Iterator<Artist> it = artists.iterator();
		while (it.hasNext()) {
			Artist a = it.next();
			JSONObject object=new JSONObject();
			object.put("login", a.getLogin());
			object.put("firstname", a.getFirstname());
			object.put("lastname", a.getLastname());
			object.put("address", a.getAddress().toString());
			if (a.getDescription().length()>200) object.put("description", a.getDescription().substring(0, 199));
			else object.put("description", a.getDescription());
			object.put("photo", a.getPhoto());
			arrayObj.add(object);
		}
		JSONObject object=new JSONObject();
		object.put("result", arrayObj);
		System.out.println(object);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(object);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
