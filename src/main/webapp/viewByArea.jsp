<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>

<html>
<head>
    <title>Properties in ${requestScope.searchArea}</title>
</head>
<body>
    <h2>Properties in <c:out value="${requestScope.searchArea}"/></h2>
    
    <c:choose>
        <c:when test="${empty sessionScope.Currentproperties}">
            <p>No properties found in this area.</p>
        </c:when>
        <c:otherwise>
            <table border="1">
                <tr>
                    <th>Property Name</th>
                    <th>Area</th>
                    <th>Rental Price</th>
                    <th>Current Value</th>
                    <th>Occupied</th>
                </tr>
                <c:forEach var="property" items="${sessionScope.Currentproperties}">
                    <tr>
                        <td>${property.name}</td>
                        <td>${property.area}</td>
                        <td>${property.rentalPrice}</td>
                        <td>${property.currentValue}</td>
                        <td>
                            <c:choose>
                                <c:when test="${property.occupied}">Yes</c:when>
                                <c:otherwise>No</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

    <br>
    <a href="success.jsp">Back to Dashboard</a>
</body>
</html>