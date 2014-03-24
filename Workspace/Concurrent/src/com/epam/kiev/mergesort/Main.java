package com.epam.kiev.mergesort;

import java.util.concurrent.ExecutorService;

public class Main {
	public static void main(String[] args) {
		int n = 1000000;
		int[] ai = new int[n];
		for (int i = 0; i < n; i++) {
			ai[i] = Float.floatToIntBits((float) Math.random());
		}
		long l = System.currentTimeMillis();
		int[] q = new Mergesort().sort(ai, (ExecutorService) null, 0);
		System.out.println(System.currentTimeMillis() - l + " ms");
		// for(int x:q){
		// System.out.println(x);
		// }
	}
}
