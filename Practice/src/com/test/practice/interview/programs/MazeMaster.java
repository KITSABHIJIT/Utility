package com.test.practice.interview.programs;

//name:    date:
import java.util.*;
import java.io.*;
public class MazeMaster
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the maze's filename (no .txt): ");
		//Maze m = new Maze(sc.next()+".txt");

		char[][] retArr = buildCharArr(sc.next());
		Maze m = new Maze(retArr);
		m.display();      
		System.out.println("Options: ");
		System.out.println("1: Mark all dots.");
		System.out.println("2: Mark all dots and display the number of recursive calls.");
		System.out.println("3: Mark only the correct path.");
		System.out.println("4: Mark only the correct path. If no path exists, say so.");
		System.out.println("5: Mark only the correct path and display the number of steps.\n\tIf no path exists, say so.");
		System.out.print("Please make a selection: ");
		m.solve(sc.nextInt());
		m.display();      //display solved maze
	} 

	//take in a filename, and return a char[][]
	public static char[][] buildCharArr(String fileName)
	{
		try{
			Scanner in = new Scanner(new File(fileName + ".txt"));
			int numRows = in.nextInt();
			int numColumns = in.nextInt();
			char[][] grid = new char[numRows][numColumns];
			for(int i = 0; i < numRows; i++){
				grid[i] = in.next().toCharArray();
			}
			return grid;
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found.");
			System.exit(0); // may be changed later
		}
		return new char[0][0];
	}
}


class Maze1
{
	//Constants
	private final char WALL = 'W';
	private final char DOT = '.';
	private final char START = 'S';
	private final char EXIT = 'E';
	private final char TEMP = 'o';
	private final char PATH = '*';
	//instance fields	
	private char[][] maze;
	private int startRow, startCol;
	private boolean S_Exists=false, E_Exists=false;
	//constructors

	/* 
	 * EXTENSION 
	 * This is a no-arg constructor that generates a random maze
	 */
	public Maze1()
	{

	}
	///Users/abhijit/maze1
	/* 
	 * Copy Constructor  
	 */
	public Maze1(char[][] inCharArr)  
	{
		this.maze = inCharArr;
		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze[i].length; j++){
				if(maze[i][j] == this.START){
					this.S_Exists = true;
					this.startRow = i;
					this.startCol = j;
				}
				else if(maze[i][j] == this.EXIT){
					this.E_Exists = true;
				}
			}
		}
	} 
	/* 
	 * Use a try-catch block
	 * Use next(), not nextLine()  
	 */
	public Maze1(String fileName)    
	{
		try{
			Scanner in = new Scanner(new File(fileName));
			int numRows = in.nextInt();
			int numColumns = in.nextInt();
			char[][] grid = new char[numRows][numColumns];
			for(int i = 0; i < numRows; i++){
				grid[i] = in.next().toCharArray();
			}
			this.maze = grid;
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found.");
			System.exit(0); // may be changed later
		}
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
			System.out.println();
		}
		System.out.println();
	}
	public void solve(int n){

		switch(n)
		{
		case 1:
		{
			markAll(startRow, startCol);
			break;
		}
		case 2:
		{
			int count = markAllAndCountRecursions(startRow, startCol);
			System.out.println("Number of recursions = " + count);
			break;
		}
		case 3:
		{
			markTheCorrectPath(startRow, startCol);
			break;
		}
		case 4:         //use mazeNoPath.txt 
		{
			if( !markTheCorrectPath(startRow, startCol) )
				System.out.println("No path exists."); 
			break;
		}
		case 5:
		{
			if( !markCorrectPathAndCountSteps(startRow, startCol, 0) )
				System.out.println("No path exists."); 
			break;
		}
		default:
			System.out.println("File not found");   
		}
	}
	/* 
	 * From handout, #1.
	 * Fill the maze, mark every step.
	 * This is a lot like AreaFill.
	 */ 
	private void markAll(int r, int c)
	{
		if(this.S_Exists && this.E_Exists && r < maze.length && r >= 0 && c < maze[r].length && c >= 0 && (this.DOT == maze[r][c] || this.START == maze[r][c] || this.EXIT == maze[r][c])){
			if(this.START != maze[r][c] && this.EXIT != maze[r][c])
				maze[r][c] = this.PATH;
			markAll(r+1, c);
			markAll(r-1, c);
			markAll(r, c+1);
			markAll(r, c-1);
		}
	}
	/* 
	 * From handout, #2.
	 * Fill the maze, mark and count every recursive call as you go.
	 * Like AreaFill's counting without a static variable.
	 */ 
	private int markAllAndCountRecursions(int r, int c)
	{
		if(this.S_Exists && this.E_Exists && r < maze.length && r >= 0 && c < maze[r].length && c >= 0 && (this.DOT == maze[r][c] || this.START == maze[r][c] || this.EXIT == maze[r][c])){
			if(this.EXIT == maze[r][c]){
				return 0; 
			}
			if(this.START == maze[r][c]){
				return markAllAndCountRecursions(r, c+1) + markAllAndCountRecursions(r, c-1) + markAllAndCountRecursions(r+1, c) + markAllAndCountRecursions(r-1, c);
			}
			maze[r][c] = this.PATH;
			return 1 + markAllAndCountRecursions(r, c+1) + markAllAndCountRecursions(r, c-1) + markAllAndCountRecursions(r+1, c) + markAllAndCountRecursions(r-1, c);
		}
		return 0;
	}
	/* 
	 * From handout, #3.
	 * Solve the maze, OR the booleans, and mark the path through it with an asterisk
	 * Recur until you find E, then mark the True path.
	 */ 
	private boolean markTheCorrectPath(int r, int c)
	{
		if(r >= 0 && r < maze.length && c >= 0 && c < maze[r].length){
			if(maze[r][c] == this.DOT || maze[r][c] == this.START){
				maze[r][c] = this.TEMP;
				if(markTheCorrectPath(r-1, c) || markTheCorrectPath(r+1, c) || markTheCorrectPath(r, c+1) || markTheCorrectPath(r, c-1)){
					maze[r][c] = this.PATH;
					return true;
				}
				else{
					maze[r][c] = this.DOT;
					return false;
				}
			}
			if(maze[r][c] == this.EXIT){
				return true;
			}
		}
		return false;
	}
	/*  4   Mark only the correct path. If no path exists, say so.
    Hint:  the method above returns the boolean that you need. */


	/* 
	 * From handout, #5.
	 * Solve the maze, mark the path, count the steps. 	 
	 * Mark only the correct path and display the number of steps.
	 * If no path exists, say so.
	 */ 	
	private boolean markCorrectPathAndCountSteps(int r, int c, int count)
	{
		if(r >= 0 && r < maze.length && c >= 0 && c < maze[r].length){
			if(maze[r][c] == this.DOT || maze[r][c] == this.START){
				maze[r][c] = this.TEMP;
				if(markCorrectPathAndCountSteps(r-1, c, count+1) || markCorrectPathAndCountSteps(r+1, c, count+1) || markCorrectPathAndCountSteps(r, c+1, count+1) || markCorrectPathAndCountSteps(r, c-1, count+1)){
					maze[r][c] = this.PATH;
					return true;
				}
				else{
					maze[r][c] = this.DOT;
					return false;
				}
			}
			if(maze[r][c] == this.EXIT){
				System.out.println("Number of steps = " + count + "\n");
				return true;
			}
		}
		return false;
	}
}