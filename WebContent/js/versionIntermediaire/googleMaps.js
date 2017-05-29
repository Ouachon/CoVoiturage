var directionsService; // service
																// GoogleMaps
var map, geocoder, marker, marker2; // La carte, le service de géocodage et les
									// marqueurs
var depart, arrivee, ptCheck; // point de départ, arrivé et de vérification
var latDep, lngDep, latArr, lngArr ;

/* initialise google MAP V3 */
function initMap() {
	/* gestion des routes */
	directionsDisplay = new google.maps.DirectionsRenderer();
	/* emplacement par défaut de la carte (Toulouse) */
	var maison = new google.maps.LatLng(43.6042600, 1.4436700);
	/* option par défaut de la carte */
	var myOptions = {
		zoom : 11,
		zoomControl : true,
		mapTypeId : google.maps.MapTypeId.ROADMAP,
		center : maison
	}
	/* creation de la map */
	map = new google.maps.Map(document.getElementById("divMap"), myOptions);
	/* connexion de la map + le panneau de l'itinéraire */
	directionsDisplay.setMap(map);
	directionsDisplay.setPanel(document.getElementById("divRoute"));
	/* intialise le geocoder pour localiser les adresses */
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
								//récupération des coordonnées GPS du lieu saisi
								var strposition = results[0].geometry.location+"";
								strposition=strposition.replace('(','');
								strposition=strposition.replace(')','');
								//affichage des coordonnées dans le <span>
								document.getElementById('text_latlng').innerHTML= strposition;		
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
							}
							trouveRoute();
						});
	}
}

