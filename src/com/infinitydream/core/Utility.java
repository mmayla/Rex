package com.infinitydream.core;

import java.util.Collection;

import javax.management.MXBean;

public class Utility {
    /**
     * 
     * @param data
     * @param dimension
     *            0 for x dimension and 1 for y dimensions
     * @return the dimension in double[]
     */
    public static Double[] getVector(double[][] data, int dimension) {
	if (dimension < 0 || dimension > 1)
	    throw new IllegalArgumentException("Dimension must be 0 or 1");

	Double[] dvector = new Double[data.length];
	for (int i = 0; i < data.length; i++)
	    dvector[i] = data[i][dimension];

	return dvector;
    }
    
    /**
     * 
     * @param dvector
     *            to find the peak in it
     * @return the index of the peak (highest value) in the vector
     */
    public static <E extends Comparable<E>> int indexOfPeak(E[] dvector) {
	E max = dvector[0];
	int indx = 0;
	for (int i = 0; i < dvector.length; i++) {
	    if (dvector[i].compareTo(max) > 0) {
		max = dvector[i];
		indx = i;
	    }
	}

	return indx;
    }
    
    /**
     * assign value to all elements in vector
     * @param vector 
     * @param value
     */
    public static <E> void assignAll(E[] vector, E value) {
	for(int i=0;i<vector.length;i++)
	    vector[i]=value;
    }
    
    /**
     * 
     * @param dvector
     * @return the value at index 0 represent the min value in dvector
     * and at the index 1 represent the max value
     */
    public static Double[] getMiniMax (Double[] dvector) {
	Double[] minimax = new Double[2];
	minimax[0] = dvector[0]; //min
	minimax[1] = dvector[0]; //max
	
	for(int i=0;i<dvector.length;i++) {
	    if(dvector[i]>minimax[1])
		minimax[1] = dvector[i];
	    if(dvector[i]<minimax[0])
		minimax[0] = dvector[i];
	}
	
	return minimax;
    }
    
    public static Double[] premArrayToDouble(double[] parray) {
	Double[] carray = new Double[parray.length];
	for(int i=0;i<parray.length;i++) {
	    carray[i] = parray[i];
	}
	
	return carray;
    }
    
    public static void main(String[] args) {
	double[] x = {1,4,6,8,0,19,23,5};
	//System.out.println(max);
    }
}
