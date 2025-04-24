<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Action success</title>
</head>
<body>
<p>Success!</p>

<p>Current Action: <%= request.getAttribute("currentAction") %></p>
<p>Message: <%= request.getAttribute("message") %></p>

	<a href="success.jsp"
		style="padding: 10px; background-color: green; color: white; text-decoration: none; border-radius: 5px;">
		Go to Home Page </a>

</body>
</html>