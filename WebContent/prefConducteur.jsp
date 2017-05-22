<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conducteur</title>
<link type="text/css" rel="stylesheet" href="styles.css" />
<style type="text/css">
body {
	background-color: #A7A1B1;
}

.widget-header .title {
	text-align: center;
}
</style>
</head>
<body>

	Debug:
	<br> ${profilCourant['fumeur']} ${profilCourant['age']}
	${profilCourant['sexe']} 
	<br>
	Bienvenue ${formLogin['nomUser']}
	<ul id="menu">
		<li><a href="<c:url value="index.jsp"/>">Accueil</a>
	</ul>
	<br>
	<form class="form-horizontal no-margin" action="EnregistrerPreferenceConducteur"
		method="post">
		  <input type="hidden" name="userCourant" id="userCourant" value=${formLogin['nomUser']}  style="width: 300px;"> </br>
		<br>
	
		<div class="control-group">
			<label class="control-label"> Nombre de passagers </label>
			<div class="controls controls-row">
				<select class="span4" name="nbr_passager">
					<option value="0">Selectionner...</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
				</select>

			</div>
		</div>
		<br>
		<div class="control-group">
			<label class="control-label"> Passagers </label>
			<div id="specification">
        
			 <input type="radio" name="preferences"  value="F" /> Fumeur <br />
				<input type="radio" name="preferences" value="N" /> Non fumeur <br />
				<input type="radio" name="preferences" value="I" checked="checked" />
				Pas de preferences
			</div>
		</div>
		<br>
		<div class="control-group">
			<label class="control-label" for="type"> Type de passagers </label>
			<div id="sexe">
				<input type="radio" name="type_passager" value="H" /> Homme <br />
				<input type="radio" name="type_passager" value="F" /> Femme <br />
				<input type="radio" name="type_passager" value="I" checked="checked" />
				Homme/Femme <br />
			</div>
		</div>
		<br>
		<div class="control-group">
			<label class="control-label">Age </label>
			<div class="controls controls-row">
				<select class="span4" name="age">
					<option value="0">tout �ge</option>
					<option value="1">moins de 30 ans</option>
					<option value="2">moins de 50 ans</option>
					<option value="3">plus de 50 ans</option>
				</select>
			</div>
		</div>
		<br>
			
		<div class="controls">
			<input type="submit" value="Enregistrer" name="save">

		</div>

		<div class="clearfix"></div>
	</form>
</body>
</html>