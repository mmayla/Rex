package com.infinitydream.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLArray;
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

	List<String> filepaths = new ArrayList<String>();
	for (File xmlfile : files) {
	    String pth = xmlfile.toString();
	    filepaths.add(pth);
	}
	
	Collections.sort(filepaths);
	
	for(String path : filepaths) {
	    paths.add(parseMatFile(path));
	}
	
	return paths;
    }
    
    public static List<String> parseScriptFile(String fpath) throws FileNotFoundException {
	List<String> paths = new ArrayList<String>();
	Scanner sc = new Scanner(new File(fpath));
	while(sc.hasNextLine()) {
	    paths.add(sc.nextLine().trim());
	}
	return paths;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
	String dpath = "/home/divoo/workspace/4th_year_workspace/Pattern_Project/Training data/testlabels.mat";
	
    }
    
}
