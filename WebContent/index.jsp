<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<head>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
<title>Site de co-voiturage BL</title>


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
 <link type="text/css" rel="stylesheet" href="style.css" /> 
<!-- <link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.3.0/bootstrap.min.css" /> -->

 <meta name="map" content="initial-scale=1.0, user-scalable=no" />
<!--importation de l'API google MAP Version 3-->

	 <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCtwziXLbi6nDuAq5KxSsFjg9jGByOe698&callback=initMap&sensor=false" type="text/javascript"></script>

	<script type="text/javascript" src="js/googleMaps.js" language ="javascript"></script>

</head>
	<body onload="initMap();">
	<c:import url="/WEB-INF/menu/menu.jsp" />
	<h2>Bienvenue sur le site de covoiturage de l'entreprise HEP</h2>
		<div id="destinationForm">
			<form action="UsersProcheServlet" method="post" name="direction" id="direction">
			<table>
				<tr><td><b><label>D�part: </label></b></td>
				<td><input type="text" name = "adrDep" id="adrDep" value="${formAccueil['adrDep']}" style="width:300px;"></td>
				<td><b><label>Lat.: </label></b></td>
				<td name="latDep" id="latDep" value="${formAccueil['latDep']}" style="width:50px;">&nbsp;</td>
				<td><b><label>Long.: </label></b></td>
				<td name="longDep" id="longDep" value="${formAccueil['longDep']}" style="width:50px;">&nbsp;</td>
				
		  		<tr><td><b><label>Arriv�e: </label></b></td> 

				<td><input type="text" name="adrArr" id="adrArr" value="capitole toulouse" style="width:300px;"></td>
				<td><input type="submit" value="Recherche" onclick="rechercher('adrDep','adrArr')">
				</td></tr>
			</table>
			</form>
			
			Nombre d'utilisateurs proche de chez moi: 
			${ sessionScope.listeUsersProche.size() }
		</div>
		

<div id="panel"></div>
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
