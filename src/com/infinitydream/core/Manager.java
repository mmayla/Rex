package com.infinitydream.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.infinitydream.classifiers.Classifier;
import com.infinitydream.classifiers.KNN_classifier;

public class Manager {

    private static void train(Classifier classif, String classname,
	    List<double[][]> images) {

	for (double[][] image : images) {
	    Pattern patt = new Pattern(image);
	    patt.extractFeatures();
	    classif.train(classname, patt);
	}
    }

    private static void testPatterns(Classifier classif, List<double[][]> images) {
	for (double[][] image : images) {
	    Pattern patt = new Pattern(image);
	    patt.extractFeatures();
	    System.out.println(classif.classify(patt));
	}
    }

    public static void run() throws FileNotFoundException, IOException {
	String carlist_path = "/home/divoo/workspace/4th_year_workspace/Pattern_Project/Training data/car_data/";
	String planlist_path = "/home/divoo/workspace/4th_year_workspace/Pattern_Project/Training data/plane_data/";
	String cars_testlist_path = "/home/divoo/workspace/4th_year_workspace/Pattern_Project/Training data/test_data_car/";
	String plans_testlist_path = "/home/divoo/workspace/4th_year_workspace/Pattern_Project/Training data/test_data_plan/";
	
	KNN_classifier knnclassifier = new KNN_classifier(7);
	train(knnclassifier, "car",
		FileParser.parseDirectory(carlist_path, ".mat"));
	train(knnclassifier, "plan",
		FileParser.parseDirectory(planlist_path, ".mat"));
	System.out.println("cars --------------------------------------");
	testPatterns(knnclassifier, FileParser.parseDirectory(cars_testlist_path, ".mat"));
	System.out.println("plans --------------------------------------");
	testPatterns(knnclassifier, FileParser.parseDirectory(plans_testlist_path, ".mat"));

    }

    public static void main(String[] args) throws FileNotFoundException,
	    IOException {
	run();
    }

}
