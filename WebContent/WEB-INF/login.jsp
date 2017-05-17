<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title>Mon compte</title>
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
	<form method="post" action="login">
		<fieldset>
			<div class="container" style="width: 550px;">
				<div class="jumbotron text-center">
					<h3>Connexion à votre compte</h3>
					<p>Les champs(*) sont requis</p>
				</div>
			</div>
			<!--  Tous donc inutiles <p>Les champs (*) sont obligatoires</p>  -->

			<div class="container" style="width: 400px;">
				<div class="form-group">
					<label for="email">Adresse email </label> <span class="requis">*</span>
					<input type="text" class="form-control" name="email" id="email"
						value="${formLogin['email']}"><span class="erreur"><p
							class="text-danger">${errorLogin['email']}</p></span>
				</div>
				<div class="form-group">
					<label for="mdp">Mot de passe </label> <span class="requis">*</span>
					<input type="password" class="form-control" name="pwd" id="mdp"
						value="${form['pwd']}"><span class="erreur"><p
							class="text-danger">${errorLogin['pwd']}</p></span>
				</div>

				<div class="container">
					<p>Type Usager</p>
					<div class="radio">
						<label><input type="radio" name="typeCovoit"
							value="typeconducteur" checked="checked">Conducteur</label>
					</div>
					<div class="radio">
						<label><input type="radio" name="typeCovoit"
							value="typepassager">Passager</label>
					</div>
				</div>
				<div class="form-group">
					<INPUT type=submit value="Connexion"
						class="btn btn-primary btn-block">
				</div>
			</div>

			<!--  <p class="${statusOK ? 'succes' : 'erreur'}">${statusMessage}</p> -->
		</fieldset>
	</form>
</body>
</html>