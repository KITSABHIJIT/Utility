<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>

<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login | My Application</title>
<link rel="stylesheet" href="css/style.css" />


<link rel='stylesheet prefetch'
	href='https://fonts.googleapis.com/css?family=Open+Sans:600'>

<style>
body {
	margin: 0;
	color: #6a6f8c;
	background: #c8c8c8;
	font: 600 16px/18px 'Open Sans', sans-serif;
}

*, :after, :before {
	box-sizing: border-box
}

.clearfix:after, .clearfix:before {
	content: '';
	display: table
}

.clearfix:after {
	clear: both;
	display: block
}

a {
	color: inherit;
	text-decoration: none
}

.login-wrap {
	width: 100%;
	margin: auto;
	max-width: 525px;
	min-height: 870px;
	position: relative;
	background:
		url(https://raw.githubusercontent.com/khadkamhn/day-01-login-form/master/img/bg.jpg)
		no-repeat center;
	box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0
		rgba(0, 0, 0, .19);
}

.login-html {
	width: 100%;
	height: 100%;
	position: absolute;
	padding: 90px 70px 50px 70px;
	background: rgba(40, 57, 101, .9);
}

.login-html .sign-in-htm, .login-html .sign-up-htm {
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	position: absolute;
	-webkit-transform: rotateY(180deg);
	transform: rotateY(180deg);
	-webkit-backface-visibility: hidden;
	backface-visibility: hidden;
	transition: all .4s linear;
}

.login-html .sign-in, .login-html .sign-up, .login-form .group .check {
	display: none;
}

.login-html .tab, .login-form .group .label, .login-form .group .button
	{
	text-transform: uppercase;
}

.login-html .tab {
	font-size: 22px;
	margin-right: 15px;
	padding-bottom: 5px;
	margin: 0 15px 10px 0;
	display: inline-block;
	border-bottom: 2px solid transparent;
}

.login-html .sign-in:checked+.tab, .login-html .sign-up:checked+.tab {
	color: #fff;
	border-color: #1161ee;
}

.login-form {
	min-height: 345px;
	position: relative;
	-webkit-perspective: 1000px;
	perspective: 1000px;
	-webkit-transform-style: preserve-3d;
	transform-style: preserve-3d;
}

.login-form .group {
	margin-bottom: 15px;
}

.login-form .group .label, .login-form .group .input, .login-form .group .button
	{
	width: 100%;
	color: #fff;
	display: block;
}

.login-form .group .input, .login-form .group .button {
	border: none;
	padding: 15px 20px;
	border-radius: 25px;
	background: rgba(255, 255, 255, .1);
}

.login-form .group input[data-type="password"] {
	text-security: circle;
	-webkit-text-security: circle;
}

.login-form .group .label {
	color: #aaa;
	font-size: 12px;
}

.login-form .group .button {
	background: #1161ee;
}

.login-form .group label .icon {
	width: 15px;
	height: 15px;
	border-radius: 2px;
	position: relative;
	display: inline-block;
	background: rgba(255, 255, 255, .1);
}

.login-form .group label .icon:before, .login-form .group label .icon:after
	{
	content: '';
	width: 10px;
	height: 2px;
	background: #fff;
	position: absolute;
	transition: all .2s ease-in-out 0s;
}

.login-form .group label .icon:before {
	left: 3px;
	width: 5px;
	bottom: 6px;
	-webkit-transform: scale(0) rotate(0);
	transform: scale(0) rotate(0);
}

.login-form .group label .icon:after {
	top: 6px;
	right: 0;
	-webkit-transform: scale(0) rotate(0);
	transform: scale(0) rotate(0);
}

.login-form .group .check:checked+label {
	color: #fff;
}

.login-form .group .check:checked+label .icon {
	background: #1161ee;
}

.login-form .group .check:checked+label .icon:before {
	-webkit-transform: scale(1) rotate(45deg);
	transform: scale(1) rotate(45deg);
}

.login-form .group .check:checked+label .icon:after {
	-webkit-transform: scale(1) rotate(-45deg);
	transform: scale(1) rotate(-45deg);
}

.login-html .sign-in:checked+.tab+.sign-up+.tab+.login-form .sign-in-htm
	{
	-webkit-transform: rotate(0);
	transform: rotate(0);
}

.login-html .sign-up:checked+.tab+.login-form .sign-up-htm {
	-webkit-transform: rotate(0);
	transform: rotate(0);
}

.hr {
	height: 2px;
	margin: 60px 0 50px 0;
	background: rgba(255, 255, 255, .2);
}

.foot-lnk {
	text-align: center;
}
</style>

<script>
	window.console = window.console || function(t) {
	};
</script>



<script>
	if (document.location.search.match(/type=embed/gi)) {
		window.parent.postMessage("resize", "*");
	}
</script>


</head>

<body translate="no">
	<%!String errorMessage = null;%>

	<%
		if (request.getAttribute("errorMessage") != null) {

			errorMessage = (String) request.getAttribute("errorMessage");

			if (!errorMessage.isEmpty()) {
	%>

	<div class="alert">
		<span class="closebtn"
			onclick="this.parentElement.style.display='none';">&times;</span>
		<%
			out.println(errorMessage);
		%>
	</div>

	<%
		}
		}
	%>
	<div class="login-wrap">
		<div class="login-html">
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label
				for="tab-1" class="tab">Sign In</label> <input id="tab-2"
				type="radio" name="tab" class="sign-up"><label for="tab-2"
				class="tab">Sign Up</label>
			<div class="login-form">
				<form action="authenticate" method="post">
					<div class="sign-in-htm">
						<div class="group">
							<label for="user" class="label">Username</label> <input id="userName" name="userName"
								type="text" class="input">
						</div>
						<div class="group">
							<label for="pass" class="label">Password</label> <input id="password" name="password"
								type="password" class="input" data-type="password">
						</div>
						<div class="group">
							<input id="check" type="checkbox" class="check" checked>
							<label for="check"><span class="icon"></span> Keep me
								Signed in</label>
						</div>
						<div class="group">
							<input type="submit" class="button" value="Sign In">
						</div>
						<div class="hr"></div>
						<div class="foot-lnk">
							<a href="#forgot">Forgot Password?</a>
						</div>
					</div>
				</form>
				<form action="register" method="post">
					<div class="sign-up-htm">
						<div class="group">
							<label for="user" class="label">Username</label> <input id="userName" name="userName"
								type="text" class="input">
						</div>
						<div class="group">
							<label for="pass" class="label">Password</label> <input id="password" name="password"
								type="password" class="input" data-type="password">
						</div>
						<div class="group">
							<label for="pass" class="label">Repeat Password</label> <input
								id="repeatpass" type="password" class="input"
								data-type="password">
						</div>
						<div class="group">
							<label for="pass" class="label">First Name</label> <input
								id="firstName" name="firstName" type="text" class="input">
						</div>
						<div class="group">
							<label for="pass" class="label">Last Name</label> <input
								id="lastname" name="lastname" type="text" class="input">
						</div>
						<div class="group">
							<label for="pass" class="label">Email Address</label> <input
								id="email" name="email" type="text" class="input">
						</div>
						<div class="group">
							<label for="pass" class="label">Phone</label> <input id="phone" name="phone"
								type="text" class="input">
						</div>
						<div class="group">
							<input type="submit" class="button" value="Sign Up">
						</div>
						<div class="hr"></div>
						<div class="foot-lnk">
							<label for="tab-1">Already Member?</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>