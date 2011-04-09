package domain;
/**
 * Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.

*/
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String  keyword=request.getParameter("keyword");
		PrintWriter out = response.getWriter();
		DAOArtist daoA = new DAOArtist();
		Vector<String> vData = new Vector<String>(); 

			List<Artist> artList = daoA.searchArtistByKeyword(keyword);
			Iterator<Artist> it = artList.iterator();
			
			while (it.hasNext())
			{
				Artist a = it.next();
				vData.add(a.getFirstname() + " " + a.getLastname());
				vData.add(a.getLogin());
			}
			StringBuffer buf = new StringBuffer();
			for (int i=0;i<vData.size();i++)
			{
				String word = (String)vData.get(i);
				buf.append(word+"\n");
			}
			out.print(buf.toString()); 

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
