var directionsService = new google.maps.DirectionsService(); // service GoogleMaps
var map,geocoder, marker, marker2; // La carte, le service de géocodage et les marqueurs
var depart,arrivee,ptCheck; // point de départ, arrivé et de vérification

/*initialise google MAP V3*/
function initMap(){
 /*gestion des routes*/
 directionsDisplay = new google.maps.DirectionsRenderer();
 /*emplacement par défaut de la carte (Toulouse)*/
 var maison = new google.maps.LatLng(43.6042600, 1.4436700);
 /*option par défaut de la carte*/
 var myOptions = {
 zoom:11,
 zoomControl : true,
 mapTypeId: google.maps.MapTypeId.ROADMAP,
 center: maison
 }
 /*creation de la map*/
 map = new google.maps.Map(document.getElementById("divMap"), myOptions);
 /*connexion de la map + le panneau de l'itinéraire*/
 directionsDisplay.setMap(map);
 directionsDisplay.setPanel(document.getElementById("divRoute"));
 /*intialise le geocoder pour localiser les adresses */
 geocoder = new google.maps.Geocoder();
 
// map.addMarker({	//395 rue du colombier : 43.534352, 1.517772
//     lat: 43.534352,
//     lng: 1.517772,
//     title: 'Home',
//   });
// map.addMarker({	 //BL 43.534352, 1.517772
//     lat: 43.534352,
//     lng: 1.517772,
//     title: 'Office',
//   });
//map.addMarker({	// rue des accacias, St-Orens 43.550319, 1.531440 
//lat: 43.550319,
//lng: 1.531440,
//title: 'User1',
//}); 
 }

function trouveRoute() {
	 /*test si les variables sont bien initialisés*/
	 if (depart && arrivee){
	 var request = {
	 origin:depart,
	 destination:arrivee,
	 travelMode: google.maps.DirectionsTravelMode["DRIVING"]
	};
	 /*appel à l'API pour tracer l'itinéraire*/
	directionsService.route(request, function(response, status) {
	if (status == google.maps.DirectionsStatus.OK) {
	 directionsDisplay.setDirections(response);
	 }
	 });
	 }
	 }

function rechercher(src,src2){
	 // ptCheck = code; /*adresse de départ ou arrivée ? */
	 if (geocoder) {
	 geocoder.geocode( {'adrDep': document.getElementById(src).value},
	function(results, status) {
	 if (status == google.maps.GeocoderStatus.OK) {
	 /*ajoute un marqueur à l'adresse choisie*/
	 map.setCenter(results[0].geometry.location);
	 if (marker) { marker.setMap(null);}
	 marker = new google.maps.Marker({
	 map: map,
	 position: results[0].geometry.location
	 });
	 /*on remplace l'adresse par celle fournie du geocoder*/
	 document.getElementById(src).value = results[0].formatted_address;
	 depart = results[0].formatted_address;
	 /*trace la route*/
	 
	 }
	 });
	 geocoder.geocode( {'adrArr': document.getElementById(src2).value},
	function(results, status) {
	 if (status == google.maps.GeocoderStatus.OK) {
	 /*ajoute un marqueur à l'adresse choisie*/
	 if (marker2) { marker2.setMap(null);}
	 marker2 = new google.maps.Marker({
	 map: map,
	 position: results[0].geometry.location
	 });
	 /*on remplace l'adresse par celle fournie du geocoder*/
	 document.getElementById(src2).value = results[0].formatted_address;
	 arrivee = results[0].formatted_address;
	 /*trace la route*/
	 }
	 trouveRoute();
	 });
	 }
	 }

//function codeAddress() {
//	  // recup adresse
//	  var address = document.getElementById("saisie").value;
//	  // creation objet Geocoder
//	  var geocoder = new google.maps.Geocoder();
//	  // appel methode geocode
//	  geocoder.geocode( { 'address': address}, function( data, status) {
//	    // reponse OK
//	    if( status == google.maps.GeocoderStatus.OK){
//	      // recup. position
//	      var pos = data[0].geometry.location;
//	      // creation d'un marker
//	      var marker = new google.maps.Marker({
//	        map : oMap,
//	        position : pos
//	      });
//	      // centrage de la carte
//	      oMap.setCenter( pos);
//	    }
//	    else {
//	      alert("Geocode was not successful for the following reason: " + status);
//	    }
//	  });
//	}