package com.test.practice.collections;

//Name:      
//Date:
//This program takes a text file, creates an index (by line numbers)
//for all the words in the file and writes the index
//into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker
{
	public static void main(String[] args) throws IOException
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nEnter input file name: ");
		String inFileName = keyboard.nextLine().trim();
		Scanner inputFile = new Scanner(new File(inFileName));
		String outFileName = "fishIndex.txt";
		PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
		indexDocument(inputFile, outputFile);
		inputFile.close(); 						
		outputFile.close();
		System.out.println("Done.");
	}

	public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
	{
		DocumentIndex index = new DocumentIndex();
		String line = null;
		int lineNum = 0;
		while(inputFile.hasNextLine())
		{
			lineNum++;
			index.addAllWords(inputFile.nextLine(), lineNum);
		}
		for(IndexEntry entry : index)
			outputFile.println(entry);
	}   
}

class DocumentIndex extends ArrayList<IndexEntry>
{
	/* EXTENSION ONLY */
	public static int linearCount = 0;//every time in the linearSearch a comparison is done, increase this variable.                    
	public static int binaryCount = 0;//every time in the binarySearch a comparison is done, increase this variable. 

	//constructors
	public DocumentIndex()
	{
		super(); //Calls default constructor of ArrayList
	}
	public DocumentIndex(int size)
	{
		super(size); //Calls default constructor of ArrayList with argument size
	}

	/** extracts all the words from str, skipping punctuation and whitespace 
   and for each word calls addWord().  In this situation, a good way to 
   extract while also skipping punctuation is to use String's split method, 
   e.g., str.split("[., \"!?]")       
	 */
	public void addAllWords(String str, int lineNum) 
	{
		String[] strArray = str.split("[., \"!?]"); //Splits the array except for blank lines
		for(String s : strArray) //For every String sorted
			if(!s.equals("")) //If it is not an empty line
				addWord(s, lineNum); //Add this to the num position
		/*  ignore the next 4 lines, unless you are coding the EXTENSION  */   
		if( linearCount > 0 )
			System.out.println("total number of comparisons using linear search " + linearCount);
		if( binaryCount > 0 )
			System.out.println("total number of comparisons using binary search: " + binaryCount);     
	}

	/** calls foundOrInserted, which returns a position.  At that position,  
    updates that IndexEntry's list of line numbers with lineNum. 
    EXTENSION: calls foundOrInsertedBinary instead
	 */
	public void addWord(String word, int lineNum)
	{
		boolean found = false; //Set a boolean to false
		IndexEntry ie = new IndexEntry(word); //Instantiate a new IndexEntry object holding String str
		ie.add(lineNum); //Add a number to the IndexEntry
		for(IndexEntry i : this) //For all the IndexEntries in this current object
			if(word.toUpperCase().equals(i.getWord())) //If the word is equal to the IndexEntry word
			{
				i.add(lineNum); //Add the number to the IndexEntry
				found = true; //Set the boolean to true
			}
		if(found == false && size() > 0) //Ignore if the word was found
		{
			if(get(size()-1).getWord().compareTo(word.toUpperCase()) < 0) //Compares str to the last entry
			{
				add(ie); //DocumentIndex adds the IndexEntry
				return; //Ends the method
			} 
			for(IndexEntry i : this) //For all IndexEntries in this DocumentIndex
			{
				if(i.getWord().compareTo(word.toUpperCase()) > 0) //Selectively sorts the list
				{
					add(indexOf(i), ie); //Adds the IndexEntry to the object in the alphabetic position
					return; //Quit the method to lessen runtime if possible
				}
			}
		}
		else if(found == false) //If and only if boolean found is false
			add(ie); //Add the IndexEntry to the current DocumentIndex
	}

