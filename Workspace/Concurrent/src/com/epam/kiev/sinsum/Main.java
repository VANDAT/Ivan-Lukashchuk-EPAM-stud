package com.epam.kiev.sinsum;

public class Main {
	public static void main(String[] args) {
		SinSum sinSum = new SinSum();		
		long l = System.nanoTime();
		
		System.out.println(sinSum.sinSum(20));
		
		System.out.println(System.nanoTime() - l + " ns");
	}
}
