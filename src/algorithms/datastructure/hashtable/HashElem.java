package algorithms.datastructure.hashtable;

import java.util.BitSet;



/** 
 * A class that represents an element in the hash table.
 *  
 */
public class HashElem
{
	public double[][] intent;
	public int supp;
	public BitSet extent;
	private int hash;
	private boolean isHashSet;
	public static final int HASH_SIZE = 100000;

	public HashElem(double[][] intent, int supp, BitSet image) {
		this.intent = intent;
		this.supp = supp;
		this.extent = image;
	}

	/**
	 * @return Returns the hash.
	 */
	public int getHash() 
	{
		if (!this.isHashSet) 
		{
			this.hash   = calculateHash(this.extent);
			this.isHashSet = true;
		}
		return this.hash;
	}

	/**
	 * Calculates a hash value for an IT-node.
	 * 
	 * Very important! It must return the same value for itemsets
	 * describing the same objects!
	 * 
	 * @param extent The extent of the IT-node.
	 * @return Hash value of the IT-nodes.
	 */
	public static int calculateHash(BitSet extent)
	{
		int sum = 0;
		for (int attr = extent.nextSetBit(0); attr >= 0; attr = extent.nextSetBit(attr+1))
			sum += attr; 
		return (Math.abs(sum) % HASH_SIZE);
	}
}
