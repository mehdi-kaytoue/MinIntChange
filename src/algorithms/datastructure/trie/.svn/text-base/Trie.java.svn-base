package algorithms.datastructure.trie;

import java.util.BitSet;

import algorithms.datastructure.LUT;
import algorithms.datastructure.trie.Node;



/**
 * 
*/
public class Trie
{
   /**
    * Root element of the prefix tree (trie).
    */
   private Node root;
   
   /**
    * Number of nodes in the trie.
    */
   private int nodes;
   
   /**
    * Number of words in the trie.
    * Ex.: add {1,2,3} to an empty trie. Then nodes==3, words==1.
    */
   private int words;        
   
   
   /**
    * Counting the number of nodes in the trie. It is used to assign a unique
    * identifier for nodes.
    */
   protected int idCounter;

   /** 
    * Constructor, creates a new trie.
    */
   public Trie() {
      this.idCounter = 0;  // !!! it must be initialised before creating the first (root) node
      root = new Node(this);
      nodes = words = 0;
   }
   
   
   public int getSizeNodes() { return nodes; }

   /**
    * Increments the number of nodes by one. 
    */
   void incNodeCounter() { ++nodes; }
   
   /**
    * Decrements the number of nodes by one. 
    */
   void decNodeCounter() { --nodes; }

   /**
    * Returns the number of words in the trie.
    * 
    * @return Number of words in the trie.
    */
   public int getSizeWords() { return words; }

   /**
    * Increments the number of words by one. 
    */
   void incWordCounter() { ++words; }
   
   /**
    * Decrements the number of words by one. 
    */
   void decWordCounter() { --words; }

   
   
   public void add(BitSet word, double[][] gen)
   {
      Node curr = root;
      for(int i=word.nextSetBit(0); i>=0; i=word.nextSetBit(i+1)) 
         curr = insert(i, curr);
      curr.setTerminal(this);
      curr.generators.add(gen);
   }
   

   /** 
    * Inserts a node in the trie.
    * 
    * @param value Value of the node.
    * @param parent A node _under which_ we want to insert a new node.
    * @return The newly inserted child node.
    */
   private Node insert(int value, Node parent) {
      return parent.add(LUT.getInteger(value), this);
   }


   /**
    * Finds the last element (letter) of a sequence. Example: it gets {1,5,8},
    * and it returns the address of the node 8 (the path to 8 goes through
    * 1 and 5 of course). 
    * Warning! The input sequence can be anything! Maybe it's not a real word, 
    * i.e. maybe its last letter is not terminal!
    * If you want to find whole words, use the findWord() function instead. 
    * 
    * @param sequence The word whose last letter in the trie we want to locate.
    * @return The node in the trie which represents the last letter of the word.
    */
   public Node findLastLetter(BitSet sequence)
   {
      Node curr = root;
      for(int i = sequence.nextSetBit(0); (i>=0) && (curr!=null); i = sequence.nextSetBit(i+1)) 
         curr = (Node)(curr.getChildren().get(LUT.getInteger(i)));
      return curr;
   }
   
   public Node findWord(BitSet word) 
   {
      Node last = findLastLetter(word);
      
      if ((last == null) || (last.terminal == false)) return null;
      return last;
   }
}