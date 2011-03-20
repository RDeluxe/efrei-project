bgcolor = '#888888';
bgcolor2 = '#7B7B7B';
document.write('<style type="text/css">');
document
		.write('.popper { POSITION: absolute; VISIBILITY: hidden; z-index:15; left:99px ')
document.write('#topgauche { position:absolute;  z-index:10; }')
document.write('A:hover.ejsmenu {color:#FFFFFF; text-decoration:none;}')
document.write('.ejsmenu {color:#FFFFFF; text-decoration:none;}')
document.write('</style>')
document
		.write('<div style="position:relative;height:25"><DIV class=popper id=topdeck></DIV>');
/*
 * SCRIPT EDITE SUR L'EDITEUR JAVACSRIPT http://www.editeurjavascript.com
 */

/*
 * LIENS
 */
zlien = new Array;
zlien[0] = new Array;
zlien[1] = new Array;
zlien[2] = new Array;
zlien[3] = new Array;
zlien[0][0] = '<A HREF="index.jsp" CLASS=ejsmenu>Home</A>';
zlien[1][0] = '<A HREF="/SearchEvent" CLASS=ejsmenu>Search Event</A>';
zlien[1][1] = '<A HREF="/YourEventServlet" CLASS=ejsmenu>Show Your Event</A>';
zlien[2][0] = '<A HREF="/SearchArtist" CLASS=ejsmenu>Search Artist</A>';
zlien[3][0] = '<A HREF="/LogoutServlet" CLASS=ejsmenu>Logout</A>';
if (document.getElementById) {
	skn = document.getElementById("topdeck").style
	skn.left = 99;
}

function pop(msg, pos) {
	skn.visibility = "hidden";
	a = true
	skn.top = pos;
	var content = "<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0 BGCOLOR=#000000 WIDTH=150><TR><TD><TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=1>";
	pass = 0
	while (pass < msg.length) {
		content += "<TR><TD BGCOLOR=" + bgcolor
				+ " onMouseOver=\"this.style.background='" + bgcolor2
				+ "'\" onMouseOut=\"this.style.background='" + bgcolor
				+ "'\" HEIGHT=20><FONT SIZE=1 FACE=\"Verdana\"><B>&nbsp;&nbsp;"
				+ msg[pass] + "</B></FONT></TD></TR>";
		pass++;
	}
	content += "</TABLE></TD></TR></TABLE>";
	document.getElementById("topdeck").innerHTML = content;
	skn.visibility = "visible";
}
function kill() {
	if (document.getElementById)
		skn.visibility = "hidden";
}
document.onclick = kill;
if (document.getElementById) {
	document
			.write('<DIV ID=topgauche><TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0 BGCOLOR=#000000 WIDTH=100 HEIGHT=80><TR><TD><TABLE CELLPADING=0 CELLSPACING=1 BORDER=0 WIDTH=100% HEIGHT=80>')
	document
			.write('<tr><TD WIDTH=100 ALIGN=center BGCOLOR='
					+ bgcolor
					+ ' onMouseOver="this.style.background=\''
					+ bgcolor2
					+ '\';pop(zlien[0],0)" onMouseOut="this.style.background=\''
					+ bgcolor
					+ '\'" CLASS=ejsmenu><FONT SIZE=1 FACE="Verdana"><B>Home</B></FONT></TD></tr>')
	document
			.write('<tr><TD WIDTH=100 ALIGN=center BGCOLOR='
					+ bgcolor
					+ ' onMouseOver="this.style.background=\''
					+ bgcolor2
					+ '\';pop(zlien[1],20)" onMouseOut="this.style.background=\''
					+ bgcolor
					+ '\'" CLASS=ejsmenu><FONT SIZE=1 FACE="Verdana"><B>Event</B></FONT></TD></tr>')
	document
			.write('<tr><TD WIDTH=100 ALIGN=center BGCOLOR='
					+ bgcolor
					+ ' onMouseOver="this.style.background=\''
					+ bgcolor2
					+ '\';pop(zlien[2],40)" onMouseOut="this.style.background=\''
					+ bgcolor
					+ '\'" CLASS=ejsmenu><FONT SIZE=1 FACE="Verdana"><B>Artist</B></FONT></TD></tr>')
	document
			.write('<tr><TD WIDTH=100 ALIGN=center BGCOLOR='
					+ bgcolor
					+ ' onMouseOver="this.style.background=\''
					+ bgcolor2
					+ '\';pop(zlien[3],60)" onMouseOut="this.style.background=\''
					+ bgcolor
					+ '\'" CLASS=ejsmenu><FONT SIZE=1 FACE="Verdana"><B>Logout</B></FONT></TD></tr>')
	document.write('</TABLE></TD></TR></TABLE></DIV>')
}
document.write('</div>');