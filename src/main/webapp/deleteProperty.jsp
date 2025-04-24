<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
       

<!DOCTYPE html>
<html>
<head>
    <title>Delete Property</title>
</head>
<body>
    <h2>Available Properties</h2>

    <!-- Check if property list exists in session -->
    <%
        java.util.List<com.arbor.model.property> propertyList = 
            (java.util.List<com.arbor.model.property>) session.getAttribute("propertyList");

        if (propertyList == null || propertyList.isEmpty()) {
    %>
        <p>No properties available.</p>
    <%
        } else {
    %>

    <!-- Form to delete selected properties -->
    <html:form action="deletePropertyAction" method="post">
        <table border="1">
            <tr>
                <th>Select</th>
                <th>Property Name</th>
                <th>Area</th>
                <th>Rental Price</th>
            </tr>

            <%
                for (com.arbor.model.property property : propertyList) {
            %>
                <tr>
                    <td><input type="checkbox" name="selectedPropertyId" value="<%= property.getId() %>"></td>
                    <td><%= property.getName() %></td>
                    <td><%= property.getArea() %></td>
                    <td><%= property.getRentalPrice() %></td>
                </tr>
            <%
                }
            %>
        </table>

        <br>
        <html:submit value="Delete Selected"/>
    </html:form>

    <%
        }
    %>

    <br>
    <a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>