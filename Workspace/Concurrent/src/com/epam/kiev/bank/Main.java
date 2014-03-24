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
		for (int i = 0; i < 10; i++) {
			bank.getAccounts().add(new Account(rand(), i));
		}
		System.out.println(bank);
		System.out.println(bank.getAmountOfMoney() + " $$$");
		ExecutorService service = Executors.newCachedThreadPool();
		long l = System.currentTimeMillis();
		for(int i = 0; i < 10; i++){
			service.submit(new Runnable() {				
				@Override
				public void run() {
					bank.rendomTransfer();
				}
			});
		}
		service.shutdown();		
		try {
			service.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		bank.transfer(bank.getAccounts().get(1), bank.getAccounts().get(2), 133);
		System.out.println(bank.getAccounts().get(1) + " asdf");
		
		System.out.println(bank.getAmountOfMoney() + " $$$");
		System.out.println(System.currentTimeMillis() - l + " ms");
		System.out.println(bank);
	}
}
