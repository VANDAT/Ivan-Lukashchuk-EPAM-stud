package com.epam.kiev.bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

	private int id;
	private Lock lock = new ReentrantLock();
	private int balance;

	public Account(int ballance, int id) {
		if (ballance < 0) {
			throw new IllegalArgumentException();
		}
		this.balance = ballance;
		this.id = id;

	}

	public void withdraw(int amount) {
		balance -= amount;
	}

	public void deposit(int amount) {
		balance += amount;
	}

	public int getBalance() {
		return balance;
	}

	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}
	
	@Override
	public String toString() {
		return id + " " + balance + "$";
	}
}
