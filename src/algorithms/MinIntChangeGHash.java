package algorithms;


import java.util.BitSet;

import main.Main;

import algorithms.datastructure.NumericalData;
import algorithms.datastructure.hashtable.HashElem;
import algorithms.datastructure.hashtable.MyHash;


/**
 * The algorithm MinIntChangeG-h designed in the submitted paper.
 * Extracts frequent interval pattern generators.
 * Subsumption checking use a hash table datastructure
 * 
 *
 */
public class MinIntChangeGHash extends Algorithm{

	// For Computation
	private double[][] minGenL, minGenR;
	private BitSet prime;
	// For results
	MyHash myHash = new MyHash();
	public int gensCount;

	public MinIntChangeGHash(NumericalData d, int minSup)
	{
		super(d,minSup);
	}

	public void start ()
	{
		long beginAt = System.currentTimeMillis();
		double[][] topPattern = getMinimalPattern();
		BitSet A = new BitSet();
		A.flip(0, objCount);
		process(0, 0, A, topPattern, clonePattern(topPattern));
		System.out.println(">> " + this.getClass().getCanonicalName() + "\t- " + gensCount +" generators - " +
				(System.currentTimeMillis() - beginAt) + "ms.");
		//myHash.printInfo();
	}


	protected void process(int att, int pos, BitSet C, double[][] D, double[][] minGen) 
	{
		if  (C.cardinality() < minSup) return;
		if (!addCandidate(minGen, C)) return;
		
		
		if (Main.printResult)
		{
			System.out.print(" Closed " + toStringPattern(D) +" ; ");
			System.out.println("Generator " +toStringPattern(minGen) + " --- " + toStringBitSet(C));
		}
		
		// for Trie version : if D is canonical, we could free main memory
		// of its attached generators 
		// (i.e. removing from the trie the associated word)
		
		
		gensCount++;	

		for (int i = attCount-1; i >= att; i--)
		{
			if (D[i][0] == D[i][1])  continue;
			minGenL  = clonePattern(minGen);
			minGenL[i][0]  = domains[i].higher(D[i][0]);
			if (minGenL[i][0] <= minGenL[i][1])
			{
				prime = prime(minGenL,C);
				process(i, 1, prime, prime(prime), minGenL);
			}

			if (!(i == att && pos == 1))
			{
				minGenR  = clonePattern(minGen);
				minGenR[i][1]  = domains[i].lower(D[i][1]);
				if (minGenR[i][0] <= minGenR[i][1])
				{
					prime = prime(minGenR,C);
					process(i, 0, prime, prime(prime), minGenR);
				}
			}

		}
	}

	
	protected boolean addCandidate(double[][] minGen, BitSet C) {
		HashElem node = new HashElem(minGen, C.cardinality(), C);
		if (this.myHash.containsSubsumeeOf(node))
			return false;
		myHash.add(node);
		return true;
	}
	
	
}