function getXmlHttpRequestObject() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else if(window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		alert("Your Browser Sucks!\nIt's about time to upgrade don't you think?");
	}
}
function createAjaxObj(){
  var httprequest=false
  if (window.XMLHttpRequest)
  { // if Mozilla, Safari etc
    httprequest=new XMLHttpRequest()
    if (httprequest.overrideMimeType)
      httprequest.overrideMimeType('text/xml')
   }
   else if (window.ActiveXObject)
   { // if IE
     try {
       httprequest=new ActiveXObject("Msxml2.XMLHTTP");
     }
     catch (e){
       try{
          httprequest=new ActiveXObject("Microsoft.XMLHTTP");
       }
       catch (e){}
     }
   }
   return httprequest;
}
//Our XmlHttpRequest object to get the auto suggest
var searchReq = createAjaxObj();

function displayProfileReq() {
	if (searchReq.readyState == 4 || searchReq.readyState == 0) {
			searchReq.open("GET", 'Profile' , true);
			searchReq.onreadystatechange = handleProfileReq;
			searchReq.send(null);
	}
}


function handleProfileReq() {
	if (searchReq.readyState == 4) {
		var str = searchReq.responseText.split('\n');
		var displayer = document.getElementById('contenu');
		var result = '<div id="left">'
		+'<div id="info">'
		+ '<h3>'+ str[1] + ' ' +str[2] + '</h3>'
		+ '<p>Address : '+ str[6] + ', '+ str[8] + ' ' + str[7] + ' ' + str[9] + '</p>'
		+'<p>Mail : '+ str[3] +'</p>'
		+'</div>'
		+ '<br/>' 
		+ ' <br/>'
		+'<div id="desc"><h3>Description</h3><p>'
		+ str[11]+'</p></div>' + '<br/>' + ' <br/>'
		+'<input type="button" value="Modify your profile" onclick="javascript:modifyReq();" />'
		+'</div>'
		+'</div>'
		+'<div id="right" >'
		+'<div id="pic">';
		if (str[10]==" "){
			result += '<img src="Img/portrait.jpg" width="180" height="240" border=no>';
		}
		else {
			result += '<img src="'+ str[10] +'" width="180" height="240" border=no>';
		}
		result+= '</div>'
		+'<div id="player">'
		+'</div>'
		+'<div id="events">'
		+'</div>'
		+'</div>';
		displayer.innerHTML = result;
		if(str[12]!=null)
			{
		if((str[12].length > 0)&&(str[12]!="none")){
			var player = '<object type="application/x-shockwave-flash" data="player/dewplayer-mini.swf" width="200" height="20" id="dewplayer" name="dewplayer">'
			+'<param name="movie" value="player/dewplayer-mini.swf" />'
			+'<param name="flashvars" value="mp3='+str[12]+'" />'
			+'<param name="wmode" value="transparent" />'
			+'</object>';
			$('#player').append(player);
			
		}
			}
		
		
	}
}

function getProfileReq(login) {
	if (searchReq.readyState == 4 || searchReq.readyState == 0) {
			searchReq.open("POST", 'GetProfile?login=' + login , true);
			searchReq.onreadystatechange = handleGetProfileReq;
			searchReq.send(null);
	}
}


