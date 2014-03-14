package com.epam.kiev.skipass;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.kiev.skipass.factories.cards.CountableSkiPass;
import com.epam.kiev.skipass.factories.cards.SkiPass;

public class CountableSkiPassTest {

	@Test
	public void testsCountableSkiPassWith3LiftsBy3rdLiftEqualsTrue() {
		int lifts = 3;
		SkiPass skiPass = new CountableSkiPass(lifts);
		skiPass.lift();
		skiPass.lift();
		assertTrue(skiPass.lift());
	}
	
	@Test
	public void testsCountableSkiPassWith3LiftsBy4rdLiftEqualsFalse() {
		int lifts = 3;
		SkiPass skiPass = new CountableSkiPass(lifts);
		skiPass.lift();
		skiPass.lift();
		skiPass.lift();
		assertFalse(skiPass.lift());
	}

}
