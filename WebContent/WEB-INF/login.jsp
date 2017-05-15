<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mon espace</title>
<link type="text/css" rel="stylesheet" href="styles.css" />
</head>
<body>

	<ul id="menu">
            <li> <a href="<c:url value="index.jsp"/>">Accueil</a>   
    </ul>
	<br>
	<form method="post" action="login">
		<fieldset>
			<legend>Connexion</legend>
			<p>Les champs (*) sont obligatoires</p>

			<label for="nom">Adresse email <span class="requis">*</span></label>
			<input type="email" name="email" value="${form.email}" size="20"
				maxlength="60" /> <span class="erreur">${error['email']}</span> <br />

			<label for="motdepasse">Mot de passe <span class="requis">*</span></label>
			<input type="password" name="pwd" value="" size="20" maxlength="20" />
			<span class="erreur">${error['motdepasse']}</span> <br /> <br>

			<div class="control-group">
				En tant que :
				<div id="specification">
					<input type="radio" name="typeCovoit" value="typeconducteur"
						checked="checked" /> Conducteur <br /> <input type="radio"
						name="typeCovoit" value="typepassager" /> Passager <br />
				</div>
			</div>
			<br> <input type="submit" value="Connexion" class="sansLabel" />
			<br />

			<p class="${statusOK ? 'succes' : 'erreur'}">${statusMessage}</p>
		</fieldset>
	</form>
</body>
</html>