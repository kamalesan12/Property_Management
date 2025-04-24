<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
       
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
</head>
<body>
<div style="text-align: center;margin-top: 20px;">
<h2>SIGN UP FORM </h2>

<html:form action="signupAction" method="post">
    <pre>
    Username: <html:text property="name" /><br>
    Password: <html:password property="password" />
    
    <html:submit property="submit">Sign Up</html:submit>
    </pre>
    
    <c:set var="message" value="${sessionScope.error}"/>
    <c:if test="${not empty message}">
    <div style="text-align: center;margin-top: 20px;">
    <p>${message}</p>
    </div>
    </c:if>

<!-- Link to login page -->
<p>Already have an account?</p>
</div>  

<div style="text-align: center; margin-top: 10px;">
    <a href="login.jsp" 
       style="display: inline-block; padding: 10px 20px; background-color: #007bff; 
              color: white; text-decoration: none; border-radius: 5px; font-weight: bold;">
        Login here
    </a>
</div>
</html:form>


</body>
</html>