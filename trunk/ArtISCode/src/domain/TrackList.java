package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TrackList
 */
public class TrackList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrackList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		controller.Search search = new controller.Search();
		List<Artist> artists = search.searchRandomArtists();
		response.setContentType("text/xml");
		Writer out = response.getWriter();
		out.write("<?xml version='1.0' encoding='UTF-8'?><playlist version='0' xmlns = 'http://xspf.org/ns/0/'><trackList>");
		for (Artist a : artists) {
			if (a.getMusic()!=null) out.write("<track><location>" + a.getMusic() + "</location><title>" + a.getFirstname() + " " + a.getLastname() + "</title></track>");
		}
		out.write("</trackList></playlist>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
