import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Account {
	private static AtomicInteger failCounter;
	private int balance;
	private Lock l;
	
	public Account(int balance){
		this.balance = balance;
		l = new ReentrantLock();
	}
	
	public static void incFailTransferCount(){
		failCounter.incrementAndGet();
	}
	
	public static AtomicInteger getFailCounter(){
		return failCounter;
	}
	
	public Lock getLock(){
		return l;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public void withdraw(int amount){
		balance -= amount;
	}
	
	public void deposit(int amount){
		balance += amount;
	}
}
