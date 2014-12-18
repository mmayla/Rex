package com.infinitydream.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLDouble;

public class FileParser {
    public static double[][] parseMatFile(String path) throws FileNotFoundException, IOException {
	MatFileReader fr = new MatFileReader(path);
	double[][] data = ((MLDouble)fr.getMLArray( "x" )).getArray();
	return data;
    }
    
    public static List<double[][]>  parseDirectory(String dirpath,final String ext) throws FileNotFoundException, IOException {
	List<double[][]> paths = new ArrayList<double[][]>();
	File dir = new File(dirpath);
	File [] files = dir.listFiles(new FilenameFilter() {
	    @Override
	    public boolean accept(File dir, String name) {
	        return name.endsWith(ext);
	    }
	});

	for (File xmlfile : files) {
	    paths.add(parseMatFile(xmlfile.toString()));
	}
	
	return paths;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
	String dpath = "/home/divoo/workspace/4th_year_workspace/Pattern_Project/Training data/car_data/";
	parseDirectory(dpath, ".mat");
    }
    
}
