package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class QueensAttack {
	static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
		Set<String> possibleAttacks=new HashSet<String>();
		Set<String> obstaclePoints=new HashSet<String>();

		for(int i=0;i<obstacles.length;i++) {
			obstaclePoints.add(obstacles[i][0]+" "+obstacles[i][1]);
		}
		
		boolean xPlusAttacked=false,xMinusAttacked=false;
		boolean yPlusAttacked=false,yMinusAttacked=false;
		boolean z1PlusAttacked=false,z1MinusAttacked=false;
		boolean z2PlusAttacked=false,z2MinusAttacked=false;
		
		boolean xPlusCompleted=false,xMinusCompleted=false;
		boolean yPlusCompleted=false,yMinusCompleted=false;
		boolean z1PlusCompleted=false,z1MinusCompleted=false;
		boolean z2PlusCompleted=false,z2MinusCompleted=false;
		
		for(int i=1;i<=n;i++) {
			if(r_q+i<=n && c_q+i<=n && !z1PlusAttacked) {
				String node=(r_q+i)+" "+(c_q+i);
				if(obstaclePoints.contains(node)) {
					z1PlusAttacked=true;
				}else {
					possibleAttacks.add((r_q+i)+" "+(c_q+i));
				}
			}else {
				z1PlusCompleted=true;
			}

			if(r_q-i>0 && c_q-i>0 && !z1MinusAttacked) {
				String node=(r_q-i)+" "+(c_q-i);
				if(obstaclePoints.contains(node)) {
					z1MinusAttacked=true;
				}else {
					possibleAttacks.add(node);
				}
			}else {
				z1MinusCompleted=true;
			}

			if(r_q-i>0 && c_q+i<=n && !z2PlusAttacked) {
				String node=(r_q-i)+" "+(c_q+i);
				if(obstaclePoints.contains(node)) {
					z2PlusAttacked=true;
				}else {
					possibleAttacks.add(node);
				}
			}else {
				z2PlusCompleted=true;
			}

			if(r_q+i<=n && c_q-i>0 && !z2MinusAttacked) {
				String node=(r_q+i)+" "+(c_q-i);
				if(obstaclePoints.contains(node)) {
					z2MinusAttacked=true;
				}else {
					possibleAttacks.add(node);
				}
			}else {
				z2MinusCompleted=true;
			}

			if(r_q+i<=n && !yMinusAttacked) {
				String node=(r_q+i)+" "+(c_q);
				if(obstaclePoints.contains(node)) {
					yMinusAttacked=true;
				}else {
					possibleAttacks.add(node);
				}
			}else {
				yMinusCompleted=true;
			}

			if(r_q-i>0 && !yPlusAttacked) {
				String node=(r_q-i)+" "+(c_q);
				if(obstaclePoints.contains(node)) {
					yPlusAttacked=true;
				}else {
					possibleAttacks.add(node);
				}
			}else {
				yPlusCompleted=true;
			}

			if(c_q+i<=n && !xPlusAttacked) {
				String node=(r_q)+" "+(c_q+i);
				if(obstaclePoints.contains(node)) {
					xPlusAttacked=true;
				}else {
					possibleAttacks.add(node);
				}
			}else {
				xPlusCompleted=true;
			}

			if(c_q-i>0 && !xMinusAttacked) {
				String node=(r_q)+" "+(c_q-i);
				if(obstaclePoints.contains(node)) {
					xMinusAttacked=true;
				}else {
					possibleAttacks.add(node);
				}
			}else {
				xMinusCompleted=true;
			}
			
			if(xPlusCompleted && xMinusCompleted
					&& yPlusCompleted && yMinusCompleted
					&& z1PlusCompleted && z1MinusCompleted
					&& z2PlusCompleted && z2MinusCompleted) {
				break;
			}
		}

		return possibleAttacks.size();
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		String[] nk = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nk[0]);

		int k = Integer.parseInt(nk[1]);

		String[] r_qC_q = scanner.nextLine().split(" ");

		int r_q = Integer.parseInt(r_qC_q[0]);

		int c_q = Integer.parseInt(r_qC_q[1]);

		int[][] obstacles = new int[k][2];

		for (int i = 0; i < k; i++) {
			String[] obstaclesRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int j = 0; j < 2; j++) {
				int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
				obstacles[i][j] = obstaclesItem;
			}
		}

		int result = queensAttack(n, k, r_q, c_q, obstacles);

		System.out.println(String.valueOf(result));


		scanner.close();
	}
}
