package com.epam.kiev.mergesort;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		int n = 100;
		int[] ai = new int[n];
		for (int i = 0; i < n; i++) {
			ai[i] = Float.floatToIntBits((float) Math.random());
		}
		long l = System.currentTimeMillis();
		ExecutorService service = Executors.newCachedThreadPool();
		int[] q = new Mergesort().sort(ai, Executors.newCachedThreadPool());
		service.shutdown();
		System.out.println(System.currentTimeMillis() - l + " ms");
//		 for(int x:q){
//		 System.out.println(x);
//		 }
	}
}
