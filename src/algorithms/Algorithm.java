package algorithms;

import java.util.BitSet;
import java.util.TreeSet;

import algorithms.datastructure.NumericalData;



/**
 * This abstract class represents an algorithm with common methods and initialization
 * 
 *
 */
public abstract class Algorithm {

	double [][] data;
	int objCount,attCount;
	int minSup = 1;
	// Each TreeSet stores the different and unique values of an attribute domain.
	// It is needed to operate in log(n) the procedure of minimal change 
	TreeSet<Double>[] domains; // Unique values only !

	NumericalData numData;

	/**
	 * Constructor
	 * @param data
	 * @param minSup
	 */
	public Algorithm(NumericalData  d, int minSup)
	{
		this.numData = d;
		this.data = d.values;
		this.objCount = data.length;
		this.attCount = data[0].length;
		this.minSup = minSup;
		initDomains();
	}
	
	public Algorithm(){}
	
	/**
	 * The algorithm. 
	 */ 	
	public abstract void start();
	
	
	/**
	 * The first operator of the Galois connection
	 * 
	 * @param D an interval pattern
	 * @param Z the set of object in which we know image of D is
	 * 
	 * @return the image of D
	 */
	public BitSet prime(double[][] D, BitSet Z)
	{
		boolean addObject;
		BitSet extent = new BitSet();
		for (int i = Z.nextSetBit(0); i >= 0; i = Z.nextSetBit(i+1)) {
			addObject = true;
			for (int j = 0; j < attCount && addObject ; j++) 
			{
				if (data[i][j] < D[j][0] || data[i][j] > D[j][1]) 
				{
					addObject=false;
					break;
				}
			}
			if (addObject) extent.set(i);
		}
		return extent;
	}

	/**
	 * The second operator of the Galois connection
	 * 
	 * @param extent a set of object
	 * @return its description
	 */
	public double[][] prime (BitSet extent)
	{
		int pos = extent.nextSetBit(0);
		double[][] prime = new double[attCount][2];

		for (int i = 0; i < prime.length; i++) {
			prime[i][0] = data[pos][i];
			prime[i][1] = data[pos][i];
		}

		for (int i = pos; i >= 0 && i < data.length; i = extent.nextSetBit(i+1))
			for (int j = 0; j < prime.length; j++) 
			{
				if (data[i][j] < prime[j][0]) prime[j][0] = data[i][j];
				if (data[i][j] > prime[j][1]) prime[j][1] = data[i][j];
			}
		return prime;
	}
	
	
	/**
	 * Initialize the list of ordered values for each attribute.
	 * Will be used for getting minimal changes. log(n) operations
	 */
	@SuppressWarnings("unchecked")
	public void initDomains()
	{
		double domainSize = 1;
		
		domains  = new TreeSet[attCount];
		for (int i = 0; i < attCount; i++) 
			domains[i] = new TreeSet<Double>();

		for (int i = 0; i < objCount; i++)
			for (int j = 0; j < attCount; j++)
				domains[j].add(data[i][j]);
		
		for (int i = 0; i < domains.length; i++) {
			domainSize *= (domains[i].size() * (domains[i].size()+1)/2); 
		}
		numData.domainSize = domainSize;
		
	}

	
	/**
	 * A set of object is represented by a bitset
	 * This method allows returns set of associated object labels.
	 * 
	 * @param bs bit set representation of an object set
	 * @return String representation of an object set
	 */
	public String toStringBitSet(BitSet bs)
	{
		String res = "Image: {";
		for(int i = bs.nextSetBit(0) ; i!=-1 ; i = bs.nextSetBit(i+1))
			res+= this.numData.lineId[i] +",";
		return res.substring(0,res.length()-1) + "}";
	}
	
	/**
	 * Builds a string representation <[x,x], ..., [x,x]> 
	 * for a given interval pattern
	 * @param pattern an interval pattern
	 * @return its string representation
	 */
	public String toStringPattern(double[][] pattern)
	{
		String res = "<";
		for (int i = 0; i < pattern.length; i++)
			res += "[" + pattern[i][0] + "," + pattern[i][1] + "]";
		return res + ">";
	}

	
	/**
	 * To make a clone (exact copy but not same reference) of a pattern
	 * 
	 * @param pattern the pattern to clone
	 * @return the clone
	 */
	public double[][] clonePattern (double[][] pattern){
		double[][] clone = new double[pattern.length][2];
		for (int i = 0; i < clone.length; i++) {
			clone[i][0] = new Double(pattern [i][0]);
			clone[i][1] = new Double(pattern [i][1]);
		}
		return clone;
	}

	/**
	 * Get most frequent pattern (it is both closed and generators)
	 * 
	 * @return minimal pattern w.r.t. subsumption, i.e. with largest intervals
	 */
	public double[][] getMinimalPattern()
	{
		double[][] topPattern = new double[attCount][2];
		for (int i = 0; i < topPattern.length; i++) 
		{
			topPattern[i][0] = domains[i].first();
			topPattern[i][1] = domains[i].last();
		}
		return topPattern;
	}
}
