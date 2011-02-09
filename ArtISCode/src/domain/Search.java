package domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        Connection con = null;
		Statement stat = null;
		Vector vData = new Vector(); 
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/artisdb", "root", "root");
			
			stat = con.createStatement();
			StringBuilder content = new StringBuilder();
			content.append("%");
			for (int i = 0; i < keyword.length(); i++) {
				content.append(keyword.charAt(i) + "%");
			}
			ResultSet rs = stat.executeQuery("select * from user where firstname like \"%"+content.toString()+"%\" limit 5");
			
			while (rs.next())
			{
				vData.add(rs.getString("firstname"));
			}
			StringBuffer buf = new StringBuffer();
			for (int i=0;i<vData.size();i++)
			{
				String word = (String)vData.get(i);
				buf.append(word+"\n");
			}
			out.print(buf.toString()); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// this will close any associated ResultSets
				if (stat != null)
					stat.close();
				if (con != null)
					con.close();
			} catch (SQLException sqle) {
			}
		}
		
		/*
		
			DAO dao=new DAO();
			out.println("<html>");
	    	out.println("<head>");
	    	out.println("<title>Login Accept</title>");
	    	out.println("</head>");
	    	out.println("<body>");
	    	
	    	try {
	    		out.println("MMMMM))))))"+keyword);
				out.println(" Return Accueil ahahahahhahaah***********************"+dao.search(1120));
				out.println("skskskskskskks%%%%%%%%%%%%%%%%5"+dao.search(1120));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	out.println("</html>");*/
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
