package no.hvl.dat100ptc.oppgave5;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static int MARGIN = 50;		// margin on the sides 
	
	//FIXME: use highest point and scale accordingly
	private static int MAXBARHEIGHT = 500; // assume no height above 500 meters
	
	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		GPSComputer gpscomputer =  new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {

		// ybase indicates the position on the y-axis where the columns should start
		
		// TODO - START
		int max = 0;
		for (int s = 0; s<gpspoints.length;s++) {
			if (max<(int)gpspoints[s].getElevation()) {
				max = (int)gpspoints[s].getElevation();
			}
		}
		int m = 0;
		for(int i = 0; i<gpspoints.length;i++) {
			
			if(i%2 == 0) {
				setColor(0,0,255);
			}
			else {
				setColor(104,180,255);
			}
			
			drawLine(MARGIN + i,ybase,MARGIN+i,ybase-((int)gpspoints[i].getElevation()));
			pause(25);
			
			m=i;
			
		}
		for (int j = 0; j < gpspoints.length; j++) {
			if (j%10 == 0 && j<max+10) {
				setColor(0,0,0);
				drawString(" " + j,0,ybase-j);
				drawLine(30, ybase-j, m+MARGIN,ybase-j);
			pause(100);
			}
			
		// TODO - SLUTT
	}

}
}
