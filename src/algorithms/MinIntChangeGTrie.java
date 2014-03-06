package algorithms;

import java.util.BitSet;
import java.util.Vector;

import algorithms.datastructure.NumericalData;
import algorithms.datastructure.trie.Node;
import algorithms.datastructure.trie.Trie;


/**
 * The algorithm MinIntChangeG-t designed in the submitted paper.
 * Extracts frequent interval pattern generators.
 * Subsumption checking use a trie data structure
 * 
 *
 */
public class MinIntChangeGTrie extends MinIntChangeGHash {

	public int nbClosed = 0;
	Trie trie = new Trie();

	public MinIntChangeGTrie(NumericalData d, int minSup)
	{
		super(d, minSup);
	}

	@Override
	public void start ()
	{
		long beginAt = System.currentTimeMillis();
		double[][] topPattern = getMinimalPattern();
		BitSet A = new BitSet();
		A.flip(0, objCount);
		process(0, 0, A, topPattern, clonePattern(topPattern));
		System.out.println(">> " + this.getClass().getCanonicalName() + "\t- " + gensCount +" generators - " +
				nbClosed + " closed - " +  (System.currentTimeMillis() - beginAt) + "ms. Compression rate:" + (1-((double)gensCount / numData.domainSize)));

	}

	@Override
	protected boolean addCandidate(double[][] minGen, BitSet C) {

		Node n = trie.findWord(C);
		if (n == null)
		{
			nbClosed++;
			trie.add(C,minGen);// we store the generator associated to its image.
			return true;
		}
		Vector<double[][]> gens = n.generators;
		for (int i = 0; i < gens.size(); i++) 
			if (subsummes(minGen, gens.get(i)))
				return false;
		gens.add(minGen);
		
		return true;
	}


	private boolean subsummes(double[][] candidate, double[][] current)
	{
		for (int i = 0; i < current.length; i++)
			if (candidate[i][0]< current[i][0] || candidate[i][1]> current[i][1])
				return false;
		return true;
	}

}