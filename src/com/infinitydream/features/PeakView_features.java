package com.infinitydream.features;

public class PeakView_features extends Feature {

    public PeakView_features(int dimension, double[][] image) {
	super("Hill Climb (" + (dimension == 0 ? "X" : "Y") + ") feature", 3, image);
	
	extractFeature(image);
    }

    @Override
    protected void extractFeature(double[][] image) {
	
    }

}
