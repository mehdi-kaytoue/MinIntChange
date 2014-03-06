package algorithms.datastructure.trie;

import java.util.HashMap;
import java.util.Vector;

/**
 * Nodes of the prefix tree (trie).
 * 
 */
public class Node
{
	/**
	 * Each word is an extent (closed itemset) and we store its generators here
	 * need for fast subsumption checking.
	 */
	public Vector<double[][]> generators = new Vector<double[][]>();
	
	
   /**
    * The trie to which this node belongs.
    */
   private Trie trie;
   
   
   /**
    * Children of the node (stored in a hash).
    */
   private HashMap<Integer, Node> children;
   
   
   /**
    * Is this node a terminal node?
    */
   public boolean terminal;
   

   /**
    * Constructor. Children of a node are stored in a hashmap. 
    * @param trie The trie data structure to which this node belongs to.
    */
   Node(Trie trie) {
      this.trie         = trie;
      this.children     = new HashMap<Integer, Node>();
      this.terminal     = false;
   }
   
   
   /** 
    * Constructor. Create a node and set its value.
    * 
    * @param trie The trie data structure to which this node belongs to.
    * @param value Value of the newly created node.
    */
   Node(Trie trie, int value) {
      this(trie);
   }


   /** 
    * Adds a node to the trie.
    * 
    * @param value Value of the new node.
    * @param trie The trie itself.
    * @return The new node that we just inserted, or the node if it already existed.
    */
   public Node add(Integer value, Trie trie) 
   {
      Node child = (Node)children.get(value);
      if (child == null)                  // if it doesn't exist yet
      {
         child = new Node(this.trie, value.intValue());
         children.put(value, child);
         trie.incNodeCounter();
      }
      // else, if it already exists => just send it back
      return child; 
   }


   /** 
    * Get all the children of a node.
    *  
    * @return Children of a node in a hashmap.
    */
   public HashMap<Integer, Node> getChildren() {
      return children;
   }
   

   /** 
    * Makes a node terminal (that is: it's the end of a word).
    * @param trie The trie itself (bacause we need to increment the wordcount).
    */
   public void setTerminal(Trie trie) 
   {
      if (terminal == false) {
         terminal = true;
         trie.incWordCounter();
      }
   }
   
   public void addGenerator(double[][] gen)
   {
	   this.generators.add(gen);
   }
}
