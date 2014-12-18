package com.infinitydream.features;

import java.lang.*;

import com.infinitydream.core.Utility;

/**
 * Hill climb feature find increasing and decreasing hills
 * for each dimension
 * it's feature vector consist of 3 values (increasing_hills,decreasing_hills,absolute_diffrence)
 * @author Mohamed Mayla
 *
 */
public class HillClimb_feature extends Feature {

    private final int dimension;
    private int noise;
    
    public HillClimb_feature(int dimension, int noise, double[][] image) {
	super("Hill Climb (" + (dimension == 0 ? "X" : "Y") + ") feature", 3,image);

	if (dimension < 0 || dimension > 1)
	    throw new IllegalArgumentException("Dimension must be 0 or 1");

	this.dimension = dimension;
	this.noise = noise;
	extractFeature(image);
	
    }

    @Override
    protected void extractFeature(double[][] image) {
	Double[] dvector = getVector(image, this.dimension);
	int peakIdx = indexOfPeak(dvector);
	int hcnt = countHills(dvector, 1, peakIdx);
	int lcnt = countHills(dvector, dvector.length - 1, peakIdx);
	int adiff = Math.abs(hcnt - lcnt);

	featureVector[0] = (double) hcnt;
	featureVector[1] = (double) lcnt;
	featureVector[2] = (double) adiff;
	
//	System.out.println(hcnt);
//	System.out.println(lcnt);
//	System.out.println(adiff);
    }

    /**
     * 
     * @param dvector
     *            to find the peak in it
     * @return the index of the peak (highest value) in the vector
     */
    private int indexOfPeak(Double[] dvector) {
	return Utility.indexOfPeak(dvector);
    }

    /**
     * 
     * @param data
     * @param dimension
     *            0 for x dimension and 1 for y dimensions
     * @return the dimension in double[]
     */
    private Double[] getVector(double[][] data, int dimension) {
	return Utility.getVector(data, dimension);
    }

    /**
     * 
     * @param dvector
     * @param startIdx
     * @param endIdx
     * @return no of hills
     */
    private int countHills(Double[] dvector, int startIdx, int endIdx) {
	// System.out.println("Max: " + endIdx);
	int hillsCount = 0;
	boolean increasing = startIdx <= endIdx ? true : false;

	double prevstep = dvector[startIdx];
	int noisecount = 0;
	boolean newlocal = true;
	for (int i = (increasing ? startIdx + 1 : startIdx - 1); (increasing ? i < endIdx
		: i >= endIdx); i = (increasing ? i + 1 : i - 1)) {
	    if (dvector[i] < prevstep) {
		if (newlocal) {
		    noisecount = 0;
		    newlocal = false;
		    // System.out.println("> " + i + " " + data[i]);
		} else {
		    noisecount++;
		}
	    } else {
		if (noisecount >= noise) {
		    hillsCount++;
		    noisecount = 0;
		    // System.out.println("^");
		}
		newlocal = true;
	    }

	    prevstep = dvector[i];
	}

	// System.out.println("h: " + hillsCount);
	return hillsCount;
    }

}
