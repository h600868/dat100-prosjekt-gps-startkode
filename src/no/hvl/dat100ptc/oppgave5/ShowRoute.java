package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat));
		
		return ystep;
		
		
		
		// TODO - START
		
	//	throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		
		double xstep = xstep();
		double ystep = ystep();
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints)); //Få inn min lat
		double minlong = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints)); //Få inn min long

		
		int x;
		int y;
		int lastx = 0;
		int lasty = 0;
		setColor(0, 255, 0); // Sett fargen grønn
		for (int i = 0; i < gpspoints.length; i++) {
			x = MARGIN + (int) ((gpspoints[i].getLongitude() - minlong) * xstep); //Tar maks longitude - minste og ganger med xstep for å få estimert distanse mellom x koordinatene på x-aksen
			y = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);	//Gjør det samme bare med y aksen

			if (i != 0)
				drawLine(lastx, lasty, x, y);// Tegner linje fra gamle koordinat til nye koordinat

			if (i == gpspoints.length - 1) { // Fyller blå sirkel når for løkken er ferdig
				setColor(0, 0, 255);
				fillCircle(x, y, 6);
			} else { 						// Ellers fyller grønne sirkler på hver koordinat
				fillCircle(x, y, 3);
			}

			lastx = x; 	//Oppdaterer siste x til ny x
			lasty = y;  //Oppdaterer siste y til ny y
		}
		
		// throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20; 

		setColor(0,0,0);
		setFont("Courier",12);
		
		String drawstring = "";	 //Skal skrive mye det samme s´vi slipper å skrive string hver gang
		//Bruker String Format for å left shifte teksen, og totalcaper stringen på 16 characters, så : er på enden til høyre for den er right shifted by default
		String totalTime = GPSUtils.formatTime(gpscomputer.totalTime()); //Henter totaltid fra gpscomputer
		drawstring = String.format("%-16s", "Total Time") + ": " + totalTime;
		drawString(drawstring, TEXTDISTANCE, TEXTDISTANCE);

		String totalDist = GPSUtils.formatDouble(gpscomputer.totalDistance() / 1000); //Henter total distanse men i m
		drawstring = String.format("%-16s", "Total Distance") + ": " + totalDist + " km"; 

		String totalElev = GPSUtils.formatDouble(gpscomputer.totalElevation()); //Henter total elevation
		drawstring = String.format("%-16s", "Total Elevation") + ": " + totalElev + " m";
		drawString(drawstring, TEXTDISTANCE, TEXTDISTANCE * 3);

		String maxSpeed = GPSUtils.formatDouble(gpscomputer.maxSpeed()); //Henter maxspeed
		drawstring = String.format("%-16s", "Max Speed ") + ": " + maxSpeed + " km/t"; 
		drawString(drawstring, TEXTDISTANCE, TEXTDISTANCE * 4);

		String averageSpeed = GPSUtils.formatDouble(gpscomputer.averageSpeed()); //Henter gjns speed
		drawstring = String.format("%-16s", "Average Speed") + ": " + averageSpeed + " km/t";
		drawString(drawstring, TEXTDISTANCE, TEXTDISTANCE * 5);
		
		String kcalBurned = GPSUtils.formatDouble(gpscomputer.totalKcal(80)); //Henter kcal brent når du veier 80kg
		drawstring = String.format("%-16s", "Energy ") + ": " + kcalBurned + " kcal ";
		drawString(drawstring, TEXTDISTANCE, TEXTDISTANCE * 6);
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT;
	}

}
