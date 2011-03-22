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
		+'Name : '+ str[2] +' <br/>'
		+'Firstname : '+ str[1] +' <br/>'
		+'Address : '+ str[6] + '&nbsp;'
		+' Town : '+ str[7] + '&nbsp;'
		+' Zip : '+ str[8] + '&nbsp;'
		+' Country : '+ str[9] +' <br/>'
		+' Mail : '+ str[3] +' <br/>'
		+' Tags : '

		+'</div>'
		+'<div id="desc">'
		+ str[10]+'</div>' + '<br/>' + ' <br/>'
		+'<input type="button" value="Modify your profile" onclick="javascript:modifyReq();" />'
		+'</div>'
		+'</div>'
		+'<div id="right" >'
		+'<div id="pic">'
		+'<img src="Img/default_big.gif" width="180" height="240" border=no>'
		+'</div>'
		+'<div id="events">'
		+'</div>'
		+'</div>';
		displayer.innerHTML = result;
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
		+'Name : '+ str[2] +' <br/>'
		+'Firstname : '+ str[1] +' <br/>'
		+'Address : '+ str[6] + '&nbsp;'
		+' Town : '+ str[7] + '&nbsp;'
		+' Zip : '+ str[8] + '&nbsp;'
		+' Country : '+ str[9] +' <br/>'
		+' Mail : '+ str[3] +' <br/>'
		+' Tags : '
		+'</div>'
		+'<div id="desc">'
		+ str[10]
		+'</div>'+ '<br/>' + ' <br/>'
		+'<div><input type="button" value="Invite to your Event" onclick="javascript:getEventReq();"/>'
		+'</div><div id="search_suggest_event"></div>'
		+'</div>'
		+'</div>'
		+'<div id="right" >'
		+'<div id="pic">'
		+'<img src="Img/default_big.gif" width="180" height="240" border=no>'
		+'</div>'
		+'<div id="events">'
		+'</div>'
		+'</div>';
		displayer.innerHTML = result;
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
		  +'</fieldset>';
		if (str[0] == "2") {
			result += '<fieldset>'
			    +'<legend>Artist Information</legend>'
			    +'<p><label for="form1_description">Your Description</label>'
			    +'<textarea name="description" id="form1_description">'+str[10]+'</textarea></p>'
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
		}
		displayer.innerHTML = result
		  +'<input type="submit"'
		  +'value="Submit" />'
		  +'<input type="reset"'
		  +'value="Reset" />'
		  +'</form>';
	}
}

function registerArtist() {
	var text = '<fieldset>'
	    +'<legend>Artist Information</legend>'
	    +'<p><label for="form1_description">Your Description</label>'
	    +'<textarea name="description" id="form1_description"></textarea></p>'
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
			suggest += 'onclick="javascript:addArtistToEvent();" ';
			suggest += 'class="suggest_link"><input id="eventID" type="hidden" value="'+str[i] +'"/>' + str[i+1] + '</div>';
			ss.innerHTML += suggest;
		}
	}
}

function addArtistToEvent() {
	if (searchReq.readyState == 4 || searchReq.readyState == 0) {
		var login = document.getElementById('loginArtist').value;
		var event = document.getElementById("eventID").value;
		searchReq.open("POST", 'EventServlet?login='+login+'&event='+event , true);
		searchReq.send(null);
	}
}