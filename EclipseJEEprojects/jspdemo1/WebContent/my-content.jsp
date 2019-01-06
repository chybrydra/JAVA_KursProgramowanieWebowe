<div id="content">
<form method="post">
	Name:<br/> 
	<input type="text" name="studentName" /><br/>
	2nd name:<br/> 
	<input type="text" name="studentSecondName" /><br/>
	<input type="submit" value="go!" /><br/>
	<%		
	if (request.getParameter("studentName")!=null 
	&& request.getParameter("studentSecondName")!=null){
		String name=request.getParameter("studentName");
		String surname=request.getParameter("studentSecondName");		
		out.println("Name: " + name + ", surname: " + surname);
	}
	%>
</form>
</div>