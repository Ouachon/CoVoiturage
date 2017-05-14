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
	<c:import url="/WEB-INF/menu/menu.jsp" />
	<br>
	<form class="form-horizontal no-margin" action="preferenceConducteur"
		method="post">

		<div class="control-group">
			<label class="control-label"> Date de départ </label>
			<div class="controls controls-row">
				<input type="date" name="dateD">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> Heure de départ </label>
			<div class="controls controls-row">
				<input class="span12" type="time" name="heureD">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> Date de retour </label>
			<div class="controls controls-row">
				<input type="date" name="dateD">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> Heure de retour </label>
			<div class="controls controls-row">
				<input class="span12" type="time" name="heureR">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> Type véhicule </label>
			<div class="controls controls-row">
				<input class="span12" type="text" name="type_veh"
					placeholder="Ex: Peugeot 405">
			</div>
		</div>


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

		<div class="control-group">
			<label class="control-label"> Passagers </label>

			<div id="specification">
				<input type="radio" name="preferences" value="fumeur" /> Fumeur <br />

				<input type="radio" name="preferences" value="non fumeur" /> Non
				fumeur <br /> <input type="radio" name="preferences"
					value="pas de preferences" /> Pas de preferences
			</div>

		</div>

		<div class="control-group">
			<label class="control-label" for="type"> Type de passagers </label>
			<div id="sexe">
				<input type="radio" name="type_passager" value="homme" /> Homme <br />

				<input type="radio" name="type_passager" value="femme" /> Femme <br />


				<input type="radio" name="type_passager" value="homme/femme" />
				Homme/Femme <br />

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">Km mini à parcourir </label>
			<div class="controls controls-row">
				<select class="span4" name="mini_kmr">
					<option value="0">Selectionner...</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
				</select>

			</div>
		</div>

		<div class="controls">
			<input type="submit" value=" Enregistrer"> <input
				type="reset" value="Reinistialiser"
				class="btn btn-danger pull-right" />
		</div>

		<div class="clearfix"></div>

	</form>
	<div class="control-group">
		<label class="control-label"> Date Obtention Permis </label>
		<div class="controls controls-row">
			<input type="date" name="datePermis">
		</div>
	</div>

</body>
</html>