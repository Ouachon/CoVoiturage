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
    <ul id="menu">
            <li> <a href="<c:url value="index.jsp"/>">Accueil</a>   
    </ul>
    <br>
	<form class="form-horizontal no-margin" action="preferencePassager"
		method="post">


		<div class="control-group">
			<label class="control-label"> Conducteur </label>

			<div id="specification">
				<input type="radio" name="preferences" value="fumeur" /> Fumeur <br />
				<input type="radio" name="preferences" value="non fumeur" /> Non
				fumeur <br /> <input type="radio" name="preferences"
					value="pas de preferences" checked="checked"/> Pas de preferences
			</div>

		</div>
        <br>
		<div class="control-group">
			<label class="control-label" for="type"> Type de passagers </label>
			<div id="sexe">
				<input type="radio" name="type_passager" value="homme" /> Homme <br />
				<input type="radio" name="type_passager" value="femme" /> Femme <br />
				<input type="radio" name="type_passager" value="homme/femme" checked="checked"/>
				Homme/Femme <br />
			</div>
		</div>
        <br>
        <div class="control-group">
            <label class="control-label">Age </label>
            <div class="controls controls-row">
                <select class="span4" name="mini_kmr">
                    <option value="0">Selectionner...</option>
                    <option value="1">moins de 30 ans</option>
                    <option value="2">moins de 50 ans</option>
                    <option value="3">plus de 50 ans</option>
                </select>
            </div>
        </div>
        <br>
		<div class="controls">
			<input type="submit" value=" Enregistrer"> <input
				type="reset" value="Reinistialiser"
				class="btn btn-danger pull-right" />
		</div>

		<div class="clearfix"></div>

	</form>

</body>
</html>