<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
<head>
<title>Update Property</title>
</head>
<body>
	<div style="text-align: center; margin-top: 20px;">
		<h2>Update Property</h2>
	</div>

	<html:form action="/updateProperty.do" method="post">
		<pre>	

		<c:set var="selectedProperty" value="${sessionScope.updateReqDetails}" />
		
	<div style="text-align: center;">
    <label>ID</label>
    <html:text property="id" value="${selectedProperty.id}"
					readonly="true" />
				<br>

    <label>NAME</label>
    <html:text property="name" value="${selectedProperty.name}" readonly="true"/>
				<br>

    <label>AREA</label>
    <html:text property="area" value="${selectedProperty.area}" />
				<br>

    <label>RENTAL PRICE</label>
    <html:text property="rentalPrice"
					value="${selectedProperty.rentalPrice}" />
				<br>

    <label>CURRENT VALUE</label>
    <html:text property="currentValue"
					value="${selectedProperty.currentValue}" />
				<br>

    <label>OCCUPIED</label>
    <html:text property="occupied" value="${selectedProperty.occupied}" />
				<br>

    <label>MANAGER REF ID</label>
    
    <c:choose>
        <c:when test="${not empty sessionScope.managersList}">
            <html:select property="managerRef">
            	<option value="">
                <c:forEach var="manager" items="${sessionScope.managersList}">
                    <option value="${manager.id}"
                    <c:if test="${selectedProperty.propertyManagerRef == manager.id}">selected</c:if> >
                    ${manager.name}
                    </option>
                </c:forEach>
            </html:select>
        </c:when>
        <c:otherwise>
            <p style="color: red;">Managers Not Available</p>
        </c:otherwise>
    </c:choose>
</div>

<div style="text-align: center; margin-top: 20px;">
		<html:submit value="Update Property" />
</div>
</pre>
	</html:form>