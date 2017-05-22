<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="styesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">
<link type="text/css" rel="stylesheet" href="style.css" />
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-default">
	 <div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar"></button>
				<h5>
					<c:import url="/WEB-INF/menu/menu.jsp" />
				</h5>
			</div>
			</div>
	</nav>

	<div class="container-fluid bg-1 text-center">
	<br>
	<c:import url="/WEB-INF/user/form.jsp" />
	<br />
	<c:if test="${ !errorStatus }">
		<c:import url="/WEB-INF/user/card.jsp" />
	</c:if>
</div>




</body>
</html>