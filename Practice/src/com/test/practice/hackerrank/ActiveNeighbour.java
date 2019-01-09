package com.test.practice.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class ActiveNeighbour {
	 public List<Integer> cellCompete(int[] states, int days)
	    {
	    // WRITE YOUR CODE HERE
	        List<Integer> currentDayCompleteCell =new ArrayList<Integer>();
	        for(int i:states){
	            currentDayCompleteCell.add(i);
	        }
	        for(int i=1;i<=days;i++){
	        	List<Integer> nextDayCompleteCell =new ArrayList<Integer>();
	            for(int j=0;j<currentDayCompleteCell.size();j++){
	                if(j==0){
	                    nextDayCompleteCell.add((currentDayCompleteCell.get(j+1)==0)?0:1);
	                }else if(j==(currentDayCompleteCell.size()-1)){
	                    nextDayCompleteCell.add((currentDayCompleteCell.get(j-1)==0)?0:1);
	                }else{
	                    if(currentDayCompleteCell.get(j-1)==currentDayCompleteCell.get(j+1)){
	                        nextDayCompleteCell.add(0);
	                    }else{
	                        nextDayCompleteCell.add(1);
	                    }
	                }
	            }
	            currentDayCompleteCell=nextDayCompleteCell;
	        }
	        return currentDayCompleteCell;
	    }
	  // METHOD SIGNATURE ENDS
	 
	 public static void main(String ...strings) {
		 ActiveNeighbour activeNeighbour=new ActiveNeighbour();
		 int[] intArray = new int[]{ 1,0,0,0,0,1,0,0}; 
		 System.out.println(activeNeighbour.cellCompete(intArray, 1));
	 }
}
