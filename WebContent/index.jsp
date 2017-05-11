<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<head>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
<title>Site de co-voiturage BL</title>
<c:import url="/WEB-INF/menu/menu.jsp" />
<link type="text/css" rel="stylesheet" href="style.css" />

 <meta name="map" content="initial-scale=1.0, user-scalable=no" />
<!--importation de l'API google MAP Version 3-->
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
	 <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCtwziXLbi6nDuAq5KxSsFjg9jGByOe698&callback=initMap" type="text/javascript"></script>

	<script type="text/javascript" src="js/googleMaps.js" language ="javascript"></script>

</head>
	<body onload="initMap();">
	
	<h2>Bienvenue sur le site de covoiturage de l'entreprise HEP</h2>
		<div>
			<table>
				<tr><td><b>Départ: </b></td>
				<td><input type="text" id="adrDep" value="" style="width:300px;"></td>
				<tr><td><b>Arrivée: </b></td>
				<td><input type="text" id="adrArr" value=""style="width:300px;"></td>
				<td><input type="button" value="Recherche" onclick="rechercher('adrDep','adrArr')">
				</td></tr>
			</table>
		</div>
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
		<center>
		</center>
	</body>
</html>
