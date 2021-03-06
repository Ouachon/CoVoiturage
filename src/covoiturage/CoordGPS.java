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
	public CoordGPS(String stringPt) {
		super();
		String[] uneCoord = stringPt.split(",");
		System.out.println("Point :" +stringPt);
		this.latitude = Double.parseDouble(uneCoord[0]);
		this.longitude = Double.parseDouble(uneCoord[1]);
		
	}
	
	@Override
	public String toString() {
		Double lat = this.getLatitude();
		Double lng = this.getLongitude();
		String strLat = lat.toString();
		String strlng = lng.toString();
		
		return "(" + strLat+","+strlng + ")";
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
		double distanceEnKm = kmAVolOiseauDe(autreCoord);
		return (distanceEnKm <= rayon);
	}

	public double kmAVolOiseauDe(CoordGPS autreCoord) {
		return distance(this.latitude, this.longitude, autreCoord.latitude, autreCoord.longitude, "K");
	}

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
