<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Action Failure</title>
</head>
<body>
<div style="text-align: center;margin-top: 20px;">
<p>Action Failed to Process </p>
<p>Current Action: <%= request.getAttribute("currentAction") %></p>
<p>       Message: <%= request.getAttribute("message") %></p>
</div>

<div style="text-align: center; margin-top: 10px;">
    <a href="success.jsp" 
       style="display: inline-block; padding: 10px 20px; background-color: #007bff; 
              color: white; text-decoration: none; border-radius: 5px; font-weight: bold;">
        Back To DashBoard
    </a>
</div>

</body>
</html>