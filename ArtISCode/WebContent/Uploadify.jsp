<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="uploadify/uploadify.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="uploadify/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="uploadify/swfobject.js"></script>
<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>
<script type="text/javascript">
$(document).ready(function() {
  $('#file_upload').uploadify({
	  'scriptData': { 'session': '<%=session.getAttribute("login")%>'},
    'uploader'    : 'Upload',
    'swf'  : 'uploadify/uploadify.swf',
    'cancelImage' : 'uploadify/uploadify-cancel.png',
    'auto'      : true
  });
 
});
</script>
</head>
<body>
<input id="file_upload" name="file_upload" type="file" />
</body>
</html>