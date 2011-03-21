//Gets the browser specific XmlHttpRequest Object
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
   return httprequest
}
//Our XmlHttpRequest object to get the auto suggest
var searchReq = createAjaxObj();

//Called from keyup on the search textbox.
//Starts the AJAX request.
function searchSuggest() {
	if (searchReq.readyState == 4 || searchReq.readyState == 0) {
		if (document.getElementById('txtSearch').value != '') {
		var str = escape(document.getElementById('txtSearch').value);
		searchReq.open("GET", 'Search?keyword=' + str, true);
		searchReq.onreadystatechange = handleSearchSuggest;
		searchReq.send(null);
		}
	}
}

function result() {
	if (searchReq.readyState == 4 || searchReq.readyState == 0) {
		var str = escape(document.getElementById('txtSearch').value);
		searchReq.open("GET", 'Result?keyword=' + str, true);
		document.getElementById('txtSearch').value = '';
		document.getElementById('search_suggest').innerHTML = '';
		searchReq.onreadystatechange = handleResult;
		searchReq.send(null);
	}
}

//Called when the AJAX response is returned.
function  handleResult() {
	if (searchReq.readyState == 4) {
		var ss = document.getElementById('contenu');
		ss.innerHTML = '';
		var str = searchReq.responseText.split("\n");
		i=0;
		while(i < str.length - 1) {
			//Build our element string.  This is cleaner using the DOM, but
			//IE doesn't support dynamically added attributes.
			
			var suggest = '<div id="info" onmouseover="javascript:suggestOver(this);" ';
			suggest += 'onmouseout="javascript:suggestOut(this);" ';
			suggest += 'class="suggest_link"><h3>FirstName :</h3>' + str[i] + '&nbsp&nbsp&nbsp<h3> LastName :</h3>'+str[i+1];
			suggest += '<br /><br />';
			suggest += '<input id="loginReq" type="hidden" name="login" value="'+str[i+2]+'">';
			suggest += '<button onclick="javascript:getProfileReq()">Go on the profile</button>';
			suggest += '</form>';
			suggest += '</div>';
			ss.innerHTML += suggest;
			i=i+3;
		}
	}
}

function handleSearchSuggest() {
	if (searchReq.readyState == 4) {
		var ss = document.getElementById('search_suggest');
		ss.innerHTML = '';
		var str = searchReq.responseText.split("\n");
		for(i=0; i < str.length - 1; i++) {
			//Build our element string.  This is cleaner using the DOM, but
			//IE doesn't support dynamically added attributes.
			var suggest = '<div onmouseover="javascript:suggestOver(this);" ';
			suggest += 'onmouseout="javascript:suggestOut(this);" ';
			suggest += 'onclick="javascript:setSearch(this.innerHTML);javascript:result();" ';
			suggest += 'class="suggest_link">' + str[i] + '</div>';
			ss.innerHTML += suggest;
		}
	}
}

//Mouse over function
function suggestOver(div_value) {
	div_value.className = 'suggest_link_over';
}
//Mouse out function
function suggestOut(div_value) {
	div_value.className = 'suggest_link';
}
//Click function
function setSearch(value) {
	document.getElementById('txtSearch').value = value;
	document.getElementById('search_suggest').innerHTML = '';
} 