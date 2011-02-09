package domain;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ManageUser;
import controller.SearchService;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		
		ManageUser service= new ManageUser();
		controller.Search search = new controller.Search();
		if(pass1.equals(pass2))
		{
			if(kind.equalsIgnoreCase("user"))
			{
				User user=new User();
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
				Boolean check=service.RegisteringUser(user);
				if(check==true)
				{
					request.setAttribute("resultreg", "ok");
					request.setAttribute("login", login);
					request.getRequestDispatcher("RegResult.jsp").forward(request, response);
				}else{
					request.setAttribute("resultreg", "ko");
					request.getRequestDispatcher("RegResult.jsp").forward(request, response);
				}
			}
			if(kind.equalsIgnoreCase("artist"))
			{
				String description = request.getParameter("description");
				Artist artist=new Artist();
				artist.setFirstname(firstname);
				artist.setLastname(lastname);
				artist.setEmail(mail);
				artist.setLogin(login);
				artist.setPassword(pass1);
				artist.setDescription(description);
				Address address=new Address();
				address.setCity(city);
				address.setCountry(country);
				address.setStreet(street);
				address.setZip(zip);
				artist.setAddress(address);
				Boolean check =service.RegisteringArtist(artist);
				try {
					artist = service.checkLoginArtist(login);
					String tag1 = request.getParameter("tag1");
					String tag2 = request.getParameter("tag2");
					String tag3 = request.getParameter("tag3");
					Tag Tag1 = search.SearchTagByName(tag1);
					Tag Tag2 = search.SearchTagByName(tag2);
					Tag Tag3 = search.SearchTagByName(tag3);
					artist.getTag().clear();
					Tag1.addArtist(artist);
					Tag2.addArtist(artist);
					Tag3.addArtist(artist);
					artist.addTag(Tag1);
					artist.addTag(Tag2);
					artist.addTag(Tag3);
					service.modifyArtist(artist);
				} catch (Exception e) {
					artist = service.checkLoginArtist(login);
					String tag1 = request.getParameter("tag1");
					String tag2 = request.getParameter("tag2");
					String tag3 = request.getParameter("tag3");
					Tag Tag1 = search.SearchTagByName(tag1);
					Tag Tag2 = search.SearchTagByName(tag2);
					Tag Tag3 = search.SearchTagByName(tag3);
					artist.getTag().clear();
					Tag1.addArtist(artist);
					Tag2.addArtist(artist);
					Tag3.addArtist(artist);
					artist.addTag(Tag1);
					artist.addTag(Tag2);
					artist.addTag(Tag3);
					service.modifyArtist(artist);
				}
				if(check==true)
				{
					request.setAttribute("resultreg", "ok");
					request.setAttribute("login", login);
					request.getRequestDispatcher("RegResult.jsp").forward(request, response);
				}else{
					request.setAttribute("resultreg", "ko");
					request.getRequestDispatcher("RegResult.jsp").forward(request, response);
				}
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
