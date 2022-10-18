package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {

	//faste objektvariabler for tid, bredde/lengde-grad og høyde
	private int time;
	private double latitude;
	private double longitude;
	private double elevation;
		
	//konstruktør som gir verdi til alle objektvariablene
	public GPSPoint(int time, double latitude, double longitude, double elevation) {

		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;

	}

	//diverse hent(get) og sett(set) metoder for å kunne kalle frem eller sette verdier
	public int getTime() {
		
		return time;
		
	}

	public void setTime(int time) {
				
		this.time = time;

	}

	public double getLatitude() {
		
		return latitude;
		
	}

	public void setLatitude(double latitude) {
		
		this.latitude = latitude;
		
	}

	public double getLongitude()  {
		
		return longitude;
		
	}

	public void setLongitude(double longitude) {
		
		this.longitude = longitude;
		
	}

	public double getElevation() {
		
		return elevation;
		
	}

	public void setElevation(double elevation) {
		
		this.elevation = elevation;
		
	}
	
	//returnerer en streng representasjon av et gps punkt
	public String toString() {
		
		String str = this.time + " (" + this.latitude + "," + this.longitude + ") " + this.elevation + "\n";
		
		return str;
		
	}
}
