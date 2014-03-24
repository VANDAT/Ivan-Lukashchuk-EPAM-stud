package com.epam.kiev.sinsum;

public class Main {
	public static void main(String[] args) {
		SinSumVJ sinSum = new SinSumVJ();		
		long l = System.currentTimeMillis();
		
		System.out.println(sinSum.sinSum(1000000, 4));		
		
		System.out.println(System.currentTimeMillis() - l + " ms");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SinSumES sinSum1 = new SinSumES();		
		long l1 = System.currentTimeMillis();
		
		System.out.println(sinSum1.sinSum(1000000, 4));		
		
		System.out.println(System.currentTimeMillis() - l1 + " ms");
	}
}
