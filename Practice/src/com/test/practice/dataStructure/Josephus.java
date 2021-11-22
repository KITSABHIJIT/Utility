package com.test.practice.dataStructure;

// Name: J1-018-21
// Date: 11/16/21

import java.util.*;
import java.io.*;

public class Josephus
{
	private static String WINNER = "Josephus";

	public static void main(String[] args) throws FileNotFoundException
	{
		ListNode p = null;
		p = insert(p, "B");
		p = insert(p, "C");
		p = insert(p, "D");
		p = insert(p, "E");
		p = insert(p, "F");
		print(p);

		/* run numberCircle first with J_numbers.txt  */
		Scanner sc = new Scanner(System.in);
		System.out.print("How many names (2-20)? ");
		int n = Integer.parseInt(sc.next());
		System.out.print("How many names to count off each time?"  );
		int countOff = Integer.parseInt(sc.next());
		ListNode winningPos = numberCircle(n, countOff, "/Users/abhijit/Downloads/J_numbers.txt");
		System.out.println(winningPos.getValue() + " wins!");  

		/* run josephusCircle next with J_names.txt  */
		System.out.println("\n **  Now start all over. ** \n");
		System.out.print("Where should "+WINNER+" stand?  ");
		int winPos = Integer.parseInt(sc.next());        
		ListNode theWinner = josephusCircle(n, countOff, "/Users/abhijit/Downloads/J_names.txt", winPos);
		System.out.println(theWinner.getValue() + " wins!");  
	}

	public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
	{
		ListNode p = null;
		p = readNLinesOfFile(n, new File(filename));
		p = countingOff(p, countOff, n);
		return p;
	}

	public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
	{
		ListNode p = null;
		p = readNLinesOfFile(n, new File(filename));
		replaceAt(p, WINNER, winPos);
		p = countingOff(p, countOff, n);
		return p;
	}

	/* reads the names, calls insert(), builds the linked list.
	 */
	public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
	{
		Scanner sc = new Scanner(f);
		List<String> arrayStr= new ArrayList<String>();
		while(sc.hasNext())
			arrayStr.add(sc.next());
		
		// Create a circular linked list of 
        // size N. 
		ListNode head = insert(null,arrayStr.get(0)); 
		ListNode prev = head; 
        for(int i = 1; i < arrayStr.size(); i++) 
        { 
        	prev=insert(prev,arrayStr.get(i));
        } 
          
        // Connect last node to first 
        prev.setNext(head); 
        return prev;
	}

	/* helper method to build the list.  Creates the node, then
      inserts it in the circular linked list.
	 */
	public static ListNode insert(ListNode p, Object obj)
	{      
		if(p == null)
		{
			p = new ListNode(obj, null);
			p.setNext(p); 
			return p;        
		}
		else
		{
			ListNode i = new ListNode(obj, p.getNext());
			p.setNext(i);
			return i;
		}      
	}

	/* Runs a Josephus game, counting off and removing each name. Prints after each removal.
      Ends with one remaining name, who is the winner. 
	 */
	public static ListNode countingOff(ListNode head, int m, int n)
	{
		/* while only one node is left in the 
        linked list*/
		ListNode ptr1 = head, ptr2 = head; 
          
        while(ptr1.getNext() != ptr1) 
        { 
        	print(ptr1);
            // Find m-th node 
            int count = 1; 
            while(count != m) 
            { 
                ptr2 = ptr1; 
                ptr1 = ptr1.getNext(); 
                count++; 
            } 
              
            /* Remove the m-th node */
            ptr2.setNext(ptr1.getNext());
            ptr1 = ptr2.getNext(); 
            
        } 
        print(ptr1);
        return ptr1;
	}

	/* removes the node after counting off count-1 nodes.
	 */
	public static ListNode remove(ListNode p, int count)
	{
		return null;
	}

	/* prints the circular linked list.
	 */
	public static void print(ListNode p)
	{
		ListNode pointer = p.getNext();
		while(pointer != p)
		{
			System.out.print(pointer.getValue() + " ");
			pointer = pointer.getNext();
		}
		System.out.println(pointer.getValue());
	}

	/* replaces the value (the String) at the winning node.
	 */
	public static void replaceAt(ListNode p, Object obj, int pos)
	{

	}
}
/********************

 B C D E F
 How many names (2-20)? 5
 How many names to count off each time? 2
 1 2 3 4 5
 3 4 5 1
 5 1 3
 3 5
 3
 3 wins!

 **  Now start all over. ** 

 Where should Josephus stand?  3
 Michael Hannah Josephus Ruth Matthew
 Josephus Ruth Matthew Michael
 Matthew Michael Josephus
 Josephus Matthew
 Josephus
 Josephus wins!

  ----jGRASP: operation complete.

 ******************/