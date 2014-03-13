package com.epam.kiev.skipass.factories.cards;

import java.util.Calendar;
import java.util.Date;

public abstract class PartOfDaySkiPass extends UncountableSkiPass {
	
	private static final long serialVersionUID = -6803829359199006054L;
	
	protected Date validFrom;

	public PartOfDaySkiPass(int fromHours, int length) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(activationDate);	
		cal.set(Calendar.HOUR_OF_DAY, fromHours);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);	
		validFrom = cal.getTime();
		cal.add(Calendar.HOUR_OF_DAY, length);
		validUntil = cal.getTime();
	}		

	@Override
	public boolean isValid() {
		return super.isValid() && validFrom.before(new Date());
	}

	@Override
	public String toString() {
		return "PartOfDaySkiPass [validFrom=" + validFrom + ", activateDate="
				+ activationDate + ", validUntil=" + validUntil + "]";
	}
	
	
}
