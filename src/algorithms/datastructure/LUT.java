package algorithms.datastructure;

/**
 * Dynamic integer lookup-table. Returns a reference
 * to Integer objects. If you have to store/work with
 * lots of Integer-s, it may speed up the program
 * and reduce memory use. Increases the size of the lookup 
 * table automatically. 
 * Its size can also be reduced to a given size.
 * 
 */
public class LUT
{
   /**
	* Default size of the lookup table. This is the maximal number that can be
	* referenced by the lookup table. 
	*/
   private static int defaultSize = 300;
	
   /**
    * An Integer array containing references to Integer objects.
    */
   private static Integer lut[];             
   
   /**
    * Constructor. Creates a lookup table from which the maximal element ('max')
    * can still be retrieved. 
    * 
    * @param max The maximal element that we want to retrieve from the lookup table.
    */
   static 
   {
      LUT.lut = new Integer[LUT.defaultSize+1];             // for ex. max. element is 3, then we need to make this: [0][1][2][3]
      for (int i = 0; i<LUT.lut.length; ++i)
         LUT.lut[i] = new Integer(i);
   }

   /**
    * Get the reference to an Integer object.
    * 
    * @param i The integer whose reference we want to get.
    * @return An Integer reference that has the value of 'i'.
    */
   public static Integer getInteger(int i) {
      if (i >= LUT.lut.length) resize(i);
      return lut[i];
   }
   
   /**
    * Get the reference to an Integer object.
    * 
    * @param i An Integer whose reference we want to get.
    * @return An Integer reference that has the value of 'i'.
    */
   public static Integer getInteger(Integer i) {
      return LUT.getInteger(i.intValue());
   }

   /**
    * Resizes the lookup table (increases its size) by KEEPING former
    * references. New references are added to the end of the lookup table.
    * 
    * @param new_max The new maximal element.
    */
   private static void resize(int new_max) 
   {
      Integer[] new_lut = new Integer[new_max+1];
      System.arraycopy(lut,0, new_lut,0, lut.length);
      for (int i = lut.length; i<new_lut.length; ++i)
         new_lut[i] = new Integer(i);

      LUT.lut = new_lut;	// the lookup table will be this new array
   }


   /**
    * @return String representation of the lookup tabale. It mainly exists
    * for debug purposes.
 	*/
   public String toString()
   {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      for (int i = 0; i < LUT.lut.length; ++i)
         sb.append((i>0?", ":"")+i);
      sb.append("}");

      return sb.toString();
   }
}
