package com.epam.kiev.skipass.factories.cards;

import com.epam.kiev.skipass.adminapi.History;

public class CountableSkiPass extends AbstractSkiPass {

	private static final long serialVersionUID = 585572936638396944L;
	
	private static History history = new History(CountableSkiPass.class);
	
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
		boolean isValid = super.lift();		
		if (isValid) {
			numberOfLifts--;
		}
		return isValid;
	}

	@Override
	public boolean isValid() {
		return (super.isValid()) && (numberOfLifts > 0);
	}

	@Override
	protected void historyCount(boolean isValid) {
		history.count(isValid);
	}
}
