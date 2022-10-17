package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		for (int i = 1; i < gpspoints.length; i++){	
				double lengthPoint = GPSUtils.distance(gpspoints[i-1], gpspoints[i]);
				distance += lengthPoint;
		}
			return distance;
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		for (int i = 1; i < gpspoints.length; i++){	
				double elevationPoint1 = gpspoints[i].getElevation();
				double elevationPoint2 = gpspoints[i-1].getElevation();
				
				if ( elevationPoint1 > elevationPoint2) {
						elevation += elevationPoint1 - elevationPoint2;
				}
		}
		
			return elevation;

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		int time = gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
		
		return time;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		double[] speeds = new double[gpspoints.length - 1];
		int index = 0;
		
		for (int i = 1; i < gpspoints.length; i++) {
			speeds[index] = GPSUtils.speed(gpspoints[i-1], gpspoints[i]);
			index++;
		}
		
		return speeds; 
	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		double[] speeds = speeds();
		
		maxspeed = GPSUtils.findMax(speeds);
		
		return maxspeed;
		
	}

	public double averageSpeed() {

		double average = 0;
		
		average = (totalDistance() / totalTime()) * 3.6;
		
		return average;
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		if (speedmph < 10) {
			met = 4.0;
		} else if (10 <= speedmph && speedmph < 12) {
			met = 6.0;
		} else if (12 <= speedmph && speedmph < 14) {
			met = 8.0;
		} else if (14 <= speedmph && speedmph < 16) {
			met = 10.0;
		} else if (16 <= speedmph && speedmph < 20) {
			met = 12.0;
		} else {
			met = 16.0;
		}
		
		kcal = (met * secs * weight) / 3600; 
		
		return kcal;
	
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		
		int totalTime = totalTime();
		double averageSpeed = averageSpeed();
		
		totalkcal = kcal(weight, totalTime, averageSpeed);

		return totalkcal;
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {
		
		
		String str = "";
		
		System.out.println("==============================================");
		
		
		System.out.println("==============================================");

	}

}
