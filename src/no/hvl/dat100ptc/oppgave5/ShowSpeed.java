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
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t

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

		int N = gpspoints.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	public void showSpeedProfile(int ybase, int N) {
		
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));
		
		
		
		
		// TODO - START
		
		int sluttX= 0;
		
		double Average = gpscomputer.averageSpeed();
		double maxSpeed = gpscomputer.maxSpeed();
		double[] speed = gpscomputer.speeds();
		
		for(int i = 0; i<speed.length;i++) {
			if(i%2 == 0) {
			setColor(0,0,255);
			}
			else {
			setColor(104,180,255);
			}
			double speeds = speed[i];
			drawLine(i,ybase,i,ybase-(int)speeds);
			sluttX = i;
			
			pause(25);
		}
		//Average
		setColor(0,255,0);
		drawLine(0,ybase - (int)Average,sluttX,ybase - (int)Average);
		
		//Maxspeed
		setColor(255,0,0);
		drawLine(0,ybase-(int)maxSpeed,sluttX,ybase -(int)maxSpeed);
		
	
		// TODO - SLUTT
	}
}