function handleGetProfileReq() {
	if (searchReq.readyState == 4) {
		var str = searchReq.responseText.split('\n');
		var displayer = document.getElementById('contenu');
		var result = '<div id="left">'
		+'<div id="info">'
		+'<input id="loginArtist" type="hidden" value="'+str[4]+'"/>'
		+ '<h3>'+ str[1] + ' ' +str[2] + '</h3>'
		+ '<p>Address : '+ str[6] + ', '+ str[8] + ' ' + str[7] + ' ' + str[9] + '</p>'
		+'<p>Mail : '+ str[3] +'</p>'
		+'</div>'
		+'<div id="desc"><h3>Description</h3><p>'
		+ str[11]+'</p></div>' + '<br/>' + ' <br/>'
		+'<div><input type="button" value="Invite to your Event" onclick="javascript:getEventReq();"/>'
		+'</div><div id="search_suggest_event"></div>'
		+'</div>'
		+'</div>'
		+'<div id="right" >'
		+'<div id="pic">';
		if (str[10]==" "){
			result += '<img src="Img/default_big.gif" width="180" height="240" border=no>';
		}
		else {
			result += '<img src="'+ str[10] +'" width="180" height="240" border=no>';
		}
		result+='</div>'
		+'<div id="player">'
		+'</div>'
		+'<div id="events">'
		+'</div>'
		+'</div>';
		displayer.innerHTML = result;
		if((str[12].length > 0)&&(str[12]!="none")){
			var player = '<object type="application/x-shockwave-flash" data="player/dewplayer-mini.swf" width="200" height="20" id="dewplayer" name="dewplayer">'
			+'<param name="movie" value="player/dewplayer-mini.swf" />'
			+'<param name="flashvars" value="mp3='+str[12]+'" />'
			+'<param name="wmode" value="transparent" />'
			+'</object>';
			$('#player').append(player);
			
		}
	}
}

function modifyReq() {
	if (searchReq.readyState == 4 || searchReq.readyState == 0) {
			searchReq.open("GET", 'Profile' , true);
			searchReq.onreadystatechange = handleModifyReq;
			searchReq.send(null);
	}
}


