package com.epam.kiev.sinsum;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SinSum {

	static volatile AtomicDouble atomicDouble = new AtomicDouble();

	public double sinSum(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		ExecutorService service = Executors.newCachedThreadPool();

		int x = -n;
		while (x <= n) {
			final int X = x;
			try {
				atomicDouble.add(service.submit(new Callable<Double>() {

					@Override
					public Double call() throws Exception {
						// TODO Auto-generated method stub
						return sin(X);
					}

				}).get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			x += 1;
		}
		service.shutdown();
		
		return atomicDouble.get();

	}

	private double sin(int n) {
		return Math.sin(n);
	}

}
