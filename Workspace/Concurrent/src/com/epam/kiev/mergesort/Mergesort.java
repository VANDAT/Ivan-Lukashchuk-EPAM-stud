package com.epam.kiev.mergesort;

import java.util.concurrent.ExecutorService;

public class Mergesort {

	public int[] sort(int[] a, ExecutorService service, int numberOfThreads) {
		if (a.length == 1) {
			return a;
		}
		int middle = a.length / 2;
		int[] left = new int[middle];
		int[] right = new int[a.length - middle];

		for (int i = 0; i < a.length; i++) {
			if (i < middle) {
				left[i] = a[i];
			} else {
				right[i - middle] = a[i];
			}
		}

		return merge(sort(left, service, numberOfThreads / 2),
				sort(right, service, numberOfThreads / 2));
	}

	private int[] merge(int[] left, int[] right) {
		int[] result = new int[left.length + right.length];

		int i = 0, j = 0, k = 0;

		while (k < result.length) {
			if (i == left.length) {
				result[k] = right[j];
				j++;
			} else if (j == right.length) {
				result[k] = left[i];
				i++;
			} else {
				if (left[i] < right[j]) {
					result[k] = left[i];
					i++;
				} else {
					result[k] = right[j];
					j++;
				}
			}
			k++;
		}
		return result;
	}

}