function handleModifyReq() {
	if (searchReq.readyState == 4) {
		var str = searchReq.responseText.split('\n');
		var displayer = document.getElementById('contenu');
		var result = '<form id="form1" method="post" action="Profile">'
		  +'<fieldset>'
		  +'<legend>Basic Info</legend>'
		  +'<p><label for="kind">Kind of account:</label>'
		  +'<input type="text" name="kind" value="';
		  if (str[0] == "1") result += 'User';
		  else result += 'Artist';
		  result += '" readonly="readonly"/></p>'
		  +'<p><label for="form1_firstname">Firstname:</label>'
		  +'<input type="text" name="firstname" value="' + str[1] + '" size="25" /></p>'
		  +'<p><label for="form1_lastname">Lastname:</label>'
		  +'<input type="text" name="lastname" value="' + str[2] + '" size="25" /></p>'
		  +'<p><label for="form1_mail">Mail:</label>'
		  +'<input type="text" name="mail" value="' + str[3] + '" /></p>'
		  +'</fieldset>'
		  +'<fieldset>'
		  +'<legend>Connection Info</legend>'
		  +'<p><label for="login">Your login:</label>'
		  +'<input type="text" name="login" value="' + str[4] + '" size="25" readonly="readonly" /></p>'
		  +'<p><label for="pass1">Your password:</label>'
		  +'<input type="password" name="pass1" value="' + str[5] + '" size="25" /></p>'
		  +'<p><label for="pass2">Password Check:</label>'
		  +'<input type="password" name="pass2" value="' + str[5] + '" size="25" /></p>'
		  +'</fieldset>'
		  +'<fieldset>'
		  +'<legend>Your Address</legend>'
		  +'<p><label for="street">Street:</label>'
		  +'<input type="text" name="street" value="' + str[6] + '" size="60" /></p>'
		  +'<p><label for="city">City:</label>'
		  +'<input type="text" name ="city" value="' + str[7] + '"  size="25" /></p>'
		  +'<p><label for="zip">Zip code:</label>'
		  +'<input type="text" name="zip" value="' + str[8] + '"  size="10" /></p>'
		  +'<p><label for="country">Country:</label>'
		  +'<input type="text" name="country" value="' + str[9] + '"  size="25" /></p>'
		  +'</fieldset>'
		  +'<fieldset>'
		  +'<legend>Your Picture</legend>'
		  +'<div id=pic2>'
		  +'<img src='+str[10]+' width="180" height="240" border=no>'
		  +'</div>'
		  +'<div id=upload>'
		  +'<input id="pic_upload" name="file_upload" type="file"/>'
		  +'</div>'
		  +'</fieldset>';
		 
		
		if (str[0] == "2") {
			result += '<fieldset>'
			    +'<legend>Artist Information</legend>'
			    +'<p><label for="form1_description">Your Description</label>'
			    +'<textarea name="description" id="form1_description">'+str[11]+'</textarea></p>'
			    +'<p><label for="form1_tag">Choose Tags (3 maximum):</label>'
			    +'<SELECT id="form1_tag" name="tag1">'
			    +'<OPTION VALUE="rock">Rock</OPTION>'
			    +'<OPTION VALUE="pop">Pop</OPTION>'
			    +'<OPTION VALUE="classic">Classic</OPTION>'
			    +'<OPTION VALUE="folk">Folk</OPTION>'
			    +'<OPTION VALUE="soloist">Soloist</OPTION>'	
			    +'<OPTION VALUE="band">Band</OPTION>'
			    +'<OPTION VALUE="celtic">Celtic</OPTION>'	
			    +'<OPTION VALUE="hard">Hard rock</OPTION>'		
			    +'</SELECT>'
			    +'<SELECT id="form1_tag" name="tag2">'
			    +'<OPTION VALUE="rock">Rock</OPTION>'
			    +'<OPTION VALUE="pop">Pop</OPTION>'
			    +'<OPTION VALUE="classic">Classic</OPTION>'
			    +'<OPTION VALUE="folk">Folk</OPTION>'
			    +'<OPTION VALUE="soloist">Soloist</OPTION>'
			    +'<OPTION VALUE="band">Band</OPTION>'
			    +'<OPTION VALUE="celtic">Celtic</OPTION>'	
			    +'<OPTION VALUE="hard">Hard rock</OPTION>'	
			    +'</SELECT>'
			    +'<SELECT id="form1_tag" name="tag3"><br/>'
			    +'<OPTION VALUE="rock">Rock</OPTION>'
			    +'<OPTION VALUE="pop">Pop</OPTION>'
			    +'<OPTION VALUE="classic">Classic</OPTION>'
			    +'<OPTION VALUE="folk">Folk</OPTION>'
			    +'<OPTION VALUE="soloist">Soloist</OPTION>'	
			    +'<OPTION VALUE="band">Band</OPTION>'
			    +'<OPTION VALUE="celtic">Celtic</OPTION>'
			    +'<OPTION VALUE="hard">Hard rock</OPTION>'	
			    +'</SELECT></p>'
			    +'</fieldset>'
			    +'<fieldset>'
			    +'<legend>Upload your music</legend>'
			    +'<div=musicup>'
			    +'<input id="music_upload" name="file_upload" type="file"/>'
			    +'</div>'
			    +'</fieldset>';
		}
		
		result +='<input type="submit"'
		  +'value="Submit" />'
		  +'<input type="reset"'
		  +'value="Reset" />'
		  +'</form>';
		displayer.innerHTML = result;
		$('#pic_upload').uploadify({
			  
			   'uploader'    : 'Upload?login=' +str[4],
			    'swf'  : 'uploadify/uploadify.swf',
			    'cancelImage' : 'uploadify/uploadify-cancel.png',
			    'auto'      : true,
			    'fileTypeExts'    : '*.jpg;',
			    'onUploadComplete' : function() {document.getElementById('pic2').innerHTML = '<img src='+str[10]+' width="180" height="240" border=no>';}
			    			  });
		$('#music_upload').uploadify({
			  
			   'uploader'    : 'Upload?login=' +str[4],
			    'swf'  : 'uploadify/uploadify.swf',
			    'cancelImage' : 'uploadify/uploadify-cancel.png',
			    'auto'      : true,
			    'fileTypeExts'    : '*.mp3;'
			    			  });
	}
}

