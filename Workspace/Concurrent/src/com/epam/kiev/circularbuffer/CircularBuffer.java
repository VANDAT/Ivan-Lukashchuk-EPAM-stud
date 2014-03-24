package com.epam.kiev.circularbuffer;

public interface CircularBuffer<T> {
	void write(T item);
	T read();
}
