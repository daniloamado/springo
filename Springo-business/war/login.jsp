<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<!DOCTYPE html>

<html>
<head>
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<meta charset="utf-8">
</head>
<body>
	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String url = userService.createLoginURL(request.getRequestURI());
		String urlLinktext = "Login";

		if (user != null) {
			url = userService.createLogoutURL(request.getRequestURI());
			urlLinktext = "Logout";
		}
	%>
	<div style="width: 100%;">
		<div class="line"></div>
		<div class="topLine">
			<div style="float: left;">
				
			</div>
			<div style="float: left;" class="headline">Todos</div>
			<div style="float: right;">
				<a href="<%=url%>"><%=urlLinktext%></a>
				<%=(user == null ? "" : user.getNickname())%></div>
		</div>
	</div>

	<hr />

	<div class="main">

		<div class="headline">New todo</div>

		<%
			if (user != null) {
		%>


		<%
			} else {
		%>

		Please login with your Google account

		<%
			}
		%>
	</div>
</body>
</html>