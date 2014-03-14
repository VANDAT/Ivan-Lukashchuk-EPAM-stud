package com.epam.kiev.skipass.factories.cards.weekdays;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.epam.kiev.skipass.factories.cards.AbstractSkiPass;
import com.epam.kiev.skipass.factories.cards.SkiPass;

public abstract class WeekDaysWrup extends AbstractSkiPass {

	private static final long serialVersionUID = 7442248176279854793L;

	private static final String DAY_OF_WEEK_SIMPLE_DATE_FORMAT_PATTERN = "u";
	private static final int SATURDAY = 6;

	protected AbstractSkiPass skiPass;

	public WeekDaysWrup(SkiPass skiPass) {
		this.skiPass = (AbstractSkiPass) skiPass;
	}

	@Override
	public boolean lift() {
		boolean isValid = super.lift();
		if (isValid) {
			return skiPass.lift();
		}
		return false;
	}

	protected boolean isWorkDay() {
		int dayOfWeek = Integer.parseInt(new SimpleDateFormat(
				DAY_OF_WEEK_SIMPLE_DATE_FORMAT_PATTERN).format(new Date()));
		return dayOfWeek < SATURDAY;
	}

	public SkiPass getSkiPass() {
		return skiPass;
	}

	public void setSkiPass(SkiPass skiPass) {
		this.skiPass = (AbstractSkiPass) skiPass;
	}		
}
