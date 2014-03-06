package main;

import algorithms.MinIntChangeC;
import algorithms.MinIntChangeGHash;
import algorithms.MinIntChangeGTrie;
import algorithms.datastructure.NumericalData;
import algorithms.datastructure.NumericalDataReader;


/***
 * This class is the main entry point.
 */

public class Main {

	// Relative support, between 0 and 1	
	static double minSuppRelative = 0; 
	// Absolute support
	static int minSup = 122;

	// the file containing the numerical data
	//static String filename = "./data/AP.dat";
	static String filename = "./data/BL.dat";
	//static String filename = "./data/BK.dat";
	
	// A basic structure allowing access to data properties (objects, attributes, values)
	static NumericalData dataStructure = null;
	
	
	// Set to true for printing the results.
	// Warning: this make worse the execution time, since printing in standard output each pattern 
	// when just generated is really not efficient in java.
	// Redirect output into a file is much more efficient.
	public static boolean printResult = false;
	

	public static void main(String[] args) throws Exception {
		
		/*** Read the data and minimum support ****/
		dataStructure = new NumericalDataReader(filename).getData();
		
		double[][] data =  dataStructure.values;
		System.out.println(">> Data read from file: "+  filename);
		if (minSuppRelative != -1)
			minSup = (int) ((double)data.length * minSuppRelative);
		System.out.println(">> Minimum support is " + minSup + "/"+ data.length );


		/**
		 * Now we can launch the different algorithms.
		 * Simply comment/un-comment the next lines to run particular algorithms
		 */
		
		/*** Frequent closed interval pattern extraction with MinIntChange****/
		new MinIntChangeC(dataStructure,minSup).start();

		/** Frequent interval patterns generators with algorithm MinIntChangeG-t **/
		MinIntChangeGTrie micgt = new MinIntChangeGTrie(dataStructure,minSup);
		micgt.start();
		
		/** Frequent interval patterns generators with algorithm MinIntChangeG-h **/
		//MinIntChangeGHash micgh = new MinIntChangeGHash(dataStructure,minSup);
		//micgh.start();
	}

}
