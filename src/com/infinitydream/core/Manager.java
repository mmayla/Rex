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
	    patt.setBelongTo(classname);
	    patt.extractFeatures();
	    classif.train(classname, patt);
	}
    }

    private static double validatePatterns(Classifier classif,
	    List<double[][]> images, String expected) {
	int truecnt = 0;
	double imagescnt = images.size();
	for (double[][] image : images) {
	    Pattern patt = new Pattern(image);
	    patt.extractFeatures();
	    String belongto = classif.classify(patt);
	    if (belongto.equals(expected))
		truecnt++;
	    System.out.println(belongto);
	}

	double accuracy = truecnt / imagescnt;
	System.out.println(">" + accuracy * 100);
	return accuracy;
    }

    public static String run_validation1(String trscript, String valscript)
	    throws FileNotFoundException, IOException {

	List<String> trainingPaths = FileParser.parseScriptFile(trscript);
	List<String> validationPaths = FileParser.parseScriptFile(valscript);

	KNN_classifier knnclassifier = new KNN_classifier(5);

	// trainig
	for (int i = 0; i < trainingPaths.size(); i += 2) {
	    String label = trainingPaths.get(i);
	    String cpath = trainingPaths.get(i + 1);
	    train(knnclassifier, label,
		    FileParser.parseDirectory(cpath, ".mat"));
	}

	// validation
	double accuracy = 0;
	for (int i = 0; i < validationPaths.size(); i += 2) {
	    //
	    String expectedlabel = validationPaths.get(i);
	    String cpath = validationPaths.get(i + 1);
	    System.out.println("------------- " + expectedlabel
		    + " -------------");
	    accuracy += validatePatterns(knnclassifier,
		    FileParser.parseDirectory(cpath, ".mat"), expectedlabel);
	}

	System.out.println("Accuracy "
		+ ((accuracy / (validationPaths.size() / 2)) * 100 + "%"));
	return "nothing";
    }

    public static String run_validation2(String trscript, String valscript)
	    throws IOException {
	String resultsStr = "";
	List<String> trainingPaths = FileParser.parseScriptFile(trscript);
	List<String> validationlabels = FileParser.parseScriptFile(valscript);

	KNN_classifier knnclassifier = new KNN_classifier(5);

	// trainig
	for (int i = 0; i < trainingPaths.size(); i += 2) {
	    String label = trainingPaths.get(i);
	    String cpath = trainingPaths.get(i + 1);
	    train(knnclassifier, label,
		    FileParser.parseDirectory(cpath, ".mat"));
	}
	
	// validation
	double accuracy = 0;
	List<double[][]> images = FileParser.parseDirectory(validationlabels.get(0), ".mat");
	for (int i = 1; i < validationlabels.size(); i ++) {
	    String expected = validationlabels.get(i);
	    Pattern pattern = new Pattern(images.get(i-1));
	    pattern.extractFeatures();
	    String belongto = knnclassifier.classify(pattern);
	    resultsStr += belongto+"\n"; //file content
	    System.out.println(belongto);
	    if(belongto.equals(expected))
		accuracy++;
	}
	
	accuracy /= images.size();
	System.out.println("Accuracy "+accuracy*100+"%");
	
	return resultsStr;
    }

    public static void main(String[] args) throws FileNotFoundException,
	    IOException {
	// String trscript =
	// "/home/divoo/workspace/4th_year_workspace/Pattern_Project/Training data/training_script";
	// String valscript =
	// "/home/divoo/workspace/4th_year_workspace/Pattern_Project/Training data/validation_script";
	// run_validation1(trscript,valscript);

	String trscript = "/home/divoo/workspace/4th_year_workspace/Pattern_Project/Training data 2/training_script";
	String valscript = "/home/divoo/workspace/4th_year_workspace/Pattern_Project/Training data 2/validation_script";
	run_validation2(trscript, valscript);
    }

}
