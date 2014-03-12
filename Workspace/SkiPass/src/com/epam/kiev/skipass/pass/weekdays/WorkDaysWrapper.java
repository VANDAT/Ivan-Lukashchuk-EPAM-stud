package com.epam.kiev.skipass.pass.weekdays;

import com.epam.kiev.skipass.pass.SkiPass;

public class WorkDaysWrapper extends WeekDaysWrup {
	
	public WorkDaysWrapper(SkiPass skiPass){
		super(skiPass);
	}	
	
	@Override
	public boolean isValid() {
		return skiPass.isValid() && isWorkDay();
	}
}
