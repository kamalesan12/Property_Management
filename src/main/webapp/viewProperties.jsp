<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
           
<!DOCTYPE html>


<html>
<head>
    <title>View Properties</title>
</head>
<body>
    <h2>View Properties</h2>

    <h3>1. View Property with the Lowest Current Value</h3>
    <logic:present name="lowestProperty">
        <p><strong>Property Name:</strong> <bean:write name="lowestProperty" property="name"/></p>
        <p><strong>Area:</strong> <bean:write name="lowestProperty" property="area"/></p>
        <p><strong>Current Value:</strong> <bean:write name="lowestProperty" property="currentValue"/></p>
    </logic:present>

    <h3>2. View Occupied Properties</h3>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Property Name</th>
            <th>Area</th>
            <th>Rental Price</th>
            <th>Current Value</th>
        </tr>
        <logic:iterate id="property" name="occupiedProperties">
            <tr>
                <td><bean:write name="property" property="id"/></td>
                <td><bean:write name="property" property="name"/></td>
                <td><bean:write name="property" property="area"/></td>
                <td><bean:write name="property" property="rentalPrice"/></td>
                <td><bean:write name="property" property="currentValue"/></td>
            </tr>
        </logic:iterate>
    </table>

    <h3>3. View Properties by Area</h3>
    <html:form action="viewPropertiesByAreaAction" method="post">
        <p>Select Area:
            <html:text property="area"/>
            <html:submit value="Search"/>
        </p>
    </html:form>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Property Name</th>
            <th>Rental Price</th>
            <th>Current Value</th>
            <th>Occupied</th>
        </tr>
        <logic:iterate id="property" name="areaProperties">
            <tr>
                <td><bean:write name="property" property="id"/></td>
                <td><bean:write name="property" property="name"/></td>
                <td><bean:write name="property" property="rentalPrice"/></td>
                <td><bean:write name="property" property="currentValue"/></td>
                <td><bean:write name="property" property="occupied"/></td>
            </tr>
        </logic:iterate>
    </table>

    <br/>
    <a href="propertyList.jsp">Back to Property List</a>
</body>
</html>