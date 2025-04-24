<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
    
<!DOCTYPE html>

<html>
<head>
    <title>Occupied Properties</title>
</head>
<body>
    <h2>Currently Occupied Properties</h2>
    
    <c:choose>
        <c:when test="${empty sessionScope.Currentproperties}">
            <p>No occupied properties found.</p>
        </c:when>
        <c:otherwise>
            <table border="1">
                <tr>
                    <th>Property Name</th>
                    <th>Area</th>
                    <th>Rental Price</th>
                    <th>Current Value</th>
                </tr>
                <c:forEach var="property" items="${sessionScope.Currentproperties}">
                    <tr>
                        <td>${property.name}</td>
                        <td>${property.area}</td>
                        <td>${property.rentalPrice}</td>
                        <td>${property.currentValue}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

    <br>
    <a href="success.jsp">Back to Dashboard</a>
</body>
</html>