<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire d'inscription</title>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet" href="style.css" />

 <meta name="map" content="initial-scale=1.0, user-scalable=no" />
	 <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCtwziXLbi6nDuAq5KxSsFjg9jGByOe698&callback=initMap&sensor=false" type="text/javascript"></script>
	<script type="text/javascript" src="js/googleMapsv2.js" language ="javascript"></script>

</head>
<body onload="initMap();"> 
	<form class="form-group" method="post" onsubmit="return validForm(this)" action="DispatchServlet">
		
			<div class="container-fluid bg-3 text-left" style="width: 550px;">
				<div class="jumbotron text-center">
					<h3>Inscription</h3>
				
					<p>Formulaire d'inscription , les champs <span class="requis">(*)</span> sont requis:</p>
				</div>
			
				<div class="form-group" style="font-size: 14px;">
				
					<label for="email">Adresse email: <span class="requis">*</span></label>
					<input class="form-control" type="text" name="email" id="email" value="${form['email']}" placeholder="email" required="required" />
					<span class="error">${errors['email']}</span>
				</div>
				<div class="form-group" style="font-size: 14px;">										
					<label for="pwd1">Mot de passe <span class="requis">*</span></label>
					<input class="form-control" type="password" name="pwd1" id="pwd1" value="" placeholder="Mot de passe" required="required" />
					<span class="error">${errors['pwd1']}</span>
				</div>
				<div class="form-group" style="font-size: 14px;">		
					<label for="pwd1">Confirmation mot de passe <span class="requis">*</span></label>
					<input class="form-control" type="password" name="pwd2" id="pwd2" value="" placeholder="confirmation" required="required" />
					<span class="error">${errors['pwd2']}</span>
				</div>	
				<div class="form-group" style="font-size: 14px;">
					<label for="name">Nom</label>
					<input class="form-control" type="text" name="name" id="name" value="${form['name']}" />
					<span class="error">${errors['name']}</span>
				</div>
				<div class="form-group" style="font-size: 14px;">	
					<label for="age">Age</label>
					<input class="form-control" type="text" name="age" id="age" value="${form['age']}" />
				</div>
				<div class="form-group" style="font-size: 14px;">	
					<label for="sexe">Sexe</label>
					<select name="sexe"><option>Femme</option><option>Homme</option>
					<input type="text" class="form-control" name="sexe" id="sexe" value="${form['sexe']}" />
				</div>
				<div class="form-group" style="font-size: 14px;">	
					<label for="adresse">Adresse</label>
					<input onChange="rechercherEtTracer('adrDep','adrArr')" class="form-control" type="text" name="adrDep" id="adrDep" value="${form['adrDep']}" />
				</div>
					<input type="text"  class="form-control" name="adrArr" id="adrArr" value="${form['adrArr']}" />
				<div class="form-group" style="font-size: 14px;">
					
					<label for="fumeur">Fumeur</label>
					<select name="sexe"><option>Fumeur</option><option>Non Fumeur</option>
					<input type="hidden" class="form-control" name="fumeur" id="fumeur" value="${form['fumeur']}" />
				</div>
					<input class="form-control" type="text" name="travail" id="travail" value="capitole toulouse" />
					
					<input type="text" class="form-control"  name="coord_001" id="coord_001" value="" />
					<input type="text" class="form-control"  name="coord_002" id="coord_002" value="" />
					<input type="text" name="latLong" id="latLong" value="45,32" />
					<input type="text" name="latLongArr" id="latLongArr" value="45,32" />
					<input type="text" name="route" id="route" value="45,32:46,33:" />
					<div class="text-right">
					<input class="btn-primary" type="submit"  style="font-size: 14px;" value="Enregistrement" /></div>
				</div>
		
		</form>
		<p class="info">${actionMessage}</p>
</div>
	
				<div id="panel">
				<div id="map">
				 <style>	
					  #map {
					  height : 600px;       /* IMPERATIF pour affichage de la map!!!*/
					  width : 600px;
					  margin : auto;  
					  }
					  html, body {
					    height: 100%;
					    margin: 0;
					    padding: 0;
					  }
					</style>	
				<div id="divMap" style="float:left;width:70%; height:80%">
					<p>Veuillez patienter pendant le chargement de la carte...</p></div>
				<div id="divRoute" style="float:right;width:30%;height 80%"></div>		
	</div>
	<table name="coordUsersProche" id="coordUsersProche" style="width:100%">
	</table>

</body>
</html>