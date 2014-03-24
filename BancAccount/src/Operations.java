import java.io.IOException;
import java.util.IllegalFormatCodePointException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Operations {

	static void transfer(Account acc1, Account acc2, int amount)
			throws IllegalFormatCodePointException {
		if (acc1.getBalance() < amount) {
			throw new IllegalFormatCodePointException(1);
		}
		// synchronized(acc1){
		// System.out.println("lock1");
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// synchronized (acc2) {

		try {
			if (acc1.getLock().tryLock(2, TimeUnit.SECONDS)) {
				try {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (acc2.getLock().tryLock(2, TimeUnit.SECONDS)) {
						try {
							if (acc1.getBalance() < amount) {
								throw new IOException();
							}
							System.out.println("lock2");
							acc1.withdraw(amount);
							acc2.deposit(amount);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							acc2.getLock().unlock();
						}
					}
				} finally {
					acc1.getLock().unlock();
				}
			} else {
				acc1.incFailTransferCount();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		final Account a = new Account(1000);
		final Account b = new Account(2000);

		new Thread(new Runnable() {

			public void run() {
				transfer(a, b, 500);
				System.out.println(a.getBalance() + " " + b.getBalance());
			}
		}).start();

		transfer(b, a, 300);
		System.out.println(a.getBalance() + " " + b.getBalance());
	}

}
