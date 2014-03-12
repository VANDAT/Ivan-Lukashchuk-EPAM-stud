package com.epam.kiev.skipass.pass.weekdays;

import com.epam.kiev.skipass.pass.SkiPass;

public class WeekendWrapper extends WeekDaysWrup{
	
	public WeekendWrapper(SkiPass skiPass){
		super(skiPass);
	}	
	
	@Override
	public boolean isValid() {
		return skiPass.isValid() && !isWorkDay();
	}
}
