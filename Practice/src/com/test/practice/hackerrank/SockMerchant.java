package com.test.practice.hackerrank;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class SockMerchant {
	static int sockMerchant(int n, int[] ar) {
		int pairCount=0;
		Map<Integer,Integer> colorCount = new HashMap<Integer,Integer>();
		for(int color:ar) {
			colorCount.put(color, (colorCount.containsKey(color))?(colorCount.get(color)+1):1);
		}
		Iterator<Entry<Integer, Integer>> colorCountIterator =colorCount.entrySet().iterator();
		while(colorCountIterator.hasNext()) {
			Map.Entry<Integer, Integer> entry= colorCountIterator.next();
			pairCount=pairCount+(entry.getValue()/2);
		}
		return pairCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);

        System.out.println(String.valueOf(result));

        scanner.close();
    }
}
