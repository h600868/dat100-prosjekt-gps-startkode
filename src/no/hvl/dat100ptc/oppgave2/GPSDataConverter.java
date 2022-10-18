package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	private static int TIME_STARTINDEX = 11; // posisjon for start av tidspunkt i timestr

		//konverterer tiden gitt i en tekststreng til int verdi sekunder
	public static int toSeconds(String timestr) {
		
		//deler opp tidsstrengen vår gitt som vi kan se tidligere opp i egne strenger for hh,mm,ss osv
		String txtHr = timestr.substring(TIME_STARTINDEX, 13);
		String txtMin = timestr.substring(14, 16);
		String txtSec = timestr.substring(17, 19);
		
		//omgjør teksstrengen fra time til int verdi for time osv
		int hr = Integer.parseInt(txtHr);
		int min = Integer.parseInt(txtMin);
		int sec = Integer.parseInt(txtSec);
		
		//kalkulerer antall sekund ut ifra timene, minuttene og sekundene
		int secs = (hr * 60 * 60 + min * 60 + sec);
		
		return secs;
		
	}
	
		//konverterer gpspunkt fra tekststreng
	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		//gjør om hver av strengene til enten int eller double verdi
		int time = toSeconds(timeStr);
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);
		
		// returnerer nytt gpspunkt med verdiene int/double verdi og ikke string.
		GPSPoint gpspoint = new GPSPoint(time, latitude, longitude, elevation);

	    return gpspoint;
	}
	
}
