package com.epam.kiev.bank;

public class Bank {
	public void transfer(Account from, Account to,	int amount){
		atomic{
			from.withdraw(amount);
			to.deposit(amount);
		}
	}
}
