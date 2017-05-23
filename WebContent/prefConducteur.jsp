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
 <script src="jquery.js"></script>
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
        <br>
<!-- 		<div class="control-group">
			<label type="hidden" class="control-label"> Type véhicule </label>
			<div class="controls controls-row"><input class="span12" type="hidden" name="type_veh" placeholder="Ex: Peugeot 3008"></div>
		</div>  -->
       
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
			<label class="control-label"> Passagers </label></br>
			
			<label type="hidden" class="control-label" id="fumeur" name="fumeur">${profilCourant['fumeur']}</label>
			<form name="fumeur" >
			
				<input type="radio" name="preferences" value="F" id="F" checked="" /> Fumeur<br />
				<input type="radio" name="preferences" value="N" id="N" checked=""/> Non fumeur<br />
				<input type="radio" name="preferences" value="I" id="I" checked=""/> Indifférent<br />
			</form>
			<!--  	if $('value')=(${profilCourant['fumeur']}).attr('checked':'checked')	
			 jQuery("input[type=radio][name=preferences]:[value=${profilCourant['fumeur']}).attr("checked","checked");-->
			 
		<!--	 <script>
				function displayVals() {
				  var val = ${profilCourant['fumeur']};
				  var checked = "checked";
				  if (val=="F") {
					  $(':radio').id(['F']).attr('checked':'checked');
				  }
				
				
				</script>	-->
				
			</div>
		
        <br>
		<div class="control-group">
			<label class="control-label" for="type"> Type de passagers </label>
			<div id="sexe">${profilCourant['sexe']}
				<input type="radio" name="type_passager" id="H" value="H" /> Homme <br />
				<input type="radio" name="type_passager" id="F" value="F" /> Femme <br />
				<input type="radio" name="type_passager" id="I" value="I" checked="checked" />Indifférent<br />
			</div>
		</div>
		<br>
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
     
<!-- 		<div class="control-group">
			<label class="control-label">Km mini à parcourir </label>
			<div class="controls controls-row">
				<select class="span4" name="mini_kmr">
					<option value="0">Selectionner...</option>
					<option value="1">0 km</option>
					<option value="2">5 km</option>
					<option value="3">10 km</option>
				</select>
			</div>
		</div> -->
<!-- 		<div class="control-group">
			<label class="control-label"> Date Obtention Permis </label>
			<div class="controls controls-row">
				<input type="date" name="datePermis">
			</div>
		</div>		-->
		<div class="controls">
		<div class="text-right">
			<input class="btn-primary" type="submit"  style="font-size: 14px;" value="Enregistrer" /></div>
		</div></div>

		<!--  <input	type="reset" value="Reinitialiser"
				class="btn btn-danger pull-right" />	-->	
	
		


		<div class="clearfix"></div>
	</form>
	</div>

</body>
</html>