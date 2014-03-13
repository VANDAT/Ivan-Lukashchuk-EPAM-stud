package com.epam.kiev.skipass;

import com.epam.kiev.skipass.adminapi.Admin;
import com.epam.kiev.skipass.factories.cards.SkiPass;
import com.epam.kiev.skipass.turnstile.Turnstile;

public class Demo {

	public static void main(String[] args) {

		Turnstile turnstile = new Turnstile();
		Admin admin = new Admin();
		SkiPass skiPass = admin.letOut10LiftsWorkDaysSkiPass();
		byte[] skiPassBytes = turnstile.writeOnCard(skiPass);

		for (int i = 0; i < 11; i++) {
			skiPass = turnstile.readFromCard(skiPassBytes);
			System.out.println(skiPass.lift());
			skiPassBytes = turnstile.writeOnCard(skiPass);
		}
	}
}