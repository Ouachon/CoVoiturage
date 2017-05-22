<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet" href="style.css" />

<title>Conducteur</title>
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

<div class="container-fluid bg-1 text-left" >
	Bienvenue ${formLogin['nomUser']}
</div>	

	<br><br>
	ON AFFICHE ICI LA LISTE DES PASSAGERS 
	AYANT essay� de me contacter et le status pour chacun (accept� refus�)
	<div class="container-fluid bg-3 text-left" style="width: 550px;">
				<div class="jumbotron text-center">
	<form method="post" action="ConducteurCovoit">
		<input type="text" name="userCourant" id="userCourant" value="${formLogin['email']}"  style="width: 300px;">
		<fieldset>
			 <a
				href="<c:url value="prefConducteur.jsp"/>">preference Conducteur</a>
		</fieldset>
		
		<input type="submit" value="Pr�f�rence Conducteur" class="sansLabel" />
	</form>
	</div>
	</div>
</body>
</html>