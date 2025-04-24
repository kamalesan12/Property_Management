<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<title>Available Properties</title>
</head>
<body>

<div style="text-align: center;margin-top: 20px;">
	<h1>Welcome User</h1>
</div>

<div style="text-align: center;margin-top: 20px;">
	<h2>List of All Available Properties</h2>
</div>

<div style="display: flex; justify-content: center; margin-top: 20px;">

	<table border="2" style=border-collapse:collapse;text-align: center;">
		<tr>
			<th>ID</th>
			<th>Property Name</th>
			<th>Area</th>
			<th>Rental Price</th>
			<th>Current Value</th>
			<th>Occupied</th>
		</tr>

		<c:forEach var="property" items="${sessionScope.AvailableProperties}">
			<tr>
				<td>${property.id}</td>
				<td>${property.name}</td>
				<td>${property.area}</td>
				<td>${property.rentalPrice}</td>
				<td>${property.currentValue}</td>
				<td><c:choose>
						<c:when test="${property.occupied}">Yes</c:when>
						<c:otherwise>No</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
</div>

	<br>
<div style="text-align: center; margin-top: 20px;">
	<!-- Login Link -->
	<a href="login.jsp">Login to See Individual Data</a>
</div>

</body>
</html>