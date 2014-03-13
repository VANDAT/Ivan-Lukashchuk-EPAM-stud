package com.epam.kiev.skipass.factories.cards;

import com.epam.kiev.skipass.adminapi.History;

public class EveningSkiPass extends PartOfDaySkiPass {

	private static final long serialVersionUID = 7748780117874086638L;
	
	private static History history = new History(EveningSkiPass.class);

	public EveningSkiPass() {
		super(13, 4);
	}
	
	@Override
	protected void historyCount(boolean isValid) {
		history.count(isValid);
	}
}
