import java.util.IllegalFormatCodePointException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


public class Transfer implements Callable {
	Account acc1;
	Account acc2;
	int amount;
	
	public Transfer(Account accFrom, Account accTo, int amount){
		acc1 = accFrom;
		acc2= accTo;
		this.amount = amount;
	}

	public Object call() throws Exception {
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
			if (acc1.getLock().tryLock(1, TimeUnit.SECONDS)) {
				try {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (acc2.getLock().tryLock(1, TimeUnit.SECONDS)) {
						try {
							System.out.println("lock2");
							acc1.withdraw(amount);
							acc2.deposit(amount);
							return Boolean.TRUE;
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
		return Boolean.FALSE;
	}

}