function registerArtist() {
	var text = '<fieldset>'
	    +'<legend>Artist Information</legend>'
	    +'<p><label for="form1_description">Your Description</label>'
	    +'<textarea name="description" id="form1_description" rows="10" cols="50"></textarea></p>'
	    +'<p><label for="form1_tag">Choose Tags (3 maximum):</label>'
	    +'<SELECT id="form1_tag" name="tag1">'
	    +'<OPTION VALUE="rock">Rock</OPTION>'
	    +'<OPTION VALUE="pop">Pop</OPTION>'
	    +'<OPTION VALUE="classic">Classic</OPTION>'
	    +'<OPTION VALUE="folk">Folk</OPTION>'
	    +'<OPTION VALUE="soloist">Soloist</OPTION>'	
	    +'<OPTION VALUE="band">Band</OPTION>'
	    +'<OPTION VALUE="celtic">Celtic</OPTION>'	
	    +'<OPTION VALUE="hard">Hard rock</OPTION>'		
	    +'</SELECT>'
	    +'<SELECT id="form1_tag" name="tag2">'
	    +'<OPTION VALUE="rock">Rock</OPTION>'
	    +'<OPTION VALUE="pop">Pop</OPTION>'
	    +'<OPTION VALUE="classic">Classic</OPTION>'
	    +'<OPTION VALUE="folk">Folk</OPTION>'
	    +'<OPTION VALUE="soloist">Soloist</OPTION>'
	    +'<OPTION VALUE="band">Band</OPTION>'
	    +'<OPTION VALUE="celtic">Celtic</OPTION>'	
	    +'<OPTION VALUE="hard">Hard rock</OPTION>'	
	    +'</SELECT>'
	    +'<SELECT id="form1_tag" name="tag3"><br/>'
	    +'<OPTION VALUE="rock">Rock</OPTION>'
	    +'<OPTION VALUE="pop">Pop</OPTION>'
	    +'<OPTION VALUE="classic">Classic</OPTION>'
	    +'<OPTION VALUE="folk">Folk</OPTION>'
	    +'<OPTION VALUE="soloist">Soloist</OPTION>'	
	    +'<OPTION VALUE="band">Band</OPTION>'
	    +'<OPTION VALUE="celtic">Celtic</OPTION>'
	    +'<OPTION VALUE="hard">Hard rock</OPTION>'	
	    +'</SELECT></p>'
	    +'</fieldset>';
		document.getElementById('kindform').innerHTML = text;
}

function registerUser() {
	document.getElementById('kindform').innerHTML = '';
}

function getEventReq() {
	if (searchReq.readyState == 4 || searchReq.readyState == 0) {
		searchReq.open("GET", 'EventServlet' , true);
		searchReq.onreadystatechange = handleGetEvent;
		searchReq.send(null);
	}
}

function handleGetEvent() {
	if (searchReq.readyState == 4) {
		var ss = document.getElementById('search_suggest_event');
		ss.innerHTML = '';
		var str = searchReq.responseText.split("\n");
		for(i=0; i < str.length - 1; i+=2) {
			//Build our element string.  This is cleaner using the DOM, but
			//IE doesn't support dynamically added attributes.
			var suggest = '<div onmouseover="javascript:suggestOver(this);" ';
			suggest += 'onmouseout="javascript:suggestOut(this);" ';
			suggest += 'onclick="javascript:addArtistToEvent(' +str[i]+');" ';
			suggest += 'class="suggest_link"><input id="eventID" type="hidden" value="'+str[i] +'"/>' + str[i+1] + '</div>';
			ss.innerHTML += suggest;
		}
	}
}

function addArtistToEvent(event) {
	if (searchReq.readyState == 4 || searchReq.readyState == 0) {
		var login = document.getElementById('loginArtist').value;
		//var event = document.getElementById("eventID").value;
		searchReq.open("POST", 'EventServlet?login='+login+'&event='+event , true);
		searchReq.send(null);
	}
}
function updateEvent(name) {
	var elem = document.getElementById('artist_status'+name);
	alert(elem.options[elem.selectedIndex].value);
	document.location="UpdateEvent?event=" + name + "&artist_status" + name + "=" + elem.options[elem.selectedIndex].value;
}

