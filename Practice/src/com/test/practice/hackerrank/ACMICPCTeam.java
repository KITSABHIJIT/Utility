package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ACMICPCTeam {
	// Complete the acmTeam function below.
	static int[] acmTeam(String[] topic) {
		int [] team =new int[2];
		Map<Integer,Integer> scoreMap=new HashMap<Integer,Integer>();
		int score=0;
		for(int i=0;i<topic.length;i++){
			char[] data =topic[i].toCharArray();
			for(int j=i+1;j<topic.length;j++){
				if(i!=j){
					int tempScore=0;
					char[] compareData=topic[j].toCharArray();
					for(int k=0;k<data.length;k++){
						if((Character.getNumericValue(data[k]) + Character.getNumericValue(compareData[k]))>=1){
							tempScore=tempScore+1;
						}
					}
					if(j==0) {
						score=tempScore;
						scoreMap.put(score, 1);
					}else {
						if(tempScore>=score) {
							score=tempScore;
							if(scoreMap.containsKey(score)) {
								scoreMap.put(score, (scoreMap.get(score)+1));
							}else {
								scoreMap.put(score, 1);
							}
						}
					}
				}

			}  
		}
		team[0]=score;
		team[1]=scoreMap.get(score);

		return team;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		String[] nm = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nm[0]);

		int m = Integer.parseInt(nm[1]);

		String[] topic = new String[n];

		for (int i = 0; i < n; i++) {
			String topicItem = scanner.nextLine();
			topic[i] = topicItem;
		}

		int[] result = acmTeam(topic);

		for (int i = 0; i < result.length; i++) {
			System.out.println(String.valueOf(result[i]));

			if (i != result.length - 1) {
				System.out.println("\n");
			}
		}

		scanner.close();
	}
}
