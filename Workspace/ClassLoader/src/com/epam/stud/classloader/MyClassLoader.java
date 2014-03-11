package com.epam.stud.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class MyClassLoader extends ClassLoader {

	HashMap<String, String> mappings;

	public MyClassLoader(HashMap<String, String> mappings) {
		this.mappings = mappings;
	}

	public synchronized Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		System.out.println(123);
		byte[] b = loadClassData(name);
		Class<?> clazz = defineClass(name, b, 0, b.length);
		if (resolve) {
			resolveClass(clazz);
		}

		return clazz;
	}

	private byte[] loadClassData(String name) {
		FileInputStream fin;
		byte[] bbuf = null;
		try {
			String fileName = mappings.get(name);
			fin = new FileInputStream(fileName);
			bbuf = new byte[(int) (new File(fileName).length())];
			fin.read(bbuf);
			fin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bbuf;

	}

}
