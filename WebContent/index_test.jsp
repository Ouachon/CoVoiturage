<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<head>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
<title>Site de co-voiturage BL</title>


<!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
 <link type="text/css" rel="stylesheet" href="style.css" /> 
 <link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.3.0/bootstrap.min.css" /> -->

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet" href="style.css" />


 <meta name="map" content="initial-scale=1.0, user-scalable=no" />
<!--importation de l'API google MAP Version 3-->

	 <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCtwziXLbi6nDuAq5KxSsFjg9jGByOe698&callback=initMap&sensor=false" type="text/javascript"></script>

	<script type="text/javascript" src="js/googleMapsv2.js" language ="javascript"></script>

</head>

<body onload="initMap();">
	<div class="container">
		<div class="jumbotron">
			<h3>Bienvenue sur le site de covoiturage de l'entreprise HEP</h3>
		</div>
			
				<div class="col-sm-3 sidenav" >
					<h4><c:import url="/WEB-INF/menu/menu.jsp" /></h4>	
				</div>

				 <div class="form-group">
					<form action="" method="post" action="servletCoord">		
						<label>Départ: </label>
							<span class="glyphicon glyphicon-envelope"></span>
							<input type="text" id="adrDep" value="" style="width:300px;">
		  				<label>Arrivée: </label>
							<span class="glyphicon glyphicon-envelope"></span>
							<input type="text" id="adrArr" value = "Capitole, Toulouse, France"  style="width:300px;">
						<input type="button" class="btn btn-primary" value="Recherche" onclick="rechercher('adrDep','adrArr')">
						<hr>PositionGPS départ : <span id="text_latlng"></span></hr>
					</form>
				</div>
			</div>
		
			<div class="col-sm-9"id="panel">
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
		</div>
	</div>
</body>
</html>
