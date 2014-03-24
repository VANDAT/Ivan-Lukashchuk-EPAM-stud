package com.epam.kiev.sinsum;

import java.util.Collection;
import java.util.LinkedList;

public class SinSumVJ {

	static volatile double sum;

	public double sinSum(final int n, final int numberOfThreads) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}

		int count = n * 2 + 1;
		int countByThread = count / numberOfThreads;
		int residue = count % numberOfThreads;
		int from = -n;
		int size = 0;
		Collection<MyThread> collection = new LinkedList<>();
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
			MyThread thread = new MyThread(finalFrom, finalSize);
			thread.start();
			collection.add(thread);
		}
		for (MyThread thread : collection) {
			try {
				thread.join();
				sum += thread.value;
			} catch (InterruptedException e) {
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

	class MyThread extends Thread {
		double value;
		int from;
		int size;

		public MyThread(int from, int size) {
			this.from = from;
			this.size = size;
		}

		@Override
		public void run() {
			value = partSinSum(from, size);
		}
	}

}
