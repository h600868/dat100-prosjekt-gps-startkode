package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {

	private static final int MARGIN = 50;
	private static final int BARHEIGHT = 200; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;

	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length - 1; // number of data points

		makeWindow("Speed profile", 2 * MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);

		showSpeedProfile(MARGIN + BARHEIGHT, N);
	}

	public void showSpeedProfile(int ybase, int N) {

		// get segments speeds from the GPS computer object

		int x = MARGIN, y;

		// TODO - START
		int j = 10;
		int max = 0;
		int horisontal = 10;
		for (int i = 0; i < gpscomputer.speeds().length; i++) {
			double[] speeds = gpscomputer.speeds();
			double speeds1 = speeds[i];
			long k = Math.round(speeds1);
			int p = (int) k;

			setColor(0, 0, 255);
			drawLine(j, ybase, j, ybase - p);

			
			max += p;
			horisontal = horisontal + 2;
			j = j + 2;
		}
		int gjennomsnitt = max / gpscomputer.speeds().length;
		setColor(0, 255, 0);
		drawLine(10, ybase-gjennomsnitt ,horisontal, ybase - gjennomsnitt);
		
		
		
		// TODO - SLUTT
	}
}
