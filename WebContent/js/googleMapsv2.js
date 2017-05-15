
	/* emplacement par défaut de la carte (Toulouse) */
//	var maison = new google.maps.LatLng(43.534352, 1.517772);  //sigems 44.406115, 0.738207   //rue du colombier 43.534352, 1.517772 //BL 43.534352, 1.517772
var direction;
var depart,arrivee = "24 rue Rostand, Labège" ,ptCheck;
var directionsDisplay;
var directionsService = new google.maps.DirectionsService();


			function initMap() {
				
				//indispensable pour l'affichage ultérieur du trajet 
				
				direction = new google.maps.DirectionsRenderer({
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
			   
				/*connexion de la map + le panneau de l'itinéraire*/
			    directionsDisplay.setMap(map);
			    directionsDisplay.setPanel(document.getElementById("divRoute"));
				/*intialise le geocoder pour localiser les adresses */
				geocoder = new google.maps.Geocoder();
				
			}
				// Récupérer l’itinéraire
//				var adresse_depart = 'capitole, toulouse';
//				var adresse_arrivee = 'rue rostand, toulouse';
//				var encoded_polyline;
//				//var directionsService = new google.maps.DirectionsService();
//				directionsService.route({origin:adresse_depart, destination:adresse_arrivee, travelMode:google.maps.TravelMode.DRIVING},function(result, status){
//				    if(status == google.maps.DirectionsStatus.OK)
//				    {
//				        encoded_polyline = result['routes'][0]['overview_polyline']['points'];
//				    }
//				});
//				// Préparons la carte OpenLayers
//				var cartographie;
//				var epsg_4326 = new OpenLayers.Projection("EPSG:4326");
//				var epsg_3857 = new OpenLayers.Projection("EPSG:3857");
//				cartographie = new OpenLayers.Map('carte');
//
//				// Couche "OSM"
//				var couche_osm = new OpenLayers.Layer.OSM('OpenStreetMap');
//				cartographie.addLayer(couche_osm);
//				cartographie.setCenter(new OpenLayers.LonLat(2, 48).transform(epsg_4326, epsg_3857), 7);
//
//				// Couche "Itinéraires"
//				var couche_itineraires = new OpenLayers.Layer.Vector('Itinéraires',{styleMap:new OpenLayers.StyleMap({'default':new OpenLayers.Style({strokeWidth:2,strokeColor:'red'})})});
//				cartographie.addLayer(couche_itineraires);
//				
//				//Transformation de l’itinéraire Google Maps en ligne classique
//				var decoded_polyline = google.maps.geometry.encoding.decodePath(encoded_polyline);
//				var points = new Array();
//				for(var i in decoded_polyline)
//				{
//				    // On récupère les deux coordonnées en WGS84 pour en faire un objet "Point" correctement reprojeté
//				    var lonlat = new OpenLayers.LonLat(decoded_polyline[i].lng(), decoded_polyline[i].lat()).transform(epsg_4326, epsg_3857);
//				    var point = new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat);
//				    // On ajoute ce point à notre tableau de points
//				    points.push(point);
//				}
//				// On ajoute, à notre couche d'itinéraires, un objet "Ligne" construit à partir du tableau de "Points"
//				couche_itineraires.addFeatures([new OpenLayers.Feature.Vector(new OpenLayers.Geometry.LineString(points))]);
//				cartographie.zoomToExtent(couche_itineraires.getDataExtent());
//			}
		

			
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
			
			function rechercher(adrDep,adrArr){
			    var origin      = document.getElementById('adrDep').value; // Le point départ
			    var destination = document.getElementById('adrArr').value; // Le point d'arrivé
			    if(origin && destination){
			        var request = {
			            origin      : origin,
			            destination : destination,
			            travelMode  : google.maps.DirectionsTravelMode.DRIVING // Type de transport
			        }
			       
			        directionsService.route(request, function(response, status){ // Envoie de la requête pour calculer le parcours
			            if(status == google.maps.DirectionsStatus.OK){
			                direction.setDirections(response); // Trace l'itinéraire sur la carte et les différentes étapes du parcours
			                trouveRoute();
			            }
			        });
			    } //http://code.google.com/intl/fr-FR/apis/maps/documentation/javascript/reference.html#DirectionsRequest
			};

			
//	   		function geolocate(){
//   			// Define user location
//		      GMaps.geolocate({
//		          success: function(position) {
//  		            map.setCenter(position.coords.latitude, position.coords.longitude);
//		          },
//		          error: function(error) {
//		            alert('Geolocation failed: '+error.message);
//		          },
//		          not_supported: function() {
//		            alert("Your browser does not support geolocation");
//		          }
//		      });
//		   // Creating marker of user location
//		      map.addMarker({
//		          lat: position.coords.latitude,
//		          lng: position.coords.longitude,
//		          title: 'Home',
//		          click: function(e) {
//		            alert('You clicked in this marker');
//		          },
//		          infoWindow: {
//		              content: '<p>You are here!</p>'
//		            }
//		    });
//			}