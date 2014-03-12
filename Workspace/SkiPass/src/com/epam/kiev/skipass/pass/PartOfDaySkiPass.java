package com.epam.kiev.skipass.pass;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public abstract class PartOfDaySkiPass extends UncountableSkiPass {
	
	protected int fromHours;
	protected int length;

	public PartOfDaySkiPass(int fromHours, int length){
		this.fromHours = fromHours;
		this.length = length;
	}
	
	@Override
	public boolean lift() {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean validByTime() {
		int nowHour = new GregorianCalendar(Locale.getDefault())
				.get(Calendar.HOUR_OF_DAY);
		return ((nowHour >= fromHours) && (nowHour < fromHours + length)) ? true
				: false;
	}
	
	@Override
	public boolean isValid() {	
		boolean validByTime = validByTime();
		boolean valid = true;
		if(!validByTime){
		System.currentTimeMillis() - activateDate.getTime();
		}			
		return super.isValid() && valid && validByTime();
	}
}
