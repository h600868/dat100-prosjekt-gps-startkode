package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitude = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			latitude[i] = gpspoints[i].getLatitude();
		}
		
		return latitude;
		
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitude = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			longitude[i] = gpspoints[i].getLongitude();
		}
		return longitude;
	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {


		double latitude1, longitude1, latitude2, longitude2;

		double radLatitude1 = Math.toRadians(gpspoint1.getLatitude());
		double radLongitude1 = Math.toRadians(gpspoint1.getLongitude());
		double radLatitude2 = Math.toRadians(gpspoint2.getLatitude());
		double radLongitude2 = Math.toRadians(gpspoint2.getLongitude());
		
		double latMinLat = radLatitude2 - radLatitude1;
		double longMinLong = radLongitude2 - radLongitude1;
		
		double a = (pow(sin(latMinLat / 2), 2)) + cos(radLatitude1) * cos(radLatitude2) * (pow(sin(longMinLong / 2), 2));
		double c = 2 * atan2(sqrt(a), sqrt(1-a));
		
		double d = R * c;

		return d;
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {


		double tid1 = gpspoint1.getTime();
		double tid2 = gpspoint2.getTime();
		
		double secs = tid2 - tid1;
		
		double distanse = distance(gpspoint1, gpspoint2);
		
		double ms = distanse / secs; //meter pr sek
		double kmt = ms * 3.6; //km i timen
		
		return kmt;
	}

	public static String formatTime(int secs) {

		String timestr = "";
		String TIMESEP = ":";
		
		int tim = secs / 3600;
		int min = (secs % 3600) / 60;
		int sek = (secs % 3600) % 60;
		
		int[] tid = {tim, min, sek};
		
		for (int x : tid) {
			timestr += String.format("%02d", x);
			if (x != tid[tid.length - 1]) {
				timestr += TIMESEP;
			}
		}
		
		timestr = String.format("%1$10s", timestr);
		
		return timestr;

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str = " ";

		
		
	}
}
