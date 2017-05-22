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
	<div class="panel panel-default"> 
	    <div class="panel-heading">
	    <b> Inscription </b>
	    <br/>
		Formulaire d'inscription , les champs <span class="requis">(*)</span> sont requis:
		</div>
		
		<form class="form-group" onsubmit="return validForm(this)" method="POST" action="DispatchServlet" >
					<label for="email">Adresse email: <span class="requis">*</span></label>
					<input class="form-control" type="text" name="email" id="email" value="${form['email']}" placeholder="email" required="required" />
					<span class="error">${errors['email']}</span>
					</br>
					
					
					
					<label for="pwd1">Mot de passe <span class="requis">*</span></label>
					<input class="form-control" type="password" name="pwd1" id="pwd1" value="" placeholder="Mot de passe" required="required" />
					<span class="error">${errors['pwd1']}</span>
					</br>
					
					
					<label for="pwd1">Confirmation mot de passe <span class="requis">*</span></label>
					<input class="form-control" type="password" name="pwd2" id="pwd2" value="" placeholder="confirmation" required="required" />
					<span class="error">${errors['pwd2']}</span>
					</br>
					
					<label for="name">Nom</label>
					<input class="form-control" type="text" name="name" id="name" value="${form['name']}" />
					<span class="error">${errors['name']}</span>
					</br>
					
					<label for="age">Age</label>
					<input class="form-control" type="text" name="age" id="age" value="${form['age']}" />
					</br>
					
					<label for="sexe">Sexe</label>
					<input class="form-control" type="text" name="sexe" id="sexe" value="${form['sexe']}" />
					</br>
					
					<label for="adresse">Adresse</label>
					<input onChange="rechercherEtTracer('adrDep','adrArr')" class="form-control" type="text" name="adrDep" id="adrDep" value="${form['adrDep']}" />
					</br>
					<input  class="form-control" type="text" name="adrArr" id="adrArr" value="${form['adrArr']}" />
					
					
					<label for="fumeur">Fumeur</label>
					<input class="form-control" type="text" name="fumeur" id="fumeur" value="${form['fumeur']}" />
					</br>
					
					
					<input class="form-control" type="text" name="coord_001" id="coord_001" value="" />
					<input class="form-control" type="text" name="coord_002" id="coord_002" value="" />
					<input type="text" name="latLong" id="latLong" value="45,32" />
					<input type="text" name="latLongArr" id="latLongArr" value="45,32" />
					<input type="text" name="route" id="route" value="45,32:46,33:" />
					
					<input class="btn-danger" type="submit" value="Enregistrement" />
						
		
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