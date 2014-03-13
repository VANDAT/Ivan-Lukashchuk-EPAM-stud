package com.epam.kiev.skipass.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.epam.kiev.skipass.SkiPassAbstractFactory;
import com.epam.kiev.skipass.pass.SkiPass;

public class Test {
	public static void main(String[] args) {
		SkiPass skiPass1 = SkiPassAbstractFactory.getSkiPassFactory(
				SkiPassAbstractFactory.WORK_DAYS_TYPE)
				.letOutCountSkiPass(5);
				
		SkiPass skiPass = SkiPassAbstractFactory.getSkiPassFactory(
				SkiPassAbstractFactory.WORK_DAYS_TYPE)
				.lrtOutPartOfDaySkiPass(1);		
		System.out.println(skiPass.lift());
		System.out.println(skiPass);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(skiPass);
			byte[] ba = bos.toByteArray();
			ByteArrayInputStream bis = new ByteArrayInputStream(ba);
			ObjectInputStream ois = new ObjectInputStream(bis);
			SkiPass obj = (SkiPass) ois.readObject();
			System.out.println(obj);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
