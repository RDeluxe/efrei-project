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
		System.out.println(kind+"************************************");
		
		ManageUser service= new ManageUser();
		Search search = new Search();
		if(pass1.equals(pass2))
		{
			if(kind.equalsIgnoreCase("user"))
			{
				
				User user = service.checkLoginUser(login);
				user.setFirstname(firstname);
				user.setLastname(lastname);
				user.setEmail(mail);
				user.setLogin(login);
				user.setPassword(pass1);
				Address address= user.getAddress();
				address.setCity(city);
				address.setCountry(country);
				address.setStreet(street);
				address.setZip(zip);
				service.modifyUser(user);
				request.setAttribute("result", "upok");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}
			if(kind.equalsIgnoreCase("artist"))
			{
				try {
					Artist artist= service.checkLoginArtist(login);
					//Set<Artist> artists=new HashSet<Artist>();
					artist.setFirstname(firstname);
					artist.setLastname(lastname);
					artist.setEmail(mail);
					artist.setLogin(login);
					artist.setPassword(pass1);
					artist.setDescription(description);
					Address address= artist.getAddress();
					address.setCity(city);
					address.setCountry(country);
					address.setStreet(street);
					address.setZip(zip);
					//artists.add(artist);
					//Set<Tag> tags = new HashSet<Tag>();
					Set<Tag> tags = artist.getTag();
					tags.clear();
					Tag Tag1 = search.SearchTagByName(tag1);
					Tag Tag2 = search.SearchTagByName(tag2);
					Tag Tag3 = search.SearchTagByName(tag3);
					Tag1.addArtist(artist);
					Tag2.addArtist(artist);
					Tag3.addArtist(artist);
					artist.addTag(Tag1);
					artist.addTag(Tag2);
					artist.addTag(Tag3);
					service.modifyArtist(artist);
				} catch (Exception e) {
					Artist artist= service.checkLoginArtist(login);
					//Set<Artist> artists=new HashSet<Artist>();
					artist.setFirstname(firstname);
					artist.setLastname(lastname);
					artist.setEmail(mail);
					artist.setLogin(login);
					artist.setPassword(pass1);
					artist.setDescription(description);
					Address address= artist.getAddress();
					address.setCity(city);
					address.setCountry(country);
					address.setStreet(street);
					address.setZip(zip);
					//artists.add(artist);
					//Set<Tag> tags = new HashSet<Tag>();
					Set<Tag> tags = artist.getTag();
					tags.clear();
					Tag Tag1 = search.SearchTagByName(tag1);
					Tag Tag2 = search.SearchTagByName(tag2);
					Tag Tag3 = search.SearchTagByName(tag3);
					Tag1.addArtist(artist);
					Tag2.addArtist(artist);
					Tag3.addArtist(artist);
					artist.addTag(Tag1);
					artist.addTag(Tag2);
					artist.addTag(Tag3);
					service.modifyArtist(artist);
				}
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
