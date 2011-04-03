package domain;

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
 * Servlet implementation class Result
 */
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
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
				System.out.println("toto*************************");
				Artist a = it.next();
				vData.add(a.getFirstname());
				vData.add(a.getLastname());
				vData.add(a.getLogin());
				vData.add(a.getPhoto());
				if (a.getDescription().length()>200) vData.add(a.getDescription().substring(0, 199)+"...");
				else vData.add(a.getDescription());
				vData.add(a.getAddress().toString());
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
