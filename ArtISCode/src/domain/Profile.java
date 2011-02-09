package domain;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ManageUser;
import controller.Search;

/**
 * Servlet implementation class Profile
 */
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);	
		String login = (String) session.getAttribute("login");
		String kind = (String) session.getAttribute("kind");
		System.out.println(kind);
		if(kind.equalsIgnoreCase("1"))
		{
			System.out.println("user");
			DAOUser dao = new DAOUser();
			User user = new User();
			user= dao.searchByLogin(login);
			request.setAttribute("user",user);
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		}
		if(kind.equalsIgnoreCase("2"))
		{
			System.out.println("artist");
			DAOArtist dao = new DAOArtist();
			User user = new User();
			user= dao.searchByLogin(login);
			request.setAttribute("user",user);
			request.getRequestDispatcher("profileA.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login =request.getParameter("login");
		String pass1 = (String) request.getParameter("pass1");
		String pass2 = (String) request.getParameter("pass2");
		String kind = request.getParameter("kind");
		String firstname =request.getParameter("firstname");
		String lastname = (String) request.getParameter("lastname");
		String mail = request.getParameter("mail");
		String street =request.getParameter("street");
		String city =request.getParameter("city");
		String zip = (String) request.getParameter("zip");
		String country = request.getParameter("country");
		String description = (String) request.getParameter("description");
		String tag1 = (String) request.getParameter("tag1");
		String tag2 = (String) request.getParameter("tag2");
		String tag3 = (String) request.getParameter("tag3");
		System.out.println(description);
		
		ManageUser service= new ManageUser();
		if(pass1.equals(pass2))
		{
			if(kind.equalsIgnoreCase("user"))
			{
				System.out.println("user");
				
				User user = service.checkLoginUser(login);
				user.setFirstname(firstname);
				user.setLastname(lastname);
				user.setEmail(mail);
				user.setLogin(login);
				user.setPassword(pass1);
				Address address=new Address();
				address.setCity(city);
				address.setCountry(country);
				address.setStreet(street);
				address.setZip(zip);
				user.setAddress(address);
				service.modifyUser(user);
				request.setAttribute("result", "upok");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}
			if(kind.equalsIgnoreCase("artist"))
			{
				System.out.println("artiste");
				Tag Tag1 = new Tag();
				Tag Tag2 = new Tag();
				Tag Tag3 = new Tag();
				Tag1.setName(tag1);
				Tag2.setName(tag2);
				Tag3.setName(tag3);
				Artist artist= service.checkLoginArtist(login);
				Set<Artist> artists=new HashSet<Artist>();
				artist.setFirstname(firstname);
				artist.setLastname(lastname);
				artist.setEmail(mail);
				artist.setLogin(login);
				artist.setPassword(pass1);
				artist.setDescription(description);
				artists.add(artist);
				Set<Tag> tags = new HashSet<Tag>();
				tags.add(Tag1);
				tags.add(Tag2);
				tags.add(Tag3);
				Tag1.setArtist(artists);
				Tag2.setArtist(artists);
				Tag3.setArtist(artists);
				artist.setTag(tags);
				Address address= artist.getAddress();
				address.setCity(city);
				address.setCountry(country);
				address.setStreet(street);
				address.setZip(zip);
				artist.setAddress(address);
				service.modifyArtist(artist);
				request.setAttribute("result", "upok");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}
			/*
			if(kind.equalsIgnoreCase("professional"))
			{
				String description = request.getParameter("description");
				Entertainment_Pro pro=new Entertainment_Pro();
				pro.setFirstname(firstname);
				pro.setLastname(lastname);
				pro.setEmail(mail);
				pro.setLogin(login);
				pro.setPassword(pass1);
				pro.setDescription(description);
				Address address=new Address();
				address.setCity(city);
				address.setCountry(country);
				address.setStreet(street);
				address.setZip(zip);
				pro.setAddress(address);
				Boolean check=service.RegisteringEntertainment_Pro(pro);
				if(check==true)
				{
					request.setAttribute("result", "ok");
					request.setAttribute("login", login);
					request.getRequestDispatcher("RegResult.jsp").forward(request, response);
				}else{
					request.setAttribute("result", "ko");
					request.getRequestDispatcher("RegResult.jsp").forward(request, response);
				}
			}
			*/
	}
	}

}
