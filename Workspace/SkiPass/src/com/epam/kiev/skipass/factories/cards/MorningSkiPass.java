package com.epam.kiev.skipass.factories.cards;

import com.epam.kiev.skipass.adminapi.History;

public class MorningSkiPass extends PartOfDaySkiPass {

	private static final long serialVersionUID = -129541513221182640L;

	private static History history = new History(MorningSkiPass.class);
	
	public MorningSkiPass() {
		super(9, 4);
	}
	
	@Override
	protected void historyCount(boolean isValid) {
		history.count(isValid);
	}	
}
