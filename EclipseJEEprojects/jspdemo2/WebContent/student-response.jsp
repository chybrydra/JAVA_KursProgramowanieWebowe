<html>

<head>
<title>Student Confirmation Title</title>
</head>

<body>
	The student is confirmed: ${param.firstName} ${param.lastName} <br/><br/>
	Student's country: ${param.country} <br/><br/>
	Favourite progr. lang.: ${param.favLang} <br/><br/>
	Known technologies:
	<ul>
		<%
		String[] technologies = request.getParameterValues("knownTech");
		for (String item: technologies){
			out.println("<li>" + item + "</li>");
		}
		%>
	</ul>
</body>

</html>