package com.epam.kiev.bank;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class Bank {

	private volatile List<Account> accounts = new CopyOnWriteArrayList<>();

	public void rendomTransfer() {
		Account from = accounts
				.get((int) (Math.random() * (accounts.size() - 1)));
		Account to = accounts
				.get((int) (Math.random() * (accounts.size() - 1)));
		int amount = (int) (Math.random() * (from.getBalance()));		
		transfer(from, to, amount);
	}

	public void transfer(Account from, Account to, int amount) {
		try {
			if (from.getLock().tryLock(10, TimeUnit.MILLISECONDS)) {
				try {
					if (to.getLock().tryLock(10, TimeUnit.MILLISECONDS)) {
						try {
							from.withdraw(amount);
							to.deposit(amount);
						} finally {
							to.getLock().unlock();
						}
					}
				} finally {
					from.getLock().unlock();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e){
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

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		int i = 1;
		for (Account account : accounts) {
			stringBuilder.append(i).append("=").append(account).append(" ");
			i++;
		}
		return stringBuilder.toString();
	}
}
