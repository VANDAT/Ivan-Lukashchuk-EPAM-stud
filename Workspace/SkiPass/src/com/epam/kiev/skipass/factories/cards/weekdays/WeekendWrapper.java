package com.epam.kiev.skipass.factories.cards.weekdays;

import com.epam.kiev.skipass.adminapi.History;
import com.epam.kiev.skipass.factories.cards.SkiPass;

public class WeekendWrapper extends WeekDaysWrup{
	
	private static final long serialVersionUID = -418804487292542995L;
	
	private static History history = new History(WeekendWrapper.class);

	public WeekendWrapper(SkiPass skiPass){
		super(skiPass);
	}	
	
	@Override
	public boolean isValid() {
		return skiPass.isValid() && !isWorkDay();
	}
	
	@Override
	protected void historyCount(boolean isValid) {
		history.count(isValid);
	}
}
