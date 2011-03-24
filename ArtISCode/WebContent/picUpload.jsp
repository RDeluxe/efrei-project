<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--

The problem of the current version is if you upload the file with the same name,
it will automatically rewrite the file without asking the user.

-->
<%
    
	File uploadPath = new File("/efrei-project/trunk/ArtISCode/WebContent/Img/");// Directory to upload the file
    
	
	if (!uploadPath.exists()) {
       uploadPath.mkdirs();
    }
    // Create a buffer folder
    File tempPathFile = new File("d:\\temp\\buffer\\");
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
       upload.setSizeMax(12582912); // set the maximum file siwe(12MB)
 
       List<FileItem> items = upload.parseRequest(request);// get the file from the requesting jsp page
       Iterator<FileItem> i = items.iterator();
       while (i.hasNext()) {
           FileItem fi = (FileItem) i.next();
           String fileName = fi.getName();
           if (fileName != null) {
       File fullFile = new File("portrait.jpg");
       File savedFile = new File(uploadPath, fullFile
              .getName());
       fi.write(savedFile);
           }
       }
       out.print("File has already been uploaded");
    } catch (Exception e) {
       e.printStackTrace();
       out.print("Sorry, file size cannot be larger than 12MB");
    }
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>File upload</title>
</head>
<body>
</body>
</html>