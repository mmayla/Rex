package com.infinitydream.features;

import com.infinitydream.core.Utility;

public class CenterOfGravity_feature extends Feature {

    public CenterOfGravity_feature(double[][] image) {
	super("Center of Gravity feature", 3, image);
	extractFeature(image);
    }

    @Override
    protected void extractFeature(double[][] image) {
	Double[] fvector = calculateDistances(image);
	this.featureVector[0] = fvector[0];
	this.featureVector[1] = fvector[1];
	this.featureVector[2] = fvector[2];
    }

    private Double[] calculateCentroid(double[][] image) {
	Double[] position = new Double[2];
	Double[] xdimen = Utility.getMiniMax(Utility.getVector(image, 0));
	Double[] ydimen = Utility.getMiniMax(Utility.getVector(image, 1));

	position[0] = Math.abs(xdimen[0] - xdimen[1]);
	position[1] = Math.abs(ydimen[0] - ydimen[1]);

	return position;
    }

    private Double[] calculateDistances(double[][] image) {
	Double[] fvector = new Double[3]; //0 max, 1 min, 2 avg
	Double[] centroid = calculateCentroid(image);

	for (int i = 0; i < image.length; i++) {
	    Double d = distanceBetweenPoints(centroid,
		    Utility.premArrayToDouble(image[i]));
	    if (i == 0) {
		fvector[0] = d;
		fvector[1] = d;
		fvector[2] = d;
	    } else {
		if (d > fvector[0])
		    fvector[0] = d;
		if (d < fvector[1])
		    fvector[1] = d;
		fvector[2] += d;
	    }
	}
	fvector[2] /= image.length;
	return fvector;
    }

    private Double distanceBetweenPoints(Double[] point1, Double[] point2) {
	Double sum = Math.pow((point1[0] - point2[0]), 2)
		+ Math.pow((point2[1] - point2[1]), 2);
	return Math.sqrt(sum);
    }
}
