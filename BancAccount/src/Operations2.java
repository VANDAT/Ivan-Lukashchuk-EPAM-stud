import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Operations2 {
	public static void main(String[] args) {
		final Account a = new Account(1000);
		final Account b = new Account(2000);
		Transfer t1 = new Transfer(a, b, 300);
		Transfer t2 = new Transfer(b, a, 400);
		Transfer t3 = new Transfer(a, b, 200);
		Transfer t4 = new Transfer(b, a, 500);
		Transfer t5 = new Transfer(a, b, 100);
		Transfer t6 = new Transfer(a, b, 300);
		ExecutorService service = Executors.newFixedThreadPool(3);		
//		ScheduledExecutorService service2 = (ScheduledExecutorService) service;
//		service2.scheduleAtFixedRate(new Runnable() {
//
//			public void run() {
//				System.out.println(Account.getFailCounter());
//			}
//		}, 1, 1, TimeUnit.SECONDS);
		service.submit(t1);
		service.submit(t2);
		service.submit(t3);
		service.submit(t4);
		service.submit(t5);
		service.submit(t6);
		service.shutdown();
		try {
			service.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
