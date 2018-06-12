<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register | My Application</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>

	<%! String errorMessage = null; %>
	
	<%
		
		if(request.getAttribute("errorMessage") != null) {
			
			errorMessage = (String)request.getAttribute("errorMessage");
			
			if(!errorMessage.isEmpty()) {
	%>
	
		<div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
			<% out.println(errorMessage); %>
		</div>
	
	<%
	
			}
		}
	%>

	<form action=register method="post">

		<div class="loginBox">

			<h1>Register</h1>
			<p>
				Please register yourself :)
			</p>

			<input class="field" type="text" placeholder="Your Username" name="userName" />
			
			<input class="field" type="password" placeholder="Your Password" name="password" />

			<input class="field" type="text" placeholder="First Name" name="firstName" />
			
			<input class="field" type="text" placeholder="Last Name" name="lastName" />
			
			<input class="field" type="text" placeholder="Email" name="email" />
			
			<input class="field" type="text" placeholder="Phone" name="phone" />

			<input class="loginBtn" type="submit" value="register" />

		</div>

	</form>

</body>
</html>