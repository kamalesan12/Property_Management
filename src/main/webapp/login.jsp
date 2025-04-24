<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Property Manager Login</title>
<style>
		.error-message{
			color:red;
			font-weight:bold;
		}

</style>

</head>

<body>

<div style="border: 2px solid green; color: red; font-size: 25px; padding: 10px; text-align: center;">
    <bean:message key="login.title"/>
</div>

<html:form action="loginAction" method="post">
<div style="text-align: center;margin-top: 20px;">
<pre>
<bean:message key="login.username"/> : <html:text property="name"/> <br>
<bean:message key="login.password"/> : <html:password property="password"/> <br>

<html:submit property="submit"><bean:message key="login.button"/></html:submit><br>
<br>
<br>
<p>Don't have an account?</p>
</div>
<div style="text-align: center; margin-top: 10px;">
    <a href="signup.jsp" 
       style="display: inline-block; padding: 10px 20px; background-color: #007bff; 
              color: white; text-decoration: none; border-radius: 5px; font-weight: bold;">
        Sign Up
    </a>
</div>
</pre>
</html:form>


</body>
</html>