function closeAllNotification(login) {
	if (searchReq.readyState == 4 || searchReq.readyState == 0) {
		searchReq.open("GET", 'CloseNotification?login=' + login, true);
		searchReq.onreadystatechange = handleClosingNotifications;
		searchReq.send(null);
	}
}
function closeNotification(login, notId) {
	if (searchReq.readyState == 4 || searchReq.readyState == 0) {
		searchReq.open("GET", 'CloseNotification?login=' + login + '&notId=' + notId, true);
		searchReq.send(null);
	}
}

function handleClosingNotifications() {
	document.getElementById('notifications').setAttribute('onmouseover', "");
	document.getElementById('notifications').innerHTML = '';
}

function removeArtist(artist, event) {
	if (searchReq.readyState == 4 || searchReq.readyState == 0) {
		searchReq.open("POST", 'CancelArtist?artist=' + artist + '&event=' + event, true);
		searchReq.send(null);
	}
}

var directionDisplay;
var directionsService;
var map;

function initializeMap() {
	directionsService = new google.maps.DirectionsService();
	directionsDisplay = new google.maps.DirectionsRenderer();
	var latlng = new google.maps.LatLng(-34.397, 150.644);
  var myOptions = {
    zoom: 8,
    center: latlng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
  directionsDisplay.setMap(map);
}
function calcRoute(from, to) {
	  var request = {
	    origin:from, 
	    destination:to,
	    travelMode: google.maps.DirectionsTravelMode.DRIVING
	  };
	  directionsService.route(request, function(result, status) {
		  if (status == google.maps.DirectionsStatus.OK) {
	      directionsDisplay.setDirections(result);
	    }
	  });
	}
var geocoder;
function advancedsearch() {
	var tag = document.getElementById("tag");
	var city = document.getElementById("city");
	var tagname = tag.options[tag.selectedIndex].value;
	if (searchReq.readyState == 4 || searchReq.readyState == 0) {
		searchReq.open("GET", 'AdvancedSearch?tagname=' + tagname + '&city=' + city, true);
		searchReq.onreadystatechange = handleadvancedSearch;
		searchReq.send(null);
	}
}
var origin;
var object;
var result;
var maxdist;
var city;
function handleadvancedSearch() {
	if (searchReq.readyState == 4) {
		object = eval('('+ searchReq.responseText +')');   
		city = document.getElementById("city").value; 
		result = object.result;
		geocoder = new google.maps.Geocoder();
		var displayer = document.getElementById('contenu');
		maxdist = document.getElementById('dist').value;
		displayer.innerHTML = '';
		geocoder.geocode( { 'address': city}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				origin = results[0].geometry.location;
				var i = 0;
				while (i<result.length) {
					distance(result[i].address, origin, i);
					i++;
				}
			};
		});
	}
}

function distance(address, origin, i) {
	var a;
	var displayer = document.getElementById('contenu');
	geocoder.geocode( { 'address': address}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			a = results[0].geometry.location;
			var dist = google.maps.geometry.spherical.computeDistanceBetween(a, origin);
			if ((dist/1000)<maxdist) {
				var suggest = '<div id="infosearch" onmouseover="javascript:suggestOver(this);" ';
				suggest += 'onmouseout="javascript:suggestOut(this);" ';
				suggest += 'class="suggest_link" onclick="javascript:getProfileReq(\''+ result[i].login +'\')">'
				+'<h3>'+ result[i].firstname + ' ' +result[i].lastname +'</h3>';
				suggest += '<div id="searchdesc"><img src="'+ result[i].photo +'"/><p>' + result[i].description + '</p></div>';
				suggest += '<div id="location"><b>Location : </b>' +result[i].address+ '<p>Distance to ' + city + ' : ' + Math.ceil(dist/1000) + ' Km</p></div>';
				suggest += '<div id="pied"></div></div>';
				displayer.innerHTML += suggest;
			}
		};
	});
}