<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<title><bean:message key="dashboard.title" /></title>

<script>
	function showAddPropertyForm() {
		document.getElementById("addPropertySection").style.display = "block";
	}

	function showAreaFilter() {
		document.getElementById("areaFilterSection").style.display = "block";
	}
</script>
</head>
<body>

	<div style="text-align: center; margin-top: 20px;">
		<h3>Welcome, ${sessionScope.loggedInUser}!</h3>
		<p>Here's The List Of All Your Properties.</p>

		<!-- Property List Form -->
		<html:form action="/PropertyProcessing.do" method="post">

			<h4>Your Properties:-</h4>

			<div
				style="display: flex; justify-content: center; margin-top: 20px;">
				<table border="1" style="border-collapse: collapse; text-align:"center;">
					<tr>
						<th>Select</th>
						<th>Id</th>
						<th>Property Name</th>
						<th>Area</th>
						<th>Rental Price</th>
						<th>Current Value</th>
						<th>Occupied</th>
					</tr>
					<c:forEach var="property" items="${sessionScope.Currentproperties}">
						<tr>
							<td><html:radio property="selectedProperty"
									value="${property.id}" /></td>
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
			<br />
					<!-- End of Showcasing available properties  -->	
	</div>

	<div
		style="display: flex; justify-content: center; gap: 40px; margin-top: 10px;">
		<!-- Left-Aligned Delete Button -->
		<div>
			<html:submit property="action" value="deleteProperty" style="padding: 10px 20px;">
				<bean:message key="deleteProperty" />
			</html:submit>
		</div>
		
		<div style="text-align: center;margin-top: 20px;">
		<c:set var="message_del" value="${requestScope.deletesuccess}"/>
		<c:if test="${not empty message_del }">
		<p> ${message_del} </p>
		</c:if>
		</div>

		<!-- Right-Aligned Update Button -->
		<div>
			<html:submit property="action" value="updateProperty" style="padding: 10px 20px;">
				<bean:message key="updateProperty" />
			</html:submit>
		</div>
		
		<div style="text-align: center;margin-top: 20px;">
		<c:set var="message_upd" value="${requestScope.updatesuccess}"/>
		<c:if test="${not empty message_upd }">
		<p> ${message_upd} </p>
		</c:if>
		</div>
		
		
	</div>

	<br />

	<!-- View By Area Button -->
	<button type="button" onclick="showAreaFilter()"
		style="padding: 10px 20px; background-color: #ff9800; color: white; border: none; border-radius: 5px; font-size: 16px; cursor: pointer;">
		View By Area</button>

	<!-- Hidden Area Input -->
	<div id="areaFilterSection" style="display: none; margin-top: 10px;">
		<label for="area">Enter Area:</label>
		<html:text property="area" />
		<html:submit property="action" value="viewByArea">
			<bean:message key="viewByArea" />
		</html:submit>
	</div>

	<!-- View Occupied Properties Button -->
	<html:submit property="action" value="occupiedProperties"
		style="padding: 10px 20px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; font-size: 16px; cursor: pointer; margin-left: 10px;">
            View Occupied Properties
        </html:submit>

	</html:form>

	<br />
	<br />

	<!-- Add Property Section -->
	<html:form action="/PropertyProcessing.do?action=addProperty"
		method="post">
		<!-- Add Property Button -->
		<button type="button" onclick="showAddPropertyForm()"
			style="padding: 10px 20px; background-color: #007bff; color: white; border: none; border-radius: 5px; font-size: 16px; cursor: pointer;">
			Add Property</button>

		<!-- Hidden Add Property Form -->
		<div id="addPropertySection"
			style="display: none; margin-top: 20px; border: 1px solid #ccc; padding: 15px;">
			<h2>Add New Property</h2>
			<table>
				<tr>
					<td>ID:</td>
					<td><html:text property="id" /></td>
				</tr>
				<tr>
					<td>Manager Ref:</td>
					<td><html:text property="managerRef" /></td>
				</tr>
				<tr>
					<td>Property Name:</td>
					<td><html:text property="name" /></td>
				</tr>
				<tr>
					<td>Area:</td>
					<td><html:text property="area" /></td>
				</tr>
				<tr>
					<td>Rental Price:</td>
					<td><html:text property="rentalPrice" /></td>
				</tr>
				<tr>
					<td>Current Value:</td>
					<td><html:text property="currentValue" /></td>
				</tr>
				<tr>
					<td>Occupied (Y/N):</td>
					<td><html:text property="occupied" /></td>
				</tr>
				<tr>
					<td colspan="2"><html:submit value="Add Property" /></td>
				</tr>
			</table>
		</div>
		
		<div style="text-align: center;margin-top: 20px;">
		<c:set var="message_add" value="${requestScope.addpropsuccess}"/>
		<c:if test="${not empty addpropsuccess }">
		<p> ${addpropsuccess} </p>
		</c:if>
		</div>
	</html:form>

	<br>
	<a href="login.jsp">Back to Dashboard</a>

</body>
</html>