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

import controller.ManageUser;
import controller.SearchEventEngine;

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
		
		 this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	
		
		String login = (String) request.getParameter("login");
		System.out.println(login);
		controller.Search s = new controller.Search();
		DAOArtist daoA = new DAOArtist();
		Artist a = (Artist) daoA.searchByLogin(login);
		User user = new User();
		String kind = new String();
		if (a==null)
		{kind="1";}else{kind="2";}
		if(kind.equalsIgnoreCase("1"))
		{
			user = s.SearchByLogin(login);
		}
		
		String folder = getServletContext().getRealPath("/" )+ File.separator + "uploads";
		        
		
		        
				File uploadPath = new File(folder);
				   // Create a buffer folder
				   File tempPathFile = new File(getServletContext().getRealPath("/" )+ File.separator + "uploads" + File.separator +  "buffer");
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
				          System.out.println(fileName);
				          
				        
				          
				          if (fileName != null) {
				        	  String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
				        	  System.out.println(extension);
					          if((extension.equalsIgnoreCase("jpg"))||(extension.equalsIgnoreCase("jpeg")))
					        		  {
					        	  
					        	  folder =  getServletContext().getRealPath("/" ) + "uploads" + File.separator +"IMG";
					        	  uploadPath = new File(folder); // Directory to upload the file
								   if (!uploadPath.exists()) {
								      uploadPath.mkdirs();
								   }
								   File fullFile = new File(fi.getName());
								      File savedFile = new File(uploadPath, fullFile.getName());
								      fi.write(savedFile);
								      if(kind.equalsIgnoreCase("1")){
								    	  if (user.getPhoto()!=null)
								    	  {
								    	  File f = new File( user.getPhoto());
								    	  f.delete();		
								    	  savedFile.renameTo(new File(user.getPhoto()));
								    	  savedFile.delete();
								    	  }else{
								    		  user.setPhoto(getServletContext().getRealPath("/" )+ "uploads" + File.separator +"IMG"+ File.separator +user.getLogin()+".jpg");
								    		  savedFile.renameTo(new File(user.getPhoto()));
									    	  savedFile.delete();
									    	  ManageUser manager = new ManageUser();
									    	  manager.modifyUser(user);
								    	  }
								    	 
								      }
								      if(kind.equalsIgnoreCase("2")){
								    	  if (a.getPhoto()!=null)
								    	  {
								    	  File f = new File( a.getPhoto());
								    	  f.delete();		
								    	  savedFile.renameTo(new File(a.getPhoto()));
								    	  savedFile.delete();
								    	  }else{
								    		  a.setPhoto(getServletContext().getRealPath("/" ) + "uploads" + File.separator +"IMG"+ File.separator +a.getLogin()+".jpg");
								    		  savedFile.renameTo(new File(a.getPhoto()));
									    	  savedFile.delete();
									    	  ManageUser manager = new ManageUser();
									    	  manager.modifyArtist(a);
								    	  }
								    	 
								      }
					        		  }
					          if((extension.equalsIgnoreCase("mp3"))&&(kind.equalsIgnoreCase("2")))
					          {
					        	  
					        	  folder = getServletContext().getRealPath("/" ) + "uploads" + File.separator +"MP3";
					        	  uploadPath = new File(folder); // Directory to upload the file
								   if (!uploadPath.exists()) {
								      uploadPath.mkdirs();
								   }
								   File fullFile = new File(fi.getName());
								      File savedFile = new File(uploadPath, fullFile.getName());
								      fi.write(savedFile);
								      if (a.getMusic()!=null)
							    	  {
							    	  File f = new File( a.getMusic());
							    	  f.delete();		
							    	  savedFile.renameTo(new File(a.getMusic()));
							    	  savedFile.delete();
							    	  }else{
							    		  a.setMusic(getServletContext().getRealPath("/" ) + "uploads" + File.separator +"MP3"+ File.separator+a.getLogin()+".mp3");
							    		  savedFile.renameTo(new File(a.getMusic()));
								    	  savedFile.delete(); 
								    	  ManageUser manager = new ManageUser();
								    	  manager.modifyUser(a);
							    	  }
					          }
				      
				          }
				      }
				      System.out.print("File has already been uploaded");
				   } catch (Exception e) {
				      e.printStackTrace();
				      System.out.print("Sorry, file size cannot be larger than 12MB");
				   }
		    }
		
	}



