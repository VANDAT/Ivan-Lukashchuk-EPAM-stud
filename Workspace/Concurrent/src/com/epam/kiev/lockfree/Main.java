package com.epam.kiev.lockfree;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		final LockFree lockFree = new LockFree();
		Callable<Object> callable = new Callable<Object>() {

			@Override
			public Object call() throws Exception {
				System.out.println(lockFree.next());
				return null;
			}
		};
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 8; i++) {
			service.submit(callable);
		}
		service.shutdown();

	}
}
