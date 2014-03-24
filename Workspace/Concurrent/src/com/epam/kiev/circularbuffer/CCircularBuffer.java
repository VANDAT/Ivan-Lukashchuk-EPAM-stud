package com.epam.kiev.circularbuffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CCircularBuffer<T> implements CircularBuffer<T> {

	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();

	private Node<T> readNode;
	private Node<T> writeNode;

	@SuppressWarnings("hiding")
	private class Node<T> {
		T item;
		Node<T> next;
	}

	public CCircularBuffer(int size) {
		Node<T> firstNode = new Node<T>();
		Node<T> node = firstNode;
		for (int i = 1; i < size; i++) {
			node.next = new Node<T>();
			node = node.next;
		}
		node.next = firstNode;
		readNode = node;
		writeNode = node;
	}

	public void write(T item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		lock.lock();
		try {
			if (writeNode.item != null) {
				try {
					notFull.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			writeNode.item = item;
			writeNode = writeNode.next;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public T read() {
		lock.lock();
		try {
			T item = readNode.item;
			if (item == null) {
				try {
					notEmpty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				item = readNode.item;
			}
			readNode.item = null;
			readNode = readNode.next;
			notFull.signal();
			return item;
		} finally {
			lock.unlock();
		}
	}
}
