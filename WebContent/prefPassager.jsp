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
<title>Préférences Passager</title>

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
	<div class="container-fluid bg-1 text-center" >
	
	<form class="form-horizontal no-margin" action="preferencePassager"
		method="post">


		<div class="control-group">
            <label class="control-label"> Passagers </label>
            <div id="specification">
                <input type="radio" name="preferences" value="F" /> Fumeur <br />
                <input type="radio" name="preferences" value="N" /> Non
                fumeur <br /> <input type="radio" name="preferences"
                    value="I" checked="checked" /> Pas de preferences
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
                <select class="span4" name="mini_kmr">
                    <option value="0">tout âge</option>
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