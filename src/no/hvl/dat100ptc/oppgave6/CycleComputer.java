package no.hvl.dat100ptc.oppgave6;

import javax.swing.JOptionPane;

import easygraphics.*;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class CycleComputer extends EasyGraphics {

	private static int SPACE = 10;
	private static int MARGIN = 20;
	
	// FIXME: take into account number of measurements / gps points
	private static int ROUTEMAPXSIZE = 800; 
	private static int ROUTEMAPYSIZE = 400;
	private static int HEIGHTSIZE = 200;
	private static int TEXTWIDTH = 200;

	private GPSComputer gpscomp;
	private GPSPoint[] gpspoints;
	
	private int N = 0;

	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;

	public CycleComputer() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");

		gpscomp = new GPSComputer(filename);
		gpspoints = gpscomp.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		N = gpspoints.length; // number of gps points

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));

		xstep = xstep();
		ystep = ystep();

		makeWindow("Cycle Computer", 
				2 * MARGIN + ROUTEMAPXSIZE,
				2 * MARGIN + ROUTEMAPYSIZE + HEIGHTSIZE + SPACE);

		bikeRoute();

	}

	
	public void bikeRoute() {

	//Høydeprofil

		int max = 0;
		int time = 0;
		int m = 0;
		for (int s = 0; s<gpspoints.length;s++) {
			if (max<(int)gpspoints[s].getElevation()) {
				max = (int)gpspoints[s].getElevation();
			}
		}
		
			
		
		
		//Løype

		double[] ytab = GPSUtils.getLatitudes(gpspoints);
		double[] xtab = GPSUtils.getLongitudes(gpspoints);
		double xstep = xstep();
		double ystep = ystep();
		double[] climbs = gpscomp.climbs();
		
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		int ybase = 1050;
		
		for(int j = 0; j<gpspoints.length;j++) {
			int x = MARGIN + (int)((xtab[j]-minlon)*xstep);
			int y = (ybase-(int)((ytab[j] - minlat) * ystep))/2;
				setColor(0,255,0);
				
				//STARTPUNKT
				if (j==0) {
				setColor(0,0,255);
				fillCircle(x, y,7);
				}
				
				//SLUTTPUNKT
				else if(j==gpspoints.length-1) {
				setColor(0,0,255);
				drawLine(x,y,MARGIN + (int)((xtab[j-1]-minlon)*xstep), (ybase - (int)((ytab[j-1] - minlat) * ystep))/2);
				fillCircle(x, y,7);
				}
				
				//RØDT NÅR OPPOVER
				else if (climbs[j]>0){
				setColor(255,0,0);
				fillCircle(x, y,3);	
				drawLine(x,y,MARGIN + (int)((xtab[j-1]-minlon)*xstep), (ybase - (int)((ytab[j-1] - minlat) * ystep))/2);
				}
				
				//GRØNT NÅR NED ELLER FLATT
				else {
				setColor(0,255,0);
				fillCircle(x, y,3);	
				drawLine(x,y,MARGIN + (int)((xtab[j-1]-minlon)*xstep), (ybase - (int)((ytab[j-1] - minlat) * ystep))/2);
				}
		}
		
		//FOR løkken somtegner opp alt i vinduet 
		for(int i = 0; i<gpspoints.length;i++) {
		
			//Flytte syklisten langs løypen
			int x = MARGIN + (int)((xtab[i]-minlon)*xstep);
			int y = (ybase-(int)((ytab[i] - minlat) * ystep))/2;
			int x1 = 0;
			int y1 = 0;
			
			int syklist = (fillCircle(x,y,3));
			setColor(0,0,255);
			move(syklist,x,y);
			
			
			if (i>0) {
					x1 = MARGIN + (int)((xtab[i-1]-minlon)*xstep);
					y1 = (ybase-(int)((ytab[i-1] - minlat) * ystep))/2;
				
				
				//RØDT NÅR OPPOVER
				if (climbs[i-1]>0){
				setColor(255,0,0);
				fillCircle(x1, y1,3);	
				
				}
				
				//GRØNT NÅR NED ELLER FLATT
				else {
				setColor(0,255,0);
				fillCircle(x1, y1,3);	
				
				}
			}
	
	
			
			//VERDIER FOR FART OG TID
			double [] speeds = new double [gpspoints.length-1];
			
			for (int f = 1; f<gpspoints.length-1; f++) {
				speeds[f-1] = GPSUtils.speed(gpspoints[f-1], gpspoints[f]);
			}
			
			if (i<196) {
			int tid1 = gpspoints[i].getTime();
			int tid2 = gpspoints[i+1].getTime();
			
			time += tid2-tid1;
			}
			
			if (i<196) {
				setColor(255,255,255);
				fillRectangle(470,30,70,40);
				setColor(0,0,0);
				drawString("Live speed: "+ GPSUtils.formatDouble(speeds[i]) + " km/t",400, 50);
				drawString("Time           :     "+ GPSUtils.formatTime(time),400,65);
				
			}
			
		
			
			
		
			
			
			
			
			
		//HØYDEPROFILEN
			if(i%2 == 0) {
				setColor(0,0,255);
			}
			else {
				setColor(104,180,255);
			}
			
			drawLine(MARGIN + i,max+50,MARGIN+i,max+50-((int)gpspoints[i].getElevation()));
			m=i;
			
			
			
			pause(100);
		}
		
		//LINJER SOM VISER HØYDE VERDIEN
		for (int j = 0; j<gpspoints.length;j++) {
			if (j%10 == 0 && j<max+10) {
				setColor(0,0,0);
				drawString(" " + j,0,max+50-j);
				drawLine(25, max+50-j, m+MARGIN,max+50-j);
			}
			pause(25);
}
}


	public double xstep() {

		double xstep = ROUTEMAPXSIZE / (Math.abs(maxlon - minlon)); 
		
		return xstep;
	}

	public double ystep() {

		double ystep = ROUTEMAPXSIZE / (Math.abs(maxlat - minlat)); 
		
		return ystep;
	}

}
