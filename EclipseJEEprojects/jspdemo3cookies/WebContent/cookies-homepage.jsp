<html>

<head>
<title>HOMEPAGE</title>
</head>

<body>
<h3>Training portal</h3>

<!-- read fav programming language from cookie -->
<%
	//default
	String favLang = "JAVA";
	
	//ask for all cookies 
	Cookie[] theCookies = request.getCookies();
	
	//find our favourite language cookie
	if (theCookies != null){
		for (Cookie tempCookie: theCookies){
			if ("myApp.favouriteLang".equals(tempCookie.getName())){
				favLang = tempCookie.getValue();
				break;
			}
		}
	}
%>


<!--  show personalized page -->
<h4>New books for <%= favLang %></h4>
<ul>
	<li>item1</li>
	<li>item2</li>
</ul>

<h4>Latest news reports for <%= favLang %></h4>
<ul>
	<li>item1</li>
	<li>item2</li>
</ul>

<h4>Hot jobs for <%= favLang %></h4>
<ul>
	<li>item1</li>
	<li>item2</li>
</ul>

<hr/>
<a href="cookies-personalize-form.html">Personalize this page</a>

</body>
</html>