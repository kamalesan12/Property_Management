<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignUp Failure</title>
</head>
<body>

<c:set var="message" value="${sessionScope.error}"/>
<c:if test="${not empty message}">
<p>${message}</p>
</c:if>
<br>
<div style="text-align: center; margin-top: 10px;">
    <a href="login.jsp" 
       style="display: inline-block; padding: 10px 20px; background-color: #007bff; 
              color: white; text-decoration: none; border-radius: 5px; font-weight: bold;">
        Back To Login
    </a>
</div>
</body>
</html>