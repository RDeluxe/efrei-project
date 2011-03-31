package domain;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;
import java.util.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload
 */
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
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
	
		
		String login = (String) request.getParameter("session");
		System.out.println(login);
		System.out.println("lol");
		String login2 = (String) request.getSession().getAttribute("login");
		System.out.println(login2);
		        
		        File uploadPath = new File("C:\\Users\\Pierrick\\Dropbox\\Project\\artIS\\WebContent\\uploads"); // Directory to upload the file
				   if (!uploadPath.exists()) {
				      uploadPath.mkdirs();
				   }
				   // Create a buffer folder
				   File tempPathFile = new File("C:\\Users\\Pierrick\\Dropbox\\Project\\artIS\\WebContent\\uploads\\buffer\\");
				   if (!tempPathFile.exists()) {
				      tempPathFile.mkdirs();
				   }
				   try {
				      // Create a factory for disk-based file items
				      DiskFileItemFactory factory = new DiskFileItemFactory();

				      // Set factory constraints
				      factory.setSizeThreshold(4096); // set the buffer size(4kb)
				      factory.setRepository(tempPathFile);// set buffer folder

				      // Create a new file upload handler
				      ServletFileUpload upload = new ServletFileUpload(factory);
				      // Set overall request size constraint
				      upload.setSizeMax(12582912); // set the maximum file size(12MB)

				      List<FileItem> items = upload.parseRequest(request); // get the file from the requesting jsp page
				      Iterator<FileItem> i = items.iterator();
				      while (i.hasNext()) {
				          FileItem fi = (FileItem) i.next();
				          String fileName = fi.getName();
				          if (fileName != null) {
				      File fullFile = new File(fi.getName());
				      File savedFile = new File(uploadPath, fullFile.getName());
				      fi.write(savedFile);
				          }
				      }
				      System.out.print("File has already been uploaded");
				   } catch (Exception e) {
				      e.printStackTrace();
				      System.out.print("Sorry, file size cannot be larger than 12MB");
				   }
		    }
		
	}



