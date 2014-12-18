package com.infinitydream.core;

import java.util.ArrayList;
import java.util.List;

import com.infinitydream.features.Feature;
import com.infinitydream.features.HillClimb_feature;

public class Pattern {
    private double[][] image;
    private List<Feature> featuresVector;
    
    public Pattern(double[][] image) {
	featuresVector = new ArrayList<Feature>();
	this.image = image;
    }
    
    public void extractFeatures() {
	featuresVector.add(new HillClimb_feature(0,7,image.clone()));
	featuresVector.add(new HillClimb_feature(1,7,image.clone()));
    }

    public double[][] getImage() {
        return image;
    }

    public List<Feature> getFeaturesVector() {
        return featuresVector;
    }
}
