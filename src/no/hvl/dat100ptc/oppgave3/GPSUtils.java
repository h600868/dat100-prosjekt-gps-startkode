package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;
import java.util.Locale;


import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	//brukt for å finne max verdien i en gitt tabell
	public static double findMax(double[] da) {

		double max; 
		//setter første verdien som høyeste så langt
		max = da[0];
		
		//sjekker videre gjennom tabellen, om det finnes en høyere verdi en max
		//blir d satt som ny max
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

		//samme som forrgie bare at den sjekker for minste verdi i en tabell
	public static double findMin(double[] da) {

		double min;

		min = da[0];
		//samme startkode som findMax bare snudd om der den leter etter d som er mindre enn min
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;

	}
		
		//brukes for å finne breddegrader ved å ta for seg gpspunktene
	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		//lager en ny tabell for breddegradene som er like lang som gpspunktene
		double[] latitude = new double[gpspoints.length];

		//for løkke som setter inn alle breddegradene i den nye tabellen
		for (int i = 0; i < gpspoints.length; i++) {
			latitude[i] = gpspoints[i].getLatitude();
		}
		//returnerer tabellen
		return latitude;
		
	}
		
		//identisk som forrgie bare at denne returnerer lengdegradene i en tabell
	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitude = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			longitude[i] = gpspoints[i].getLongitude();
		}
		return longitude;
	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		
		//denne metoden returner distansen mellom to punkt ved å bruke bredde og lengdegrad til to punkter


		//bruker formelen i oppgavearket og gjør punktene om til radianer
		double radLatitude1 = Math.toRadians(gpspoint1.getLatitude());
		double radLongitude1 = Math.toRadians(gpspoint1.getLongitude());
		double radLatitude2 = Math.toRadians(gpspoint2.getLatitude());
		double radLongitude2 = Math.toRadians(gpspoint2.getLongitude());
		
		double latMinLat = radLatitude2 - radLatitude1;
		double longMinLong = radLongitude2 - radLongitude1;
		
		double a = (pow(sin(latMinLat / 2), 2)) + cos(radLatitude1) * cos(radLatitude2) * (pow(sin(longMinLong / 2), 2));
		double c = 2 * atan2(sqrt(a), sqrt(1-a));
		
		double d = R * c;
		
		//etter formelen får vi returnert d, som er avstand mellom punktene oppgitt i meter
		
		return d;
	}

		//regner ut hastigheten brukt mellom to punkter
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {


		double tid1 = gpspoint1.getTime();
		double tid2 = gpspoint2.getTime();
		
		//her finner vi ut tiden mellom de to punktene
		double secs = tid2 - tid1;
		
		//her bruker vi metoden for å finne distansen
		double distanse = distance(gpspoint1, gpspoint2);
		
		//formel for fart 
		double ms = distanse / secs; //meter pr sek
		double kmt = ms * 3.6; //km i timen
		
		return kmt;
	}

		//denne returnerer en streng for tidsformatet. i hh:mm:ss
	public static String formatTime(int secs) {

		String timestr = "";
		String TIMESEP = ":";
		
		//bruker sekundene for å regne ut timer, så gjenverende minutter, så gjenverende sekunder ved hjelp av modelo.
		int tim = secs / 3600;
		int min = (secs % 3600) / 60;
		int sek = (secs % 3600) % 60;
		
		//setter sammen i en tabell
		int[] tid = {tim, min, sek};
		
		//løkken for 
		for (int x : tid) {
			timestr += String.format("%02d", x);
			if (x != tid[tid.length - 1]) {
				timestr += TIMESEP;
			}
		}
		//her blir tekst strengen satt til å ha lengde på 10
		timestr = String.format("%1$10s", timestr);
		
		return timestr;

	}
	private static int TEXTWIDTH = 10;

	//omgjør et flyttall til to desimaler
	public static String formatDouble(double d) {

		String str = "";
		
		//runder av til to desimaler
		d = Math.round(d*100) / 100.0;
		//brukt for å endre kommaet i "1,35" til "1.35"
		str += String.format(Locale.ENGLISH, "%.2f", d);
		//setter lengden på strengen til 10. så den blir slik "      1.35" og returner
		str = String.format("%" + TEXTWIDTH + "s", str);
		return str;
		
	}
}
