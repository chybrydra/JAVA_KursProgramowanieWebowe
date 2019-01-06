<html>

<head>
<title>Declaration test</title>
</head>

<body>

<%!
	String makeItLower(String data){
		return data.toLowerCase();
	}

	String text = "TeXt WeiRdlY meYd";
%>
Lower case "TeXt WeiRdlY meYd": <%= makeItLower(text) %>


</body>


</html>