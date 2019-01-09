package com.test.practice.interview.programs;

public class PrintPattern {

	/*Write a program in Java to display the pattern like right angle triangle with a number
	 	1                                                                                
		12                                                                               
		123                                                                              
		1234                                                                             
		12345                                                                            
		123456                                                                           
		1234567                                                                          
		12345678                                                                         
		123456789                                                                        
		12345678910 
	 * */
	public static void pattern1(int level){
		for(int i=1;i<=level;i++){
			for(int j=1;j<=i;j++){
				System.out.print(j+" ");
			}
			System.out.println();
		}
	}

	/*Write a program in Java to make such a pattern like right angle triangle with a number which will repeat a number in a row.
	 	1
		22
		333
		4444 
	 */
	public static void pattern2(int level){
		for(int i=1;i<=level;i++){
			for(int j=1;j<=i;j++){
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}

	/*Write a program in Java to make such a pattern like right angle triangle with number increased by 1
		1
		2 3
		4 5 6
		7 8 9 10 
	 */
	public static void pattern3(int level){
		int counter=1;
		for(int i=1;i<=level;i++){
			for(int j=1;j<=i;j++){
				System.out.print(counter+" ");
				counter++;
			}
			System.out.println();
		}
	}

	/*Write a program in Java to make such a pattern like a pyramid with a number which will repeat the number in the same row
 		   1
	      2 2
	     3 3 3
	    4 4 4 4 
	 */
	public static void pattern4(int level){
		int position=level-1;

		for(int i=1;i<=level;i++){
			for(int j=position;j>0;j--){
				System.out.print(" ");
			}
			for(int k=1;k<=i;k++){
				System.out.print(k+" ");
			}
			System.out.println();
			position--;
		}
	}
	/*Write a program in Java to print the Floyd's Triangle
	 *  1                                                                                
		01                                                                               
		101                                                                              
		0101                                                                             
		10101
	 */
	public static void floydsTraingle(int level){
		for(int i=1;i<=level;i++){
			int p=(i%2==0)?1:0;
			int q=(i%2==0)?0:1;
			for(int k=1;k<=i;k++){
				System.out.print((k%2==0)?p+" ":q+" ");
			}
			System.out.println();
		}
	}

	/*Write a program in Java to display the pattern like a diamond.
	 *                                                                          
	 ***                                                                         
	 *****                                                                        
	 *******                                                                       
	 *********                                                                      
	 ***********                                                                     
	 *************                                                                    
	 ***********                                                                     
	 *********                                                                      
	 *******                                                                       
	 *****                                                                        
	 ***                                                                         
	 *                   
	 */
	public static void diamondPattern(int level){

		/*int position=level-1;
			for(int i=1;i<=level;i++){
				for(int j=position;j>0;j--){
					System.out.print(" ");
				}
				for(int k=1;k<=i;k++){
					System.out.print("* ");
				}
				System.out.println();
				position--;
			}
			position=level-1;
			for(int i=1;i<=level;i++){
				for(int k=1;k<=i;k++){
					System.out.print(" ");
				}
				for(int j=0;j<position;j++){
					System.out.print("* ");
				}

				System.out.println();
				position--;

		}*/

		int i,j;  
		for(i=0;i<=level;i++)  
		{  
			for(j=1;j<=level-i;j++)  
				System.out.print(" ");  
			for(j=1;j<=2*i-1;j++)  
				System.out.print("*");  
			System.out.print("\n");  
		}  

		for(i=level-1;i>=1;i--)  
		{  
			for(j=1;j<=level-i;j++)  
				System.out.print(" ");  
			for(j=1;j<=2*i-1;j++)  
				System.out.print("*");  
			System.out.print("\n");  
		}  
	}
	/*Write a Java program to display Pascal's triangle.
	      1                                                                          
	     1 1                                                                         
	    1 2 1                                                                        
	   1 3 3 1                                                                       
	  1 4 6 4 1     
	 */
	public static void pascalsTraingle(int level){
		int value=1;
		for(int i=0;i<level;i++){
			
			for(int j=1;j<=level-i;j++){
				System.out.print(" ");
			}
			for(int k=0;k<=i;k++){
				if(i==0||k==0){
					value=1;
				}else{
					value=value*(i-k+1)/k;
				}
				System.out.print(value+" ");
			}
			System.out.println();
		}
	}
	
	/*Write a java program to generate a following *'s triangle
		******                                                   
		 *****                                                   
		  ****                                                   
		   ***                                                   
		    **                                                   
		     *  
	 */
	
	public static void starTraingle(int level){
		for(int i=level;i>0;i--){
			
			for(int j=0;j<level-i;j++){
				System.out.print(" ");
			}
			for(int k=0;k<i;k++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	/*Write a java program to generate a following @'s triangle
			  @                                                  
		     @@                                                  
		    @@@                                                  
		   @@@@                                                  
		  @@@@@                                                  
		 @@@@@@
	 */
	public static void atTraingle(int level){
		for(int i=1;i<=level;i++){
			
			for(int j=0;j<level-i;j++){
				System.out.print(" ");
			}
			for(int k=0;k<i;k++){
				System.out.print("@");
			}
			System.out.println();
		}
	}
	public static void main(String ...strings){
		//pattern1(10);
		//pattern2(10);
		//pattern3(10);
		//pattern4(10);
		//floydsTraingle(10);
		//diamondPattern(10);
		//pascalsTraingle(10);
		//starTraingle(10);
		atTraingle(10);
	}
}
