package com.epam.kiev.circularbuffer;

import java.util.concurrent.LinkedBlockingQueue;

public class CircularBuffer<T> {

	private LinkedBlockingQueue<T> linkedBlockingQueue;
	
	public CircularBuffer(int size){
		linkedBlockingQueue = new LinkedBlockingQueue<>(size);
	}
	
	
//	private Node<T> node;
//
//	private class Node<T> {
//		T item;
//		Node<T> next;		
//	}
//
//	public CircularBuffer(int size) {
//		Node<T> firstNode = new Node<T>();
//		Node<T> node = firstNode;
//		for (int i = 0; i < size; i++) {
//			node.next = new Node<T>();
//			node = node.next;
//		}
//		node.next = firstNode;
//	}
//
//	public T get() {
//		return node.item;
//	}
}
