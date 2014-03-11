package com.epam.kiev.skipass;

public abstract class CountableSkiPass extends AbstractSkiPass {

	private int numberOfLifts;

	public int getNumberOfLifts() {
		return numberOfLifts;
	}

	public void setNumberOfLifts(int numberOfLifts) {
		this.numberOfLifts = numberOfLifts;
	}
	
}
