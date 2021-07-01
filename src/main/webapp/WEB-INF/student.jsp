<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Page</title>
</head>
<body>
	<h1>
		Welcome
		<c:out value="${std.name}" />
	</h1>
	<form action="/enroll/${std.id}" method="post">
		Classes: <select name="cls_type">
			<c:forEach items="${otherclasses}" var="cls">
				<option value="${cls.id}">${cls.name}</option>
			</c:forEach>
		</select> <input type="submit" value="Enroll">
	</form>
	<table>
		<tr>
			<th>Class Name</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${myclasses}" var="cls">
			<tr>
				<td>${cls.name}</td>
				<td><a href="/drop/${std.id}/${cls.id}">Drop</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>