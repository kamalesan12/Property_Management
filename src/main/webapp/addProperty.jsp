<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
        
<!DOCTYPE html>

<html>
<head>
    <title>Add Property</title>
</head>
<body>
    <h2>Add New Property</h2>
    <html:form action="/PropertyProcessing.do?action=addProperty">
        <table>
            <tr><td>ID:</td><td><html:text property="id"/></td></tr>
            <tr><td>Manager Ref:</td><td><html:text property="managerRef"/></td></tr>
            <tr><td>Property Name:</td><td><html:text property="name"/></td></tr>
            <tr><td>Area:</td><td><html:text property="area"/></td></tr>
            <tr><td>Rental Price:</td><td><html:text property="rentalPrice"/></td></tr>
            <tr><td>Current Value:</td><td><html:text property="currentValue"/></td></tr>
            <tr><td>Occupied (Y/N):</td><td><html:text property="occupied"/></td></tr>
            <tr><td colspan="2"><html:submit value="Add Property"/></td></tr>
        </table>
        
        <br>
        <!-- Back to Dashboard Link -->
        <a href="success.jsp">Back to Dashboard</a>  
    </html:form>
    
   
</body>
</html>