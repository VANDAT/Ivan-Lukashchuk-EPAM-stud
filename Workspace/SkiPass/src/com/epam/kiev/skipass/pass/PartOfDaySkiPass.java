package com.epam.kiev.skipass.pass;

import java.util.Calendar;
import java.util.Date;

public abstract class PartOfDaySkiPass extends UncountableSkiPass {
	
	protected Date validFrom;

	public PartOfDaySkiPass(int fromHours, int length) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(activateDate);	
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
				+ activateDate + ", validUntil=" + validUntil + "]";
	}
	
	
}
