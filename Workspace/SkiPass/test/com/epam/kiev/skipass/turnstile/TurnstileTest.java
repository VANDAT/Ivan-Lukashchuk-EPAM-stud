package com.epam.kiev.skipass.turnstile;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.kiev.skipass.factories.cards.MorningSkiPass;
import com.epam.kiev.skipass.factories.cards.SkiPass;
import com.epam.kiev.skipass.factories.cards.weekdays.WeekendWrapper;

public class TurnstileTest {

	@Test
	public void testReadWriteCard() {
		Turnstile turnstile = new Turnstile();
		SkiPass skiPass1 = new WeekendWrapper(new MorningSkiPass());
		SkiPass skiPass2 = turnstile.readFromCard(turnstile.writeOnCard(skiPass1));
		assertEquals(skiPass1, skiPass2);
	}
}
