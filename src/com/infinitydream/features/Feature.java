package com.infinitydream.features;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import com.infinitydream.core.FileParser;

public abstract class Feature {
    private String featureName;
    protected Double[] featureVector;
    
    public Feature(String fname, int size, double[][] image) {
	featureName = fname;
	featureVector = new Double[size];
    }
    
    protected abstract void extractFeature(double[][] image);
    
    public Double[] getFeature() {
	return featureVector;
    }
    
    public static void main(String args[]) throws FileNotFoundException, IOException {
	String carpath1 = "/home/divoo/workspace/4th_year_workspace/Pattern_Project/Training data/car_data/Class4_Sample23.mat";
	String planpath1 = "/home/divoo/workspace/4th_year_workspace/Pattern_Project/Training data/plane_data/Class7_Sample23.mat";
	//double[][] data = FileParser.parseMatFile(planpath1);
	double[][] data = FileParser.parseMatFile(planpath1);
	
	HillClimb_feature hcf = new HillClimb_feature(0,data);
    }
    
}
