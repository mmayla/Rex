package com.infinitydream.features;

import com.infinitydream.core.Utility;

public class PeakView_features extends Feature {
    int dimension;
    public PeakView_features(int dimension, double[][] image) {
	super("Hill Climb (" + (dimension == 0 ? "X" : "Y") + ") feature", 3, image);
	
	this.dimension = dimension;
	extractFeature(image);
    }

    @Override
    protected void extractFeature(double[][] image) {
	Double[] fvector = calculateDistances(image);
	
	this.featureVector[0] = fvector[0];
	this.featureVector[1] = fvector[1];
	this.featureVector[2] = fvector[2];
    }
    
    private Double[] calculateDistances(double[][] image) {
	Double[] fvector = new Double[3]; //0 max, 1 min, 2 avg
	Double[] points = Utility.getVector(image, this.dimension);
	int peakidx = Utility.indexOfPeak(points);
	Double peakvalue = points[peakidx];
	
	for(int i=0; i<points.length;i++) {
	    double d = distanceBetweenPoints(peakvalue, points[i]);
	    if(i==0){
		fvector[0] = d;
		fvector[1] = d;
		fvector[2] = d;
	    }else {
		if(d > fvector[0])
		    fvector[0] = d;
		if(d < fvector[1])
		    fvector[1] = d;
		fvector[2]+=d;
	    }
	}
	
	fvector[2] /= points.length;
	return fvector;
    }
    
    private Double distanceBetweenPoints(Double point1, Double point2) {
	return Math.abs(point1-point2);
    }

}
