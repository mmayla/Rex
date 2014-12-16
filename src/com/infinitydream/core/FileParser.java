package com.infinitydream.core;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLDouble;

public class FileParser {
    public static double[][] parseMatFile(String path) throws FileNotFoundException, IOException {
	MatFileReader fr = new MatFileReader(path);
	double[][] data = ((MLDouble)fr.getMLArray( "x" )).getArray();
	return data;
    }
}
