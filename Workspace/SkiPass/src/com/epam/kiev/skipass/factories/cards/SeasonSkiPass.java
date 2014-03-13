package com.epam.kiev.skipass.factories.cards;

import com.epam.kiev.skipass.adminapi.History;

public class SeasonSkiPass extends UncountableSkiPass {

	private static final long serialVersionUID = 2567970873340881819L;

	private static History history = new History(SeasonSkiPass.class);
		
	@Override
	protected void historyCount(boolean isValid) {
		history.count(isValid);
	}

}
