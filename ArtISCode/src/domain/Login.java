package domain;

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
		DAOUser dao = new DAOUser();
		User user =dao.searchByLogin(login);
		
		if(user==null){
			System.out.println("ko1");
			request.setAttribute("result","ko1");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}else {
		System.out.println("check !");
		if(user.getLogin().equalsIgnoreCase(login)){
			
			if(user.getPassword().equalsIgnoreCase(pass))
			{
				System.out.println("ok");
				request.setAttribute("result", "ok");
				request.setAttribute("login", login);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else {
				System.out.println("ko2");
				request.setAttribute("result","ko2");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		
		}
		
		
	}

}
