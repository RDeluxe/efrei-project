package domain;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetProfile
 */
public class GetProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProfile() {
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
		// TODO Auto-generated method stub
		
		String login = request.getParameter("login");
		DAOArtist daoA = new DAOArtist();
		Artist artist = (Artist) daoA.searchByLogin(login);
		Writer out = response.getWriter();
		out.write("2" + '\n');
		out.write(artist.getFirstname() + '\n');
		out.write(artist.getLastname() + '\n');
		out.write(artist.getEmail() + '\n');
		out.write(artist.getLogin() + '\n');
		out.write(artist.getPassword() + '\n');
		Address a = artist.getAddress();
		out.write(a.getStreet() + '\n');
		out.write(a.getCity() + '\n');
		out.write(a.getZip() + '\n');
		out.write(a.getCountry() + '\n');
		if(artist.getPhoto()!=null)
		{
			out.write("uploads/IMG/" +artist.getLogin()+".jpg" + '\n');
		}else{out.write("Img/portrait.gif" + '\n');}
		out.write(artist.getDescription() +'\n');
		if(artist.getMusic()!=null)
		{
			out.write("uploads/MP3/" +artist.getLogin()+".mp3" + '\n');
		}else{out.write("none");}
		
	}

	
}
