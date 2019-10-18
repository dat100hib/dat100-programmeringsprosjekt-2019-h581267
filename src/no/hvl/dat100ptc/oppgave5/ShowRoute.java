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

		playRoute(MARGIN + MAPYSIZE);
		
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
	
		double ystep;
		
		// TODO - START
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		ystep = MAPYSIZE / (Math.abs(maxlat-minlat));
		
		return ystep;
		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		
		double[] ytab = GPSUtils.getLatitudes(gpspoints);
		double[] xtab = GPSUtils.getLongitudes(gpspoints);
		double xstep = xstep();
		double ystep = ystep();
		
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
	
		for(int i = 0; i<gpspoints.length;i++) {
			
		int x = MARGIN + (int)((xtab[i]-minlon)*xstep);
		int y = ybase - (int)((ytab[i] - minlat) * ystep);
			setColor(0,255,0);
			if (i==0) {
			setColor(0,0,255);
			fillCircle(x, y,7);
			}
			else if (i==gpspoints.length-1) {
			setColor(0,0,255);
			drawLine(x,y,MARGIN + (int)((xtab[i-1]-minlon)*xstep), ybase - (int)((ytab[i-1] - minlat) * ystep));
			fillCircle(x, y,7);
			}
			else {
			fillCircle(x, y,3);	
			drawLine(x,y,MARGIN + (int)((xtab[i-1]-minlon)*xstep), ybase - (int)((ytab[i-1] - minlat) * ystep));
			}
			pause(25);
		}
	
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		
	
	
		drawString("Total Time      :       " + GPSUtils.formatTime(gpscomputer.totalTime()), 0, 0);
		
		drawString("Total distance  :   " + GPSUtils.formatDouble(gpscomputer.totalDistance()/1000) + " km", 0, 20);
		
		drawString("Total elevation :   " + GPSUtils.formatDouble(gpscomputer.totalElevation())+ " m", 0 , 30);
		
		drawString("Max speed       :   " + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t", 0 , 40);
		
		drawString("Average speed   :   " + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t", 0, 50);
		
		drawString("Energy          :   " + GPSUtils.formatDouble(gpscomputer.totalKcal(80)) + " kcal", 0, 60); 
		
		// TODO - SLUTT;
	}

	public void playRoute(int ybase) {

		// TODO - START
		
		
		
		// TODO - SLUTT
	}

}
