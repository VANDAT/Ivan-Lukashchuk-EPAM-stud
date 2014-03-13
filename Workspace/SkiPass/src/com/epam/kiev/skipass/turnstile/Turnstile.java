package com.epam.kiev.skipass.turnstile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.epam.kiev.skipass.factories.cards.SkiPass;

public class Turnstile {

	public SkiPass readFromCard(byte[] skiPassByts) {
		ByteArrayInputStream bais = new ByteArrayInputStream(skiPassByts);
		try {
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (SkiPass) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean pass(SkiPass skiPass) {
		return skiPass.lift();
	}

	public byte[] writeOnCard(SkiPass skiPass) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(skiPass);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
