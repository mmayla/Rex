package com.infinitydream.core;

import java.util.ArrayList;
import java.util.List;

import com.infinitydream.features.CenterOfGravity_feature;
import com.infinitydream.features.Feature;
import com.infinitydream.features.HillClimb_feature;
import com.infinitydream.features.PeakView_features;

public class Pattern {
    private double[][] image;
    private List<Feature> featuresVector;
    private String belongTo;
    
    public Pattern(double[][] image) {
	featuresVector = new ArrayList<Feature>();
	this.image = image;
	belongTo = "";
    }
    
    public void extractFeatures() {
	featuresVector.add(new HillClimb_feature(0,0,image.clone()));
	featuresVector.add(new HillClimb_feature(1,0,image.clone()));
	featuresVector.add(new CenterOfGravity_feature(image.clone()));
	featuresVector.add(new PeakView_features(0,image.clone()));
	featuresVector.add(new PeakView_features(1,image.clone()));
    }

    public double[][] getImage() {
        return image;
    }

    public List<Feature> getFeaturesVector() {
        return featuresVector;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }
}
