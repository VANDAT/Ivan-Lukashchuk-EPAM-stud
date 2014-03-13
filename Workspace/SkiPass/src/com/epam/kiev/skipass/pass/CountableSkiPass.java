package com.epam.kiev.skipass.pass;

public class CountableSkiPass extends AbstractSkiPass {

	private int numberOfLifts;
	
	public CountableSkiPass(int numberOfLifts){
		this.numberOfLifts = numberOfLifts;
	}

	public int getNumberOfLifts() {
		return numberOfLifts;
	}

	public void setNumberOfLifts(int numberOfLifts) {
		this.numberOfLifts = numberOfLifts;
	}

	@Override
	public boolean lift() {
		if (isValid()) {
			numberOfLifts--;
			return true;
		}
		return false;
	}

	@Override
	public boolean isValid() {
		return (super.isValid()) && (numberOfLifts > 0);
	}

}
