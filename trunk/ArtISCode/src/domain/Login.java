package domain;

import controller.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String pass = (String) request.getParameter("pass");
		String kind = (String) request.getParameter("kind");
		ManageUser service= new ManageUser();
		Boolean check=false;
		
		if(kind.equalsIgnoreCase("2")){
			check=service.logInArtist(login, pass);
			System.out.println("artiste!");
		}else if (kind.equalsIgnoreCase("1")) {
			System.out.println("user!");
			check=service.logInUser(login, pass);
		}
		
		
		
		if(check==false){
			System.out.println("ko");
			request.setAttribute("result","ko");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}else {
		System.out.println("check !");
				System.out.println("ok");
				request.setAttribute("result", "ok");
				request.setAttribute("kind",kind);
				request.setAttribute("login", login);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}

	}

}
