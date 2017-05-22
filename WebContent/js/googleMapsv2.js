
/* emplacement par défaut de la carte (Toulouse) */
//	var maison = new google.maps.LatLng(43.534352, 1.517772);  //sigems 44.406115, 0.738207   //rue du colombier 43.534352, 1.517772 //BL 43.534352, 1.517772
var depart,arrivee,ptCheck;
var directionsDisplay;
var directionsService ;
var map, geocoder, marker, marker2, markerUser; // La carte, le service de géocodage et les
var navigator;




			function testOnChange() {
				alert('OnChange googlescript v2');
			}

			function initMap() {			
				//indispensable pour l'affichage ultérieur du trajet 
				//alert('debut initmap');
				directionsDisplay = new google.maps.DirectionsRenderer();			
				var tableauMarqueurs = [
					{ lat:43.543265, lng:1.512196 },// BL 43.543265, 1.512196
					{ lat:43.562192, lng:1.514709 }, //43.562192, 1.514709
					{ lat:43.548577, lng:1.532985 }, //43.548577, 1.532985
					{ lat:43.601015, lng:1.446260 }, //43.601015, 1.446260
					{ lat:43.551229, lng:1.502564 }, //43.551229, 1.502564
					{ lat:43.591301, lng:1.418863 } //43.591301, 1.418863
				];
				var zoneMarqueurs = new google.maps.LatLngBounds();
				var mapStyles =
					  [
					    {
					      featureType: "road",
					      stylers: [
					        { visibility: "off" }
					      ]
					    }
					  ];

				var optionsCarte = {
										zoom: 8,
										center: { lat: 43.534352,lng: 1.517772 },//BL 43.543265, 1.512196
										disableDefaultUI: true,
									    zoomControl: true,
									    zoomControlOptions: {
									        style: google.maps.ZoomControlStyle.SMALL
									      },
									    styles: mapStyles,
									    mapTypeId : google.maps.MapTypeId.ROADMAP

									}

				map = new google.maps.Map( document.getElementById("divMap"), optionsCarte );
				
				// On met un marqueur par user proche
				var arrayLignes = document.getElementById('coordUsersProche').rows; //l'array est stocké dans une variable
				var nbLignes = arrayLignes.length;//on peut donc appliquer la propriété length
				var noLig=0; 

				var debug=""
				while(noLig<nbLignes)
				{
					var arrayColonnes = arrayLignes[noLig].cells;//on récupère les cellules de la ligne
					
					// Colonne 0 latitude et colonne 1 la longitude
					
					var latitude = arrayColonnes[0].innerHTML;
					var longitude = arrayColonnes[1].innerHTML;
					debug=debug + ":" + latitude + "/" + longitude;
					
					var optionsMarqueur = {
					map: map,
					position: new google.maps.LatLng( latitude, longitude )
					};
					markerUser = new google.maps.Marker( optionsMarqueur );
					zoneMarqueurs.extend( markerUser.getPosition() );
					noLig++;
				}
				
				// Puis on ajoute le point d'arrivée, et eventuellement le point depart (adresse indiquée)
				// Ca permet d'etre centré sur l'arrivée tant qu'aucune adresse n'est indiquée
				// Ou si aucun user n'est proche
				rechercherEtTracer('adrDep','adrArr');
				// Retrouver les coord de l'arrivé et du depart et les ajouter dans les marqueurs
				var strArr=document.getElementById('latLongArr').value+"";
				if (strArr.length > 0 )  {
					var listePositionArr = strArr.split(',');
					var imageMarqueurArr = {
							url: "building.png",
							size: new google.maps.Size(150, 120),
							anchor: new google.maps.Point(28, 120)
						};
					var optionsMarqueurArr = {
							map: map,
							position: new google.maps.LatLng( listePositionArr[0], listePositionArr[1] ),
							icon: imageMarqueurArr
							};
					markerUser = new google.maps.Marker( optionsMarqueurArr );
					zoneMarqueurs.extend( markerUser.getPosition() );
				}
				
				

				var strDep = document.getElementById('latLong').value+"";
				if (strDep.length > 0 )  {
					var listePosition = strDep.split(',');
					var imageMarqueurDep = {
							url: "maison.jpg",
							size: new google.maps.Size(100, 80),
							anchor: new google.maps.Point(28, 120)
						};
					
					var optionsMarqueurDep = {
							map: map,
							position: new google.maps.LatLng( listePosition[0], listePosition[1] ),
							icon: imageMarqueurDep
							};
					markerUser = new google.maps.Marker( optionsMarqueurDep );
					zoneMarqueurs.extend( markerUser.getPosition() );
					
				}
	
				
//				tableauMarqueurs.forEach( function(latlng) {
//					var latitude = latlng.lat,
//						longitude = latlng.lng;
//					var optionsMarqueur = {
//						map: map,
//						position: new google.maps.LatLng( latitude, longitude )
//					};
//					markerUser = new google.maps.Marker( optionsMarqueur );
//					zoneMarqueurs.extend( markerUser.getPosition() );
//				} );
				map.fitBounds( zoneMarqueurs );
				/*creation de la map*/
				//alert('av direction display');
				/*connexion de la map + le panneau de l'itinéraire*/
			    directionsDisplay.setMap(map);
			    //alert('milieu initmap');
			    directionsDisplay.setPanel(document.getElementById("divRoute"));
				/*intialise le geocoder pour localiser les adresses */
			    
				geocoder = new google.maps.Geocoder();
				directionsService = new google.maps.DirectionsService();
				
				// On trace la route si on a le départ et l'arrivée
				if (strDep.length > 0 && strArr.length > 0) {
					rechercherEtTracer('adrDep','adrArr');
				}
				//alert('fin initmap');
				
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
							
							var strRoute="Test";
//							alert(response.routes.length);
//							alert(response.routes[0].overview_path[0].length)
							var lesPoints=response.routes[0].overview_path;
							var nbPoints = lesPoints.length;
							strRoute="Test nb= " + nbPoints + ":";
							for (var iPoint = 0; iPoint < nbPoints; iPoint++) {
								var unStrPoint=lesPoints[iPoint] + "";
								strRoute=strRoute+unStrPoint;
							}
//							var point0=monTrajet.overview_path[0];
//							
//							var latPoint0= point0.K;
//							var longPoint0=point0.B;
//							strRoute=strRoute+lapoint0 + "," + longPoint0 + ":";
														
							// On balaye tous les points et la chaine formatté est rangé dans une balise HTML
							document.getElementById('route').value = strRoute;
						}
					});
				}
			}
			

			function geoLocaliserDepart(src)  {
				//alert("geolocaliser depart");
				if (geocoder) {
					//alert("1er if");
					geocoder
					.geocode(
							{
								'address' : document.getElementById(src).value
							},
							function(results, status) {
								if (status == google.maps.GeocoderStatus.OK) {
									/* ajoute un marqueur à l'adresse choisie */
									//alert('a l interieur');
									map.setCenter(results[0].geometry.location);								
									//récupération des coordonnées GPS du lieu saisi
									var strposition = results[0].geometry.location+"";
									
									
									
									strposition=strposition.replace('(','');
									strposition=strposition.replace(')','');
									
									//Memo des coordonnées dans HTML
									document.getElementById('latLong').value = strposition;


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

								}
							});

					//alert('geolocalisation adresse finie');
				}
			}
			
			function rechercherEtTracer(src, src2) {
				// ptCheck = code; /*adresse de départ ou arrivée ? */
				//alert("rechercher et tracer");
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
											//document.getElementById('text_latlng').innerHTML= strposition;
											
											document.getElementById('latLong').value = strposition;
//											var form = document.getElementsById('formProche'); 
//											var field = document.createElement('input');
//											field.name='coord_001' ;//+ ligne_boucle
//											field.value=strposition;
//											field.type='text';
//											filed.name='coord_001';
//											
//											form.appendChild(field);
											
											
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
											trouveRoute();  										}
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
			
			function initCompteGoogleMap() {
				geocoder = new google.maps.Geocoder();
			}
			

		

	