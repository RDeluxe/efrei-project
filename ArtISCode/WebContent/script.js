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
		var result = '<form method="post" action="Profile">' +
		  +'<fieldset>'
		  +'<legend>Registration page for User</legend>'
		  +'<fieldset>'
		  +'<legend>Basic Info</legend><br/>'
		  +'Kind of account:<br/>'
		  +'<input type="text" name="kind" value="User" readonly="readonly" /><br/>'
		  +'Firstname:<br/>'
		  +'<input type="text" name="firstname" value="' + str[1] + '" size="25" /><br/>'
		  +'Lastname:<br/>'
		  +'<input type="text" name="lastname" value="' + str[2] + '" size="25" /><br/>'
		  +'Mail:<br/>'
		  +'<input type="text" name="mail" value="' + str[3] + '" /><br/>'
		  +'<br/>'
		  +'</fieldset>'
		  +'<fieldset>'
		  +'<legend>Connection Info</legend><br/>'
		  +'Your login:<br/>'
		  +'<input type="text" name="login" value="' + str[4] + '" size="25" readonly="readonly" /><br/>'
		  +'Your password:<br/>'
		  +'<input type="password" name="pass1" value="' + str[5] + '" size="25" /><br/>'
		  +'Password Check:<br/>'
		  +'<input type="password" name="pass2" value="' + str[5] + '" size="25" /><br/>'
		  +'</fieldset>'
		  +'<fieldset>'
		  +'<legend>Your Address</legend><br/>'
		  +'Street:<br/>'
		  +'<input type="text" name="street" value="' + str[6] + '" size="60" /><br/>'
		  +'City:<br/>'
		  +'<input type="text" name ="city" value="' + str[7] + '"  size="25" /><br/>'
		  +'Zip code:<br/>'
		  +'<input type="text" name="zip" value="' + str[8] + '"  size="10" /><br/>'
		  +'Country:<br/>'
		  +'<input type="text" name="country" value="' + str[9] + '"  size="25" /><br/>'
		  +'</fieldset>';
		if (str[0] == "2") {
			result += '<fieldset>'
		    +'<legend>Artist Information</legend><br/>'
		    +'<label for="description">Your Description</label><br />'
		    +' <textarea name="description" id="description">'+ str[10] +'</textarea><br/>'
		    +'</fieldset>';
		}
		displayer.innerHTML = result
		  +'<br/>'
		  +'<p></p>'
		  +'<input type="submit"'
		  +'value="Submit" />'
		  +'<input type="reset"'
		  +'value="Reset" />'
		  +'</fieldset>'
		  +'</form>';
	}
}