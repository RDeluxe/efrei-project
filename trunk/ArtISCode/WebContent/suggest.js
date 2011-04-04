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

function emptySearchSuggest() {
	document.getElementById('search_suggest').innerHTML = '';
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
			
			var suggest = '<div id="infosearch" onmouseover="javascript:suggestOver(this);" ';
			suggest += 'onmouseout="javascript:suggestOut(this);" ';
			suggest += 'class="suggest_link" onclick="javascript:getProfileReq(\''+ str[i+2] +'\')">'
			+'<h3>'+ str[i] + ' ' +str[i+1] +'</h3>';
			suggest += '<div id="searchdesc"><img width="180" height="240" border=no src="'+ str[i+3] +'"/><p>' + str[i+4] + '</p></div>';
			suggest += '<div id="location"><b>Location : </b>' + str[i+5] + '</div>';
			suggest += '<div id="pied"></div></div>';
			ss.innerHTML += suggest;
			i=i+6;
		}
	}
}

function handleSearchSuggest() {
	if (searchReq.readyState == 4) {
		var ss = document.getElementById('search_suggest');
		ss.innerHTML = '';
		var str = searchReq.responseText.split("\n");
		for(i=0; i < str.length - 1; i+=2) {
			//Build our element string.  This is cleaner using the DOM, but
			//IE doesn't support dynamically added attributes.
			var suggest = '<div onmouseover="javascript:suggestOver(this);" ';
			suggest += 'onmouseout="javascript:suggestOut(this);" ';
			suggest += 'onclick="javascript:setSearch(this.childNodes[0].value);javascript:result();" ';
			suggest += 'class="suggest_link"><input id="loginReq" type="hidden" name="login" value="'+str[i+1]+'">' + str[i] + '</div>';
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