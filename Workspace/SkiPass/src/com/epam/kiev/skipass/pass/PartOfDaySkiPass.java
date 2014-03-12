package com.epam.kiev.skipass.pass;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public abstract class PartOfDaySkiPass extends UncountableSkiPass {

	protected int fromHours;
	protected int length;

	public PartOfDaySkiPass(int fromHours, int length) {
		this.fromHours = fromHours;
		this.length = length;
	}

	protected boolean validByTime() {
		int nowHour = new GregorianCalendar(Locale.getDefault())
				.get(Calendar.HOUR_OF_DAY);
		return ((nowHour >= fromHours) && (nowHour < fromHours + length)) ? true
				: false;
	}

	@Override
	public boolean isValid() {
		return super.isValid() && validByTime() && isToday(activateDate);
	}

	// Test
	private boolean isToday(Date date) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		Calendar cal2 = Calendar.getInstance();
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
				&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
					.get(Calendar.DAY_OF_YEAR) == cal2
				.get(Calendar.DAY_OF_YEAR));
	}
}
