package com.epam.kiev.bank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	
	static int rand(){				
		return (int)(Math.random()*10000);	
	}	
	
	public static void main(String[] args) {	
		final Bank bank = new Bank();
		for (int i = 0; i < 1000; i++) {
			bank.getAccounts().add(new Account(rand()));
		}
		System.out.println(bank);
		System.out.println(bank.getAmountOfMoney() + " $$$");
		ExecutorService service = Executors.newCachedThreadPool();
		long l = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			service.submit(new Runnable() {				
				@Override
				public void run() {
					bank.rendomTransfer();
				}
			});
		}
		service.shutdown();		
		try {
			service.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		System.out.println(bank.getAmountOfMoney() + " $$$");
		System.out.println(System.currentTimeMillis() - l + " ms");
		System.out.println(bank);
		
	}
}
