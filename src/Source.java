import java.io.FileNotFoundException;
import java.io.IOException;

import com.infinitydream.core.FileParser;
import com.jmatio.*;
import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLDouble;

public class Source {

    public static int indexOfMax(double[] dvector) {
	double max = dvector[0];
	int indx = 0;
	for (int i = 0; i < dvector.length; i++) {
	    if (dvector[i] > max) {
		max = dvector[i];
		indx = i;
	    }
	}

	return indx;
    }

    public static int countNoOfLocalMaxima(double[] data) {
	int maxidx = indexOfMax(data);
	System.out.println("Max: " + maxidx);
	int noise = 1;
	int highcount = 0;
	int lowcount = 0;

	// increasing peaks
	double prevstep = data[0];
	int noisecount = 0;
	boolean newlocal = true;
	for (int i = 1; i < maxidx; i++) {
	    if (data[i] < prevstep) {
		if (newlocal) {
		    noisecount = 0;
		    newlocal = false;
		    System.out.println("> "+i+" "+data[i]);
		} else {
		    noisecount++;
		}
	    } else {
		if (noisecount >= noise) {
		    highcount++;
		    noisecount=0;
		    System.out.println("^");
		}
		newlocal = true;
	    }

	    prevstep = data[i];
	}

	System.out.println("h: " + highcount);
	return highcount;
    }

    public static double[] double2unity(double[][] data, int one) {
	double[] dvector = new double[data.length];
	for (int i = 0; i < data.length; i++) {
	    dvector[i] = data[i][0];
	}

	return dvector;
    }

    public static void main(String[] args) throws FileNotFoundException,
	    IOException {
	String path = "/home/divoo/workspace/4th_year_workspace/Pattern_Project/Training data/plane_data/Class1_Sample1.mat";
	double[][] data = FileParser.parseMatFile(path);

	countNoOfLocalMaxima(double2unity(data, 0));

	// System.out.println(data.length +" " + data[0].length);
	// for(int i=0; i<10;i++){
	// System.out.println(data[i][0] + " "+ data[i][1]);
	// }
    }

}
