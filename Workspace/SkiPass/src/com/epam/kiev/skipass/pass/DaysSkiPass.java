package com.epam.kiev.skipass.pass;

public class DaysSkiPass extends UncountableSkiPass {

	private int numberOfDays;
	
	@Override
	public boolean lift() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

}
