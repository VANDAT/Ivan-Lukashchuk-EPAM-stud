package com.epam.kiev.circularbuffer;

public class NCCircularBuffer<T> implements CircularBuffer<T> {

	private Node<T> readNode;
	private Node<T> writeNode;

	@SuppressWarnings("hiding")
	private class Node<T> {
		T item;
		Node<T> next;
	}

	public NCCircularBuffer(int size) {
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
		synchronized (writeNode) {
			if (writeNode.item != null) {
				try {
					writeNode.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			writeNode.item = item;
			writeNode = writeNode.next;
			synchronized (readNode) {
				readNode.notify();
			}
		}
	}

	public T read() {
		synchronized (readNode) {
			T item = readNode.item;
			if (item == null) {
				try {
					readNode.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				item = readNode.item;
			}
			readNode.item = null;
			readNode = readNode.next;
			synchronized (writeNode) {
				writeNode.notify();
			}
			return item;
		}
	}
}
