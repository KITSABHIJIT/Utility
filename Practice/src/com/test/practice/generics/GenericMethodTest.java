package com.test.practice.generics;

/**
 * 
 * 	Java Generic methods and generic classes enable programmers to specify, with a single method declaration, 
 	a set of related methods or, with a single class declaration, a set of related types, respectively.

	Generics also provide compile-time type safety that allows programmers to catch invalid types at compile time.

	Using Java Generic concept, we might write a generic method for sorting an array of objects, then 
	invoke the generic method with Integer arrays, Double arrays, String arrays and so on, to sort the array elements.
 *
 */
public class GenericMethodTest {
	// generic method printArray                         
	public static < E > void printArray( E[] inputArray )
	{
		// Display array elements              
		for ( E element : inputArray ){        
			System.out.printf( "%s ", element );
		}
		System.out.println();
	}

	public static void main( String args[] )
	{
		// Create arrays of Integer, Double and Character
		Integer[] intArray = { 1, 2, 3, 4, 5 };
		Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
		Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

		System.out.println( "Array integerArray contains:" );
		printArray( intArray  ); // pass an Integer array

		System.out.println( "\nArray doubleArray contains:" );
		printArray( doubleArray ); // pass a Double array

		System.out.println( "\nArray characterArray contains:" );
		printArray( charArray ); // pass a Character array
	} 
}
