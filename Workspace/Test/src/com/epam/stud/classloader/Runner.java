package com.epam.stud.classloader;

import java.util.HashMap;

public class Runner {
	public static void main(String[] args) {
		HashMap<String,String> mappings = new HashMap<String, String>();
		mappings.put(
				"com.epam.stud.classloader.TestModule",
				"D:\\Workspace\\ClassLoader\\bin\\com\\epam\\stud\\classloader\\TestModule.class");

		while (true) {
			MyClassLoader myClassLoader = new MyClassLoader(mappings);
			try {
				Class clazz = myClassLoader
						.loadClass("com.epam.stud.classloader.TestModule");

				Object t = clazz.newInstance();
				System.out.println(t);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
