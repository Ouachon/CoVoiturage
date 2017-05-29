var directionsService; // service
																// GoogleMaps
var map, geocoder, marker, marker2; // La carte, le service de géocodage et les
									// marqueurs
var depart, arrivee= "capitole, Toulouse", ptCheck; // point de départ, arrivé et de vérification
var latDep, lngDep, latArr, lngArr ;
/* emplacement par défaut de la carte (Toulouse) */
//var maison = new google.maps.LatLng(43.534352, 1.517772);  //sigems 44.406115, 0.738207   //rue du colombier 43.534352, 1.517772 //BL 43.534352, 1.517772
//var direction;
var directionsDisplay;
var directionsService;



/* initialise google MAP V3 */
function initMap() {
	/* gestion des routes */
	directionsDisplay = new google.maps.DirectionsRenderer();
	//direction = new google.maps.DirectionsRenderer({
//  map   : map, 
//  panel : panel 
//});
	var tableauMarqueurs = [
		{ lat:43.543265, lng:1.512196 },// BL 43.543265, 1.512196
		{ lat:43.562192, lng:1.514709 }, //43.562192, 1.514709
		{ lat:43.548577, lng:1.532985 }, //43.548577, 1.532985
		{ lat:43.601015, lng:1.446260 }, //43.601015, 1.446260
		{ lat:43.551229, lng:1.502564 }, //43.551229, 1.502564
		{ lat:43.591301, lng:1.418863 } //43.591301, 1.418863
	];
	var zoneMarqueurs = new google.maps.LatLngBounds();
	tableauMarqueurs.forEach( function(latlng) {
		var latitude = latlng.lat,
			longitude = latlng.lng;
		var optionsMarqueur = {
			map: map,
			position: new google.maps.LatLng( latitude, longitude )
		};
		var marqueur = new google.maps.Marker( optionsMarqueur );
		zoneMarqueurs.extend( marqueur.getPosition() );
	} );
	map.fitBounds( zoneMarqueurs );
	
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

