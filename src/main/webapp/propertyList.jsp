<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
        
    
<!DOCTYPE html>

<html>
<head>
    <title>Property List</title>
</head>
<body>
    <h2>All Properties</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Property Name</th>
            <th>Area</th>
            <th>Rental Price</th>
            <th>Current Value</th>
            <th>Occupied</th>
        </tr>
        <bean:define id="propertyList" name="propertyList"/>
        <logic:iterate id="property" name="propertyList">
            <tr>
                <td><bean:write name="property" property="id"/></td>
                <td><bean:write name="property" property="name"/></td>
                <td><bean:write name="property" property="area"/></td>
                <td><bean:write name="property" property="rentalPrice"/></td>
                <td><bean:write name="property" property="currentValue"/></td>
                <td><bean:write name="property" property="occupied"/></td>
            </tr>
        </logic:iterate>
    </table>
</body>
</html>