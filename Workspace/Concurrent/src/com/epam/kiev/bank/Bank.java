package com.epam.kiev.bank;

import java.util.ArrayList;
import java.util.List;

public class Bank {

	private volatile List<Account> accounts = new ArrayList<>();

	public void rendomTransfer() {			
		Account from = accounts.get((int)(Math.random()*(accounts.size()-1)));
		Account to = accounts.get((int)(Math.random()*(accounts.size()-1)));
		int amount = (int)(Math.random()*(from.getBalance()));
		System.out.println("from: " + from);
		System.out.println("to: " + to);
		System.out.println("amount=" + amount);
		transfer(from, to, amount);
	}

	public void transfer(Account from, Account to, int amount) {
		if (from.getBalance() < amount) {
//			try {
//				if (from.getLock().tryLock(1, TimeUnit.SECONDS)) {
//					try {
//						try {
//							Thread.sleep(200);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						if (to.getLock().tryLock(1, TimeUnit.SECONDS)) {
//							try {
								from.withdraw(amount);
								to.deposit(amount);
//							} finally {
//								to.getLock().unlock();
//							}
//						}
//					} finally {
//						from.getLock().unlock();
//					}
//				}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}

	public int getAmountOfMoney() {
		int amount = 0;
		for (Account account : accounts) {
			amount += account.getBalance();
		}
		return amount;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		int i = 1;
		for(Account account : accounts){
			stringBuilder.append(i).append("=").append(account).append(" ");
			i++;
		}
		return stringBuilder.toString();
	}
}
