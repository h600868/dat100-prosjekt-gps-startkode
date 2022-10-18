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
	
	// beregn total distanse (i meter)
	public double totalDistance() {

		double distance = 0;
		
		//bruker løkken for å summerere total distance fra lengden mellom alle punktene i gpspoints
		for (int i = 1; i < gpspoints.length; i++){	
				double lengthPoint = GPSUtils.distance(gpspoints[i-1], gpspoints[i]);
				distance += lengthPoint;
		}
			return distance;
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		//bruker løkken for å sumere sammen totale høydemeteren løypen har steget. Sjekker det nye punktet med det forrige punktet. 
		
		for (int i = 1; i < gpspoints.length; i++){	
				double elevationPoint1 = gpspoints[i].getElevation();
				double elevationPoint2 = gpspoints[i-1].getElevation();
				
    	//Sjekker i if-løkken om det nye er høyere, om dette er sant legges differansen til den totale høydemeterern.
				if ( elevationPoint1 > elevationPoint2) {
						elevation += elevationPoint1 - elevationPoint2;
				}
		}
		
			return elevation;

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		//sjekker tid ved siste punktet og trekker ifra det første punktet i målingene, og regner ut total tid. 
		int time = gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
		
		return time;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		//lager en tabell som kan oppbevare gjennomsnittshastighetene 
		double[] speeds = new double[gpspoints.length - 1];
		int index = 0;
		
		//bruker løkken for å legge inn hastigheten mellom hvert av punktene inn i sin plass i tabellen speeds.
		for (int i = 1; i < gpspoints.length; i++) {
			speeds[index] = GPSUtils.speed(gpspoints[i-1], gpspoints[i]);
			index++;
		}
		
		return speeds; 
	}
		//blir brukt for å finne maksimale hastigheten målt mellom to punkter. 
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		double[] speeds = speeds();
		
		maxspeed = GPSUtils.findMax(speeds);
		
		return maxspeed;
		
	}
		/*regner ut gjennomsnittshastigheten, der vi bruker total distansen
		  delt på total tiden. også gange 3,6 for å få meter per sekund*/
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
		
		//sjekker hvilken kategori forbrenningen ligger på innenfor MET. som tar hastigheten og regner ut "arbeid" utført
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
		
		//regner ut total kcal forbrenning iløpet av hele turen.  
		//og bruker kcal metoden ovenfor bare med totaltime og average speed som input i tilegg. 
	public double totalKcal(double weight) {

		double totalkcal = 0;
		
		int totalTime = totalTime();
		double averageSpeed = averageSpeed();
		
		totalkcal = kcal(weight, totalTime, averageSpeed);

		return totalkcal;
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {
		
		
		
		
		System.out.println("==============================================");
		//printer ut i konsollen. "%-15s" for sette semikolonet et fast sted 15 "tegn" bak. 
		System.out.printf("%-15s:","Total Time");
		//så blir verdien for totalTime trekt ut og printet. 
		System.out.println(GPSUtils.formatTime(this.totalTime()));
		
		System.out.printf("%-15s:", "Total distance");
		System.out.println(GPSUtils.formatDouble(this.totalDistance() / 1000) + " km");
		
		System.out.printf("%-15s:", "Total elevation");
		System.out.println(GPSUtils.formatDouble(this.totalElevation()) + "m");
		
		System.out.printf("%-15s:", "Max speed");
		System.out.println(GPSUtils.formatDouble(this.maxSpeed()) + " km/t");
		
		System.out.printf("%-15s:", "Average speed");
		System.out.println(GPSUtils.formatDouble(this.averageSpeed()) + " km/t");
		
		System.out.printf("%-15s:", "Energy");
		System.out.println(GPSUtils.formatDouble(this.totalKcal(WEIGHT)) + " kcal");
		
		System.out.println("==============================================");

	}

}
