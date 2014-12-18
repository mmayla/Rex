package com.infinitydream.classifiers;

import java.util.ArrayList;
import java.util.List;

import com.infinitydream.core.Pattern;
import com.infinitydream.core.Utility;

public abstract class Classifier {
    protected List<String> classes;
    protected List<List<Pattern>> patterns;

    public Classifier() {
	classes = new ArrayList<String>();
	patterns = new ArrayList<List<Pattern>>();
    }

    /**
     * train from patterns and assign each pattern to a class if class dosn't
     * exist, new class will be created
     * 
     * @param classname
     * @param pattern
     */
    public void train(String classname, Pattern pattern) {
	int cnameidx = classes.indexOf(classname);
	if (cnameidx == -1) {
	    classes.add(classname);
	    patterns.add(new ArrayList<Pattern>());
	    patterns.get(patterns.size() - 1).add(pattern);
	} else {
	    patterns.get(cnameidx).add(pattern);
	}
    }

    /**
     * the classification method
     * 
     * @param pattern
     * @return
     */
    private String _classify(Pattern pattern) {
	int nofeatures = pattern.getFeaturesVector().size();
	Integer[] features = new Integer[nofeatures];
	Integer[] classificationVector = new Integer[classes.size()];
	Utility.assignAll(classificationVector, 0);

	for (int i = 0; i < features.length; i++) {
	    classificationVector = runClassification(pattern,
		    classificationVector, i);
	}

	int most = Utility.indexOfPeak(classificationVector);
	return classes.get(most);
    }

    /**
     * the classification for a single feature
     * 
     * @param pattern
     * @param featureVector
     * @return
     */
    protected abstract Integer[] runClassification(Pattern pattern,
	    Integer[] featureVector, int featueNo);

    /**
     * a wrapper for _classify method
     * 
     * @param pattern
     * @return
     */
    public String classify(Pattern pattern) {
	return _classify(pattern);
    }
}
