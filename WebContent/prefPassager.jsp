<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Passager</title>
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
	<form class="form-horizontal no-margin" action="preferencePassager"
		method="post">


		<div class="control-group">
			<label class="control-label"> Conducteur </label>

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

		<div class="controls">
			<input type="submit" value=" Enregistrer"> <input
				type="reset" value="Reinistialiser"
				class="btn btn-danger pull-right" />
		</div>

		<div class="clearfix"></div>

	</form>

</body>
</html>