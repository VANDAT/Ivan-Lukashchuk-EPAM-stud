package com.epam.kiev.skipass.pass.weekdays;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.epam.kiev.skipass.pass.AbstractSkiPass;
import com.epam.kiev.skipass.pass.SkiPass;

public abstract class WeekDaysWrup extends AbstractSkiPass {

	protected AbstractSkiPass skiPass;

	public WeekDaysWrup(SkiPass skiPass) {
		this.skiPass = (AbstractSkiPass) skiPass;
	}
	
	@Override
	public boolean lift() {
		if (isValid()) {
			skiPass.lift();
			return true;
		}
		return false;
	}
	
	protected boolean isWorkDay() {
		int dayOfWeek = Integer.parseInt(new SimpleDateFormat("u")
				.format(new Date()));
		return (dayOfWeek < 6) ? true : false;
	}

	public SkiPass getSkiPass() {
		return skiPass;
	}

	public void setSkiPass(SkiPass skiPass) {
		this.skiPass = (AbstractSkiPass) skiPass;
	}	
}
