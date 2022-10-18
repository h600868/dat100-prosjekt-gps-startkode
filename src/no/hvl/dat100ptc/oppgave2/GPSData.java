package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	//konstruerer en ny tabell med lengde n
	public GPSData(int n) {

		gpspoints = new GPSPoint[n];
		antall = 0;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	//
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			return !inserted;
		}
		
		return inserted;
	}

	// tar ett gpspunkt som strengverdi og setter inn nye verdier etter konverteringen er gjennomført
	public boolean insert(String time, String latitude, String longitude, String elevation) {
		
		boolean insert = false;
		GPSPoint gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
		
		//returnerer boolen verdi når gjennomkjørt
		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			return !insert;
		}
		
		return insert;
	}
	
	// Skriver ut gpspunktene med "punkt + (breddegrad + lengdegrad) + høyde
	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		for (GPSPoint i : gpspoints) {
			System.out.print(i.toString());
		}

		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
