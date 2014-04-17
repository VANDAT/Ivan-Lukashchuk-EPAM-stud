package com.epam.kiev.circularbuffer;

public class Main {
	public static void main(String[] args) {
		final CircularBuffer<Integer> buffer = new NCCircularBuffer<>(3);
		new Thread(){
			public void run() {
			buffer.write(5);
			buffer.write(23);
			buffer.write(222);
			buffer.write(33234);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buffer.write(3443);
			buffer.write(456);
			buffer.write(45677);
			buffer.write(47);
			System.out.println("bbb");
			};
		}.start();
		
		
		new Thread(){
			public void run() {
		System.out.println(buffer.read());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(buffer.read());
		System.out.println(buffer.read());
		System.out.println(buffer.read());
		System.out.println(buffer.read());
		System.out.println(buffer.read());
		System.out.println(buffer.read());
		System.out.println(buffer.read());
		System.out.println("aaa");
			}
		}.start();
	}
}
