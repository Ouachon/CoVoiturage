<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<head>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
<title>Site de co-voiturage BL</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet" href="style.css" />

 <meta name="map" content="initial-scale=1.0, user-scalable=no" />
	 <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCtwziXLbi6nDuAq5KxSsFjg9jGByOe698&callback=initMap&sensor=false" type="text/javascript"></script>
	<script type="text/javascript" src="js/googleMapsv2.js" language ="javascript"></script>

</head>

<body onload="initMap();">

			<nav class="navbar navbar-default">
 				 <div class="container">
   					 <div class="navbar-header">
   					  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar"> </button>
					<h5><c:import url="/WEB-INF/menu/menu.jsp" /></h5>
				    </div>
			  </div>
			</nav>
<!-- First Container -->
<div class="container-fluid bg-1 text-center">
  <h3 class="margin">Bienvenue sur le site de covoiturage de l'entreprise HEP</h3>
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
			
			Nombre d'utilisateurs proche de chez moi: 
			${ sessionScope.listeUsersProche.size() }
		</div>
		
</div>		
		
<!-- Second Container -->
	<div class="container-fluid bg-2 text-center">
		<form method="post" action="UsersProcheServlet">
			<div class="form-group">
				<label for="adrDep">Départ: </label>
				<div class="input-group mb-2 mr-sm-2 mb-sm-0">
					<div class="input-group-addon glyphicon glyphicon-envelope"></div>
					<input class="form-control" type="text" id="adrDep" value="${formAccueil['adrDep']}" placeholder="adrDep" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<label for="adrArr">Arrivée: </label> <span
					class="glyphicon glyphicon-envelope"></span> <input
					class="form-control" type="text" id="adrArr"
					value="Capitole, Toulouse" style="width: 300px;">
			</div>
			<a><button type="button" class="btn btn-primary"
					onclick="rechercher('adrDep','adrArr')">
					<span class="glyphicon glyphicon-search"></span>Rechercher</button></a>
			<hr>
			<input type="hidden" name="latDep" id="latDep" value="">
			<input type="hidden" name="longDep" id="longDep" value="">
			
			
			PositionGPS départ : <span input type="text" id="text_latlng"></span>
			<input type="submit"  name="utProche" id="utProche" >
			</hr>
			Nombre d'utilisateurs proche de chez moi: 
			${ sessionScope.listeUsersProche.size() }
			
		</form>
	</div>

</body>
</html>
