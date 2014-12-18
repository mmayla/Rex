package com.infinitydream.classifiers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.infinitydream.core.Pattern;
import com.infinitydream.core.Utility;

public class KNN_classifier extends Classifier {

    private class TableRow {
	public int classno;
	public double d;

	public TableRow(int c, double d) {
	    this.classno = c;
	    this.d = d;
	}
    }

    private int k;

    public KNN_classifier(int k) {
	super();
	this.k = k;
    }

    @Override
    protected Integer[] runClassification(Pattern pattern,
	    Integer[] featureVector, int featueNo) {
	List<TableRow> tr = createDistanceTable(pattern, featueNo);
	int classifiedTo = findKNN(tr);
	featureVector[classifiedTo]++;
	return featureVector;
    }

    private double computeDistance(Pattern pattern1, Pattern pattern2,
	    int featureNo) {
	double dsum = 0;
	Double[] f1 = pattern1.getFeaturesVector().get(featureNo).getFeature();
	Double[] f2 = pattern2.getFeaturesVector().get(featureNo).getFeature();
	for (int i = 0; i < f1.length; i++) {
	    dsum += Math.pow(Math.abs(f1[i] - f2[i]), 2);
	}

	double d = Math.sqrt(dsum);

	return d;
    }

    private List<TableRow> createDistanceTable(Pattern pattern, int featureNo) {
	List<TableRow> dists = new ArrayList<TableRow>();
	for (int i = 0; i < patterns.size(); i++) {
	    for (int k = 0; k < patterns.get(i).size(); k++) {
		Pattern otherPattern = patterns.get(i).get(k);
		double d = computeDistance(pattern, otherPattern, featureNo);
		TableRow tb = new TableRow(i, d);
		dists.add(tb);
	    }
	}

	return dists;
    }

    private int findKNN(List<TableRow> disttable) {
	Integer[] knn = new Integer[classes.size()];

	for (int i = 0; i < knn.length; i++)
	    knn[i] = 0;

	Collections.sort(disttable, new Comparator<TableRow>() {
	    @Override
	    public int compare(TableRow o1, TableRow o2) {
		return Double.compare(o1.d, o2.d);
	    }
	});

	for (int i = 0; i < this.k; i++) {
	    int idx = disttable.get(i).classno;
	    knn[idx]++;
	}

	return Utility.indexOfPeak(knn);
    }
}
