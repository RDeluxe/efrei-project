package domain;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Iterator;
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
		String kind = new String();
		HttpSession session = request.getSession(true);	
		String login = (String) session.getAttribute("login");
		DAOArtist daoA = new DAOArtist();
		Artist artist = (Artist) daoA.searchByLogin(login);
		if (artist==null)
		{kind="1";}else{kind="2";}
		
		
		if(kind.equalsIgnoreCase("1"))
		{
			DAOUser dao = new DAOUser();
			User user = new User();
			user= dao.searchByLogin(login);
			Writer out = response.getWriter();
			out.write(kind + '\n');
			out.write(user.getFirstname() + '\n');
			out.write(user.getLastname() + '\n');
			out.write(user.getEmail() + '\n');
			out.write(user.getLogin() + '\n');
			out.write(user.getPassword() + '\n');
			Address a = user.getAddress();
			out.write(a.getStreet() + '\n');
			out.write(a.getCity() + '\n');
			out.write(a.getZip() + '\n');
			out.write(a.getCountry() + '\n');	
		}
		if(kind.equalsIgnoreCase("2"))
		{
			Writer out = response.getWriter();
			out.write(kind + '\n');
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
			out.write(artist.getPhoto()+'\n');
			out.write(artist.getDescription()+ '\n');
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String kind = new String();
		HttpSession session = request.getSession(true);	
		DAOArtist daoA = new DAOArtist();
		
		String login =request.getParameter("login");
		String pass1 = (String) request.getParameter("pass1");
		String pass2 = (String) request.getParameter("pass2");
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
		Artist artisttest = (Artist) daoA.searchByLogin(login);
		if (artisttest==null)
		{kind="1";}else{kind="2";}
		
		ManageUser service= new ManageUser();
		Search search = new Search();
		if(pass1.equals(pass2))
		{
			if(kind.equalsIgnoreCase("1"))
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
			if(kind.equalsIgnoreCase("2"))
			{
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
					Set<Tag> tags = artist.getTag();
					Iterator<Tag> it = tags.iterator();
					Tag Tag1 = it.next();
					Tag1.removeArtist(artist);
					Tag1 = search.SearchTagByName(tag1);
					Tag1.addArtist(artist);
					Tag Tag2 = it.next();
					Tag2.removeArtist(artist);
					Tag2 = search.SearchTagByName(tag2);
					Tag2.addArtist(artist);
					Tag Tag3 = it.next();
					Tag3.removeArtist(artist);
					Tag3 = search.SearchTagByName(tag3);
					Tag3.addArtist(artist);
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
