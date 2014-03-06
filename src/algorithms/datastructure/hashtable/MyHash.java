package algorithms.datastructure.hashtable;

import java.util.Enumeration;
import java.util.Vector;


/** 
 * A class that represents a hash table to store FIPGs.
 * 
 */
public class MyHash
{

	/**
	 * FIPGs are stored in this hash table. An element of the hash table
	 * is a list (vector), so if two objects have the same hash value, they are 
	 * stored in the same list.
	 */
	private Vector<Vector<HashElem>> hashTable = new Vector<Vector<HashElem>>();


	public void add(HashElem node)
	{
		int sizeV, j;
		int index = node.getHash();

		// increase the size of the hash table dynamically 
		if ((sizeV = this.hashTable.size()) < (index+1))
			for (j=0; j<(index+1-sizeV); ++j) this.hashTable.add(null);

		Vector<HashElem> pos = this.hashTable.get(index);
		if (pos==null) { pos = new Vector<HashElem>(); }
		pos.add(node);
		this.hashTable.set(index, pos);
	}


	// exists better?
	public boolean containsSubsumeeOf(HashElem node)
	{
		final int index = node.getHash();
		int sizeV, j;

		// increase the size of the hash table dynamically
		if ((sizeV = this.hashTable.size()) < (index+1)) 
		{
			for (j=0; j<(index+1-sizeV); ++j)
				this.hashTable.add(null);
			return false;
		}

		Vector<HashElem> pos = this.hashTable.get(index);
		if (pos == null) {
			return false;
		}
		// else

		for (Enumeration<HashElem> e = pos.elements(); e.hasMoreElements(); )
		{
			HashElem elem = e.nextElement();
			if (elem.supp == node.supp)
				if (isSubsummerOf(node.intent, elem.intent))
					return true;
		}
		return false;
	}

	public static boolean isSubsummerOf(double[][] intent, double[][] intent2) {
		// => say that intent2 has larger intervals
		// => find a contradiction.
		for (int i = 0; i < intent2.length; i++) {
			if(intent[i][0] < intent2[i][0])
				return false;
			if(intent[i][1] > intent2[i][1])
				return false;
		}
		return true;
	}


}
