package algorithms;

import java.util.BitSet;

import algorithms.datastructure.NumericalData;


import main.Main;


/**
 * The algorithm MinIntChange designed in the submitted paper.
 * Extracts frequent closed interval patterns
 * 
 *
 */
public class MinIntChangeC extends Algorithm{

	// For computation
	double[][] patternL,patternR;
	protected BitSet prime;
	
	
	// For results
	public int closedCount = 0;


	public MinIntChangeC(NumericalData d, int minSup)
	{
		super(d,minSup);
	}

	
	public void start ()
	{
		long beginAt = System.currentTimeMillis();
		double[][] topPattern = getMinimalPattern();
		BitSet A = new BitSet();
		A.flip(0, objCount);
		process(topPattern, 0, 0, A, topPattern);
		System.out.println(">> " + this.getClass().getCanonicalName() +"   - " + closedCount +" closed - " +
				(System.currentTimeMillis() - beginAt) + "ms. Compression rate:" + (1-((double)closedCount / numData.domainSize)));
	}


	/**
	 * 
	 * 
	 * @param B was previously generated
	 * @param att with a change at attribute att
	 * @param pos and position pos
	 * @param C = B^\square
	 * @param D = C^\square
	 */
	protected void process(double[][] B, int att, int pos, BitSet C, double[][] D) 
	{
		if (!isCanonical(B, D, att) || (C.cardinality() < minSup) ) return;

		if (Main.printResult)
			System.out.println("Closed " +toStringPattern(D) + " --- " + toStringBitSet(C));
		
		closedCount++;

		for (int i = att; i < attCount; i++) 
		{
			if (D[i][0] == D[i][1])  continue;
			if (!(i == att && pos == 1))
			{
				patternR = clonePattern(D);
				patternR[i][1] = domains[i].lower(D[i][1]);
				if (patternR[i][0] <= patternR[i][1])
				{
					prime = prime(patternR,C);
					process(patternR, i, 0, prime, prime(prime));
				}
			}
			patternL = clonePattern(D);
			patternL[i][0] = domains[i].higher(D[i][0]);
			if (patternL[i][0] <= patternL[i][1])
			{
				prime = prime(patternL,C);
				process(patternL, i, 1, prime, prime(prime));
			}
		}
	}


	/**
	 * Canonicity test
	 * 
	 * @param B has been generated with a minimal change
	 * @param D is the closure of B
	 * @param att the current attribute on which minimal change has been applied
	 * @return
	 */
	protected boolean isCanonical(double[][] B, double[][] D, int att) {
		for (int i = 0; i < att ; i++)
			if (B[i][0] != D[i][0] || (B[i][1] != D[i][1]))
				return false;
		return true;
	}
}