	/** linear-search this DocumentIndex, comparing word to the words in the 
     IndexEntry objects in this list, looking for the correct position of 
     word. If an IndexEntry with word is not already in that position, the 
     method creates and inserts a new IndexEntry at that position. The 
     method returns the position of either the found or the inserted 
     IndexEntry.
	 */
	private int foundOrInserted(String word)
	{
		IndexEntry temp = new IndexEntry(word); //Instantiates IndexEntry with word
		for(IndexEntry element : this) //For every IndexEntry in this DocumentIndex
		{
			if(element == get(indexOf(element))) //If it is equal to the DocumentIndexes word
				return 0; //Return the position 0 which means nothing in this case
		}

		for(IndexEntry element : this) //For every IndexEntry in the same DocumentIndex again
		{
			if(word.compareTo(element.getWord()) > 0) //If the word is of higher ACSII value
			{
				add(indexOf(element) - 1, temp); //Add the String to that position in DocumentIndex
				return indexOf(element) - 1; //Return that position
			}
		}
		return -1; //This generally should not reach this line
	}
	/** EXTENSION
    binary-search this DocumentIndex comparing word to the words in the 
    IndexEntry objects in this list, looking for the correct position of 
    word. If the IndexEntry for that word is already there, return its  
    position. If an IndexEntry with word is not in the list, then instantiate
    and insert a new IndexEntry at the correct position. Then return that   
    position.   
	 */
	public int foundOrInsertedBinary(String word)
	{
		return 0;
	}
}

class IndexEntry implements Comparable<IndexEntry>
{
	//fields  
	private String word;
	private ArrayList<Integer> numsList;
	//constructors
	public IndexEntry(String s)
	{
		word = s.toUpperCase(); //Makes the word capital in the constructor itself
		numsList = new ArrayList<Integer>(); //Default ArrayList of size 10
	}
	/**  appends num to numsList, but only if it is not already in that list.    
	 */
	public void add(int num)
	{
		if(!numsList.contains(num)) //If the numsList has not the number
			numsList.add(num); //Add it
	}
	/** this is a standard accessor method  */
	public String getWord()
	{
		return word; //Simply returns the word in the private data field
	}
	/**  returns a string representation of this Index Entry.  */
	public String toString()
	{
		String s = word + " "; //Instantiates return String with the current word
		for(Integer i : numsList) //For every integer in the number ArrayList
			s += i + ", "; //Add it and add a ,\s
		s = s.substring(0, s.length()-2); //Remove the extraneous ,\s
		return s; //Return the String representation of the Index
	}

	public int compareTo(IndexEntry entry)
	{
		return word.compareTo(entry.toString()); //Compares the String with the String of the IndexEntry
	}


}

/******  SAMPLE RUN  ****

Enter input file name: fish.txt
Done.

 ******************/


/******  EXTENSION  **********

Enter input file name: fish.txt
total number of comparisons using linear search 2
total number of comparisons using linear search 6
total number of comparisons using linear search 10
total number of comparisons using linear search 13
total number of comparisons using linear search 13
total number of comparisons using linear search 17
total number of comparisons using linear search 22
total number of comparisons using linear search 29
total number of comparisons using linear search 36
total number of comparisons using linear search 36
total number of comparisons using linear search 54
total number of comparisons using linear search 73
total number of comparisons using linear search 73
total number of comparisons using linear search 110
total number of comparisons using linear search 146
total number of comparisons using linear search 179
Done.

----jGRASP: operation complete.

----jGRASP exec: java IndexMaker_teacher

Enter input file name: fish.txt
total number of comparisons using binary search: 4
total number of comparisons using binary search: 11
total number of comparisons using binary search: 16
total number of comparisons using binary search: 22
total number of comparisons using binary search: 22
total number of comparisons using binary search: 26
total number of comparisons using binary search: 30
total number of comparisons using binary search: 37
total number of comparisons using binary search: 44
total number of comparisons using binary search: 44
total number of comparisons using binary search: 58
total number of comparisons using binary search: 75
total number of comparisons using binary search: 75
total number of comparisons using binary search: 98
total number of comparisons using binary search: 119
total number of comparisons using binary search: 141
Done.
 ****************/
