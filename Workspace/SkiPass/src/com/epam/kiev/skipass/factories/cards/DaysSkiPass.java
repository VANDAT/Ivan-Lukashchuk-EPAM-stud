package com.epam.kiev.skipass.factories.cards;

import java.util.Calendar;
import java.util.Date;

import com.epam.kiev.skipass.adminapi.History;

public class DaysSkiPass extends UncountableSkiPass {

	private static final long serialVersionUID = 5694964724866423779L;
	
	private History history = new History(DaysSkiPass.class);
	
	private int numberOfDays;
	
	public DaysSkiPass(int numberOfDays) {
		this.numberOfDays = numberOfDays;
		validUntil = getExpirationTime();
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	
	@Override
	protected void historyCount(boolean isValid) {
		history.count(isValid);
	}
	
	@Override
	public boolean isValid() {		
		return super.isValid() && validUntil.after(new Date());
	}
	
	private Date getExpirationTime(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(activationDate);
		cal.add(Calendar.DAY_OF_YEAR, numberOfDays);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
}
