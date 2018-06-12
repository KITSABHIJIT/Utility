<%@page import="com.test.entity.UserDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dash Board | My Application</title>
<link rel="stylesheet" href="css/style.css" /> 
</head>
<body>

	<%! 
		UserDetails userDetails = null;
		
		String name = null;
	
	%>

	<%
	
		if(session.getAttribute("userDetails") != null) {
			
			userDetails = (UserDetails)session.getAttribute("userDetails");
			
			name = userDetails.getFirstName()+" "+userDetails.getLastName();
		}
	
	%>

	<h1>Hello <%=name %>, Welcome to Application</h1>

	<br/>
	
	
	<!-- Your design should goes here -->
	<h3>You can design this page better than me... :))</h3>

</body>
</html>