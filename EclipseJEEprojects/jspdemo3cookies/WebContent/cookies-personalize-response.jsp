<html>

<head>
<title>Confirmation</title>
</head>

<%
	//read from data
	String favLang = request.getParameter("favouriteLang");

	//create the cookie
	Cookie theCookie = new Cookie("myApp.favouriteLang", favLang);
	
	//set the life span -> how long the cookie should exist
	theCookie.setMaxAge(365*24*60*60);
	
	//send cookie to browser
	response.addCookie(theCookie);
%>

<body>
	Thanks! We set your favourite language to ${param.favouriteLang}
	<br/><br/>
	<a href="cookies-homepage.jsp">Return to homepage</a>
</body>

</html>