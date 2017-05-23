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

<title>Préférences Conducteur </title>

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
<div class="container-fluid bg-3 text-left" style="width: 500px;">
	<div class="jumbotron text-center">
		<h3>Mes préférences</h3>
	</div>

	<form class="form-group"  style="font-size: 14px;" action="preferenceConducteur" method="post">
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

                <input type="radio" name="preferences" value="F" checked="${profilCourant['fumeurOui']}" /> Fumeur <br /> 
                <input type="radio" name="preferences" value="N" checked="${profilCourant['fumeurNon']}"/> Non fumeur <br /> 
                <input type="radio" name="preferences" value="I" checked="${profilCourant['fumeurInd']}"/> Pas de preferences
            </div>
		</div>
		<br>
		<div class="control-group">
			<label class="control-label" for="type"> Type de passagers </label>
			<div id="sexe">
				<input type="radio" name="type_passager" value="H" checked="${profilCourant['homme']}"/> Homme <br />
				<input type="radio" name="type_passager" value="F" checked="${profilCourant['femme']}"/> Femme <br />
				<input type="radio" name="type_passager" value="I" checked="${profilCourant['indifferent']}" />
				Homme/Femme <br />
			</div>
		</div>
		<br>
		<div class="control-group">
        <div class="control-group">
            <label class="control-label">Age </label>${profilCourant['age']}
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
			<input type="submit" value="Enregistrer" name="save">

		</div>

		<div class="clearfix"></div>
	</form>
	</div>
    </div>	

</body>
</html>