package com.epam.kiev.skipass.factories.cards.weekdays;

import com.epam.kiev.skipass.adminapi.History;
import com.epam.kiev.skipass.factories.cards.SkiPass;

public class WorkDaysWrapper extends WeekDaysWrup {
	
	private static final long serialVersionUID = -6634764527296668402L;
	
	private static History history = new History(WorkDaysWrapper.class);

	public WorkDaysWrapper(SkiPass skiPass){
		super(skiPass);
	}	
	
	@Override
	public boolean isValid() {
		return skiPass.isValid() && isWorkDay();
	}

	@Override
	public String toString() {
		return "WorkDaysWrapper [skiPass=" + skiPass + "]";
	}		
	
	@Override
	protected void historyCount(boolean isValid) {
		history.count(isValid);
	}
}
