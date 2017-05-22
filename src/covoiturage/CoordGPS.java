package covoiturage;

import java.util.*;

import java.io.*;

public class CoordGPS {
	private double longitude;
	private double latitude;
	public static final int RAYON=5;
	
	
	public CoordGPS(double latitude, double longitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public boolean estProche(CoordGPS autreCoord,int rayon) {
		// Regarder l'ecart en valeur absolue entre longitude et latitude
		double distanceEnKm = distance(this.latitude, this.longitude, autreCoord.latitude, autreCoord.longitude, "K");
		System.out.println("distance = " + distanceEnKm);
		return (distanceEnKm <= rayon);
	}
	public static CoordGPS traduitLatLngEnCoordGPS( String unLatLong) {
		CoordGPS retour; 
		String[] parts = unLatLong.split(",");
		retour = new CoordGPS(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]));
		return retour;
	}
	
	// TODO A TESTER, et voir pour un tableau dynamique
	public static CoordGPS[] traduitEnRouteDeCoord (String uneChaine) {
		CoordGPS[] retour = new CoordGPS[100];
		// on recoit (43.1111,1.5)(43.2555,1.6) ...
		uneChaine= uneChaine.replace("(", "");
		
		String[] parts = uneChaine.split(")");
		for (int iLatLng=0; iLatLng <= parts.length; iLatLng++) {
			CoordGPS uneCoord = traduitLatLngEnCoordGPS(parts[iLatLng]);
			retour[iLatLng] = uneCoord;
		}
		return retour;
	}
	


//		public static void main (String[] args) throws java.lang.Exception
//		{
//			System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "M") + " Miles\n");
//			System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "K") + " Kilometers\n");
//			System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "N") + " Nautical Miles\n");
//		}

		private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
			double theta = lon1 - lon2;
			double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
			dist = Math.acos(dist);
			dist = rad2deg(dist);
			dist = dist * 60 * 1.1515;
			if (unit == "K") {
				dist = dist * 1.609344;
			} else if (unit == "N") {
				dist = dist * 0.8684;
			}

			return (dist);
		}

		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		/*::	This function converts decimal degrees to radians						 :*/
		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		private  double deg2rad(double deg) {
			return (deg * Math.PI / 180.0);
		}

		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		/*::	This function converts radians to decimal degrees						 :*/
		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		private double rad2deg(double rad) {
			return (rad * 180 / Math.PI);
		}
	
	
	
}
