package com.epam.kiev.sinsum;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SinSumES {

	static volatile double sum;

	public double sinSum(final int n, final int numberOfThreads) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}

		int count = n * 2 + 1;
		int countByThread = count / numberOfThreads;
		int residue = count % numberOfThreads;
		ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
		int from = -n;
		int size = 0;
		Collection<Future<Double>> collection = new LinkedList<>();
		for (int i = 0; i < numberOfThreads; i++) {
			from += size;
			final int finalSize;
			if (residue > 0) {
				finalSize = countByThread + 1;
				residue--;
			} else {
				finalSize = countByThread;
			}
			size = finalSize;
			final int finalFrom = from;
			collection.add(service.submit(new Callable<Double>() {
				@Override
				public Double call() throws Exception {
					return partSinSum(finalFrom, finalSize);
				}
			}));
		}	
		service.shutdown();
		for (Future<Double> future : collection) {
			try {
				sum += future.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sum;

	}

	private double partSinSum(int from, int size) {
		double sum = 0;
		for (; size > 0; size--, from++) {
			sum += sin(from);
		}
		return sum;
	}

	private double sin(int n) {
		return Math.sin(n);
	}

}
