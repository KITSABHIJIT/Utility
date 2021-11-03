package com.test.practice.interview.programs;

// Name: J1-018-21
// Date: 10/05/21
import java.util.Scanner;

public class MazeGrandMaster
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the maze's filename (no .txt): ");
		Maze1 m = new Maze1(sc.next());
		// Maze m = new Maze();    
		m.display();      
		System.out.println("Options: ");
		System.out.println("1: Mark all paths.");
		System.out.println("1: Find the shortest path\n\tIf no path exists, say so.");
		System.out.println("2: Mark only the shortest correct path and display the count of STEPs.\n\tIf no path exists, say so.");
		System.out.print("Please make a selection: ");
		m.solve(sc.nextInt());
	} 
}

class Maze
{
	//Constants
	private final char WALL = 'W';
	private final char DOT = '.';
	private final char START = 'S';
	private final char EXIT = 'E';
	private final char STEP = '*';
	//Instance Fields
	private char[][] maze;
	private int startRow, startCol;

	//constructors

	/* 
	 * EXTENSION 
	 * This no a arg constructor that generates a random maze
	 */
	public Maze()
	{

	}

	/* 
	 * Copy Constructor  
	 */
	public Maze(char[][] m)  
	{
		maze = m;
		for(int r = 0; r < maze.length; r++)
		{
			for(int c = 0; c < maze[0].length; c++)
			{ 
				if(maze[r][c] == START)      //identify start
				{
					startRow = r;
					startCol = c;
				}
			}
		}
	} 

	/* 
	 * Use a try-catch block
	 * Use next(), not nextLine()  
	 */
	public Maze(String filename)    
	{



	}

	public char[][] getMaze()
	{
		return maze;
	}

	public void display()
	{
		if(maze==null) 
			return;
		for(int a = 0; a<maze.length; a++)
		{
			for(int b = 0; b<maze[0].length; b++)
			{
				System.out.print(maze[a][b]);
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public void solve(int n)
	{
		switch(n)
		{    
		case 1:
		{   
			int shortestPath = findShortestLengthPath(startRow, startCol);
			if( shortestPath!=-1 )
				System.out.println("Sortest path is " + shortestPath);
			else
				System.out.println("No path exists."); 
			break;
		}   

		case 2:
		{
			String strShortestPath = findShortestPath(startRow, startCol);
			if( strShortestPath.length()!=0 )
			{
				//System.out.println("Sortest lenght path is: " + getPathLength(strShortestPath));
				System.out.println("  Sortest path is: " + strShortestPath);
				markPath(strShortestPath);
				display();  //display solved maze
			}
			else
				System.out.println("No path exists."); 
			break;
		}
		default:
			System.out.println("File not found");   
		}
	}

	/*  1   recur until you find E, then return the shortest path
     returns -1 if it fails
     precondition: Start can't match with Exit
	 */ 
	public int findShortestLengthPath(int r, int c)
	{
		return -1; //replace this with something ore useful
	}  

	/*  2   recur until you find E, then build the True path 
     use only the shortest path
     returns -1 if it fails
     precondition: Start can't match with Exit
	 */
	public String findShortestPath(int r, int c)
	{
		return ""; //replace this with something useful
	} 


	//a recursive method that takes an argument created by the method 2 in the form of 
	//((5,0),10),((5,1),9),((6,1),8),((6,2),7),((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),((4,7),0)
	//and it marks the actual path in the maze with STEP
	//precondition:   the String is either an empty String or one that has the correct format above
	//                the indexes must be correct for this method to work  
	public void markPath(String strPath)
	{

	}
	private static boolean isSafe(int[][] mat, boolean[][] visited, int x, int y) {
        return (x >= 0 && x < mat.length && y >= 0 && y < mat[0].length) &&
                mat[x][y] == 1 && !visited[x][y];
    }
}