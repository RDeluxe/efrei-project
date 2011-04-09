<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC
        "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtmll/DTD/xhtmll-strict.dtd">
        <!--  
 Artis v 0.1
Copyright (c) 2011 Apex Team (EFREI Engineering School)

Please don't use this software or any classes coming from this software without permission.
-->

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>My Google Map</title>
<script src="http://maps.google.fr/maps?file=api&v=2&oe=utf-8&key=abcdefg" type="text/javascript">
</script>
</head>

<body onload="initialize()" onunload="GUload">
<div id="mapContainer" style="height:400px; width:400px;"></div>

</body>

<script type="text/javascript">
	function initialize(){
		if(GBrowserIsCompatible()){
     var map = new GMap2(document.getElementById("mapContainer"));
     map.setCenter(new GLatLng(33.0, 106.0), 3);
//	 map.addControl(new MyControl());
	     }}

	function MyControl(){}
	MyControl.prototype = new GControl();
	MyControl.prototype.initialize = function(map){
	    this.map = map;
	    var container = map.getContainer();
	    var label = document.createElement("div");
	    container.appendChild(label);
	    lable.innerHTML = "Welcome to use artIS";
	    return label;
	}

	MyControl.prototype.getDefaultPosition = function(){
	    return new GControlPosition(G_ANCHOR_TOP_LEFT, new GSize(50, 10));
	}
	    
</script>

</html>