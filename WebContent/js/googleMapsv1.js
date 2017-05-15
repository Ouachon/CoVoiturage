var directionsService; // service
																// GoogleMaps
var map, geocoder, marker, marker2; // La carte, le service de géocodage et les
									// marqueurs
var depart, arrivee, ptCheck; // point de départ, arrivé et de vérification

/* initialise google MAP V3 */
function initMap() {
	//indispensable pour l'affichage ultérieur du trajet 
	
	directionsDisplay = new google.maps.DirectionsRenderer({
	    map   : map, 
	    panel : panel 
	});
	
	var tableauMarqueurs = [
		{ lat:43.543265, lng:1.512196 },// BL 43.543265, 1.512196
		{ lat:43.562192, lng:1.514709 }, //43.562192, 1.514709
		{ lat:43.548577, lng:1.532985 }, //43.548577, 1.532985
		{ lat:43.601015, lng:1.446260 }, //43.601015, 1.446260
		{ lat:43.551229, lng:1.502564 }, //43.551229, 1.502564
		{ lat:43.591301, lng:1.418863 } //43.591301, 1.418863
	];
	var zoneMarqueurs = new google.maps.LatLngBounds();
	var optionsCarte = {
		zoom: 8,
		center: { lat: 43.534352,lng: 1.517772 }//BL 43.543265, 1.512196
	
	}
	var maCarte = new google.maps.Map( document.getElementById("divMap"), optionsCarte );
	tableauMarqueurs.forEach( function(latlng) {
		var latitude = latlng.lat,
			longitude = latlng.lng;
		var optionsMarqueur = {
			map: maCarte,
			position: new google.maps.LatLng( latitude, longitude )
		};
		var marqueur = new google.maps.Marker( optionsMarqueur );
		zoneMarqueurs.extend( marqueur.getPosition() );
	} );
	maCarte.fitBounds( zoneMarqueurs );
	/*creation de la map*/
	
	/* connexion de la map + le panneau de l'itinéraire */
	directionsDisplay.setMap(maCarte);
	directionsDisplay.setPanel(document.getElementById("divRoute"));

	/*intialise le geocoder pour localiser les adresses */
	geocoder = new google.maps.Geocoder();	
	directionsService = new google.maps.DirectionsService();
}


function trouveRoute() {
	/* test si les variables sont bien initialisés */
	if (depart && arrivee) {
		var request = {
			origin :depart,
			destination :arrivee,
			travelMode : google.maps.DirectionsTravelMode["DRIVING"]
		};
		/* appel à l'API pour tracer l'itinéraire */
		directionsService.route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				directionsDisplay.setDirections(response);
			}
		});
	}
}


function rechercher(src, src2) {
	// ptCheck = code; /*adresse de départ ou arrivée ? */
	if (geocoder) {
		geocoder
				.geocode(
						{
							'address' : document.getElementById(src).value
						},
						function(results, status) {
							if (status == google.maps.GeocoderStatus.OK) {
								/* ajoute un marqueur à l'adresse choisie */
								map.setCenter(results[0].geometry.location);
								if (marker) {
									marker.setMap(null);
								}
								marker = new google.maps.Marker({
									map : map,
									position : results[0].geometry.location
								});
								/*
								 * on remplace l'adresse par celle fournie du
								 * geocoder
								 */
								document.getElementById(src).value = results[0].formatted_address;
								depart = results[0].formatted_address;
								/* trace la route */
								trouveRoute();
							}
						});
		geocoder
				.geocode(
						{
							'address' : document.getElementById(src2).value
						},
						function(results, status) {
							if (status == google.maps.GeocoderStatus.OK) {
								/* ajoute un marqueur à l'adresse choisie */
								if (marker2) {
									marker2.setMap(null);
								}
								marker2 = new google.maps.Marker({
									map : map,
									position : results[0].geometry.location
								});
								/*
								 * on remplace l'adresse par celle fournie du
								 * geocoder
								 */
								document.getElementById(src2).value = results[0].formatted_address;
								arrivee = results[0].formatted_address;
								/* trace la route */
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