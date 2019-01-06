<html>

<head>
<title>HTTP Request in JSP</title>
</head>

<body>
<h3>JSP Built-In Objects</h3>
Request user agent: <%= request.getHeader("User-Agent") %>
<br/>
Request language: <%= request.getLocale() %>
<br/>

</body>
</html>