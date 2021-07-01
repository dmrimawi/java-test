<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>School Home</title>
</head>
<body>
	<div style="display: flex;">
		<div style="flex: 1;">
			<h1>New Student</h1>
			<form:form action="/new/student" method="post"
				modelAttribute="student">
				<p>
					<form:input path="name" placeholder="Name" />
					<form:errors path="name" />
				</p>
				<p>
					<form:input type="number" path="age" placeholder="Age" />
					<form:errors path="age" />
				</p>
				<p>
					<form:input type="text" path="email" placeholder="E-Mail" />
					<form:errors path="email" />
				</p>
				<p>
					<form:input type="password" path="password" placeholder="Password" />
					<form:errors path="password" />
				</p>
				<p>
					<form:input type="password" path="passwordConfirmation" placeholder="Password Conf." />
					<form:errors path="passwordConfirmation" />
				</p>
				<input type="submit" value="Create Student" />
			</form:form>
		</div>
		<div style="flex: 1;">
			<h1>New Class</h1>
			<form:form action="/new/class" method="post" modelAttribute="cls">
				<p>
					<form:input path="name" placeholder="Class Name" />
					<form:errors path="name" />
				</p>
				<p>
					<form:input path="room" placeholder="Class Room" />
					<form:errors path="room" />

				</p>
				<input type="submit" value="Create Class" />
			</form:form>
		</div>
	</div>

</body>
</html>