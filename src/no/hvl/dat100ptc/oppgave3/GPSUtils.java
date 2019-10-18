package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;


import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START

		min = da[0];
		
		for(double d : da) {
			if (d<min) {
				min = d;
			}
		}
		return min;
		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] latitudetab = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length;i++) {
		latitudetab[i]=gpspoints[i].getLatitude();
		
		}
		return latitudetab;
		
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double [] longitudetab = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length;i++) {
			longitudetab[i] = gpspoints[i].getLongitude();
		}
		return longitudetab;
		
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START

		latitude1 = gpspoint1.getLatitude();
		latitude2 = gpspoint2.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		longitude2 = gpspoint2.getLongitude();
		
		double ø1 = toRadians(latitude1);
		double ø2 = toRadians(latitude2);
		double eø = ø2-ø1;
		
		double landa1 = toRadians(longitude1);
		double landa2 = toRadians(longitude2);
		double elanda = landa2 -landa1;
		
		double a = pow(sin(eø/2),2) + cos(ø1)*cos(ø2)*pow(sin(elanda/2),2);
		double c = 2*atan2(sqrt(a),sqrt(1-a));
		
		d=R*c;
		
		return d;
		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START
		double d = distance(gpspoint1, gpspoint2);
		int secs1 = gpspoint1.getTime();
		int secs2 = gpspoint2.getTime();
		secs = secs2-secs1;
		
		speed = (d/secs)*3.6;
		
		return speed;
		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

	
		// TODO - START

		int time = secs/3600;
		int rest1 = secs%3600;
		int minutt = rest1/60;
		int sec = rest1%60; 
		
		String t = String.format("%02d", time);
		String m = String.format("%02d", minutt);
		String s = String.format("%02d", sec);	
			
		timestr = "  " + t + TIMESEP + m + TIMESEP + s;
		
		return timestr;
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START

		double tall = (d*100)+0.5;
		int runde = (int) tall;
		double svar = (double)runde/100;
		
		str = "      " + svar;
		
		return str;

		// TODO - SLUTT
		
	}
}
