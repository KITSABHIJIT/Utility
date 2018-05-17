package com.test.practice.dataStructure;

import java.util.BitSet;

/**
 * 
 * @author RoyAb001
 *
 * The BitSet class implements a group of bits or flags that can be set and cleared individually.
 * This class is very useful in cases where you need to keep up with a set of Boolean values; 
 * you just assign a bit to each value and set or clear it as appropriate.
 */
public class BitSetDemo {
	public static void main(String args[]) {
	     BitSet bits1 = new BitSet(16);
	     BitSet bits2 = new BitSet(16);
	      
	     // set some bits
	     for(int i=0; i<16; i++) {
	        if((i%2) == 0) bits1.set(i);
	        if((i%5) != 0) bits2.set(i);
	     }
	     System.out.println("Initial pattern in bits1: ");
	     System.out.println(bits1);
	     System.out.println("\nInitial pattern in bits2: ");
	     System.out.println(bits2);

	     // AND bits
	     bits2.and(bits1);
	     System.out.println("\nbits2 AND bits1: ");
	     System.out.println(bits2);

	     // OR bits
	     bits2.or(bits1);
	     System.out.println("\nbits2 OR bits1: ");
	     System.out.println(bits2);

	     // XOR bits
	     bits2.xor(bits1);
	     System.out.println("\nbits2 XOR bits1: ");
	     System.out.println(bits2);
	  }
}
