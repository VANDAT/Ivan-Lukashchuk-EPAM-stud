package com.epam.kiev.skipass.pass;

import java.util.Calendar;
import java.util.Date;

public class DaysSkiPass extends UncountableSkiPass {

	private int numberOfDays;
	
	public DaysSkiPass(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	
	@Override
	public boolean isValid() {		
		return super.isValid() && validByLeftDays();
	}
	
	private boolean validByLeftDays(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(activateDate);
		cal.add(Calendar.DAY_OF_YEAR, numberOfDays);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime().after(new Date());		
	}
}
